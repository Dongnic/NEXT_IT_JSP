package com.di.step5;


import javax.inject.Inject;

import org.springframework.stereotype.Component;

@Component
public class FreeBoardServiceImpl1 implements IFreeBoardService{
	
	IFreeBoardDao freeBoardDao;
	
	@Inject
	public FreeBoardServiceImpl1(IFreeBoardDao freeBoardDao) {
		this.freeBoardDao=freeBoardDao;
	}
	
	@Override
	public void modifyBoard() {
		freeBoardDao.updateBoard();
		//System.out.println("여기서 바로");
	}
}
