

<%@page import="com.study.member.vo.MemberVO"%>

<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
</head>
<body>
<%@include file="/WEB-INF/inc/top.jsp"%>
<jsp:useBean id="memberL" class="com.study.member.vo.MemberVO"></jsp:useBean>
<jsp:setProperty property="*" name="memberL"/>
<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try{
		conn=DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
		StringBuffer sb = new StringBuffer();
		
		sb.append(" UPDATE member 					");
		sb.append(" SET 							");
		sb.append("		 mem_del_yn = 'Y'			");
		sb.append(" WHERE 1=1						");
		sb.append(" AND mem_id = ?					");
		pstmt = conn.prepareStatement(sb.toString());
		int idx = 1;
		
		pstmt.setString(idx++, memberL.getMemId());
		
		int cnt = pstmt.executeUpdate(); 
		
	}catch (SQLException e){
		e.printStackTrace();
	}finally{
		if(rs !=null) {try{ rs.close();}catch(Exception e){}}
		if(pstmt !=null) {try{ pstmt.close();}catch(Exception e){}}
		if(conn !=null) {try{ conn.close();}catch(Exception e){}}
	}
%>



	<div class="container">
		<h3>회원삭제</h3>

		
			<div class="alert alert-warning">
				<h4>삭제 성공</h4>
				정상적으로 회원을 삭제했습니다.
			</div>
		



		
			<div class="alert alert-warning">
				<h4>회원이 존재하지 않습니다.</h4>
				올바르게 접근해주세요.
			</div>
	
		
		<div class="alert alert-warning">
			<h4>삭제에 실패했습니다.</h4>
			삭제 실패
		</div>
		
		<a href="memberList.jsp?" class="btn btn-default btn-sm"> <span class="glyphicon glyphicon-list" aria-hidden="true"></span> &nbsp;목록
		</a>
	</div>
</body>
</html>