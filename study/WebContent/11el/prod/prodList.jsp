<%@page import="java.text.DecimalFormat"%>
<%@page import="com.study.common.util.ProductList"%>
<%@page import="com.study.common.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/inc/header.jsp" %>
<title>제품 목록</title>
<style>
.prod-list tbody td {border: 1px dashed;}
.prod-list tbody ul {padding-left: 20px;}
.prod-list tbody ul li {list-style: none; line-height: 1.4em;}

.prod-title a ,
.prod-title a:active,
.prod-title a:focus   {text-overflow:ellipsis; text-decoration: none; }

.prod-image {text-align: center;}
.prod-image img {
	 height: 150px; 
}
</style>
</head>
<body>
<div class="container">
<h3>제품 목록</h3>
<table class="prod-list">
	<caption class="hidden"><em>컴퓨터 제품 목록</em></caption>
	<colgroup>
		<col style="width: 33%;">
		<col style="width: 33%;">
		<col />
	</colgroup>
	
	<%
		List<ProdVO> prodList=ProductList.getProductList();
		DecimalFormat won = new DecimalFormat("###,### 원");
	%>
	<tbody>
		<%
			for(int i=0; i<prodList.size(); i++){
				if(i!=0 && i%3==0){out.print("</tr>");}	
				if(i%3==0){out.print("<tr>");}
		%>
			<td>
				<ul>
					<li class="prod-image"><a href="prodView.jsp?prodId=<%=prodList.get(i).getProdId()%>"><img alt="" src="/study/resources/images/prod/<%=prodList.get(i).getProdId()%>.jpg"></a>
					<li class="prod-title"><a href="prodView.jsp?prodId=<%=prodList.get(i).getProdId()%>"><%=prodList.get(i).getProdName()%></a>
					<li class="prod-price"><%=won.format(prodList.get(i).getProdPrice()) %>
					<li class="prod-reg-date"><%=prodList.get(i).getProdRegDate() %>
				</ul>
			</td>
		<%
			}
		%>
	</tbody>

</table>
</div>
</body>
</html>
