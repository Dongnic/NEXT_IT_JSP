package com.di.step1;

public class FreeBoardServiceImpl2 implements IFreeBoardService{
	
	IFreeBoardDao freeBoardDao= new FreeBoardDaoOracle();
	
	@Override
	public void modifyBoard() {
		freeBoardDao.updateBoard();
	}
}
