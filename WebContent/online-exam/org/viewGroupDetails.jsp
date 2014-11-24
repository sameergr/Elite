<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
			<div >
				<div class="form-blk">						
				 			<h1>Group Details</h1>
				 			<h3 >Group's Name :&nbsp;<span style="color: black;">${gDetail.groupName}</span></h2>
								<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
								 <tr>
					                 <th>First Name</th>
					                 <th>Last Name</th>
					                 <th>Email Id</th>
					                 <th>Member Id</th>
					                 <th>Phone</th>                   
                 				 	</tr>		                 		
                 				  <c:forEach items="${gDetail.members}" var="member" varStatus="status"> 
									 <tr >
										 <td>${member.account.firstName}</td>
										 <td>${member.account.lastName}</td>
										 <td>${member.account.email}</td>
										 <td>${member.memberId}</td>
										 <td>${member.account.phone}</td>										 
		 							 </tr>
				 				  </c:forEach> 
               					</table>
					    </div>					
			</div >
			 <div style="color: #FF2200; margin-top: 20px;">Total Members : ${fn:length(gDetail.members)}</div> 
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>