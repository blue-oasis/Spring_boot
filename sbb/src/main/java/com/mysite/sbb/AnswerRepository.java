package com.mysite.sbb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer>{
	//대답 엔티티 저장을 위한 인터페이스
}
