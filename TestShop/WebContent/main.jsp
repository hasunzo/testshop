<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String id = (String)session.getAttribute("id");
	
		if(id!=null){
			out.print(id+"님 안녕하세요!<br>");
			if(id.equals("admin")){
				out.print("<a href='#'>회원관리</a>");
			}
		}else{
			out.print("<a href='memberForm.html'>회원가입</a>");
		}
	%>
</body>
</html>