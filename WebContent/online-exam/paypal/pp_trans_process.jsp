<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<META HTTP-EQUIV="refresh" CONTENT="2;URL=${URL}">
<title>Untitled Document</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/online-exam/css/loading.css" media="all" />
</head>

<body>
<div class="processing_box">
	<img src="${pageContext.request.contextPath}/online-exam/images/processing.gif">
    <p>${message} <br>
    Please do not click on the browser back button</p>
    
</div>
</body>

</html>
