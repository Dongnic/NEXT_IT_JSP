
<%@page import="com.study.code.vo.CodeVO"%>
<%@page import="com.study.code.service.CommCodeServiceImpl"%>
<%@page import="com.study.code.service.ICommCodeService"%>
<%@page import="com.study.member.service.MemberServiceImpl"%>
<%@page import="com.study.member.service.IMemberService"%>
<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html lang="ko">
<head>
<style type="text/css">
	.t th{text-align: center;}
	.t td{text-align: center;}
</style>
<%request.setCharacterEncoding("utf-8"); %>
<link rel="shortcut icon" href="../resources/images/favicon.ico">
	<%@ include file="/WEB-INF/inc/header.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/inc/top.jsp"%>
<jsp:useBean id="searchVO" class="com.study.member.vo.MemberSearchVO"></jsp:useBean>
<jsp:setProperty property="*" name="searchVO"/>
<%
	IMemberService memberService= new MemberServiceImpl();
	List<MemberVO> memberList= memberService.getMemberList(searchVO);
	request.setAttribute("memberList", memberList);
	
	ICommCodeService codeService=new CommCodeServiceImpl();
	List<CodeVO> hobbyList= codeService.getCodeListByParent("HB00");
	request.setAttribute("hobbyList", hobbyList);
	List<CodeVO> jobList= codeService.getCodeListByParent("JB00");
	request.setAttribute("jobList", jobList);
%>
후 : ${searchVO }
 <div class="container">	
	<h3>회원목록</h3>	
	<!-- START : 검색 폼  -->
	<div class="panel panel-default collapse in" id="id_search_area">
		<div class="panel-body">
			<form name="search" action="memberList.jsp" method="post" class="form-horizontal">
				<input type="hidden" name="curPage" value="${searchVO.curPage }"> <input type="hidden" name="rowSizePerPage" value="${searchVO.rowSizePerPage }">
				<div class="form-group">
					<label for="id_searchType" class="col-sm-2 control-label">검색</label>
					<div class="col-sm-2">
						<select id="id_searchType" name="searchType" class="form-control input-sm">
							<option value="NM" ${searchVO.searchType eq "NM" ? "selected='selected'" : "" }>이름</option>
							<option value="ID" ${searchVO.searchType eq "ID" ? "selected='selected'" : "" }>아이디</option>
							<option value="HP" ${searchVO.searchType eq "HP" ? "selected='selected'" : "" } >핸드폰번호</option>
						</select>
					</div>
					<div class="col-sm-2">
						<input type="text" name="searchWord" class="form-control input-sm" value="${searchVO.searchWord }" placeholder="검색어">
					</div>
					<div>
						<label for="id_searchJob" class="col-sm-1 col-sm-offset control-label">직업</label>
						<div class="col-sm-2">
							<select id="id_searchJob" name="searchJob" class="form-control input-sm">
								<option value="">-- 전체 --</option>
								<c:forEach items="${jobList}" var="code">
									<option value="${code.commCd}" ${searchVO.searchJob eq code.commCd ? "selected='selected'" : "" }>${code.commNm}</option>
								</c:forEach>
							</select>
						</div>
						<label for="id_searchHobby" class="col-sm-1 col-sm-offset control-label">취미</label>
						<div class="col-sm-2">
							<select id="id_search" name="searchHobby" class="form-control input-sm">
								<option value="">-- 전체 --</option>
								<c:forEach items="${hobbyList}" var="code">
									<option value="${code.commCd}" ${searchVO.searchHobby eq code.commCd ? "selected='selected'" : "" }>${code.commNm}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 col-sm-offset-9 text-right">
						<button type="button" id="id_btn_reset" class="btn btn-sm btn-default">
							<i class="fa fa-sync"></i> &nbsp;&nbsp;초기화
						</button>
					</div>
					<div class="col-sm-1 text-right">
						<button type="submit" class="btn btn-sm btn-primary ">
							<i class="fa fa-search"></i> &nbsp;&nbsp;검 색
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- END : 검색 폼  -->	
	<!-- START : 목록건수 및 새글쓰기 버튼  -->
	<div class="row" style="margin-bottom: 10px;">
		<div class="col-sm-3 form-inline">
			전체 1000건 조회
			<select id="id_rowSizePerPage" name="rowSizePerPage" class="form-control input-sm">
				<c:forEach var="i" begin="10" end="50" step="10">
					<option value="${i }" ${i eq searchVO.rowSizePerPage  ? "selected='selected'" : "" }>${i }</option>
				</c:forEach>
			</select>
		</div>
	</div>
