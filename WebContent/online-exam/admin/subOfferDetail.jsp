<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="../include/adminHeader.jsp"></jsp:include>

	<div class="container" id="main-content">
		<section>			
			<div class="col spn_2_3" style="margin-left: 20%">			
					<div class="form-blk" >					
						<h1><u>Subscription Offer Detail</u></h1>						
							<form>
								   <div class="form-item" >
								   		<div class="form-value">
								   			<div>Member Type :<input type="text" value="${offer.registrationType}" disabled="disabled" /></div>																			
											<div>Plan Type :<input type="text" value="${offer.planType}" disabled="disabled" /></div>
										</div>
								   </div>
								   
								   <div class="form-item" >
								   		<div class="form-value">
								   			<div>Offer Type :<input type="text" value="${offer.offerType}" disabled="disabled" /></div>																			
											<div>Offer Status :<input type="text" value="${offer.valid}" disabled="disabled" /></div>
										</div>
								   </div>
																								
								   <div class="form-item" >
								   		<div class="form-value">
								   			<div>Plan Time :<input type="text" value="${offer.planTime}" disabled="disabled" /></div>																			
											<div>Offer Code :<input type="text" value="${offer.code}" disabled="disabled" /></div>
										</div>
								   </div>						
															
								   <div class="form-item">
										<div class="form-value">
											<div>Offer Amount :<input type="text" value="${offer.offerAmount}" disabled="disabled" /></div>
											<div>Created By :<input type="text" value="${offer.createdBy.firstName}" disabled="disabled"/></div>																				
										</div>
								   </div>
															
								   <div class="form-item">
										<div class="form-value">
											<div>Description :<input type="text" value="${offer.description}" disabled="disabled" /></div>
											<div>Image Path :<input type="text" value="${offer.adImageURL}" disabled="disabled"/></div>										
										</div>								
								   </div>
								   
								   <div class="form-item">
										<div class="form-value">
											<div>Creation Date :<input type="text" value="${offer.creationTime}" disabled="disabled" /></div>
											<div>Expiry date :<input type="text" value="${offer.expiryDate}" disabled="disabled"/></div>										
										</div>								
								   </div>								   						
							</form>								
					</div>
				 </div>				 
			</section>
	</div>
	</body>

<jsp:include page="../include/footer.jsp"></jsp:include>