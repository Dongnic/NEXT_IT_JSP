package com.study.free.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import com.study.common.vo.PagingVO;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.dao.IFreeBoardDao;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

@Service
public class FreeBoardServiceImpl  implements IFreeBoardService{
	
	@Inject
	IFreeBoardDao freeBoardDao;
	
	@Override
	public List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO) {
		
		//jsp에서 넘어왔을 때 
		//pagingVO는 curPage만 세팅되어있고 나머지는 기본값
		int totalRowCount=freeBoardDao.getTotalRowCount(searchVO);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		//나머지 필드 전부 세팅이 됐겠지..
		return freeBoardDao.getBoardList(searchVO);
	}

	@Override
	public FreeBoardVO getBoard(int boNo) throws BizNotFoundException {
		FreeBoardVO freeBoard=freeBoardDao.getBoard(boNo);
		if(freeBoard==null) {
			throw new BizNotFoundException();
		}
		return freeBoard;
	}

	@Override
	public void increaseHit(int boNo) throws BizNotEffectedException {
		// BizNotEffectedException은 특별한 일 없으면 발생 안해요.
		int cnt=freeBoardDao.increaseHit(boNo);
		if(cnt==0)throw new BizNotEffectedException();
		
	}

	@Override
	public void modifyBoard(FreeBoardVO freeBoard)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		//글 쓸 때 입력한 비밀번호(현재 DB에 있는 비밀번호)랑  
		//지금 입력한 비밀번호가 같을 때만 update 
		FreeBoardVO vo=freeBoardDao.getBoard(freeBoard.getBoNo());
		if(vo==null) {
			throw new BizNotFoundException();
		}
		//DB에서 Pass는 null 아님
		if( ! vo.getBoPass().equals(freeBoard.getBoPass())) {
			throw new BizPasswordNotMatchedException();
		}
		
		if(vo.getBoPass().equals(freeBoard.getBoPass())) {
			//vo는 DB에 있는 현재 값
			//freeBoard는 edit.jsp에서 사용자가 입력한 값
			int cnt=freeBoardDao.updateBoard(freeBoard);
			if(cnt<1) throw new BizNotEffectedException();
		}
	}

	@Override
	public void removeBoard(FreeBoardVO freeBoard)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		FreeBoardVO vo=freeBoardDao.getBoard(freeBoard.getBoNo());
		if(vo==null) {
			throw new BizNotFoundException();
		}
		if( ! vo.getBoPass().equals(freeBoard.getBoPass())) {
			throw new BizPasswordNotMatchedException();
		}
		if(vo.getBoPass().equals(freeBoard.getBoPass())) {
			int cnt=freeBoardDao.deleteBoard(freeBoard);
			if(cnt<1) throw new BizNotEffectedException();
		}
	}

	@Override
	public void registBoard(FreeBoardVO freeBoard) throws BizNotEffectedException {
		int cnt=freeBoardDao.insertBoard(freeBoard);
		if(cnt<1) throw new BizNotEffectedException();
	}
	
}
