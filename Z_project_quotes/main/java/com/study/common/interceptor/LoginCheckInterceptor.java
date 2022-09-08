package com.study.common.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.study.login.vo.UserVO;
public class LoginCheckInterceptor extends HandlerInterceptorAdapter{
	
	//로그인 여부 검사하기, 로그인 되어있으면(문제가 없으면) true -> 원래 가려던 @RequestMapping메소드로 가면 됨
	//				   , 로그인 안 되어있으면(문제가 있으면) false -> 못감
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String ajax = request.getHeader("X-requested-with");		// x-request-with, X-Request-With 둘 중하나 찍어보고 맞는거롤 ㄱㄱ
		UserVO user = (UserVO)session.getAttribute("USER_INFO");
		if(user==null) {
			if(ajax!=null) { // ajax 요청이면
				response.sendError(401);
				return false;
			}
			response.sendRedirect(request.getContextPath()+"/login/login.wow");
			return false;
		}
		return true;
	}
}