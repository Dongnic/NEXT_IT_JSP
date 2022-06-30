<%@page import="com.study.code.vo.CodeVO"%>
<%@page import="com.study.code.service.CommCodeServiceImpl"%>
<%@page import="com.study.code.service.ICommCodeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="/WEB-INF/inc/header.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/inc/top.jsp"%>


 <div class="container">	
	<h3>회원가입</h3>		
	<form action="memberRegist.wow" method="post" >
	<table class="table table-striped table-bordered">
		<tbody>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="memId" class="form-control input-sm" 
						   required="required" pattern="\w{4,}" title="알파벳과 숫자로 4글자 이상 입력" ></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="memPass" class="form-control input-sm"
						   required="required" pattern="\w{4,}" title="알파벳과 숫자로 4글자 이상 입력" ></td>
			</tr>
			<tr>
				<th>회원명</th>
				<td><input type="text" name="memName" class="form-control input-sm"
						   required="required" pattern="[가-힣]{2,}" title="한글로 2글자 이상 입력" ></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="memZip" class="form-control input-sm" ></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="memAdd1" class="form-control input-sm" >
					<input type="text" name="memAdd2" class="form-control input-sm" >
				</td>
			</tr>
			<tr>
				<th>생일</th>
				<td><input type="date" name="memBir" class="form-control input-sm" ></td>
			</tr>
			<tr>
				<th>메일</th>
				<td><input type="email" name="memMail" class="form-control input-sm" ></td>
			</tr>
			<tr>
				<th>핸드폰</th>
				<td><input type="tel" name="memHp" class="form-control input-sm" ></td>
			</tr>
			<tr>
				<th>직업</th>
				<td>
					<select name="memJob" class="form-control input-sm" required="required">
						<option value="">-- 직업 선택 --</option>
						<c:forEach items="${jobList }" var="job">
						<option value="${job.commCd }">${job.commNm }</option>
						</c:forEach>
								
					</select>				
				</td>
			</tr>
			<tr>
				<th>취미</th>
				<td>
					<select name="memHobby" class="form-control input-sm" required="required">
						<option value="">-- 취미 선택 --</option>
						<c:forEach items="${hobbyList }" var="hobby">
						<option value="${hobby.commCd }">${hobby.commNm }</option>
						</c:forEach>
					</select>				
				</td>
			</tr>			
			<tr>
				<td colspan="2">
					<button type="submit" class="btn btn-primary">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
					&nbsp;회원가입
					</button>
					<a href="memberList.wow" class="btn btn-info btn-sm">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					&nbsp;목록
					</a>
				</td>
			</tr>
		</tbody>	
	</table>
	</form>
</div>

</body>
</html>