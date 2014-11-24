<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="../include/adminHeader.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
		<p style="color:green; display: none;"><c:out value="${err}"></c:out></p>
			<div class="table_views" >	
				<div class="form-blk" >			
				             <h1>List Of Organization</h1>
					<div>
						<table  width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
		                 <tr >
		                   <th>Organization Name</th>
		                   <th>Website</th>
		                   <th>User Email</th>
		                   <th>Created By</th>
		                   <th>Status Active</th>
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
		            	     <c:forEach items="${orgList}" var="org" varStatus="status">
								<tr align="center" >
									<td>${org.name}</td>
									<td>${org.website}</td>
									<td>${org.userProfile.email}</td>
									<td>${org.userProfile.firstName}</td>
									<td>${org.active}</td>
									
									<c:if test="${vp==true}">									
										<td><a href="${pageContext.request.contextPath}/admin/viewOrganization/${org.id}" >View</a></td>
									</c:if>
									<c:if test="${ep==true}">									
										<td><a href="${pageContext.request.contextPath}/admin/editOrganization/${org.id}" >Edit</a></td>
									</c:if>
									<c:if test="${dp==true}">						
										<td><a href="${pageContext.request.contextPath}/admin/deleteOrganization/${org.id}" >Delete</a></td>
									</c:if>
			 					</tr>
							 </c:forEach>
		                 </table>
		                 <div style="color: #FF2200; margin-top: 20px;" class="mt10">Total Organizations : ${fn:length(orgList)}</div>
				    </div >
			</div>
		</div>
		<c:if test="${cp==true }">
			<div class="form-actions">
				<a style="text-decoration: none;" class="button" href="${pageContext.request.contextPath}/admin/createNewOrganization">CREATE NEW Organization</a>
			</div>
		</c:if>	
		</section>
		
	</div>
	

<jsp:include page="../include/footer.jsp"></jsp:include>