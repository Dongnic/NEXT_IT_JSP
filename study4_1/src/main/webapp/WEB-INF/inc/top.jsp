<%-- <%@page import="com.study.login.vo.UserVO"%> --%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">areum home</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="<%=request.getContextPath()%>/about/aboutHome.wow">About</a></li>
            <li><a href="#contact">Contact</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">게시판 <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="${pageContext.request.contextPath }/free/freeList.wow">자유게시판</a></li>
                <li><a href="#">자료실</a></li>
                <li><a href="#">공지사항</a></li>
                 <li><a href="<%=request.getContextPath()%>/member/memberList.wow">회원목록</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
         	
         
	         <c:if test="${USER_INFO eq null }">
	           <li><a href="<%=request.getContextPath()%>/login/login.wow">로그인</a></li>
	            <li><a href="<%=request.getContextPath()%>/join/step1.wow">회원가입</a></li> 
	         </c:if>
            <c:if test="${USER_INFO ne null }">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">${USER_INFO.userName }님 <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="<%=request.getContextPath()%>/mypage/info.wow">
	                <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
	                &nbsp;&nbsp;My page
	                </a></li>
                <li><a href="#">
                	<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                	&nbsp;&nbsp;비밀번호 변경
                	</a></li>
                <li><a href="#">
                	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
                	&nbsp;&nbsp;1:1 문의게시판
                	</a></li>
                <li class="divider"></li>
                <li><a href="<%=request.getContextPath()%>/login/logout.wow">
                	<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                	&nbsp;&nbsp;로그아웃
                	</a></li>
              </ul>
            </li>
         </c:if>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>		 		



<br><br><br><br><br><br>



		
 