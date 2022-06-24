
<%@page import="com.study.exception.BizPasswordNotMatchedException"%>
<%@page import="com.study.exception.BizNotEffectedException"%>
<%@page import="com.study.exception.BizNotFoundException"%>
<%@page import="com.study.member.service.MemberServiceImpl"%>
<%@page import="com.study.member.service.IMemberService"%>
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

	IMemberService memberService= new MemberServiceImpl();
	try{
		memberService.modifyMember(memberL);
	}catch(BizNotFoundException bnf){
		request.setAttribute("bnf", bnf);
	}catch(BizNotEffectedException bne){
		request.setAttribute("bne", bne);
	}
	
%>


	<div class="container">
		<h3>회원수정</h3>
			
		<c:if test="${bnf eq null and bne eq null }">
			<div class="alert alert-success">정상적으로 수정했습니다.</div>
		</c:if>
		<c:if test="${bnf ne null }">
			<div class="alert alert-warning">수정하려는 글을 못 찾았습니다</div>
		</c:if>
		<c:if test="${bne ne null }">
			<div class="alert alert-warning">수정실패</div>
		</c:if>

		<a href="memberList.jsp" class="btn btn-default btn-sm"> <span class="glyphicon glyphicon-list" aria-hidden="true"></span> &nbsp;목록
		</a> <a href="memberView.jsp?memId=${memberL.memId }" class="btn btn-default btn-sm"> <span class="glyphicon glyphicon-list" aria-hidden="true"></span> &nbsp;해당 뷰
		</a>

	</div>
</body>
</html>