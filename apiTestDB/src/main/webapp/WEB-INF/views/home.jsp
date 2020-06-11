<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%
	String contextPath = request.getContextPath();
%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<br><hr><br>
<div>
	<button type="button" onclick="javascript:location.href='<%= contextPath %>/apiDBList'">data</button>
</div>

</body>
</html>
