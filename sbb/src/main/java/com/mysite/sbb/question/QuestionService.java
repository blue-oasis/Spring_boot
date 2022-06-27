package com.mysite.sbb.question;

import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import com.mysite.sbb.DataNotFoundException;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; //페이징 기능 추가
import org.springframework.data.domain.PageRequest;

import com.mysite.sbb.user.SiteUser;

@RequiredArgsConstructor
@Service //데이터 처리를 위한 서비스 만들기
public class QuestionService {
	
	private final QuestionRepository questionRepository;

	public Page<Question> getList(int page) { //질문 목록 조회 + 페이징 서비스 추가
        List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate")); //작성일시 역순조건 추가 조회
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts)); 
        return this.questionRepository.findAll(pageable);
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

	public void create(String subject, String content, SiteUser user) { //질문 저장 서비스
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		q.setAuthor(user);
		this.questionRepository.save(q);
	}

	public void modify(Question question, String subject, String content) {
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		this.questionRepository.save(question);
	}

	public void delete(Question question) {
		this.questionRepository.delete(question);
	}

	public void vote(Question question, SiteUser siteUser) {
		question.getVoter().add(siteUser);
		this.questionRepository.save(question);
	}
}
