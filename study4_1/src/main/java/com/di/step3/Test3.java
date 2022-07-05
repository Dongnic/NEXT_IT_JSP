package com.di.step3;

public class Test3 {

	public static void main(String[] args) {
		// DI : Dependency Inject 의존 주입
		// 의존하는 객체를 외부에서 주입함으로써 코드의 재사용성을 높이는 기술
		// 의존이란 어떤 클래스1에서 다른 클래스2의 메소드(or 필드 등)을 사용할 때
		// 클래스1은 클래스2에 의존한다.
		
		// step3에서는 의존객체를 외부에서 Assembler를 이용해서 하기 
		
		// DaoOracle -> DaoMySql로 변경하라
		
		Assembler assembler= new Assembler();
		// assembler는 freeBoardDao, service 1~3의 객체를 가지고 있다.
		// 스프링에서 assembler(의 역할을 하는)가 가지고 있는 객체 = 빈(Bean)
		
		
		IFreeBoardService freeBoardService1= assembler.getFreeBoardService1();
		freeBoardService1.modifyBoard();
		IFreeBoardService freeBoardService2= assembler.getFreeBoardService2();
		freeBoardService2.modifyBoard();
		IFreeBoardService freeBoardService3= assembler.getFreeBoardService3();
		freeBoardService3.modifyBoard();
		
	}

}
