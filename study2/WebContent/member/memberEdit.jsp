
<%@page import="com.study.exception.BizNotFoundException"%>
<%@page import="com.study.member.service.MemberServiceImpl"%>
<%@page import="com.study.member.service.IMemberService"%>
<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="shortcut icon" href="#">
	<%@ include file="/WEB-INF/inc/header.jsp" %>
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
		<a href="memberList.jsp" class="btn btn-default btn-sm"> <span class="glyphicon glyphicon-list" aria-hidden="true"></span> &nbsp;목록
		</a>
	</c:if>

	<c:if test="${bne eq null }">	
		 <div class="container">	
			<h3>회원 정보 수정</h3>	
			<form action="memberModify.jsp" method="post" >
			<table class="table table-striped table-bordered">
				<tbody>
					<tr>
						<th>아이디</th>
						<td>${memberL.memId }<input type="hidden" name="memId" value="${memberL.memId }"></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="memPass" class="form-control input-sm" 
								    pattern="\w{4,}" title="알파벳과 숫자로 4글자 이상 입력" ></td>
					</tr>
					<tr>
						<th>회원명</th>
						<td><input type="text" name="memName" class="form-control input-sm" value="${memberL.memName }"
								   required="required" pattern="[가-힣]{2,}" title="한글로 2글자 이상 입력" ></td>
					</tr>
					<tr>
						<th>우편번호</th>
						<td><input type="text" name="memZip" class="form-control input-sm" value='${memberL.memZip }'></td>
					</tr>
					<tr>
						<th>주소</th>
						<td><input type="text" name="memAdd1" class="form-control input-sm" value='${memberL.memAdd1 }'>
							<input type="text" name="memAdd2" class="form-control input-sm" value='${memberL.memAdd2 }'>
						</td>
					</tr>
					<tr>
						<th>생일</th>
						<fmt:parseDate value="${memberL.memBir }" pattern="yyyy-MM-dd" var="date_date"/>
						<fmt:formatDate value="${date_date}" pattern="yyyy-MM-dd" var="str_date"/>
						<td><input type="date" name="memBir" class="form-control input-sm" value='${str_date }'></td>
					</tr>
					<tr>
						<th>메일</th>
						<td><input type="email" name="memMail" class="form-control input-sm" required="required" value='${memberL.memMail }'></td>
					</tr>
					<tr>
						<th>핸드폰</th>
						<td><input type="tel" name="memHp" class="form-control input-sm" value='${memberL.memHp }'></td>
					</tr>
					<tr>
						<th>직업</th>
						<td>
						
							<select name="memJob" class="form-control input-sm" >
								<option value="">-- 직업 선택 --</option>
								<option value="JB01" ${memberL.memJob eq "JB01" ? "selected='selected'" : "" }>주부</option>
								<option value="JB02" ${memberL.memJob eq "JB02" ? "selected='selected'" : "" }>은행원</option>
								<option value="JB03" ${memberL.memJob eq "JB03" ? "selected='selected'" : "" }>공무원</option>
								<option value="JB04" ${memberL.memJob eq "JB04" ? "selected='selected'" : "" }>축산업</option>
								<option value="JB05" ${memberL.memJob eq "JB05" ? "selected='selected'" : "" }>회사원</option>
								<option value="JB06" ${memberL.memJob eq "JB06" ? "selected='selected'" : "" }>농업</option>
								<option value="JB07" ${memberL.memJob eq "JB07" ? "selected='selected'" : "" }>자영업</option>
								<option value="JB08" ${memberL.memJob eq "JB08" ? "selected='selected'" : "" }>학생</option>
								<option value="JB09" ${memberL.memJob eq "JB09" ? "selected='selected'" : "" }>교사</option>					
							</select>			
						</td>
					</tr>
					<tr>
						<th>취미</th>
						<td>
						
							<select name="memHobby" class="form-control input-sm" >
								<option value="">-- 취미 선택 --</option>
								<option value="HB01" ${memberL.memHobby eq "HB01" ? "selected='selected'" : "" }>서예</option>
								<option value="HB02" ${memberL.memHobby eq "HB02" ? "selected='selected'" : "" }>장기</option>
								<option value="HB03" ${memberL.memHobby eq "HB03" ? "selected='selected'" : "" }>수영</option>
								<option value="HB04" ${memberL.memHobby eq "HB04" ? "selected='selected'" : "" }>독서</option>
								<option value="HB05" ${memberL.memHobby eq "HB05" ? "selected='selected'" : "" }>당구</option>
								<option value="HB06" ${memberL.memHobby eq "HB06" ? "selected='selected'" : "" }>바둑</option>
								<option value="HB07" ${memberL.memHobby eq "HB07" ? "selected='selected'" : "" }>볼링</option>
								<option value="HB08" ${memberL.memHobby eq "HB08" ? "selected='selected'" : "" }>스키</option>
								<option value="HB09" ${memberL.memHobby eq "HB09" ? "selected='selected'" : "" }>만화</option>
								<option value="HB10" ${memberL.memHobby eq "HB10" ? "selected='selected'" : "" }>낚시</option>
								<option value="HB11" ${memberL.memHobby eq "HB11" ? "selected='selected'" : "" }>영화감상</option>
								<option value="HB12" ${memberL.memHobby eq "HB12" ? "selected='selected'" : "" }>등산</option>
								<option value="HB13" ${memberL.memHobby eq "HB13" ? "selected='selected'" : "" }>개그</option>
								<option value="HB14" ${memberL.memHobby eq "HB14" ? "selected='selected'" : "" }>카레이싱</option>					
							</select>			
						</td>
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
						<td colspan="2">
							<a href="memberList.jsp" class="btn btn-info btn-sm">
							<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
							&nbsp;목록으로
							</a>
							<button type="submit" class="btn btn-primary">
							<span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
							&nbsp;&nbsp; 저장
							</button>
							
								<button type="submit" formaction="memberDelete.jsp" class="btn btn-danger btn-sm">
							<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
							&nbsp;&nbsp; 삭제
							</button>
							
							
						</td>
					</tr>
				</tbody>	
			</table>
			</form>
		</div>
	</c:if>

</body>
</html>