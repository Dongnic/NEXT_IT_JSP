package com.di.step4;

public class FreeBoardServiceImpl1 implements IFreeBoardService{
	
	IFreeBoardDao freeBoardDao;
	
	public FreeBoardServiceImpl1(IFreeBoardDao freeBoardDao) {
		this.freeBoardDao=freeBoardDao;
	}
	
	@Override
	public void modifyBoard() {
		freeBoardDao.updateBoard();
	}
}
