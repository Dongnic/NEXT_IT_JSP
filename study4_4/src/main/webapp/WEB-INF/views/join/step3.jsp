<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="shortcut icon" href="../resources/images/favicon.ico">
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>회원가입 3단계</title>
<style type="text/css">
	.form-group-sm span{
		color: red;
		font-size: 10px;
	}

</style>
<script type="text/javascript">

</script>
</head>
<body>
	<%@include file="/WEB-INF/inc/top.jsp"%>
	<div class="container">
	<form:form modelAttribute="member" action="regist.wow" method="post">
		<div class="row col-md-8 col-md-offset-2">
			<div class="page-header">
				<h3>회원가입 3단계</h3>
			</div>


			<table class="table">
				<colgroup>
					<col width="20%" />
					<col />
				</colgroup>

				<tr class="form-group-sm">
					<th>생일</th>
					<td>
						<input type ="date" name="memBir" class="form-control input-sm" value="${member.memBir }">
						<form:errors path="memBir"></form:errors>	
					</td>
				</tr>
				<tr class="form-group-sm">
					<th>우편번호</th>
					<td>
						<form:input path="memZip" cssClass="form-control input-sm" />
						<form:errors path="memZip"></form:errors>	
					</td>
				</tr>
				<tr class="form-group-sm">
					<th>주소</th>
					<td>
						<form:input path="memAdd1" cssClass="form-control input-sm" />
						<form:errors path="memAdd1"></form:errors>	
						<form:input path="memAdd2" cssClass="form-control input-sm" />
						<form:errors path="memAdd2"></form:errors>	
					</td>
				</tr>
				<tr class="form-group-sm">
					<th>핸드폰</th>
					<td>
						<form:input path="memHp" cssClass="form-control input-sm" />
						<form:errors path="memHp"></form:errors>	
					</td>
				</tr>
				<tr>
					<th>직업</th>
					<td>
						<div class="form-group-sm">
							<!-- form select써서 
							<select name="memJob" class="form-control">

							</select> -->
							<form:select path="memJob" cssClass="form-control input-sm">
								<form:option value=""> -- 선택하세요 -- </form:option>
								<form:options items="${jobList }" itemLabel="commNm" itemValue="commCd"/>
							</form:select>
							<form:errors path="memJob"></form:errors>
							
						</div>
					</td>
				</tr>
				<tr>
					<th>취미</th>
					<td class="form-group-sm">
						<!--<select name="memLike" class="form-control">
						</select> -->
						<form:select path="memHobby" cssClass="form-control input-sm">
								<form:option value=""> -- 선택하세요 -- </form:option>
								<form:options items="${hobbyList }" itemLabel="commNm" itemValue="commCd"/>
						</form:select>
						<form:errors path="memHobby"></form:errors>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="pull-left">
							<a href="${pageContext.request.contextPath}/join/cancel" class="btn btn-sm btn-default"> <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> &nbsp;&nbsp;취 소
							</a>
						</div>
						<div class="pull-right">
							<button type="submit" class="btn btn-sm btn-primary">
								<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> &nbsp;&nbsp;다 음
							</button>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form:form>
	</div>
</body>
</html>



