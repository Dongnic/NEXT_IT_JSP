<%@page contentType="text/html; charset=utf-8" %>
<html>
<head>
<title> hello world</title>
</head>
<body>
localhost:8080/study/NewFile.jsp	
<!--study프로젝트의 Webcontents/NewFile.jsp -->

<%=new java.util.Date()%>부터 jsp,Spring 수업을 함께 합니다.
화이팅!

<%
 String ai = request.getParameter("ai");
    int num=0;
    if(ai==null){
    }else{
        num=Integer.parseInt(ai);
    }
    out.print(num);
%>
</body>
</html>
