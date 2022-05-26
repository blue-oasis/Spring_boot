package com.mysite.sbb;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	//데이터베이스 crud 처리 위해 인터페이스 생성, <리포지토리 대상 엔티티, 엔티티 PK 속성 타입>
}
