package com.study.servlet;

import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

public class A00SimpleFreeListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 어떤 작업하고 나서 /WEB-INF/views/free/freeList.jsp로 포워딩
		// 어떤 작업 : JSP에 있는 자바코드
		//<jsp:useBean id="searchVO" class="com.study.free.vo.FreeBoardSearchVO"></jsp:useBean>
		//<jsp:setProperty property="*" name="searchVO"/>
		// 파라미터를 전부 VO에 한번에 세팅해주는건데 이것을 대신해주는 건 lib가 필요
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
		
	}
}
