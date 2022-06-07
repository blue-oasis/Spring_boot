package com.mysite.sbb.question;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;

    @RequestMapping("/question/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
}