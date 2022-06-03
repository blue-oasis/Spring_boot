package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //컨트롤러 지정 어노테이션
public class MainController {
	
	@RequestMapping("/sbb") //경로 매핑
	//@ResponseBody //index 함수를 응답함수로 지정
	public String index() {
		return "sbb";
	}
	
}
