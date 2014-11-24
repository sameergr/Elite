<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../include/adminHeader.jsp"></jsp:include>
<head>
<meta charset="utf-8">
<title>jQuery UI Datepicker - Default functionality</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/online-exam/css/themes/jquery-ui.css">
<script>
$(function() {
$( "#datepicker" ).datepicker();
});
</script>
</head>
	<div class="container" id="main-content">
		<section>
			<div id="register" class="col spn_1_2">
				<h1>Subscription Plan</h1>
				
					<form:form   action="createSubPlan" modelAttribute="createSubPlan"  method="POST">
						<div class="form-blk">			                 
                   		  
	                    <div class="form-item">
	                         
	                         <div class="form-value">
		                         <table style="width: 100%">
		                         	<tr><td><h4>Plan Name</h4></td><td><h4>Registration Type</h4></td></tr>
			                         <tr>
				                         <td> <input type="text" name="planName"></td>
				                         <td> 
					                        <form:select path="registrationType">
											<form:options items="${plan.registrationType}" />
											</form:select>
										</td>
									</tr>
									<tr>
										<td><h4>Subscription Type</h4></td><td><h4>Plan Type</h4></td>
									</tr>
									<tr>
										<td>
											<form:select path="subscriptionType">
											<form:options items="${plan.subscriptionType}" />
											</form:select>
										</td>
										<td>
											<form:select path="planType">
											<form:options items="${plan.planType}" />
											</form:select>
										</td>
									</tr>
									<tr>
										<td><h4>Subscription Mode</h4></td>
										
										<td><h4>Set Default</h4></td>
									</tr>
									<tr>
										<td>
											<form:select path="subscriptionMode">
												<form:options items="${plan.subscriptionMode}" />
											</form:select>
										</td>
										<td>
											<table><tr>
											<c:choose>
												<c:when test="${empty view}">
											<td>
												<form:radiobutton path="checkDefault" value="True" />True</td>
											
											<td>
												<form:radiobutton path="checkDefault" value="False" />False</td>
												</c:when>
												
												<c:otherwise>
													<td><input type="hidden" name="checkDefault" value="false"/></td>
												</c:otherwise>
											</c:choose>
											</tr>
											</table>
											
											
										</td>
									</tr>
			                         <tr>
										<td><h4>Amount</h4></td><td><h4>Plan Discription</h4></td>
									</tr>
									<tr>
										<td>
											<input type="text" name="amount" >
										</td>
										<td>
											<input type="text" name="planDescription" >
											
										</td>
									</tr>
									 <tr>
										<td><h4>Expiry Date</h4></td>
									</tr>
									<tr>
										<td>
											<input type="text" name="expirydate" id="datepicker" readonly="readonly">
										</td>
									</tr>
			                     
		                         </table>
		                         <table style="width: 100%">
			                         <tr><td></td><td><h4>Select Feature</h4></td><td width="55%;"><h4>Feature Type </h4></td><td><h4>Group Value</h4></td></tr>
			                         <c:forEach items="${plan.accessLevels}" var="accesslevel"  varStatus="status">
			                         	<tr>
				                         	<td><form:checkbox path="accessLevels[${status.index}].featureid" value="${accesslevel.featureid}" name="accessLevels[${status.index}].featureid"/></td>
				                         	
				                         	<td>${accesslevel.featureName}</td>
				                         	
				                         	<td><form:select path="accessLevels[${status.index}].featureType">
													<form:options items="${plan.featureType}" /></form:select>
											</td>
											
				                         	<td><input type="text" name="accessLevels[${status.index}].value">
				                         	</td>
			                         	</tr>
			                         </c:forEach>
			                         <tr>
		                         		<td></td>
		                         		<td></td>
		                         		<td><input type="submit" value="Submit" class="right btnstyle-2"/></td>
										<td></td>
									</tr>
		                         </table>
	                   		 </div>
                   		 </div>
                   		 
                   		 <div class="form-actions">
	                      
	                    </div>
                   		
                   		 </div> 	
 				</form:form>
 				<a href="${pageContext.request.contextPath}/admin/listOfSubPlan"><h2 style="color: green;"><u>View All Subscription Plans</u></h2></a>
				</div>
			</div>
			<div class="col spn_1_2" >
			</div>
		</section>	
	</div>
<jsp:include page="../include/footer.jsp"></jsp:include>