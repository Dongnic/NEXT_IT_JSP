package com.study.common.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisSqlSessionFactory {
	private static SqlSessionFactory sqlSEssionFactory;
	
	// 클래스로드 될 때 실행되는 구문
	static {
		try {
		String resource = "mybatis/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//	1.mybatis-config.xml파일 읽음
		sqlSEssionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSEssionFactory;
	}
	
}