<!-- END : 목록건수 및 새글쓰기 버튼  -->
	<div>
		<a href="memberForm.jsp" class="btn btn-primary btn-sm pull-right">회원 등록</a>
	</div>
	<table class="table table-striped table-bordered">
	<caption class="hidden">회원목록 조회</caption>
	<colgroup>
		<col style="width: 15%" />
		<col />
		<col style="width: 20%" />
		<col style="width: 20%" />
		<col style="width: 15%" />
		<col style="width: 15%" />
	</colgroup>
	<thead>
		<tr class="t">
			<th>ID</th>
			<th>회원명</th>
			<th>HP</th>
			<th>생일</th>
			<th>직업</th>
			<th>취미</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${memberList }" var="list" varStatus="status">
			<tr class="t">
				<td>${list.memId }</td>
				<td><a href="memberView.jsp?memId=${list.memId }">${list.memName }</a></td>
				<td>${list.memHp }</td>
				<td>${list.memBir }</td>
				<td>${list.memJobNm }</td>
				<td>${list.memHobbyNm }</td>
				<td>${list.memMileage } point</td>
			</tr>
		</c:forEach>
	</tbody>			
	</table>
	<!-- START : 페이지네이션  -->
		<nav class="text-center">
			<ul class="pagination">

			<!-- 첫 페이지  -->
			<li><a href="memberList.jsp?curPage=1" data-page="1"><span aria-hidden="true">&laquo;</span></a></li>


			<!-- 이전 페이지 -->
			<c:if test="${searchVO.curPage>=2 }">
				<li><a href="memberList.jsp?curPage=${searchVO.curPage-1 }" data-page="${searchVO.curPage-1 }"><span aria-hidden="true">&lt;</span></a></li>
			</c:if>

			<!-- 페이지 넘버링  -->
			<c:forEach begin="${searchVO.firstPage }" end="${searchVO.lastPage }" var="i">
			<c:if test="${searchVO.curPage!=i }">
			<li><a href="memberList.jsp?curPage=${i }" data-page="${i }">${i }</a></li>
			</c:if>
			<c:if test="${i==searchVO.curPage }">
			<li class="active"><a href="memberList.jsp">${i }</a></li>
			</c:if>
			</c:forEach>

			<!-- 다음  페이지  -->
			<c:if test="${searchVO.curPage<=searchVO.totalPageCount-1 }">
				<li><a href="memberList.jsp?curPage=${searchVO.curPage+1 }" data-page="${searchVO.curPage+1 }"><span aria-hidden="true">&gt;</span></a></li>
			</c:if>

			<!-- 마지막 페이지 -->
			<li><a href="memberList.jsp?curPage=${searchVO.totalPageCount }" data-page="${searchVO.totalPageCount }"><span aria-hidden="true">&raquo;</span></a></li>
		</ul>
		</nav>
		<!-- END : 페이지네이션  -->
		<script type="text/javascript">
	// 변수 정의
	$form=$("form[name='search']");
	$curPage=$form.find("input[name='curPage']");
	
	// 각 이벤트 등록 a태그 1,2,3,4,5,6,7,8,9
	// 페이지 링크 클릭, event전파방지, data속성값읽고 form태그 내의 input name=curPage값 바꾸기
	//submit
	$('ul.pagination li a[data-page]').click(function(e) {
		// a태그 기본이벤트 대신 form태그 submit하면됨
		//$('selector').data('page');
		e.preventDefault();
		let page= $(this).data("page");
		//let href= $(this).attr("href");
		//console.log("page : "+page);
		//console.log("href : "+href);
		//$("input[name='curPage']").val(a);
		$curPage.val(page);
		//$(".form-horizontal").submit();
		$form.submit();
		
	}); // ul.pagination li a[data-page]
	
	//form태그내의 버튼 클릭
	//이벤트전파방지, curPage 값 1로
	//submit
	$form.find("button[type=submit]").click(function(e) {
		e.preventDefault();
		$curPage.val(1);
		$form.submit();
	});
	
	
	
	// 목록 갯수 변경
	// id_rowSizePerPage 변경되었을 때
	// 페이지 1, 목록수 = 현재값 으로 변경 후 서브밋
	$('#id_rowSizePerPage').change(function(e) {
		let rsp= $("#id_rowSizePerPage option:selected").val();
		$("input[name='rowSizePerPage']").val(rsp);
// 		let rsp2= $("input[name='rowSizePerPage']").val();
// 		$("#id_rowSizePerPage option").val(rsp2);
		$form.submit();
	}); // '#id_rowSizePerPage'.change
	// 초기화 버튼 클릭
	$('#id_btn_reset').click(function() {
		$curPage.val(1);
		$("input[name='searchWord']").val("");
		$("#id_searchJob").val("");
		$("#id_search").val("");
		$("#id_searchType").val("");
		$form.submit();
	}); // #id_btn_reset.click

</script>
</div>

</body>
</html>