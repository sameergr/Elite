<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="include/header1.jsp"></jsp:include>
<%-- <script src="${pageContext.request.contextPath}/online-exam/js/jquery-1.9.1.js"></script> --%>
<script src="${pageContext.request.contextPath}/online-exam/css/jquery.ui.core.js"></script>
<script src="${pageContext.request.contextPath}/online-exam/css/jquery.ui.widget.js"></script>
<script src="${pageContext.request.contextPath}/online-exam/css/jquery.ui.accordion.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/online-exam/css/jquery.ui.all.css">


<!-- <script>
var indices = [];
var sum = 0;
$(document).ready(function(){
	alert("!!!!");
	
	$('select#selectLimit option').each(function() {
        indices[sum] = $(this).val();
        sum++;
    });
	
});

function submitData() {
	alert("&&&&");
	alert(indices.length+"!!!!");
    $.ajax({
        type: "GET",
        url: "${pageContext.request.contextPath}/org/createRole?arr="+indices,
        data:"id="+indices,
        success: function(html) {
        	alert("Success"+html(ht));
        }
    });
}
</script> -->



	<div class="container" id="main-content">
		<section>
		<p style="color:green; display: none;"><c:out value="${err}"></c:out></p>
			<div class="table_views" >	
				<div class="form-blk" >			
						<h1>Create New Role</h1>
						
						<form:form name="createRole" modelAttribute="createNewRole" action="createRole">
						
						<h3>Account Details</h3>
							
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">
									<div>User Name :<input type="text" name="schoolName" value="${member.account.username}" disabled="disabled"/></div>
									<div>Phone Number :<input type="text" name="phone" value="${member.account.phone}" disabled="disabled"/></div>
								</div>
							</div>
							
							
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">									
									<div>First Name :<input type="text" name="firstName" value="${member.account.firstName}" disabled="disabled"/></div>
									<div>Last Name :<input type="text" name="lastName" value="${member.account.lastName}" disabled="disabled"/></div>
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
									<div>Choose MemberRole Type :
									<select name="memberRole">
										<c:forEach var="role" varStatus="loop" items="${memberRole}">
											<option>${role.message}</option>
										</c:forEach>
									</select>
									</div>
									
									<div>Member Role : <input type="text" name="roleName" placeholder="Enter Member Role Type" /></div>
								</div>
							</div>
						
						<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">
									<p style="font-weight: bold;">Feature Access Setup :</p>
									<table  width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
		                 				<tr>
		                   					<th>Select</th>
		                   					<th>Available Features</th>
		                   					<th>Allowed Limit</th>
		                   					<th>Value</th>
		                   					
		                   				</tr>
												<c:forEach var="accesslevel" varStatus="innerLoop" items="${plan.accessLevels}">
														<tr>
															<td><form:checkbox path="accessLevels[${innerLoop.index}].featureid" value="${accesslevel.featureid}" name="accessLevels[${status.index}].featureid"/></td>
															<td>${accesslevel.featureName}</td>
															<td><input name="accessLevels[${innerLoop.index}].value" type="text"/></td>
															<td><form:select path="accessLevels[${innerLoop.index}].featureType">
																	<form:options items="${plan.featureType}" />
																</form:select>
															</td>
														</tr>	
												</c:forEach>
									</table>
								</div>
						</div>
						
						<input type="button" value="Save Role" onclick="createRole.submit();"/>
							
						</form:form>
					</div>
			</div>
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>