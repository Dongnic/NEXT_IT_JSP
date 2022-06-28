package com.study.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

public class A01SimpleFreeController extends HttpServlet{
	
	// 지금까지는 doGet했는데 이 메소드는 get방식만 처리함
	// post방식도 처리하기위해선 service 
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out= resp.getWriter();
		// 요청은 localhost:8080/study3/free/freeList.wow, freeView.wow
		
		// 브라우저가 View를 요청했는지 List를 요청했는지 등등.. 다 다르게 처리해야함
		// request를 이용해서 
		String uri= req.getRequestURI(); // ex study3/free/freeList.wow 
		uri= uri.substring(req.getContextPath().length());
		if(uri.equals("/free/freeList.wow")) {
			//  자바코드 복사
			FreeBoardSearchVO searchVO= new FreeBoardSearchVO();
			
			IFreeBoardService freeBoardService= new FreeBoardServiceImpl();
			// searchVO는 curPage만 세팅된 값
			List<FreeBoardVO> freeBoardList= freeBoardService.getBoardList(searchVO);
			req.setAttribute("freeBoardList", freeBoardList);
			// 이 이후의 searchVO는 전부 세팅된 값
			
			// 밑에 있는 코드는 FreeBoardDaoOracle로 갔음.
			ICommCodeService codeService=new CommCodeServiceImpl();
			List<CodeVO> cateList= codeService.getCodeListByParent("BC00");
			req.setAttribute("cateList", cateList);
			
			RequestDispatcher rd= req.getRequestDispatcher("/WEB-INF/views/free/freeList.jsp");
			rd.forward(req, resp);
		}else if(uri.equals("/free/freeView.wow")) {
			// View 자바코드 복사
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
		}else if(uri.equals("/free/freeEdit.wow")) {
			// freeEdit.jsp 자바코드 복사
			int boNo = Integer.parseInt(req.getParameter("boNo"));
			
			IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
			
			try{
				FreeBoardVO freeBoard = freeBoardService.getBoard(boNo);
				req.setAttribute("freeBoard", freeBoard);
			}catch (BizNotFoundException e){
				req.setAttribute("e", e);
			}
			
			ICommCodeService codeService = new CommCodeServiceImpl();
			List<CodeVO> categoryList = codeService.getCodeListByParent("BC00");
			req.setAttribute("categoryList", categoryList);
			
			RequestDispatcher rd= req.getRequestDispatcher("/WEB-INF/views/free/freeEdit.jsp");
			rd.forward(req, resp);
		}
	}
	
}
