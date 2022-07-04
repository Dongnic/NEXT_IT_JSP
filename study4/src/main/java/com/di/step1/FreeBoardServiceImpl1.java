package com.di.step1;

public class FreeBoardServiceImpl1 implements IFreeBoardService{
	
	IFreeBoardDao freeBoardDao= new FreeBoardDaoOracle();
	
	@Override
	public void modifyBoard() {
		freeBoardDao.updateBoard();
	}
}
