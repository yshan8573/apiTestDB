<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		var text11 = "";
			$.ajax({          
	            url: '<%=contextPath%>/PublicData.do',
				type : 'get',
				dataType : 'text',
				success : function(msg) {
					text11 = $('.data').text();
					$('.data').text(text11 + msg);

				}
			});
	})
</script>

</head>
<body>
	<div>데이터 출력</div>
	<br>
	<hr>
	<br>
	<div>
		<pre class="data"></pre>
	</div>
</body>
</html>