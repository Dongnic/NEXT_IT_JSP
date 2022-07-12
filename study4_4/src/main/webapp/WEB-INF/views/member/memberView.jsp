
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/inc/top.jsp"%>


	<div class="container">
		<h3>상세보기</h3>
		<table class="table table-striped table-bordered">
			<tbody>
				<tr>
					<th>아이디</th>
					<td>${member.memId }</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>${member.memPass }</td>
				</tr>
				<tr>
					<th>회원명</th>
					<td>${member.memName }</td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td>${member.memZip }</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>${member.memAdd1 }${member.memAdd2 }</td>
				</tr>
				<tr>
					<th>생일</th>
					<td><input type="date" name="memBir"
						class="form-control input-sm" value='${member.memBir }'
						readonly="readonly"></td>
					<!-- 'YYYY-MM-DD'형태만 value값으로 들어갈수있어요 -->
				</tr>
				<tr>
					<th>핸드폰</th>
					<td>${member.memHp }</td>
				</tr>
				<tr>
					<th>직업</th>
					<td>${member.memJobNm }</td>
				</tr>
				<tr>
					<th>취미</th>
					<td>${member.memHobbyNm }</td>
				</tr>
				<tr>
					<th>마일리지</th>
					<td>${member.memMileage }</td>
				</tr>
				<tr>
					<th>탈퇴여부</th>
					<td>${member.memDelYn }</td>
				</tr>
				<tr>
					<td colspan="2"><a href="memberList.wow"
						class="btn btn-default btn-sm"> <span
							class="glyphicon glyphicon-list" aria-hidden="true"></span>
							&nbsp;목록
					</a> <a href='memberEdit.wow?memId=${member.memId }'
						class="btn btn-info btn-sm"> <span
							class="glyphicon glyphicon-king" aria-hidden="true"></span>
							&nbsp;수정
					</a></td>
				</tr>
			</tbody>
		</table>
	</div>


</body>
</html>