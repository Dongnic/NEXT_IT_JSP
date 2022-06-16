<%@page import="com.study.free.vo.FreeBoardVO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
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
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>

<%	
	int boNo= Integer.parseInt(request.getParameter("boNo"));
	// 로드 > 연결 > 실행 > 종료
	Class.forName("oracle.jdbc.driver.OracleDriver"); 
	Connection conn= null;
	Statement stmt= null;
	ResultSet rs= null;
	try{
		conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jsp","oracle");
		stmt=conn.createStatement();
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
		sb.append(" WHERE 1=1	 												");
		sb.append(" AND   bo_no=" + boNo); 										
		rs = stmt.executeQuery(sb.toString()); 
		if(rs.next()){
			FreeBoardVO freeBoard= new FreeBoardVO();
			freeBoard.setBoNo(rs.getInt("bo_no"));
			freeBoard.setBoTitle(rs.getString("bo_title"));
			freeBoard.setBoCategory(rs.getString("bo_category"));
			freeBoard.setBoWriter(rs.getString("bo_writer"));
			freeBoard.setBoPass(rs.getString("bo_pass"));
			freeBoard.setBoContent(rs.getString("bo_content"));
			freeBoard.setBoHit(rs.getInt("bo_hit"));
			freeBoard.setBoRegDate(rs.getString("bo_reg_date"));
			freeBoard.setBoModDate(rs.getString("bo_mod_date"));
			freeBoard.setBoDelYn(rs.getString("bo_del_yn"));
			
			request.setAttribute("freeBoard", freeBoard);
		}
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
		if(rs!=null)  { try{rs.close();   }catch(Exception e){}}
		if(stmt!=null){ try{stmt.close(); }catch(Exception e){}}
		if(conn!=null){ try{conn.close(); }catch(Exception e){}}
	}
%>

<table border="1">
	<tr>
		<td>번호</td>
		<td>${freeBoard.boNo }</td>
	</tr>
	<tr>
		<td>제목</td>
		<td>${freeBoard.boTitle }</td>
	</tr>
	<tr>
		<td>분류</td>
		<td>${freeBoard.boCategory }</td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${freeBoard.boWriter }</td>
	</tr>
	<tr>
		<td>비밀번호(임시로 보여주기)</td>
		<td>${freeBoard.boPass }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${freeBoard.boContent }</td>
	</tr>
	<tr>
		<td>조회수</td>
		<td>${freeBoard.boHit }</td>
	</tr>
	<tr>
		<td>등록일자</td>
		<td>${freeBoard.boRegDate }</td>
	</tr>
	<tr>
		<td>수정일자</td>
		<td>${freeBoard.boModDate }</td>
	</tr>
	<tr>
		<td>삭제여부</td>
		<td>${freeBoard.boDelYn }</td>
	</tr>

</table>


</body>
</html>