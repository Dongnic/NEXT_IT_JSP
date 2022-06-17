<%@page import="java.util.ArrayList"%>
<%@page import="com.study.free.vo.FreeBoardVO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="/WEB-INF/inc/header.jsp" %>
<meta charset="UTF-8">
<title>05mcmjDBCP.jsp</title>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>
<%
	// 05mcmjDBCP 서버가 켜질 때 Class.forName(오라클드라이버)하니까 아래 필요없음
	
// 	Class.forName("oracle.jdbc.driver.OracleDriver"); // Class.forName 런타임동적로딩
	Connection conn= null;
	Statement stmt= null;
	ResultSet rs= null;
	long startTime= System.currentTimeMillis();
	for(int i= 0; i<1000; i++){
	try{											// 127.0.0.1 = localhost
		//연결하는 부분만 DB직접 찾지말고 DBCP에 연결정보 요청 
		conn= DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
		// 2. 연결
		stmt=conn.createStatement(); // 쿼리수행 객체
		// 3. 쿼리 수행
		StringBuffer sb= new StringBuffer();
		sb.append(" SELECT														");
		sb.append("      bo_no         , bo_title       , bo_category 			");
		sb.append("    , bo_writer     , bo_pass        , bo_content		  	");
		sb.append("    , bo_hit      								   			");
		sb.append("    , TO_CHAR(bo_reg_date,'YYYY-MM-dd') AS bo_reg_date		");
		sb.append("    , TO_CHAR(bo_mod_date,'YYYY-MM-dd') AS bo_mod_date		");
		sb.append("    , bo_del_yn												");
		sb.append(" FROM														");
		sb.append("    free_board 												");
		rs = stmt.executeQuery(sb.toString()); // 쿼리수행, rs는 쿼리수행 결과 저장하는 객체
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
		// 4. 연결 종료
		if(rs!=null)  { try{rs.close();   }catch(Exception e){}}
		if(stmt!=null){ try{stmt.close(); }catch(Exception e){}}
		if(conn!=null){ try{conn.close(); }catch(Exception e){}}
	}
	}
	long endTime= System.currentTimeMillis();
	request.setAttribute("endTime", endTime);	
	request.setAttribute("startTime", startTime);	
%>
총 걸린시간1 : ${endTime - startTime }
<br>
총 걸린시간2 : <%=endTime - startTime %>
</body>
</html>