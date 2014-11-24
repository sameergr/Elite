<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
		<p style="color:green; display: none;"><c:out value="${err}"></c:out></p>
			<div class="table_views" >	
				<div class="form-blk" >			
				             <h1>List Of Members</h1>
					<div>
						<table  width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
		                 <tr >
		                   <th width="20%">Member Name</th>
		                   <th width="20%">Member Email ID</th>
		                   <th width="20%">Member Phone</th>
		                   <th width="10%">Role Type</th>
		                   <th width="10%">Member Details</th>
		                   <th width="10%">Member Edit</th>
		                   <th width="10%">Delete Member</th>                   
		                 </tr>
		            	     <c:forEach items="${memberList}" var="membersL" varStatus="status">
								<tr align="center" >
									<td>${membersL.account.email}</td>
									<td>${membersL.account.email}</td>
									<td>${membersL.account.phone}</td>
									<td>${membersL.memberRole}</td>
									<td><a href="${pageContext.request.contextPath}/${userType}/memberDetails/${membersL.memberId}" >View Detail</a></td>
									<td><a href="${pageContext.request.contextPath}/${userType}/memberEdit/${membersL.memberId}" >Edit</a></td>						
									<td><a href="${pageContext.request.contextPath}/${userType}/deleteMember/${membersL.memberId}" >Delete</a></td>
			 					</tr>
							 </c:forEach>
		                 </table>
		                 <div style="color: #FF2200; margin-top: 20px;" class="mt10">Total Members : ${fn:length(memberList)}</div>
				    </div >
			</div>
			
			<div class="form-actions">
				<a style="text-decoration: none;" class="button" href="${pageContext.request.contextPath}/${userType}/createNewMember">CREATE NEW Member</a>
			</div>
			
		</div>
			
		</section>
		
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>