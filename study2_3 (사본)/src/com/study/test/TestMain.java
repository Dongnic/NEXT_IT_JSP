package com.study.test;

import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;

public class TestMain {

	public static void main(String[] args) {
		String parentCode = "BC00";
		ICommCodeService ccs= new CommCodeServiceImpl();
		ccs.getCodeListByParent(parentCode);
	}

}
