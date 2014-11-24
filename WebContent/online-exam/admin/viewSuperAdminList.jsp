<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="../include/adminHeader.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
		<p><c:out value="${err}"></c:out></p>
			<div class="form-blk" >					
				             <h1>List Of Super Admin</h1>				                
								<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
		                 			<tr>
					                 <th>Email Id </th>
					                 <th>First Name</th>
					                 <th>Last Name</th>
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
                 				 <c:forEach items="${superAdminList}" var="admin" varStatus="status">
								 <tr >
									<td>${admin.username}</td>
									<td>${admin.userProfile.firstName}</td>
									<td>${admin.userProfile.lastName}</td>
									<c:if test="${vp==true}">									
										<td><a href="${pageContext.request.contextPath}/admin/viewSuperAdmin/${admin.id}" >View</a></td>
									</c:if>
									<c:if test="${ep==true}">									
										<td><a href="${pageContext.request.contextPath}/admin/editSuperAdmin/${admin.id}" >Edit</a></td>
									</c:if>
									<c:if test="${dp==true}">						
										<td><a href="${pageContext.request.contextPath}/admin/deleteSuperAdmin/${admin.id}" >Delete</a></td>
									</c:if>
	 							 </tr>
				 				 </c:forEach>
               					</table>
               					<div style="color: #FF2200;">Total Features : ${fn:length(superAdminList)}</div>					
		    </div>
		    				
		</section>
	</div>
	

<jsp:include page="../include/footer.jsp"></jsp:include>