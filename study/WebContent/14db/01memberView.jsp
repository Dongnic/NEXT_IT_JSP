<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="/WEB-INF/inc/header.jsp" %>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>
<%
	String memId= request.getParameter("memId");
	Class.forName("oracle.jdbc.driver.OracleDriver"); 
	Connection conn= null;
	ResultSet rs= null;
	try{											
		conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jsp","oracle");
		StringBuffer sb= new StringBuffer();
		sb.append(" SELECT					");
		sb.append(" *						");
		sb.append(" FROM					");
		sb.append("     member				");
		sb.append(" WHERE 1=1	 			");
		sb.append(" AND   mem_id= ?	        ");
		PreparedStatement ps = conn.prepareStatement(sb.toString());
		int idx = 1;
		ps.setString(idx++, memId);
		rs = ps.executeQuery(); 
		if(rs.next()){
			MemberVO memberL= new MemberVO();
			memberL.setMemId(rs.getString("mem_id"));
			memberL.setMemPass(rs.getString("mem_pass"));
			memberL.setMemName(rs.getString("mem_name"));
			memberL.setMemBir(rs.getString("mem_bir"));
			memberL.setMemZip(rs.getString("mem_zip"));
			memberL.setMemAdd1(rs.getString("mem_add1"));
			memberL.setMemAdd2(rs.getString("mem_add2"));
			memberL.setMemHp(rs.getString("mem_hp"));
			memberL.setMemMail(rs.getString("mem_mail"));
			memberL.setMemJob(rs.getString("mem_job"));
			memberL.setMemHobby(rs.getString("mem_hobby"));
			memberL.setMemMileage(rs.getInt("mem_mileage"));
			memberL.setMemDelYn(rs.getString("mem_del_yn"));
			
			request.setAttribute("memberL", memberL);
		}
		if(ps!=null)  { try{ps.close();   }catch(Exception e){}}
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
		if(rs!=null)  { try{rs.close();   }catch(Exception e){}}
		if(conn!=null){ try{conn.close(); }catch(Exception e){}}
	}
	
%>
<table class="table table-striped">
	<tr>
		<td>회원아이디</td>
		<td>${memberL.memId }</td>
	</tr>
	<tr>
		<td>회원비밀번호</td>
		<td>${memberL.memPass }</td>
	</tr>
	<tr>
		<td>회원이름</td>
		<td>${memberL.memName }</td>
	</tr>
	<tr>
		<td>회원생년월일</td>
		<td>${memberL.memBir }</td>
	</tr>
	<tr>
		<td>회원우편번호</td>
		<td>${memberL.memZip }</td>
	</tr>
	<tr>
		<td>회원주소</td>
		<td>${memberL.memAdd1 }</td>
	</tr>
	<tr>
		<td>회원상세주소</td>
		<td>${memberL.memAdd2 }</td>
	</tr>
	<tr>
		<td>회원전화번호</td>
		<td>${memberL.memHp }</td>
	</tr>
	<tr>
		<td>회원이메일</td>
		<td>${memberL.memMail }</td>
	</tr>
	<tr>
		<td>회원직업</td>
		<td>${memberL.memJob }</td>
	</tr>
	<tr>
		<td>회원취미</td>
		<td>${memberL.memHobby }</td>
	</tr>
	<tr>
		<td>회원마일리지</td>
		<td>${memberL.memMileage }</td>
	</tr>
	<tr>
		<td>회원삭제여부</td>
		<td>${memberL.memDelYn }</td>
	</tr>

</table>


</body>
</html>