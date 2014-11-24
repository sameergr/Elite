<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
			<div class="table_views">	
					<div class="form-blk">
				             <h1>List Of All Groups</h1>
				<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
					<c:choose>
							<c:when test="${err != null}">
									<p>${err}</p>
							</c:when>
							<c:when test="${err == null}">
									<tr>
					                 <th>Group Name</th>
					                 <th>Total Member</th>
					                 <th>Group Details</th>
					                 <th>Edit Group</th>
					                 <th>Delete Group</th>                   
                 				 	</tr>
                 				 <c:forEach items="${group}" var="group" varStatus="status">
								 <tr>
									<td>${group.groupName}</td>
									<td>${fn:length(group.members)}</td>
									<td><a href="${pageContext.request.contextPath}/org/learningGroup/${group.id}">VIEW</a></td>
									<td><a href="${pageContext.request.contextPath}/org/editGroupDetail/${group.id}">Edit</a></td>						
									<td><a href="${pageContext.request.contextPath}/org/deleteGroup/${group.id}">Delete</a></td>
	 							 </tr>
				 				 </c:forEach>
							</c:when>
	 				 </c:choose>
               	</table>    
            <div class="form-actions">
				<a style="text-decoration: none;" class="button" href="${pageContext.request.contextPath}/org/createNewGroup">CREATE NEW GROUP</a>
			</div>
			        
	   </div>   							
 	</div>			
			
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>