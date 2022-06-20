package com.study.free.service;

import java.util.List;

import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.dao.FreeBoardDaoOracle;
import com.study.free.dao.IFreeBoardDao;
import com.study.free.vo.FreeBoardVO;

// I** Service
// **ServiceImpl 
// 형태로 파일을 생성한다. 관례
public class FreeBoardServiceImpl implements IFreeBoardService{
	IFreeBoardDao freeBoardDao= new FreeBoardDaoOracle();

	@Override
	public List<FreeBoardVO> getBoardList() {
		// TODO Auto-generated method stub
		return freeBoardDao.getBoardList();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeBoard(FreeBoardVO freeBoard)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registBoard(FreeBoardVO freeBoard) throws BizNotEffectedException {
		// TODO Auto-generated method stub
		
	}
}
