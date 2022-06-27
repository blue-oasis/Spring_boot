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

import com.mysite.sbb.answer.Answer;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
@Service //데이터 처리를 위한 서비스 만들기
public class QuestionService {
	
	private final QuestionRepository questionRepository;

	public Page<Question> getList(int page, String kw) { //질문 목록 조회 + 페이징 서비스 추가 + 검색어 kw
        List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate")); //작성일시 역순조건 추가 조회
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts)); 
        Specification<Question> spec = search(kw);
		return this.questionRepository.findAll(spec, pageable);
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

	private Specification<Question> search(String kw) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true); //중복제거
				Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT); // Question, SiteUser 엔티티 아우터조인(author) 이용 질문 작성자 검색
				Join<Question, Answer> a = q.join("answerList", JoinType.LEFT); // Question, Answer 아우터조인 answerList 이용  답변 내용 검색
				Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT); //답변 작성자 검색
				// cb.or or조건, cb.like 검색 like
				return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), //제목
				cb.like(q.get("content"), "%" + kw + "%"), //내용
				cb.like(u1.get("username"), "%" + kw +"%"), //질문 작성자
				cb.like(a.get("content"), "%" + kw +"%"), //답변 내용
				cb.like(u2.get("username"), "%" + kw + "%")); //답변 작성자
			}
		};
	}
}
