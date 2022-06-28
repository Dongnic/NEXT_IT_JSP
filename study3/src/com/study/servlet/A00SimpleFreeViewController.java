package com.study.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;

public class A00SimpleFreeViewController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// freeView.jsp 자바소스 옮기기
		int boNo= Integer.parseInt(req.getParameter("boNo"));
		IFreeBoardService freeBoardService= new FreeBoardServiceImpl();
		
		try{
			freeBoardService.increaseHit(boNo);
			FreeBoardVO freeBoard= freeBoardService.getBoard(boNo);
			req.setAttribute("freeBoard", freeBoard);		
		}catch(BizNotFoundException | BizNotEffectedException e){
			req.setAttribute("bne", e);
		}
	
		RequestDispatcher rd= req.getRequestDispatcher("/WEB-INF/views/free/freeView.jsp");
		rd.forward(req, resp);
		
	}
}
