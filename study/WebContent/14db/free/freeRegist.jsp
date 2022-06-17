
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>Insert title here</title>
</head>
<body>
<%@include file="/WEB-INF/inc/top.jsp"%>
<jsp:useBean id="freeBoard" class="com.study.free.vo.FreeBoardVO"></jsp:useBean>
<jsp:setProperty property="*" name="freeBoard"/>

<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try{
		conn=DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
		StringBuffer sb = new StringBuffer();
		
		sb.append(" INSERT INTO free_board (								");
		sb.append("       bo_no          , bo_title      , bo_category		");
		sb.append("     , bo_writer      , bo_pass       , bo_content		");
		sb.append("     , bo_hit         , bo_reg_date   , bo_mod_date		");
		sb.append("     , bo_del_yn											");
		sb.append(" ) VALUES (												");
		sb.append("     seq_free_board.nextval   , ?       , ?				");
		sb.append("     , ?         , ?          , ?						");
		sb.append("     , 0         , sysdate    , null						");
		sb.append("     , 'N'												");
		sb.append(" )														");
		pstmt = conn.prepareStatement(sb.toString());
		int idx = 1;
		pstmt.setString(idx++, freeBoard.getBoTitle());
		pstmt.setString(idx++, freeBoard.getBoCategory());
		pstmt.setString(idx++, freeBoard.getBoWriter());
		pstmt.setString(idx++, freeBoard.getBoPass());
		pstmt.setString(idx++, freeBoard.getBoContent());
		
		int cnt = pstmt.executeUpdate();
		
	}catch (SQLException e){
		e.printStackTrace();
	}finally{
		if(rs !=null) {try{ rs.close();}catch(Exception e){}}
		if(pstmt !=null) {try{ pstmt.close();}catch(Exception e){}}
		if(conn !=null) {try{ conn.close();}catch(Exception e){}}
	}


%>



		<div class="alert alert-success">
			정상적으로 글이 되었습니다.
		</div>		
	
		<div class="alert alert-warning">
			글 등록 실패    
		</div>	
	
<a href="freeList.jsp?" class="btn btn-default btn-sm">
		<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
		&nbsp;목록
	</a>

</body>
</html>