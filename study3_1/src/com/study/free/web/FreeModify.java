package com.study.free.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.Handler;

public class FreeModify implements Handler {
	
	

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		FreeBoardVO freeBoard=new FreeBoardVO();
		BeanUtils.populate(freeBoard, req.getParameterMap());
		req.setAttribute("freeBoard", freeBoard);
		
		IFreeBoardService freeBoardService=new FreeBoardServiceImpl();
		ResultMessageVO resultMessageVO= new ResultMessageVO();
		
		try{
			freeBoardService.modifyBoard(freeBoard);
			resultMessageVO.messageSetting(true, 
					"MODIFY", "수정성공", "/free/freeList.wow", "목록으로");
		}catch (BizNotFoundException bnf){
			resultMessageVO.messageSetting(false, 
					"NotFound", "수정실패", "/free/freeList.wow", "목록으로");
		}catch (BizNotEffectedException bne){
			resultMessageVO.messageSetting(false, 
					"NotEffected", "수정실패", "/free/freeList.wow", "목록으로");
		}catch(BizPasswordNotMatchedException bpn){
			resultMessageVO.messageSetting(false, 
					"비밀번호틀림", "비밀번호틀렸다 너. 수정실패", "/free/freeList.wow", "목록으로");
		}
		req.setAttribute("resultMessageVO", resultMessageVO);
		return "common/message";
	}	
}
