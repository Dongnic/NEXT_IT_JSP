<%@page import="com.study.exception.BizPasswordNotMatchedException"%>
<%@page import="com.study.exception.BizNotEffectedException"%>
<%@page import="com.study.exception.BizNotFoundException"%>
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
		freeBoardService.modifyBoard(freeBoard);
	}catch(BizNotFoundException bnf){
		request.setAttribute("bnf", bnf);
	}catch(BizNotEffectedException bne){
		request.setAttribute("bne", bne);
	}catch(BizPasswordNotMatchedException bpn){
		request.setAttribute("bpn", bpn);
	}

%>
		<c:if test="${bnf ne null }">
			<div class="alert alert-warning">
				해당 글이 존재하지 않습니다.
			</div>	
		</c:if>
	
		<c:if test="${bne ne null }">
			<div class="alert alert-warning">
				수정 실패
			</div>	
		</c:if>
		<c:if test="${bpn ne null }">
			<div class="alert alert-warning">
				비밀번호가 틀립니다.
			</div>	
		</c:if>
		
		<c:if test="${bnf eq null and bne eq null and bpn eq null }">
			<div class="alert alert-success">
				정상적으로 수정했습니다.
			</div>		
		</c:if>
	<a href="freeView.jsp?boNo=${freeBoard.boNo }" class="btn btn-default btn-sm">
		<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
		&nbsp;해당 뷰
	</a>	
	
	<a href="freeList.jsp" class="btn btn-default btn-sm">
		<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
		&nbsp;목록
	</a>
</body>
</html>