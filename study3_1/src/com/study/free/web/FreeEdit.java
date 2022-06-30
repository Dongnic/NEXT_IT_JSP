package com.study.free.web;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.Handler;

public class FreeEdit implements Handler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int boNo=Integer.parseInt(req.getParameter("boNo"));
		IFreeBoardService freeBoardService=new FreeBoardServiceImpl();
		try{ 
			FreeBoardVO freeBoard=freeBoardService.getBoard(boNo);
			req.setAttribute("freeBoard", freeBoard);
			ICommCodeService codeService=new CommCodeServiceImpl();
			List<CodeVO> categoryList=codeService.getCodeListByParent("BC00");
			req.setAttribute("categoryList", categoryList);
			return "free/freeEdit";
		}catch(BizNotFoundException e){
			ResultMessageVO resultMessageVO=new ResultMessageVO();
			resultMessageVO.messageSetting(false, 
					"NotFound", "글이없다", "/free/freeList.wow", "목록으로");
			req.setAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
	
	}
}
