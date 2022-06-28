
<%@page import="com.study.exception.BizNotEffectedException"%>
<%@page import="com.study.free.service.FreeBoardServiceImpl"%>
<%@page import="com.study.free.service.IFreeBoardService"%>
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
	IFreeBoardService freeBoardService= new FreeBoardServiceImpl();
	try{
		freeBoardService.registBoard(freeBoard);
	}catch(BizNotEffectedException e){
		request.setAttribute("e", e);
	}

%>

	<c:if test="${e eq null }">
		<div class="alert alert-success">
			정상적으로 글이 되었습니다.
		</div>		
	</c:if>
	<c:if test="${e ne null }">
		<div class="alert alert-warning">
			글 등록 실패    
		</div>	
	</c:if>
	
<a href="freeList.jsp?" class="btn btn-default btn-sm">
		<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
		&nbsp;목록
	</a>

</body>
</html>