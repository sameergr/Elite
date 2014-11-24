<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="../include/adminHeader.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
			<div class="form-blk" >					
				             <h1>List Of All Subscription Plan</h1>	
				             
				             <c:choose>
				             	<c:when test="${empty subPlan}">
				             		<h1>err</h1>
				             	</c:when>
				             	
				             	<c:otherwise>
								<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
		                 			<tr>
					                 <th>Plan Name</th>
					                 <th>Member Type</th>
					                 <th>Plan Type</th>
					                 <th>Author Name</th>
					                 <th>Subscription Type</th>
					                 <th>View</th>
					                 <th>Edit</th>
					                 <th>Delete</th>					                 
                 				 	</tr>
                 				 <c:forEach items="${subPlan}" var="subPlan" varStatus="status">
								 <tr >
									<td>${subPlan.planName}</td>
									<td>${subPlan.registrationType}</td>
									<td>${subPlan.planType}</td>
									<td>${subPlan.authorName}</td>
									<td>${subPlan.subscriptionType}</td>																	
									<td><a href="${pageContext.request.contextPath}/admin/viewSubPlan/${subPlan.id}" >VIEW</a></td>
									<td><a href="${pageContext.request.contextPath}/admin/editSubPlan/${subPlan.id}" >Edit</a></td>						
									<td><a href="${pageContext.request.contextPath}/admin/deleteSubPlan/${subPlan.id}" >Delete</a></td>
	 							 </tr>
				 				 </c:forEach>
               					</table>
               					</c:otherwise>
               					
               					</c:choose>
               					<div style="color: #FF2200;">Total Plans : ${fn:length(subPlan)}</div>					
		    </div>
		    				
		</section>
	</div>
	

<jsp:include page="../include/footer.jsp"></jsp:include>