package com.study.servlet;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

@SuppressWarnings("serial")
public class DriverLoader extends HttpServlet {

	@Override
	public void init() throws ServletException {		
		loadJDBCDriver();
		initConnectionPool();
	}
	
	//ApplicationLoader가 하는역할
	private void  loadJDBCDriver() {
		// 1. 드라이버 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("오라클 드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("fail to JDBC Driver ", e);
		}
	} // loadJDBCDriver
	
	private void  initConnectionPool() {		
		try {
			// 커넥션 풀이 새로운 커넥션을 생성할 때 사용할 커넥션 팩토리를 생성한다. 
			ConnectionFactory connFactory = 
					 new DriverManagerConnectionFactory(
							"jdbc:oracle:thin:@127.0.0.1:1521:xe","jsp","oracle");
			
			
			 
			PoolableConnectionFactory poolableConnFactory 
			    = new PoolableConnectionFactory(connFactory, null);
			poolableConnFactory.setValidationQuery("select 1 from dual");
			// 커넥션이 유효한지 확인하기 위한 쿼리
		
			
			@SuppressWarnings("rawtypes")
			GenericObjectPoolConfig poolConofig = new GenericObjectPoolConfig();
			// 커넥션풀의 설정정보. 유휴 커넥션 검사주기, 검사여부, 커넥션 최소,최대 갯수
			poolConofig.setTimeBetweenEvictionRunsMillis(1000L *60L * 10L);	// 10분
			poolConofig.setTestWhileIdle(true); 
			poolConofig.setMinIdle(4);
			poolConofig.setMaxTotal(4);	// default는 8. 수업때문에 4개로 줄인것
			
			@SuppressWarnings("unchecked")
			GenericObjectPool<PoolableConnection> connectionPool 
			   = new GenericObjectPool<>(poolableConnFactory, poolConofig);
			poolableConnFactory.setPool(connectionPool);
			
			// 커넥션 풀 등록
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver)DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			driver.registerPool("study",connectionPool);
			System.out.println("DBCP study 등록 성공");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	} // initConnectionPool
	
} // class
