<%@page import="com.study.common.util.ProductList"%>
<%@page import="com.sun.scenario.effect.impl.prism.PrDrawable"%>
<%@page import="com.study.common.vo.ProdVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

html은 html태그+JS,CSS<br>
<%
	List<ProdVO> prodList= ProductList.getProductList();
	ProdVO prod= prodList.get(0);	
	request.setAttribute("prod", prod);
%>
<input type="text" name="prodId" value="<%=prod.getProdId() %>">
<button type="button" onclick="button()">버튼</button>

<script type="text/javascript">
	function button() {
		// HDD001이라는 값을 문자열로 받지 않았으므로 변수로 인식
		// 해당 변수가 없으므로 오류, 숫자라면 가능함.
		var prodId= <%=prod.getProdId() %>;
		var prodId= "<%=prod.getProdId() %>";
		// EL도 동일하게 사용
		var prodId= ${prod.prodId};
		var prodId= "${prod.prodId}";
		alert(prodId);
	}
	
	
</script>
</body>
</html>