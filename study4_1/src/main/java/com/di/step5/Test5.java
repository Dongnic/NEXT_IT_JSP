package com.di.step5;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Test5 {

	public static void main(String[] args) {
		// DaoOracle, ServiceImpl @Component
		// ServiceImpl에 @Inject는 생성자 위에
		// context.getBean("이름", 클래스이름.class)에 "이름" 지우고 
		
		/*
		 * 오류 상황 파악하고 고쳐보기
		 1. FreeBoardServiceImpl1에는 @Component, @Inject 있음
		      = DaoOracle에는 없음 
		
			- DaoOracle에 Component가 없으면 스캔에서 해당이 안되므로 Impl에서 DaoOracle을 정의할 수 없음
			- 해결 : @Component 붙임 
			
		 2. FreeBoardServiceImpl1에는 @Component, @Inject 있음
		      = DaoOracle @Component 있음 
			  = DaoMySql @Component 있음
		 
		 	- IFreeBoardDao를 implements하는 클래스가 두개이므로 어디에 매칭해야 할지 몰라서 오류 
		 	- 해결 : 둘 중 한개 @Component 지움 
		 	
		 3. FreeBoardServiceImpl1에는 @Inject 없음
			
			- Impl의 기본생성자에 접근할 수 없기때문에 오류
			- 해결 : @Inject붙임 
		*/
		
		
		GenericXmlApplicationContext context= new GenericXmlApplicationContext("di/step5.xml");
		IFreeBoardService freeBoardService1= context.getBean(FreeBoardServiceImpl1.class);
		freeBoardService1.modifyBoard();
		// context = assembler
		// context "di/step4.xml"을 읽어서 거기에 적혀있는 객체들을 만들어서 가지고 있음.
		
		
	}

}
