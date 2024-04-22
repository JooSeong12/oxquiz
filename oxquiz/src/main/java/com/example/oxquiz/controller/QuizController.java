package com.example.oxquiz.controller;

import com.example.oxquiz.dto.QuizDto;
import com.example.oxquiz.entity.QuizEntity;
import com.example.oxquiz.repository.QuizRepository;
import com.example.oxquiz.vo.MethodZip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("quiz")
@Slf4j
public class QuizController {
    private final MethodZip methodZip;
    private final QuizRepository quizRepository;

    public QuizController(MethodZip methodZip, QuizRepository quizRepository){
        this.methodZip = methodZip;
        this.quizRepository = quizRepository;
    }

    @GetMapping("")
    public String showQuiz(Model model){
        List<QuizEntity> quizList = quizRepository.findAll();
        List<QuizDto> quizDtoList = quizRepository.findAll().stream()
                .map(x-> QuizDto.fromQuizEntity(x)).toList();
//        for(QuizEntity quiz : quizList){
//           dtoList.add(QuizDto.fromQuizEntity(quiz));
//       }
        model.addAttribute("quizDtoList", quizDtoList);
        return "quizList";
    }
    @GetMapping("insert")
    public String insertQuiz(Model model){
        model.addAttribute("quizDto", new QuizDto());
        return "insert";
    }

    @PostMapping("insert")
    public String postInsert(@ModelAttribute("quizDto") QuizDto dto){
        QuizEntity quizEntity = dto.fromQuizDto(dto);
        quizRepository.save(quizEntity);
        return "redirect:/quiz";
    }

    @GetMapping("/{id}")
    public String updateId(@PathVariable("id") long id, Model model){
        QuizEntity quizEntity = quizRepository.findById(id).orElse(null);
        QuizDto dto = QuizDto.fromQuizEntity(quizEntity);
        model.addAttribute("quizDto", dto);
        return "update";
    }
    @PostMapping("/update")
    public String updatePost(@ModelAttribute("quizDto") QuizDto dto){
        log.info("dto ===================" + dto);
        QuizEntity quizEntity = dto.fromQuizDto(dto);
        quizRepository.save(quizEntity);
        return "redirect:/quiz";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam("deleteId") long id){
        quizRepository.deleteById(id);
        return "redirect:/quiz";
    }
    @GetMapping("/play")
    public String play(Model model){
        try {
            QuizDto dto = methodZip.play();
            model.addAttribute("dto", dto);
        } catch (Exception e) {
            model.addAttribute("text", "등록된 문제가 없습니다.");
            return "check";
        }
        return "play";
    }
    @PostMapping("/check")
    public String check(@RequestParam("selectAnswer") String selectAnswer,
                        @RequestParam("answer") String answer,
                        Model model){
        String trueAnswer = null;
        if(answer.equals(selectAnswer)){
            trueAnswer = "정답입니다.";
        } else {
            trueAnswer = "오답입니다.";
        }
        model.addAttribute("answer", trueAnswer);
        return "check";
    }
}
