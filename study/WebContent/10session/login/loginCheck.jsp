<%@page import="com.study.common.util.CookieUtils"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.study.login.vo.UserVO"%>
<%@page import="com.study.common.util.UserList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/inc/header.jsp"%>
<title></title>
</head>
<body>
	<!-- <a href="#" class="btn btn-default" onclick="history.go(-1)">뒤로가기</a>
아이디틀렸을때  -->


	<%
		String id = request.getParameter("userId");
		String pw = request.getParameter("userPass");
		String save_id = request.getParameter("rememberMe");
		if (save_id == null) {
			CookieUtils cookieUtils = new CookieUtils(request);
			if (cookieUtils.exists("SAVE_ID")) {
				Cookie cookie = CookieUtils.createCookie("SAVE_ID", id, "/", 0);
				response.addCookie(cookie);
			}
			save_id = "";
		}

		if ((id == null || id.isEmpty()) || (pw == null || pw.isEmpty())) {
			response.sendRedirect("login.jsp?msg=" + URLEncoder.encode("입력안했어요", "utf-8"));
		} else {

			UserList userList = new UserList();
			UserVO user = userList.getUser(id);

			if (user == null) {
				response.sendRedirect("login.jsp?msg=" + URLEncoder.encode("아이디 또는 비번확인", "utf-8"));
			} else { //id맞았을때 
				if (user.getUserPw().equals(pw)) {//다 맞는경우
					if (save_id.equals("Y")) {
						response.addCookie(CookieUtils.createCookie("SAVE_ID", id, "/", 3600 * 24 * 7));
					}
					//response.addCookie(CookieUtils.createCookie("AUTH", id));
					session.setAttribute("USER_INFO", new UserVO(id,user.getUserName(),pw,"MEMBER"));
					response.sendRedirect("login.jsp");
				} else {//  비번만 틀린경우
					response.sendRedirect("login.jsp?msg=" + URLEncoder.encode("아이디 또는 비번확인", "utf-8"));
				}

			}
		}
	%>





</body>
</html>