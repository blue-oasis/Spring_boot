package com.mysite.sbb.question;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import com.mysite.sbb.DataNotFoundException;

@RequiredArgsConstructor
@Service //데이터 처리를 위한 서비스 만들기
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	
	public List<Question> getList() {
		return this.questionRepository.findAll();
	}
	
	public Question getQuestion(Integer id) {
		Optional<Question> question = this.questionRepository.findById(id);
		if (question.isPresent()) {
			return question.get();
		}
		else {
			throw new DataNotFoundException("question not found");
		}
	}
}
