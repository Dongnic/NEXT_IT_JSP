package com.study.free.web;

import java.util.List;

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
		
//		
		FreeBoardSearchVO searchVO=new FreeBoardSearchVO();  //값이없는 searchVO
		
		req.setAttribute("searchVO", searchVO);
		BeanUtils.populate(searchVO, req.getParameterMap());
		
		IFreeBoardService freeBoardService=new FreeBoardServiceImpl();
		List<FreeBoardVO> freeBoardList=freeBoardService.getBoardList(searchVO); 
		req.setAttribute("freeBoardList", freeBoardList);
	 	
		
		ICommCodeService codeService=new CommCodeServiceImpl();
	 	List<CodeVO> cateList= codeService.getCodeListByParent("BC00");
	 	req.setAttribute("cateList",cateList);
		
	 	
	 	
		return "free/freeList";
	}
	

	
	

}
