package com.study.free.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.servlet.Handler;

public class FreeForm implements Handler {
	
	

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ICommCodeService codeService=new CommCodeServiceImpl();
		List<CodeVO> categoryList=codeService.getCodeListByParent("BC00");
		req.setAttribute("categoryList", categoryList);
		return "free/freeForm";
	}

}
