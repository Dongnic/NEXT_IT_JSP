package com.di.step4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Test4 {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext context= new GenericXmlApplicationContext("di/step4.xml");
		System.out.println(context.getBean("freeBoardService1"));
		System.out.println(context.getBean("freeBoardService2"));
		IFreeBoardService freeBoardService1= context.getBean("freeBoardService1", FreeBoardServiceImpl1.class);
		freeBoardService1.modifyBoard();
		// context = assembler
		// context "di/step4.xml"을 읽어서 거기에 적혀있는 객체들을 만들어서 가지고 있음.
		
		
	}

}
