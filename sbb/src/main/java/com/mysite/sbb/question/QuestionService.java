package com.mysite.sbb.question;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import com.mysite.sbb.DataNotFoundException;
import java.time.LocalDateTime;

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

	public void create(String subject, String content) { //질문 저장 서비스
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q);
	}
}
