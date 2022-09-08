package com.study.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.study.login.vo.UserVO;

public class ManagerCheckInterceptor extends HandlerInterceptorAdapter{
	
	
	// /member/* 는 관리자만 접근가능하다. 관리자가 아니면 들어갈 수 없다.
	// 관리자인지는 UserVO userRole가지고 확인
	// UserVO가 session에 담기는 곳은 LoginController-LoginServiceImpl 부분에 
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
		
		if(!user.getUserRole().equals("MANAGER")) {
			System.out.println("매니저 인터셉터 걸림 ㅋ");
			response.sendError(403,"you are not manager");
			return false;
		}
		return true;
	}
}
