package com.di.step2;

public class Test2 {

	public static void main(String[] args) {
		// DI : Dependency Inject 의존 주입
		// 의존하는 객체를 외부에서 주입함으로써 코드의 재사용성을 높이는 기술
		// 의존이란 어떤 클래스1에서 다른 클래스2의 메소드(or 필드 등)을 사용할 때
		// 클래스1은 클래스2에 의존한다.
		
		// step2에서는 의존객체를 외부에서 주입 -> 의존주입
		// 생성자방식, setter방식, 필드방식(only spring)인데
	
		
		IFreeBoardDao freeBoardDao= new FreeBoardDaoOracle();
		IFreeBoardService freeBoardService1= new FreeBoardServiceImpl1(freeBoardDao);
		freeBoardService1.modifyBoard();
		IFreeBoardService freeBoardService2= new FreeBoardServiceImpl2(freeBoardDao);
		freeBoardService2.modifyBoard();
		IFreeBoardService freeBoardService3= new FreeBoardServiceImpl3(freeBoardDao);
		freeBoardService3.modifyBoard();
		
		// DaoOracle -> DaoMySql로 변경하라
		
		// new FreeBoardDaoOracle() -> new FreeBoardDaoMySql()로 변경하면 됨
		
		// Test(main)이 두개라면 둘 다 바꿔야 하네?
	}

}
