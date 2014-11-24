<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="../include/adminHeader.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
		<p style="color:green; display: none;"><c:out value="${err}"></c:out></p>
			<div class="table_views" >	
				<div class="form-blk" >	
				<c:out value="${err}"></c:out>	
				             <h1>List Of Contact Request</h1>
					<div>
						<table  width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
		                 <tr >
		                   <th>Sender's Name</th>
		                   <th>Sender's Email</th>
		                   <th>Requested Date</th>
		                   <th>Status</th>
				           <th>Reply</th>
				           <th>Delete</th>
		                 </tr>
		            	     <c:forEach items="${contactReqList}" var="contactReq" varStatus="status">
								<tr align="center" >
									<td>${contactReq.userName}</td>
									<td>${contactReq.email}</td>
									<td>${contactReq.reqDate}</td>
									<td>${contactReq.status}</td>
									<td><a href="${pageContext.request.contextPath}/admin/contactRequest/${contactReq.id}" >Reply</a></td>
									<td><a href="${pageContext.request.contextPath}/admin/deleteContactReq/${contactReq.id}" >Delete</a></td>
			 					</tr>
							 </c:forEach>
		                 </table>
		                 <div style="color: #FF2200; margin-top: 20px;" class="mt10">Total Request : ${fn:length(contactReqList)}</div>
				    </div >
			</div>
		</div>
		</section>
		
	</div>
	

<jsp:include page="../include/footer.jsp"></jsp:include>