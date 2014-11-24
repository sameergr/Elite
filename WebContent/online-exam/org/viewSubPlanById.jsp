<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>

	<div class="container" id="main-content">
		<section>			
			<div class="col spn_2_3" style="margin-left: 20%">			
					<div class="form-blk" >					
						<h1><u>Subscription Plan Detail</u></h1>						
							<form>
								   <div class="form-item" >
								   		<div class="form-value">
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
											<div>Plan's Amount :<input type="text" value="${subplan.amount}" disabled="disabled" /></div>
										</div>
								   </div>														
								   															
								   <div class="form-item">
										<div class="form-value">
											<div>Plan Description :<input type="text" value="${subplan.planDescription}" disabled="disabled" /></div>
											<div>Creation Date :<input type="text" value="${subplan.creationTime}" disabled="disabled" /></div>										
										</div>								
								   </div>
								   <div>
								   <h3>Feature Access Levels :</h3>
								   <table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
		                 			<tr>
					                 <th>Feature Name</th>
					                 <th>Feature Type</th>
					                 <th>Feature Value</th>
					                 <th>Author Name</th>
					                 <th>Action Name</th>
					                 <th>Image URL</th>
					                 <th>Description</th>				                 					                 
                 				 	</tr>
                 				 <c:forEach items="${subplan.featureAccessLevelList}" var="sub" varStatus="status">
								 	<tr >
										<td>${sub.feature.featureName}</td>	
										<td>${sub.featureType}</td>
										<td>${sub.value}</td>
										<td>${sub.feature.authername}</td>
										<td>${sub.feature.actionName}</td>
										<td>${sub.feature.imageURL}</td>
										<td>${sub.feature.description}</td>								
	 							 	</tr>
				 				 </c:forEach>
               					</table>  							   
								   </div>							   								   						
							</form>								
					</div>
				 </div>				 
			</section>
	</div>
	</body>

<jsp:include page="include/footer.jsp"></jsp:include>