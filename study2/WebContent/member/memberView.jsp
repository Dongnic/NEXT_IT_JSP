
<%@page import="com.study.exception.BizNotEffectedException"%>
<%@page import="com.study.exception.BizNotFoundException"%>
<%@page import="com.study.member.service.MemberServiceImpl"%>
<%@page import="com.study.member.service.IMemberService"%>
<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="shortcut icon" href="#">
<%@ include file="/WEB-INF/inc/header.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/inc/top.jsp"%>
<%
	String memId= request.getParameter("memId");
	IMemberService memberService= new MemberServiceImpl();
	
	try{
		MemberVO memberL= memberService.getMember(memId);
		request.setAttribute("memberL", memberL);		
	}catch(BizNotFoundException bne){
		request.setAttribute("bne", bne);
	}
	
%>

	<c:if test="${bne ne null }">
		<div class="alert alert-warning">해당 멤버를 찾을 수 없습니다</div>
	</c:if>	
		
		
	<c:if test="${bne eq null }">	
		
		<a href="memberList.jsp" class="btn btn-default btn-sm"> <span class="glyphicon glyphicon-list" aria-hidden="true"></span> &nbsp;목록
		</a>


	
		<div class="container">
			<h3>상세보기</h3>
			<table class="table table-striped table-bordered">
				<tbody>
					<tr>
						<th>아이디</th>
							<td>${memberL.memId }</td>
					</tr>
					<tr>
						<th>비밀번호</th>
							<td>${memberL.memPass }</td>
					</tr>
					<tr>
						<th>회원명</th>
					<td>${memberL.memName }</td>
					</tr>
					<tr>
						<th>우편번호</th>
						<td>${memberL.memZip }</td>
					</tr>
					<tr>
						<th>주소</th>
						<td>${memberL.memAdd1 }${memberL.memAdd2 }</td>
					</tr>
					<tr>
						<th>생일</th>
						<fmt:parseDate value="${memberL.memBir }" pattern="yyyy-MM-dd" var="date_date"/>
						<fmt:formatDate value="${date_date}" pattern="yyyy-MM-dd" var="str_date"/>
						<td><input type="date" name="memBir" class="form-control input-sm" value='${str_date }' readonly="readonly"></td> <!-- 'YYYY-MM-DD'형태만 value값으로 들어갈수있어요 -->
					</tr>
					<tr>
						<th>핸드폰</th>
						<td>${memberL.memHp }</td>
					</tr>
					<tr>
						<th>직업</th>
						<td>${memberL.memJob }</td>
					</tr>
					<tr>
						<th>취미</th>
						<td>${memberL.memHobby }</td>
					</tr>
					<tr>
						<th>마일리지</th>
						<td>${memberL.memMileage }</td>
					</tr>
					<tr>
						<th>탈퇴여부</th>
						<td>${memberL.memDelYn }</td>
					</tr>
					<tr>
						<td colspan="2"><a href="memberList.jsp" class="btn btn-default btn-sm"> <span class="glyphicon glyphicon-list" aria-hidden="true"></span> &nbsp;목록
						</a> <a href='memberEdit.jsp?memId=${memberL.memId }' class="btn btn-info btn-sm"> <span class="glyphicon glyphicon-king" aria-hidden="true"></span> &nbsp;수정
						</a></td>
					</tr>
				</tbody>
			</table>
		</div>

	</c:if>
</body>
</html>