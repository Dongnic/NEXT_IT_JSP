package com.study.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//FrontController 
public class DispatcherServlet  extends HttpServlet{
	RequestMappingHandlerMapping handlerMapping;
	
	//서버시작될 때  handlerMapping객체  만들기 
	@Override
	public void init() throws ServletException {
		String contextConfigLocation=getInitParameter("contextConfigLocation");
		try {
			handlerMapping=new RequestMappingHandlerMapping(getServletContext(),contextConfigLocation);
			//웹 프로젝트마다 1개씩 있는 Servlet이다..
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String uri=req.getRequestURI();// /study3/free/freeList.wow
		String cp=req.getContextPath();//  /study3
		uri=uri.substring(cp.length());  //   /free/freeList.wow
		Handler handler=handlerMapping.getHandler(uri);
		if(handler==null) { //요청처리 못함
			resp.sendError(404,"요청에 해당하는 핸들러가 없습니다.");
			return ;
		}
		try {
			String viewPage=handler.process(req, resp);
			if(viewPage.startsWith("redirect:")) {
				resp.sendRedirect(viewPage.substring("redirect:".length()));
			}else {
				RequestDispatcher rd=req.getRequestDispatcher(ViewResolver.prefix+ viewPage+ViewResolver.suffix);
				rd.forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
