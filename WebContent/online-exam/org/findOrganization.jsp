<jsp:include page="../include/header1.jsp"></jsp:include>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
	<div class="container" id="main-content">
		<section>
			<div class="group">
				<div class="col spn_1_2">
				<h1>Find Organization</h1>
			 
	                     	<table  width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
	                     		 <tr>
					                 <th>Org&nbsp;Name</th>
					                 <th>Web&nbsp;Site</th>
					                 <th>About&nbsp;Org</th>
					                 <th>User</th>
					                 <th>Email</th>
                				 </tr>
                				
	                			<c:forEach items="${organization}" var="org" varStatus="status">
	                			<tr>
	                				  <td>
	                				  	 	${org.name}
	                				  </td>
	                				  <td>
	                				  	 	${org.website}
	                				  </td>
	                				  <td>
	                				  	 	${org.aboutOrganization}
	                				  </td>
	                				  <td>
	                				  	 	${org.userProfile.username}
	                				  </td>
	                				  <td>
	                				  	 	${org.userProfile.email}
	                				  </td>
	                			</tr>
               				  </c:forEach>
               				  </table>
			 </div>
				<div class="col spn_1_2"></div>
			</div>
		</section>
	</div>
	

<jsp:include page="../include/footer.jsp"></jsp:include>