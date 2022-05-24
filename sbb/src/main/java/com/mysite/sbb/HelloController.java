package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //컨트롤러 어노테이션
public class HelloController {
	@RequestMapping("/hello") //url 경로 매핑
	@ResponseBody //응답 함수 지정
	public String hello() {
		return "Hello World";
	}
	
}
