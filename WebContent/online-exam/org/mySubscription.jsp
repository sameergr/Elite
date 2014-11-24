<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>
<%-- <script src="${pageContext.request.contextPath}/online-exam/js/jquery-1.9.1.js"></script> --%>
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

     <script>
     function loadFeatures(Id)
     {
     	 var xmlhttp=null;
     	 if (window.XMLHttpRequest)
     	   {// code for IE7+, Firefox, Chrome, Opera, Safari
     	   xmlhttp=new XMLHttpRequest();
     	   }
     	 else if(window.ActiveXObject)
     	   {// code for IE6, IE5
     	   xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
     	   }
     	 document.getElementById("featuresDiv").innerHTML='<img border="0" src="${pageContext.request.contextPath}/online-exam/images/loading.gif">'; 
     	 
     	 xmlhttp.onreadystatechange=function()
     	  {  
     	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
     	    {  
     		    document.getElementById("featuresDiv").innerHTML=xmlhttp.responseText;
     	    }
     	  } 
     		xmlhttp.open("post","${pageContext.request.contextPath}/org/loadFeatures/"+Id,true);
     		xmlhttp.send(Id);
     } 
     </script>
     
	<div class="container" id="main-content">
		<section>
		<p>${message}</p>
			<div >
				<div class="form-blk">
						<h1>Edit Your Subscription</h1>
						
						<form:form name="editProfile" modelAttribute="confirmPlan" action="buyPlan">								
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
						
							<div id="accordion">
									<h3><p style="margin: -18px 0 2px 25px;">Show Previous Plans</p></h3>
									<div>
										
										<c:choose>
											<c:when test="${fn:length(planHistory) != 0}">
											<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
							                 			 <tr>
							                 			 	 <th>#</th>
							                 			 	 <th>User Name</th>
							                 			 	 <th>Item Name</th>
											                 <th>Order Time</th>
											                 <th>Payment Type</th>
											                 <th>Service Fee</th>
											                 <th>Time Zone</th>
											                 <th>Fee Amount</th>
											                 <th>Total Amount</th>
											                 <th>Transaction ID</th> 
											             </tr>
												<c:forEach var="allPayments" varStatus="loop" items="${planHistory}">
														<tr>
															<td>${loop.index+1}</td>
															<td>${allPayments.username}</td>
															<td>${allPayments.itemName}</td>
															<td>${allPayments.ORDERTIME}</td>
															<td>${allPayments.paymentType}</td>
															<td>${allPayments.serviceFee}</td>
															<td>${allPayments.timeZone}</td>
															<td>${allPayments.feeAmount}</td>
															<td>${allPayments.totalAmount}</td>
															<td>${allPayments.transactionID}</td>
														</tr>
											                
												</c:forEach>
												</table> 
											</c:when>
											<c:otherwise>
												<p>You Have No Previous Plans</p>
											</c:otherwise>
										</c:choose>											
										
									</div>
							</div>
	
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value">
									<br/><div>Available Subscription Plans : &nbsp;&nbsp; 
										<select name="aPlans" id="avaliablePlans">
											<option onclick="loadFeatures(0);">Choose Plan</option>
												<c:forEach var="plan" varStatus="loop" items="${plans}">
													<c:if test="${plan.planName != member.account.organization.subscription.subscriptionPlan.planName}">
														<option value="${plan.id}" onclick="loadFeatures(${plan.id});">${plan.planName}</option>
													</c:if>
												</c:forEach>
										</select></div>
								</div>
							</div>
							
							
					<div class="form-item" style="color: #9CA000;" id="feature">
						<div class="form-value">	
							<div id="featuresDiv">
							</div>
						</div>
					</div>
							
							
							<div class="form-item" style="color: #9CA000;" id="feature">
								<div class="form-value">
									<p>
										<c:forEach var="item" varStatus="loop" items="${allFeatures}">
											<p>${item.feature.featureName}</p>
										</c:forEach>					
									</p>
								</div>
							</div>
							
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
							
						</form:form>
					</div>
			</div >
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>