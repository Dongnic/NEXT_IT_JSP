<%@page import="com.study.exception.BizPasswordNotMatchedException"%>
<%@page import="com.study.exception.BizNotEffectedException"%>
<%@page import="com.study.exception.BizNotFoundException"%>
<%@page import="com.study.free.service.FreeBoardServiceImpl"%>
<%@page import="com.study.free.service.IFreeBoardService"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="shortcut icon" href="#">
	<%@ include file="/WEB-INF/inc/header.jsp" %>
	<title>freeDelete.jsp </title>
</head>
<body>
<%@include file="/WEB-INF/inc/top.jsp"%>
<jsp:useBean id="freeBoard" class="com.study.free.vo.FreeBoardVO"></jsp:useBean>
<jsp:setProperty property="*" name="freeBoard"/>

<%
	IFreeBoardService freeBoardService= new FreeBoardServiceImpl();
	try{
		freeBoardService.removeBoard(freeBoard);
	}catch(BizNotFoundException bnf){
		request.setAttribute("bnf", bnf);
	}catch(BizNotEffectedException bne){
		request.setAttribute("bne", bne);
	}catch(BizPasswordNotMatchedException bpn){
		request.setAttribute("bpn", bpn);
	}
%>

 <div class="container">	
	<h3>회원 정보 삭제</h3>		
	
	

		<c:if test="${bnf ne null }">
		<div class="alert alert-warning">
			해당 글이 존재하지 않습니다.
		</div>	
	</c:if>
	<c:if test="${bne ne null }">
		<div class="alert alert-warning">
			삭제 실패
		</div>	
	</c:if>
	
	<c:if test="${bpn ne null }">
		<div class="alert alert-warning">
			비밀번호가 틀립니다.
		</div>	
	</c:if>
		
		
	<c:if test="${bnf eq null and bne eq null and bpn eq null }">
		<div class="alert alert-success">
			정상적으로 삭제했습니다.
		</div>		
	</c:if>
	
	<a href="freeList.jsp" class="btn btn-default btn-sm">
		<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
		&nbsp;목록
	</a>
	
		
	

	</div>
</body>
</html>