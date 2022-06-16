<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
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

memlist에서는 id 이름 생일 직업 취미만 보여주기
이름을 a태그로 파라미터는 id로
view에서는 전부 보여주기

<%
	// 1. oracle 드라이버 로드 2. 실제 DB에 연결 3. 쿼리 수행 4. 연결종료
	// 
	// 1. 드라이버 로드
	Class.forName("oracle.jdbc.driver.OracleDriver"); // Class.forName 런타임동적로딩
	Connection conn= null;
	Statement stmt= null;
	ResultSet rs= null;
	try{											// 127.0.0.1 = localhost
		conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jsp","oracle");
		// 2. 연결
		stmt=conn.createStatement(); // 쿼리수행 객체
		// 3. 쿼리 수행
		StringBuffer sb= new StringBuffer();
		sb.append(" SELECT													");
		sb.append("       mem_id      , mem_pass        , mem_name			");
		sb.append("     , TO_CHAR(mem_bir, 'YYYY.MM.dd') as mem_bir      	");
		sb.append("     , mem_zip       , mem_add1      , mem_add2			");
		sb.append("     , mem_hp        , mem_mail      , mem_job			");
		sb.append("     , mem_hobby     , mem_mileage   , mem_del_yn		");
		sb.append(" FROM													");
		sb.append("     member												");
		rs = stmt.executeQuery(sb.toString()); // 쿼리수행, rs는 쿼리수행 결과 저장하는 객체
		List<MemberVO> memberList= new ArrayList<>();
		while(rs.next()){
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
			
			memberList.add(memberL);
		}
			request.setAttribute("memberList", memberList);
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
		// 4. 연결 종료
		if(rs!=null)  { try{rs.close();   }catch(Exception e){}}
		if(stmt!=null){ try{stmt.close(); }catch(Exception e){}}
		if(conn!=null){ try{conn.close(); }catch(Exception e){}}
	}
	
%>
<table class="table table-striped">
	<thead>
		<tr>
			<td>ID</td>
			<td>이름</td>
			<td>생일</td>
			<td>직업</td>
			<td>취미</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${memberList }" var="list" varStatus="status">
			<tr>
				<td>${list.memId }</td>
				<td><a href="01memberView.jsp?memId=${list.memId }">${list.memName }</a></td>
				<td>${list.memBir }</td>
				<td>${list.memJob }</td>
				<td>${list.memHobby }</td>
			</tr>
		</c:forEach>
	</tbody>

</table>

</body>
</html>