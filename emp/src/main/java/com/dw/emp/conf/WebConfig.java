package com.dw.emp.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//conf : 설정 관련된 기능
//@Configuration : Spring에게 해당 클래스는 설정하고 관련된 클래스라고 명령
@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Autowired
	private Interceptor interceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	
		registry.addInterceptor(interceptor); //우리가 만든 인터셉터 클래스 스프링에 등록!
		
	}
	
	
}
