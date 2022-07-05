package com.di.step1;

public class Test1 {

	public static void main(String[] args) {
		// DI : Dependency Inject 의존 주입
		// 의존하는 객체를 외부에서 주입함으로써 코드의 재사용성을 높이는 기술
		// 의존이란 어떤 클래스1에서 다른 클래스2의 메소드(or 필드 등)을 사용할 때
		// 클래스1은 클래스2에 의존한다.
		
		// step1은 의존객체를 내부에서 직접생성
		// oracle -> mysql로 바꾸게 되면 3곳을 바꿔야하네
		
		
		IFreeBoardService freeBoardService1= new FreeBoardServiceImpl1();
		freeBoardService1.modifyBoard();
		IFreeBoardService freeBoardService2= new FreeBoardServiceImpl2();
		freeBoardService2.modifyBoard();
		IFreeBoardService freeBoardService3= new FreeBoardServiceImpl3();
		freeBoardService3.modifyBoard();
	

	
	}

}
