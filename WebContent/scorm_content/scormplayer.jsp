<%--
//http://abc.elite.com:8080/ELMS/scorm_content/scormplayer.jsp?cmd=Y291cnNlSWQ9MSZtZW1iZXJJZD0xJm9yZ2FuaXphdGlvbklkPTE=
String qs = request.getQueryString();
--%>
<html>
<head>
	<title>Torch LMS eLearning Player</title>
        <script type="text/javascript" src="request.js"></script>
        <script type="text/javascript" src="api_torch.jsp"></script>
        <script type="text/javascript" src="rd.js"></script>
</head>
<frameset frameborder="0" framespacing="0" border="0" rows="1,*" cols="*" >
	<frame src="<%=request.getContextPath()%>/scorm/load/test" name="API" noresize>
	<frame src="<%=request.getContextPath()%>/scorm/load/sco?cmd=${cmd}" name="course">
</frameset>
</html>
<html>
<head>