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

<script>
	$(function() {
		$( "#tabs" ).tabs();
	});
</script>

<script>

     function loadFeatures(role)
     {	
    	 var Id = $("#moid").val();
     	 var xmlhttp=null;
     	 if (window.XMLHttpRequest)
     	   {// code for IE7+, Firefox, Chrome, Opera, Safari
     	   xmlhttp=new XMLHttpRequest();
     	   }
     	 else if(window.ActiveXObject)
     	   {// code for IE6, IE5
     	   xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
     	   }
     	 document.getElementById("featureTable").innerHTML='<img border="0" src="${pageContext.request.contextPath}/online-exam/images/loading.gif">'; 
     	 
     	 xmlhttp.onreadystatechange=function()
     	  {  
     	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
     	    {  
     		    document.getElementById("featureTable").innerHTML=xmlhttp.responseText;
     	    }
     	  } 
     		xmlhttp.open("post","${pageContext.request.contextPath}/org/loadPermisssions/"+Id+";role="+role,true);
     		xmlhttp.send(Id);
     } 
</script>


	<div class="container" id="main-content">
		<section>
		<p>${message}</p>
			<div>
			<h3>${err}</h3>
				<div class="form-blk">
						<h1>Member's Activity</h1>
		
		<div id="tabs">
			<ul>
				<li><a href="#tabs-1">Permissions</a></li>
				<li><a href="#tabs-2">Scoreboard</a></li>
				<li><a href="#tabs-3">Courses</a></li>
				<li><a href="#tabs-4">Subscription List of Media Center</a></li>
			</ul>
			<div id="tabs-1">
				<input type="hidden" name="moid" id="moid" value="${member.organization.id}"/>
				<p>Choose Role : <select name="organizationFAL" onchange="loadFeatures(this.value);">
					<option value="0">Choose UserType</option>
					<c:set var="memberOrganization" value="${member.organization}"></c:set>
					<c:forEach var="orgFAL" varStatus="loop" items="${memberOrganization.memberFALPermissions}">
						<option value="${orgFAL.memberRole.code}">${memberOrganization.id} - ${orgFAL.memberRole}</option>
					</c:forEach>
				</select>
				</p>
				<a href="${pageContext.request.contextPath}/${userType}/newRole?orgId=${member.organization.id}" class="button">Create New Role</a>
				<div id="featureTable" style="margin-top: 30px;">
				
				</div>
				
			</div>
			<div id="tabs-2">
				<div>
					 <table  width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
						<tr>
							<th>Series</th>
							<th>Level</th>
							<th>Score</th>
							<th>User</th>
							<th>TryOrHits</th>
							<th>Member</th>
							</tr>
						<c:forEach items="${boards}" var="boards" varStatus="boardsIndex">
						<tr>
							<td>${boards.seriesId}</td>
							<td>${boards.levelId}</td>
							<td>${boards.score}</td>
							<td>${boards.username}</td>
							<td>${boards.totalTryOrHits}</td>
							<td>${boards.memberId}</td></tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<div id="tabs-3">
				<div>
					 <table  width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
						<tr>
							<th>CourseId</th>
							<th>MemberId</th>
							<th>OrganizationId</th>
							<th>PercentComplete</th>
							<th>Score</th>
							<th>Status</th>
							<th>TryOrHits</th>
							</tr>
						<c:forEach items="${scoreBoardCourse}" var="scoreBoardCourse" varStatus="scoreBoardCourseIndex">
						<tr>
							<td>${scoreBoardCourse.courseId}</td>
							<td>${scoreBoardCourse.memberId}</td>
							<td>${scoreBoardCourse.organizationId}</td>
							<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${scoreBoardCourse.percentComplete}" />%</td>
							<td>${scoreBoardCourse.score}</td>
							<td>${scoreBoardCourse.status}</td>
							<td>${scoreBoardCourse.totalTryOrHits}</td>
						</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			
			<div id="tabs-4">
				<p>Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci
				 lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales.
				  Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec,
				   luctus a, lacus.</p>
				
			</div>
			
		</div>
	</div>
</div>
		
		</section>
	</div>
		
		
		
		
			<!-- <div>
				<div class="form-blk">
						<h1>Member Activity</h1>
						 -->
						
						
						
						
						
						
						<%-- <form:form name="editProfile" modelAttribute="confirmPlan" action="buyPlan">								
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">
									<div>Email Id :<input type="text" name="email" value="${member.account.email}" disabled="disabled"/> <input type="hidden" name="userEmail" value="${userDetails.email}"/></div>
									<div>Name :<input type="text" name="firstName" value="${member.account.firstName}" disabled="disabled"/></div>
																		
								</div>
							</div>
							
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value">
									<div>Current Subscribe Plan :
											<input type="text" disabled="disabled" value="${member.account.organization.subscription.subscriptionPlan.planName}" id="currentPlan" name="currentPlan" />
									</div>
									<div>Plan Description :
											<input type="text" disabled="disabled" value="${member.account.organization.subscription.subscriptionPlan.planDescription}" />
									</div>
								</div>
							</div>
						
	
							
							
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">									
									<div>First Name :<input type="text" name="firstName" value="${member.account.firstName}" disabled="disabled"/></div>
									<div>Last Name :<input type="text" name="lastName" value="${member.account.lastName}" disabled="disabled"/></div>
								</div>
							</div>							
							
						</form:form> --%>
		
		
		
			
	

<jsp:include page="include/footer.jsp"></jsp:include>