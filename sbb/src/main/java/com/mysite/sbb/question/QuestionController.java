package com.mysite.sbb.question;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/question") // url 프리픽스, 클래스 내 메소드 url 무조건 /question 붙고 시작
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create") // 질문 등록 get 매핑
    public String questionCreate() {
        return "question_form";
    }

    @PostMapping("/create") // 질문 등록 post 매핑
    public String questionCreate(@RequestParam String subject, @RequestParam String content) {
        this.questionService.create(subject, content); // 서비스 이용 질문 저장
        return "redirect:/question/list"; // 저장 후 질문 목록으로 이동
    }
}