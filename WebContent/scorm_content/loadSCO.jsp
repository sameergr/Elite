<%@ page import="com.innverse.elearn.util.TokenGenerator" %>
<%@page import="com.innverse.elearn.util.Base64Util"%>
<%@page import="com.innverse.elearn.util.UrlEncodedQueryString"%>
<%@page import="java.net.URI"%>
<%

	String endcodedCourseMetadata = request.getParameter("cmd");

/* 
	String courseMetada = Base64Util.decode(endcodedCourseMetadata);
	
	URI uri = new URI("http://abc.com?"+courseMetada);
	UrlEncodedQueryString queryString = UrlEncodedQueryString.parse(uri);
	
	String courseId = queryString.get("courseId");
	String memberId = queryString.get("memberId");
	String organizationId = queryString.get("organizationId");
	
 	TokenGenerator tg = new TokenGenerator();
	String sessKey = tg.generateUniqueKey();
 */
	String LMS_api = "API";
// 	String location = "http://abc.elite.com:8080/ELMS/resources/scorm/f35b3a0e-0efc-4286-85c0-1a096cddab04Credits Tutorial AICC complete incomplete/index_lms.html";
 	String location = request.getContextPath()+"/resources/scorm/"+request.getAttribute("fileName")+"/"+request.getAttribute("lauch");
 	String aicc_url = request.getContextPath()+"/scorm/aicc";

%>

 
<html>
<head>
    <title><?php echo $scorm_title; ?></title>
    <script type="text/javascript">
        //<![CDATA[
        var apiHandle = null;
        var findAPITries = 0;

        function getAPIHandle() {
           if (apiHandle == null) {
              apiHandle = getAPI();
           }
           return apiHandle;
        }

        function findAPI(win) {
           while ((win.<%=LMS_api%> == null) && (win.parent != null) && (win.parent != win)) {
              findAPITries++;
              // Note: 7 is an arbitrary number, but should be more than sufficient
              if (findAPITries > 7) {
                 return null;
              }
              win = win.parent;
           }
           return win.<%=LMS_api%>;
        }

        // hun for the API - needs to be loaded before we can launch the package
        function getAPI() {
           var theAPI = findAPI(window);
           if ((theAPI == null) && (window.opener != null) && (typeof(window.opener) != "undefined")) {
              theAPI = findAPI(window.opener);
           }
           if (theAPI == null) {
              return null;
           }
  //          alert(theAPI);
           return theAPI;
        }

        function doredirect() {
            if (getAPI() != null) {
    //            alert("API----Yes");
                location = "<%=location%>?aicc_sid=<%=endcodedCourseMetadata%>&aicc_url=<%=aicc_url%>";
            }
            else {
          //      alert("API----NO");
                document.body.innerHTML = "<p>You will be automatically redirected to the activity in <span id='countdown'>2</span>  seconds. &nbsp; <img src='wait.gif'><p>";
                var e = document.getElementById("countdown");
                var cSeconds = parseInt(e.innerHTML);
                var timer = setInterval(function() {
                    if( cSeconds && getAPI() == null ) {
                        e.innerHTML = --cSeconds;
                    } else {
                        clearInterval(timer);
                        document.body.innerHTML = "<p>Activity loading, please wait ....</p>";
                        location = "<%=location%>?aicc_sid=<%=endcodedCourseMetadata%>&aicc_url=<%=aicc_url%>";
                    }
                }, 1000);
            }
        }
        //]]>
        </script>
</head>

<body onload="doredirect();">

<p>Activity loading, please wait ....</p>


</body>

</html>

