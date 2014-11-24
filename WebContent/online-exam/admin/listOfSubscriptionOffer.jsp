<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="../include/adminHeader.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
			<div class="form-blk" >					
				             <h1>List Of All Subscription Offer</h1>
				                <div><h3>Subscription Offer:</h3>
								<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
		                 			<tr>
					                 <th>Member Type</th>
					                 <th>Offer Type</th>
					                 <th>Plan Type</th>					                 
					                  <c:if test="${vp==true}">				                 
					                 	<th>View</th>
					                 </c:if>	
					                 <c:if test="${ep==true}">				                 
					                 	<th>Edit</th>
					                 </c:if>
					                 <c:if test="${dp==true}">
					                 <th>Delete</th>
					                 </c:if>					                 
                 				 	</tr>
                 				 <c:forEach items="${subOfferList}" var="subOffer" varStatus="status">
								 <tr >
									<td>${subOffer.registrationType}</td>
									<td>${subOffer.offerType}</td>
									<td>${subOffer.planType}</td>
									<c:if test="${vp==true}">																	
										<td><a href="${pageContext.request.contextPath}/admin/subOfferDetail/${subOffer.id}" >VIEW</a></td>
									</c:if>
									<c:if test="${ep==true}">	
										<td><a href="${pageContext.request.contextPath}/admin/editSubOffer/${subOffer.id}" >Edit</a></td>						
									</c:if>
									<c:if test="${dp==true}">
										<td><a href="${pageContext.request.contextPath}/admin/deleteSubOffer/${subOffer.id}" >Delete</a></td>
									</c:if>
	 							 </tr>
				 				 </c:forEach>
               					</table>
               					<div style="color: #FF2200;">Total Offers : ${fn:length(subOfferList)}</div></div>
               			
               					            							
		    </div>
		    				
		</section>
	</div>
	

<jsp:include page="../include/footer.jsp"></jsp:include>