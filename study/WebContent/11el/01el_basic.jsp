<%@page import="com.study.common.util.ProductList"%>
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

스크립트 언어 : 응용 소프트웨어 애플리케이션 언어를 도와주는 언어 

JSP : JAVA에서 out.print()힘들어서 나온거
EL : JSP에서 JAVA를 도와주기 위해 나온 언어
		JSP태그를 사용하다보면 태그의 value=""에 객체가 들어감
<%-- 		<jsp: forword page="<%=viewPage %>"></jsp:forward> --%>
		값을 넣을 때 스크립트릿 쓰기 힘들다
		
EL이 가장 많이 쓰이는 건 기본객체(application, request, response, session)
에 있는 속성을 출력하는 용도
EL은 단독으로 쓰이기보다는 JSTL과 함께 쓰임
EL 탐색 : request-session순
EL로 prod1이라고 썼을 때 request속성 전부 조사 -         prod1이 있나 조사 있으면 출력
 				 없으면 session의 속성 전부 조사 -	   prod1이 있나 조사 있으면 출력

				 기본객체 모두 prod1 속성이 없으면 출력안함<br>

결론 :<br> 1. 출력이 안된다, 값이 안나온다 => 기본객체 속성에 저장한 이름과 다름.<br>
	   2. 필드이름을 잘못썼을 때 (propertyNotFoundException)<br>
	   3. " " <br>
<br>
&#8251; EL이 우선순위가 높아서 주석이랑 쓸 때는 우선순위가 높은 <%-- ${} --%> 주석을 쓰자
<br>
<%
	List<ProdVO> prodList= ProductList.getProductList();
	ProdVO prod1= prodList.get(0);
	request.setAttribute("prodList", prodList);
	session.setAttribute("prod1", prod1);
%>
		
${prodList }		
<br>
${prod1 }
<br>
${prod1.prodId }
${prod1.prodName }
<hr>
${dsakdlasd } 6월 13일 <!-- 해당 내용이 없으므로 출력안하고 6월 13일만 출력(에러가 안남) -->
<br>
<%-- ${prod1.abc }	<!-- prod1 내에 abc가 없기때문에 에러가 남 --> --%>


</body>
</html>