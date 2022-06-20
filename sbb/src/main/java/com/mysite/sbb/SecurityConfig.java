package com.mysite.sbb;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//PasswordEncoder bean 생성

import com.mysite.sbb.user.UserSecurityService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration //스프링 환경설정 파일로 지정하는 어노테이션
@EnableWebSecurity //모든 요성 url이 스프링 시큐리티 제어 받도록
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll()
        .and().csrf().ignoringAntMatchers("/h2-console/**")
        .and().headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
        //로그인 안해도 모든 페이지 접근 가능하게 오버라이딩 + h2 콘솔 csrf예외
        .and().formLogin().loginPage("/user/login").defaultSuccessUrl("/");
        //스프링 시큐리티 로그인 설정, 로그인페이지 url 매핑, 로그인 성공시 이동 url
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final UserSecurityService userSecurityService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }
}
