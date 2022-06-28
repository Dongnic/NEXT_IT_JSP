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
	
	//요청이 올때마다 처리하는 service메소드 
	//handlerMapping한테  req uri에 해당하는 핸들러가 있는지 물어보고
	// 있으면  그 핸들러한테 요청 처리해달라고 부탁함. 그리고 알맞은 뷰로 forwarding
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String uri=req.getRequestURI();// /study3/free/freeList.wow
		String cp=req.getContextPath();//  /study3
		uri=uri.substring(cp.length());  //   /free/freeList.wow
		Handler handler=handlerMapping.getHandler(uri);
		//프로퍼티파일에  있는 요청이면  handler를 return
		//없는 요청이면 null을 return
		if(handler==null) { //요청처리 못함
			resp.sendError(404,"요청에 해당하는 핸들러가 없습니다.");
			return ;
		}
		//요청에 해당하는 핸들러가 있는 경우
		try {
			String viewPage=handler.process(req, resp);
			if(viewPage.startsWith("redirect:")) {
				resp.sendRedirect(viewPage.substring("redirect:".length()));
			}else {
				RequestDispatcher rd= req.getRequestDispatcher(ViewResolver.prefix+ viewPage+ViewResolver.suffix);
				rd.forward(req, resp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
