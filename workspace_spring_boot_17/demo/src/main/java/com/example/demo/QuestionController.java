package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.question.Question;
import com.example.demo.question.QuestionRepository;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class QuestionController {
	
	private final QuestionRepository questionRepository;
	
	@GetMapping("/question/list")
	public String list(Model model) {
		
		List<Question> questionList = this.questionRepository.findAll();
		model.addAttribute("questionList", questionList);
		
		
		return "question_list";
	}
	@GetMapping("/test")
	public String test() {
		System.out.println("실행됨");
		
		return "test1";
	}
}
