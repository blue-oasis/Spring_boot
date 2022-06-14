package com.mysite.sbb.question;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; //페이징 기능 추가

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	//데이터베이스 crud 처리 위해 인터페이스 생성, <리포지토리 대상 엔티티, 엔티티 PK 속성 타입>
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String content);
	List<Question> findBySubjectLike(String subject);
	Page<Question> findAll(Pageable pageable); //페이징
}
