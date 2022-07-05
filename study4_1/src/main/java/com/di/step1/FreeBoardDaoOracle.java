package com.di.step1;

public class FreeBoardDaoOracle implements IFreeBoardDao{

	@Override
	public void updateBoard() {
		System.out.println("오라클에서 업데이트 했다.");
	}
}
