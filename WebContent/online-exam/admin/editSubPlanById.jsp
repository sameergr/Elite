<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="../include/adminHeader.jsp"></jsp:include>

<script src="${pageContext.request.contextPath}/online-exam/css/jquery.ui.core.js"></script>
<script src="${pageContext.request.contextPath}/online-exam/css/jquery.ui.widget.js"></script>
<script src="${pageContext.request.contextPath}/online-exam/css/jquery.ui.accordion.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/online-exam/css/jquery.ui.all.css">
	<script>
	$(function() {
		$( "#accordion" ).accordion({
			collapsible: true,
			active: false
		});
	});
	</script>

	<div class="container" id="main-content">
		<section>			
			<div class="col spn_2_3" style="margin-left: 20%">			
					<div class="form-blk" >					
						<h1>Edit Subscription Plan</h1>						
							<form:form modelAttribute="editSubscription" action="${pageContext.request.contextPath}/admin/saveEditSubscription" name="editPlan">
								   <div class="form-item" >
								   		<div class="form-value">
								   			<input type="hidden" name="planId" value="${subplan.id}">
								   			<div>Member Type :<input type="text" value="${subplan.registrationType}" disabled="disabled" /></div>																
											<div>Plan Type :<input type="text" value="${subplan.planType}" disabled="disabled" /></div>
										</div>
								   </div>
								   
								   <div class="form-item" >
								   		<div class="form-value">
								   			<div>Subscription Type :<input type="text" value="${subplan.subscriptionType}" disabled="disabled" /></div>																
											<div>Author Name :<input type="text" value="${subplan.authorName}" disabled="disabled" /></div>
										</div>
								   </div>
																								
								   <div class="form-item" >
								   		<div class="form-value">
								   			<div>Plan Name :<input type="text" value="${subplan.planName}" disabled="disabled" /></div>																			
											<div>Plan's Amount :<input type="text" value="${subplan.amount}" disabled="disabled"/></div>
										</div>
								   </div>														
								   															
								   <div class="form-item">
										<div class="form-value">
											<div>Description :<input type="text" value="${subplan.planDescription}" disabled="disabled"/></div>																					
										</div>								
								   </div>
								   <div>
								   		<h3>Current Features :</h3>
								   			<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
		                 						<tr>
		                 							<th>#</th>
					                 				<th>Feature Name</th>
					                 				<th>Feature Type</th>
					                 				<th>Group Value</th>					                 				                 					                 
                 				 				</tr>
                 				   				<c:forEach var="plan" varStatus="status" items="${editSubscriptionForm.accessLevels}">
								 				<tr>
								 					<td>${status.index+1}</td>
													<td>
														<select name="accessLevels[${status.index}].featureid">
															<option value="${plan.featureid}">${plan.featureName}</option>
														</select>
													</td>
													<td>
														<select name="accessLevels[${status.index}].featureType">
															<option value="${plan.featureType}">${plan.featureType}</option>
														</select>
													</td>
													<td><input type="text" value="${plan.value}" name="accessLevels[${status.index}].value"></td>																	
	 							 				</tr>
				 				   				</c:forEach>
               								</table>                									
								   </div>
								   
								   <div id="accordion">
									<h3><p style="margin: -18px 0 2px 25px;">Add More Features</p></h3>
									<div>
									<c:choose>
										<c:when test='${ empty editSubscriptionForm.availableFeatures }'>
											<h1>${err}</h1>
										</c:when>
										
										<c:otherwise>
										<h3>New Features :</h3>
								   			<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
		                 						<tr>
		                 							<th>#</th>
		                 							<th>Add Features</th>
					                 				<th>Feature Name</th>
					                 				<th>Feature Description</th>
					                 				<th>Feature View Type</th>					                 				                 					                 
					                 				<th>Feature Type</th>
                 				 				</tr>
                 				   					<c:forEach var="features" varStatus="innerloop" items="${editSubscriptionForm.availableFeatures}">
								 				<tr>
								 					<td>${innerloop.index+1}</td>
								 					<td><input type="checkbox" name="availableFeatures[${innerloop.index}].featureid" value="${features.featureid}"/></td>
													<td>${features.featureName}</td>
													<td>${features.description}</td>
													<td>${features.viewType}</td>
													<td>
														<form:select path="availableFeatures[${innerloop.index}].featureType">
															<form:options items="${editSubscriptionForm.featureType}" />
														</form:select>
													</td>
	 							 				</tr>
				 				   					</c:forEach>
               								</table>  
               								</c:otherwise> 
										</c:choose>
									</div>
							</div>
								   <input type="button" onclick="editPlan.submit();"/>
								   							   								   						
							</form:form>															
					</div>
				 </div>						 
			</section>
	</div>
	</body>

<jsp:include page="../include/footer.jsp"></jsp:include>