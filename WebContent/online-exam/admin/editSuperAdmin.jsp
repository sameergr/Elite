<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

						
<jsp:include page="../include/adminHeader.jsp"></jsp:include>

		<!-- <script>
         $(document).ready(function(){
        	 $('input[type="email"]').attr('value', '');
        	 $('input[type="password"]').attr('value', '');
        	 $('input[type="text"]').attr('value', '');        	
         });
        </script> -->

				<div class="container" id="main-content">
					<section>		
						<div id="register" class="col spn_1_2">
							<h1>Edit Super Admin</h1>			
								<c:out value="${err}"></c:out>
									<div class="form-blk">
										<form:form action="${pageContext.request.contextPath}/admin/saveEditSuperAdmin/${superAdmin.id}" method="POST" modelAttribute="editSuperAdmin" name="editSuperAdminForm">
												
										<div class="form-item" style="color: #9CA000;">
											<div class="form-value ">
											<div>Email :<input type="email" name="username" value="${superAdmin.username}"/></div><br/>
										</div>	
										</div>					
										<div class="form-item" style="color: #9CA000;">
											<div class="form-value ">
												<div>First Name :<input type="text" name="firstName" value="${superAdmin.userProfile.firstName}"/></div><br/>
												<br/><div>Last Name :<input type="text" name="lastName" value="${superAdmin.userProfile.lastName}"/></div>
											</div>
										</div>
															
										<h3>Permissions :</h3>
										<div class="form-actions align-center">
										<c:choose>
											<c:when test="${superAdmin.permission.createData==true}">
												<div><input type="checkbox" name="permission" value="createData" checked="checked">Create</div>
											</c:when>	
											<c:otherwise>
												<div><input type="checkbox" name="permission" value="createData">Create</div>
											</c:otherwise>
										</c:choose>
										
										<c:choose>
											<c:when test="${superAdmin.permission.viewData==true}">
												<div><input type="checkbox" name="permission" value="viewData" checked="checked"/>View</div>
											</c:when>	
											<c:otherwise>
												<div><input type="checkbox" name="permission" value="viewData"/>View</div>
											</c:otherwise>
										</c:choose>
										
										<c:choose>
											<c:when test="${superAdmin.permission.editData==true}">
												<div><input type="checkbox" name="permission" value="editData" checked="checked"/>Edit</div>
											</c:when>	
											<c:otherwise>
												<div><input type="checkbox" name="permission" value="editData"/>Edit</div>
											</c:otherwise>
										</c:choose>
										
										<c:choose>
											<c:when test="${superAdmin.permission.deleteData==true}">
												<div><input type="checkbox" name="permission" value="deleteData" checked="checked"/>Delete</div>
											</c:when>	
											<c:otherwise>
												<div><input type="checkbox" name="permission" value="deleteData"/>Delete</div>
											</c:otherwise>
										</c:choose>						
											
										</div>
											<div class="form-actions align-center">
												<input type="submit" value="Edit" onclick="createSuperAdminForm.submit();" class="btnstyle-2" />								
											</div>
										</form:form>												
									</div>														
						</div>	
					</section>				
				</div>
  
<jsp:include page="../include/footer.jsp"></jsp:include>
