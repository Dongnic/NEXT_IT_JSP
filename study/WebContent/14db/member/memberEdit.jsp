
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
	<%@ include file="/WEB-INF/inc/header.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/inc/top.jsp"%>
<%
	String memId= request.getParameter("memId");
	Connection conn= null;
	PreparedStatement pstmt= null;
	ResultSet rs= null;
	try{		
		conn= DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
		StringBuffer sb= new StringBuffer();
		sb.append(" SELECT					");
		sb.append(" *						");
		sb.append(" FROM					");
		sb.append("     member				");
		sb.append(" WHERE 1=1	 			");
		sb.append(" AND   mem_id= ?	        ");
		pstmt = conn.prepareStatement(sb.toString());
		int idx = 1;
		pstmt.setString(idx++, memId);
		rs = pstmt.executeQuery(); 
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
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
		if(rs!=null)  { try{rs.close();   }catch(Exception e){}}
		if(pstmt!=null)  { try{pstmt.close();   }catch(Exception e){}}
		if(conn!=null){ try{conn.close(); }catch(Exception e){}}
	}
	
%>


	
		<div class="alert alert-warning">?????? ????????? ?????? ??? ????????????</div>
		<a href="memberList.jsp" class="btn btn-default btn-sm"> <span class="glyphicon glyphicon-list" aria-hidden="true"></span> &nbsp;??????
		</a>



 <div class="container">	
	<h3>?????? ?????? ??????</h3>	
	<form action="memberModify.jsp" method="post" >
	<table class="table table-striped table-bordered">
		<tbody>
			<tr>
				<th>?????????</th>
				<td>${memberL.memId }<input type="hidden" name="memId" value="${memberL.memId }"></td>
			</tr>
			<tr>
				<th>????????????</th>
				<td><input type="password" name="memPass" class="form-control input-sm" 
						    pattern="\w{4,}" title="???????????? ????????? 4?????? ?????? ??????" ></td>
			</tr>
			<tr>
				<th>?????????</th>
				<td><input type="text" name="memName" class="form-control input-sm" value="${memberL.memName }"
						   required="required" pattern="[???-???]{2,}" title="????????? 2?????? ?????? ??????" ></td>
			</tr>
			<tr>
				<th>????????????</th>
				<td><input type="text" name="memZip" class="form-control input-sm" value='${memberL.memZip }'></td>
			</tr>
			<tr>
				<th>??????</th>
				<td><input type="text" name="memAdd1" class="form-control input-sm" value='${memberL.memAdd1 }'>
					<input type="text" name="memAdd2" class="form-control input-sm" value='${memberL.memAdd2 }'>
				</td>
			</tr>
			<tr>
				<th>??????</th>
				<fmt:parseDate value="${memberL.memBir }" pattern="yyyy-MM-dd" var="date_date"/>
				<fmt:formatDate value="${date_date}" pattern="yyyy-MM-dd" var="str_date"/>
				<td><input type="date" name="memBir" class="form-control input-sm" value='${str_date }'></td>
			</tr>
			<tr>
				<th>??????</th>
				<td><input type="email" name="memMail" class="form-control input-sm" required="required" value='${memberL.memMail }'></td>
			</tr>
			<tr>
				<th>?????????</th>
				<td><input type="tel" name="memHp" class="form-control input-sm" value='${memberL.memHp }'></td>
			</tr>
			<tr>
				<th>??????</th>
				<td>
				
					<select name="memJob" class="form-control input-sm" >
						<option value="">-- ?????? ?????? --</option>
						<option value="JB01" ${memberL.memJob eq "JB01" ? "selected='selected'" : "" }>??????</option>
						<option value="JB02" ${memberL.memJob eq "JB02" ? "selected='selected'" : "" }>?????????</option>
						<option value="JB03" ${memberL.memJob eq "JB03" ? "selected='selected'" : "" }>?????????</option>
						<option value="JB04" ${memberL.memJob eq "JB04" ? "selected='selected'" : "" }>?????????</option>
						<option value="JB05" ${memberL.memJob eq "JB05" ? "selected='selected'" : "" }>?????????</option>
						<option value="JB06" ${memberL.memJob eq "JB06" ? "selected='selected'" : "" }>??????</option>
						<option value="JB07" ${memberL.memJob eq "JB07" ? "selected='selected'" : "" }>?????????</option>
						<option value="JB08" ${memberL.memJob eq "JB08" ? "selected='selected'" : "" }>??????</option>
						<option value="JB09" ${memberL.memJob eq "JB09" ? "selected='selected'" : "" }>??????</option>					
					</select>			
				</td>
			</tr>
			<tr>
				<th>??????</th>
				<td>
				
					<select name="memHobby" class="form-control input-sm" >
						<option value="">-- ?????? ?????? --</option>
						<option value="HB01" ${memberL.memHobby eq "HB01" ? "selected='selected'" : "" }>??????</option>
						<option value="HB02" ${memberL.memHobby eq "HB02" ? "selected='selected'" : "" }>??????</option>
						<option value="HB03" ${memberL.memHobby eq "HB03" ? "selected='selected'" : "" }>??????</option>
						<option value="HB04" ${memberL.memHobby eq "HB04" ? "selected='selected'" : "" }>??????</option>
						<option value="HB05" ${memberL.memHobby eq "HB05" ? "selected='selected'" : "" }>??????</option>
						<option value="HB06" ${memberL.memHobby eq "HB06" ? "selected='selected'" : "" }>??????</option>
						<option value="HB07" ${memberL.memHobby eq "HB07" ? "selected='selected'" : "" }>??????</option>
						<option value="HB08" ${memberL.memHobby eq "HB08" ? "selected='selected'" : "" }>??????</option>
						<option value="HB09" ${memberL.memHobby eq "HB09" ? "selected='selected'" : "" }>??????</option>
						<option value="HB10" ${memberL.memHobby eq "HB10" ? "selected='selected'" : "" }>??????</option>
						<option value="HB11" ${memberL.memHobby eq "HB11" ? "selected='selected'" : "" }>????????????</option>
						<option value="HB12" ${memberL.memHobby eq "HB12" ? "selected='selected'" : "" }>??????</option>
						<option value="HB13" ${memberL.memHobby eq "HB13" ? "selected='selected'" : "" }>??????</option>
						<option value="HB14" ${memberL.memHobby eq "HB14" ? "selected='selected'" : "" }>????????????</option>					
					</select>			
				</td>
			</tr>	
			<tr>
				<th>????????????</th>
				<td>${memberL.memMileage }</td>
			</tr>	
			<tr>
				<th>????????????</th>
				<td>${memberL.memDelYn }</td>
			</tr>	
			<tr>
				<td colspan="2">
					<a href="memberList.jsp" class="btn btn-info btn-sm">
					<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
					&nbsp;????????????
					</a>
					<button type="submit" class="btn btn-primary">
					<span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
					&nbsp;&nbsp; ??????
					</button>
					
						<button type="submit" formaction="memberDelete.jsp" class="btn btn-danger btn-sm">
					<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
					&nbsp;&nbsp; ??????
					</button>
					
					
				</td>
			</tr>
		</tbody>	
	</table>
	</form>
</div>


</body>
</html>