<%@page import="java.io.Console"%>
<%@page import="com.sun.xml.internal.txw2.Document"%>
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
<%@include file="/WEB-INF/inc/header.jsp" %>
<title></title>
</head>
<body>
<%
	String userId= request.getParameter("userId");
	String userPass= request.getParameter("userPass");
	CookieUtils cookieUtils = new CookieUtils(request);
	String[] remember = request.getParameterValues("rememberMe");
	
// 		if("Y".equals(remember[0]) && remember[0] !=null){
// 			Cookie rem= new Cookie("SAVE_ID", userId);
// 			rem = CookieUtils.createCookie("SAVE_ID", userId, 60*60*24*7);
// 			response.addCookie(rem);
// 		}else{
// 			   Cookie cookie = CookieUtils.createCookie("SAVE_ID","", 0);
// 			   response.addCookie(cookie);
// 		}
	if(userId.isEmpty() || userPass.isEmpty()){
		response.sendRedirect("login.jsp?msg=Enter your ID or password");
	}else{
		UserList userList= new UserList();
		UserVO user= userList.getUser(userId);
		if(user==null){
			// id 틀림 
			response.sendRedirect("login.jsp?msg=ID or password does not match");
		}else{
			// id 맞음
			if(user.getUserPw().equals(userPass)){
				// 로그인 성공
				
				// 기억하기 체크
// 				if(request.getParameter("rememberMe") != null){
// 					Cookie rem= new Cookie("SAVE_ID", userId);
// 					rem = CookieUtils.createCookie("SAVE_ID", userId, 60*60*24*7);
// 					response.addCookie(rem);
// 				}else{
// 					   Cookie cookie = CookieUtils.createCookie("SAVE_ID","", 0);
// 					   response.addCookie(cookie);
// 				} 
				if(null!=remember && "Y".equals(remember[0])){
					Cookie rem= new Cookie("SAVE_ID", userId);
					rem = CookieUtils.createCookie("SAVE_ID", userId, 60*60*24*7);
					response.addCookie(rem);
				}else{
					   Cookie rem = CookieUtils.createCookie("SAVE_ID","", 0);
					   response.addCookie(rem);
				}
				Cookie cookie= new Cookie("AUTH", userId);
				response.addCookie(cookie);
				response.sendRedirect("login.jsp");
			}else{
				response.sendRedirect("login.jsp?msg=ID or password does not match");
			}
		}
	}
%>


	
	
</body>
</html>