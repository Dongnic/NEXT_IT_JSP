package com.study.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sum extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 브라우저가 요청했을 때 여기있는 내용이 실행되고 브라우저에 응답
		// 어떤 요청이 있을 때 이 메소드가 실행되는지는 서버 설정파일(web.xml)
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8"); 
		PrintWriter out= resp.getWriter();
		out.print(" <html> <body> ");

		out.print("<ul>");
		for(int i=0; i<10; i++) {
			out.print("<li> "+ i + " </li>");
		}
		out.print("</ul>");
		out.print(" </body> </html> ");
		
	}
}
