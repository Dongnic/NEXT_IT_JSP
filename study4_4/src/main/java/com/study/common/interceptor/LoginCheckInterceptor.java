package com.study.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.study.login.vo.UserVO;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter{
	
	// 로그인 여부를 감시하기, 문제가 없으면 -> true (원래 가려던 @RequestMapping 메소드로 가면 됨)
	//									   -> false (redirect : 로그인 페이지)
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session= request.getSession();
		UserVO user= (UserVO)session.getAttribute("USER_INFO");
		if(user== null) {
			System.out.println("로그인 인터셉터 걸림 ㅋ");
			response.sendRedirect(request.getContextPath()+"/login/login.wow");
			return false;
		}
		return true;
	}
}
