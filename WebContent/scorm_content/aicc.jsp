<%--  
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@page import="com.innverse.elearn.util.Base64Util"%>
<%@page import="com.innverse.elearn.util.UrlEncodedQueryString"%>
<%@page import="java.net.URI"%>
<%
String sessKey = request.getParameter("session_id");

String courseMetada = Base64Util.decode(sessKey);

URI uri = new URI("http://abc.com?"+courseMetada);
UrlEncodedQueryString queryString = UrlEncodedQueryString.parse(uri);

String courseId = queryString.get("courseId");
String memberId = queryString.get("memberId");
String organizationId = queryString.get("organizationId");

String command = request.getParameter("command");
String aiccdata = request.getParameter("aicc_data");
if(command.equalsIgnoreCase("GETPARAM")){
%>
<c:out value="error=0"></c:out>
<c:out value="error_text=Successful"></c:out>
<c:out value="aicc_data=[Core]"></c:out>
<c:out value="Student_ID=1"></c:out>
<c:out value="Student_Name=Jagia, Sumit"></c:out>
<c:out value="Lesson_Location="></c:out>
<c:out value="Credit=credit"></c:out>
<c:out value="Lesson_Status=incomplete"></c:out>
<c:out value="Score="></c:out>
<c:out value="Time=0000:00:13"></c:out>
<c:out value="Lesson_Mode=normal"></c:out>
<c:out value="[Core_Lesson]"></c:out>
<c:out value="viewed=1|lastviewedslide=1|0#2##,11,1,1,1,1,1,1,1,1,1,1,1,1###-1"></c:out>

<c:out value="[Core_Vendor]"></c:out>

<c:out value="[Evaluation]"></c:out>
<c:out value="Course_ID = {Credits_Tutorial_SCORM_1_point_2_passed_failed_using_quiz_results_not_slide_progress}"></c:out>
<c:out value="[Student_Data]"></c:out>
<c:out value="Mastery_Score=80"></c:out>
<c:out value="Max_Time_Allowed="></c:out>
<c:out value="Time_Limit_Action="></c:out>
<%
}
%>
--%>
${result}