package com.study.free.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.Handler;


public class FreeList implements Handler{
	

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		System.out.println("나는 FreeList");
		//<jsp:useBean id="searchVO" class="com.study.free.vo.FreeBoardSearchVO"></jsp:useBean>
		//<jsp:setProperty property="*" name="searchVO"/>
		FreeBoardSearchVO searchVO= new FreeBoardSearchVO();
		BeanUtils.populate(searchVO, req.getParameterMap());
							// ( VO, map<String, Object>())
		req.setAttribute("searchVO", searchVO);
		// 이후부터는 searchVO에 jsp파일 과같이 set 되어있음  
		IFreeBoardService freeBoardService= new FreeBoardServiceImpl();
		// searchVO는 curPage만 세팅된 값
		List<FreeBoardVO> freeBoardList= freeBoardService.getBoardList(searchVO);
		req.setAttribute("freeBoardList", freeBoardList);
		// 이 이후의 searchVO는 전부 세팅된 값
		
		// 밑에 있는 코드는 FreeBoardDaoOracle로 갔음.
		ICommCodeService codeService=new CommCodeServiceImpl();
		List<CodeVO> cateList= codeService.getCodeListByParent("BC00");
		req.setAttribute("cateList", cateList);
		
		return "free/freeList";
	}
	

	
	

}
