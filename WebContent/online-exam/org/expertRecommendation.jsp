<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="include/header1.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/online-exam/js/jquery-1.9.1.js"></script>
<script src="${pageContext.request.contextPath}/online-exam/js/jquery.ui.core.js"></script>
<script src="${pageContext.request.contextPath}/online-exam/js/jquery.ui.widget.js"></script>
<script src="${pageContext.request.contextPath}/online-exam/js/jquery.ui.tabs.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/online-exam/css/jquery.ui.all.css">

<script type="text/javascript">
$(function(){
	 $('#hide').hide();
});
</script>
<script type="text/javascript">
$(document).ready(function() {
    $('#show').click(function() {
            $('#hide').slideToggle("slow");
    });
});
</script>
	<div class="container" id="main-content">
		<section>
		<p>${message}</p>
			<div>
			<h3>${err}</h3>
				<div class="form-blk">
						<h1>Blog</h1>
		
						<form:form action="${pageContext.request.contextPath}/${userType}/blogSave"  modelAttribute="blogSave">
						<c:forEach items="${blogDetail}" var="blogDetail">
						<div style="border-top: 5px solid orange;box-shadow: 10px 10px 5px #888888;">
						<div style="background-color: #E5FBF9;" ><table width="100%"><tr><td><b>User name:&nbsp;&nbsp;&nbsp;</b>${blogDetail.username.username}</td><td align="right"><b>Created Time:&nbsp;&nbsp;&nbsp;</b>${blogDetail.createdTime}</td></tr></table></div>
							<table  width="100%" border="0" cellspacing="10" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse;border: 1px gray solid;" >
								
									<%-- <tr><td>User name:${blogDetail.username.username}</td><td align="right">Created Time:${blogDetail.createdTime}</td></tr> --%>
									<tr>
										<td colspan="2"><b>Subject:&nbsp;&nbsp;&nbsp;</b>${blogDetail.subject}</td>
										
									</tr>
									<tr><td colspan="2"><b>Content:&nbsp;&nbsp;&nbsp;</b>${blogDetail.content}</td></tr>
							</table>
							</div></br>
							</c:forEach>
        				
						</br>
						<input id="show" type="button" value="Create blog"/>
						<div style="border-left: 5px solid orange;box-shadow: 10px 10px 5px #888888;" class="form-item" id="hide">
		                   <div class="form-item">
		                        <div class="form-label">Subject</div>
		                        <div class="form-value">
		                            <input type="text" value="" name="subject" id="subject">
		                        </div>                    
		                    </div>
		                    <div class="form-item">
		                        <div class="form-label">Content</div>
		                        <div class="form-value">
		                            <textarea rows="5" name="content" id="content"></textarea>
		                        </div>                    
		                    </div>
		                     <div class="form-actions">
		                        <input type="submit" value="Save" class="right btnstyle-2"/>
		                    </div>
	                    </div>
	                    </form:form>
	</div>
</div>
		
		</section>
	</div>
		
		

<jsp:include page="include/footer.jsp"></jsp:include>