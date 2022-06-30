package com.study.free.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.Handler;

public class FreeView implements Handler {

	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int boNo=Integer.parseInt(req.getParameter("boNo"));
		IFreeBoardService freeBoardService=new FreeBoardServiceImpl();
		try{
			FreeBoardVO freeBoard=freeBoardService.getBoard(boNo);
			freeBoardService.increaseHit(boNo);
			req.setAttribute("freeBoard", freeBoard);
			return "free/freeView";
		}catch (BizNotFoundException  | BizNotEffectedException  e){
			ResultMessageVO resultMessageVO=new ResultMessageVO();
			resultMessageVO.messageSetting(false, 
					"NotFound or NotEffected", "글이없다 or조회수증가실패", "/free/freeList.wow", "목록으로");
			req.setAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
		
	}
}
