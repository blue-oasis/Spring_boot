package com.mysite.sbb;

import lombok.Getter; //롬복 Getter  setter는 import lombok.Setter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final 변수 생성자 어노테이션
@Getter //롬복 Getter 
public class HelloLombok {
	private final String hello;
	private final int lombok;
	
	public static void main(String[] args) {
		HelloLombok helloLombok = new HelloLombok("안녕", 5);
		//helloLombok.setHello("안녕"); setter 사용 final 아닐 때  
		//helloLombok.setLombok(5);
		
		System.out.println(helloLombok.getHello()); //get 변수이름 대소문자 유의
		System.out.println(helloLombok.getLombok());
	}
}
