<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="../include/adminHeader.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
			<div class="form-blk" >					
				             <h1>List Of All Feature</h1>				                
								<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
		                 			<tr>
					                 <th>Feature Name</th>
					                 <th>Author Name</th>
					                 <th>Action Name</th>
					                 <th>Image Url</th>
					                 <th>Feature View Type</th>
					                 <th>Status</th>
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
                 				 <c:forEach items="${featList}" var="feature" varStatus="status">
								 <tr >
									<td>${feature.featureName}</td>
									<td>${feature.authername}</td>
									<td>${feature.actionName}</td>
									<td>${feature.imageURL}</td>
									<td>${feature.viewType}</td>
									<td>${feature.active}</td>
									
									<c:if test="${vp==true}">
									<td><a href="${pageContext.request.contextPath}/admin/viewFeature/${feature.id}" >View</a></td>
									</c:if>
									<c:if test="${ep==true}">
									<td><a href="${pageContext.request.contextPath}/admin/editFeature/${feature.id}" >Edit</a></td>						
									</c:if>
									<c:if test="${dp==true}">
									<td><a href="${pageContext.request.contextPath}/admin/deleteFeature/${feature.id}" >Delete</a></td>
									</c:if>
	 							 </tr>
				 				 </c:forEach>
               					</table>
               					<div style="color: #FF2200;">Total Features : ${fn:length(featList)}</div>					
		    </div>
		    				
		</section>
	</div>
	

<jsp:include page="../include/footer.jsp"></jsp:include>