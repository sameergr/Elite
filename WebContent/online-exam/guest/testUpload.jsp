<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form enctype="multipart/form-data" action="${pageContext.request.contextPath}/org/saveUpload" modelAttribute="fileUpload" name="upload">
	<input type="file" value="Upload" name="file"/>
	<input type="button" value="Submit" onclick="upload.submit();"/>
</form:form>
</body>
</html>