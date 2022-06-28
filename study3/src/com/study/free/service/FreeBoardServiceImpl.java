package com.study.free.service;

import java.util.List;

import com.study.common.vo.PagingVO;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.dao.FreeBoardDaoOracle;
import com.study.free.dao.IFreeBoardDao;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

// I** Service
// **ServiceImpl 
// 형태로 파일을 생성한다. 관례
public class FreeBoardServiceImpl implements IFreeBoardService{
	
	IFreeBoardDao freeBoardDao= new FreeBoardDaoOracle();

	@Override
	public List<FreeBoardVO> getBoardList(FreeBoardSearchVO FreeBoardSearchVO) {
		// jsp에서 넘어왔을 때
		// pagingVO curPage만 세팅 그외는 기본 값
		
		int totalRowCount= freeBoardDao.getTotalRowCount(FreeBoardSearchVO);
		FreeBoardSearchVO.setTotalRowCount(totalRowCount);
		FreeBoardSearchVO.pageSetting();
		// 나머지 필드 전부 세팅이 되어야함.. 아마 됐겠지
		
		// TODO Auto-generated method stub
		return freeBoardDao.getBoardList(FreeBoardSearchVO);
	}

	@Override
	public FreeBoardVO getBoard(int boNo) throws BizNotFoundException {
		// TODO Auto-generated method stub
		FreeBoardVO freeBoard= freeBoardDao.getBoard(boNo);
		if(freeBoard==null) {
			throw new BizNotFoundException();
		}
		return freeBoard;
	}

	@Override
	public void increaseHit(int boNo) throws BizNotEffectedException {
		// 특별한 일 없으면 발생안함
		int cnt= freeBoardDao.increaseHit(boNo);
		if(cnt==0) throw new BizNotEffectedException();
	}

	@Override
	public void modifyBoard(FreeBoardVO freeBoard)
		throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		// 글쓸 때 입력한 비밀번호랑 지금 입력한 비밀번호가 같을 때만 update
		// 현재 DB에 있는 비밀번호 = 지금입력한 비밀번호
		FreeBoardVO vo= freeBoardDao.getBoard(freeBoard.getBoNo());
		// 아래는 현재 게시글 수정 중 글이 삭제된 경우
		if(vo==null) {
			throw new BizNotFoundException();
		}
		if(!vo.getBoPass().equals(freeBoard.getBoPass())){
			throw new BizPasswordNotMatchedException();
		}
		if(vo.getBoPass().equals(freeBoard.getBoPass())){
			int cnt = freeBoardDao.updateBoard(freeBoard);
			if(cnt<1) throw new BizNotEffectedException();
		}
		
		
	}

	@Override
	public void removeBoard(FreeBoardVO freeBoard)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		FreeBoardVO vo= freeBoardDao.getBoard(freeBoard.getBoNo());
		// 아래는 현재 게시글 수정 중 글이 삭제된 경우
		if(vo==null) {
			throw new BizNotFoundException();
		}
		if(!vo.getBoPass().equals(freeBoard.getBoPass())){
			throw new BizPasswordNotMatchedException();
		}
		if(vo.getBoPass().equals(freeBoard.getBoPass())){
			int cnt = freeBoardDao.deleteBoard(freeBoard);
			if(cnt<1) throw new BizNotEffectedException();
		}
		
	}

	@Override
	public void registBoard(FreeBoardVO freeBoard) throws BizNotEffectedException {
		int cnt=freeBoardDao.insertBoard(freeBoard);
		if(cnt<1) throw new BizNotEffectedException();
	}
}
