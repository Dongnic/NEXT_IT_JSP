<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page autoFlush="false" buffer="8kb"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- out이랑 관계있습니다 --> 
<!-- (브라우저에게 전달할)데이터를 임시저장 데이터=html태그 인 문자열 -->
8kb 8 * 1024 byte
		한글 2,3 byte , 영어 1byte
			<%-- <html>태그부터 </html>까지 8000byte가 보통 안됨
			넘어도 autoFlush()가 해주기때문에 괜찮다 --%>
<%
	long startTime = System.nanoTime();
	for(int i=0; i<1400; i++){
		out.print("코딩 좋아");
		// out.flush(); // 버퍼에 있는 값을 브라우저에 전달
		// out.clear(); // 버퍼에 있는 값을 비움	
	}
	long endTime = System.nanoTime();
	out.print(endTime-startTime); //214011
%>

</body>
</html>