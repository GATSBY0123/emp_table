package com.dw.emp.conf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dw.emp.mapper.LogsMapper;
import com.dw.emp.vo.LogsVO;

@Component
public class Interceptor implements HandlerInterceptor{

	@Autowired
	private LogsMapper logsMapper;
	
	private final Logger logger = LoggerFactory.getLogger(Interceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// logger를 이용해서 기록을 남긴다
		// logger를 이용하면 원하는 레벨에 맞게 출력이 가능하다.
		// 레벨 = (디버깅 모드, 경고 모드, 출력 모드)
//		logger.debug("디버그 전용 메세지");
//		logger.warn("경고");
		logger.info("==============================================================preHandle");
		String requestUrl = request.getRequestURI(); //접속 URL 호출
		String httpMethod = request.getMethod(); //HTTP 메소드 호출
		String userIP = request.getHeader("X-Forwarded-For");
		if(userIP == null) userIP = request.getRemoteAddr();
		
		System.out.println("요청 URL : " + requestUrl);
		System.out.println("요청 Method : " + httpMethod);
		System.out.println("사용자 IP : " + userIP);
		logger.info("==============================================================preHandle");
		
		LogsVO logVO = new LogsVO();
		logVO.setHttpMethod(httpMethod);
		logVO.setIp(userIP);
		logVO.setUrl(requestUrl);
		
		logsMapper.insertLogs(logVO); // 접속 로그 insert!
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
	
}
