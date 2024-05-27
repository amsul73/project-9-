package com.gatchaPedia.demo.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    // 예외 페이지, 로그인관련, 회원가입을 제외한 모든 경우에 대해서 로그인 이후에 발급받을 수 있는 세션을 가지고 있지 않다면
    // 로그인 페이지로 돌려보내게 공통 처리함
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginCheckInterceptor())    // 인터셉터 등록
                .order(1)   // 1순위
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/error",
                        "/signup",
                        "/login",
                        "/mainpage",
                        "/reroll",
                        "/movies/**",
                        "/movie/**"
                        );
    }

}