package com.di.step4;

public class FreeBoardDaoMySql implements IFreeBoardDao{
	@Override
	public void updateBoard() {
		System.out.println("MySql에서 업데이트 했다.");
	}
}
