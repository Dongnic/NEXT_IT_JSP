package com.di.step4;

public class FreeBoardServiceImpl2 implements IFreeBoardService{
	
	IFreeBoardDao freeBoardDao= new FreeBoardDaoOracle();
	
	public FreeBoardServiceImpl2(IFreeBoardDao freeBoardDao) {
		this.freeBoardDao=freeBoardDao;
	}
	
	@Override
	public void modifyBoard() {
		freeBoardDao.updateBoard();
	}
}
