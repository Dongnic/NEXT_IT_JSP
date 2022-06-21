
<%@page import="com.study.exception.BizDuplicateKeyException"%>
<%@page import="com.study.exception.BizNotEffectedException"%>
<%@page import="com.study.member.service.MemberServiceImpl"%>
<%@page import="com.study.member.service.IMemberService"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.study.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<!-- <link rel="shortcut icon" href="#"> -->
<%@ include file="/WEB-INF/inc/header.jsp"%>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp"%>
<jsp:useBean id="memberL" class="com.study.member.vo.MemberVO"></jsp:useBean>
<jsp:setProperty property="*" name="memberL"/>
<%
	IMemberService memberService= new MemberServiceImpl();
	try{
		memberService.registMember(memberL);
	}catch(BizNotEffectedException bne){
		request.setAttribute("bne", bne);
	}catch(BizDuplicateKeyException bde){
		request.setAttribute("bde", bde);
	}
	
%>

	<div class="container">
		<h3>회원등록</h3>
			<c:if test="${bne eq null and bde eq null }">
				<div class="alert alert-success">정상적으로 회원 등록 되었습니다.</div>
			</c:if>
			<c:if test="${bde ne null }">
				<div class="alert alert-warning">아이디 중복</div>
				<a href="#" class="btn btn-default btn-sm" onclick="history.back();"> <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> &nbsp;뒤로가기
				</a>
			</c:if>
			<c:if test="${bne ne null }">
				<div class="alert alert-warning">등록 실패 </div>
			</c:if>
		<a href="memberList.jsp" class="btn btn-default btn-sm"> <span class="glyphicon glyphicon-list" aria-hidden="true"></span> &nbsp;목록
		</a>
	</div>
</body>
</html>