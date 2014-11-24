<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
					
<jsp:include page="../include/adminHeader.jsp"></jsp:include>

     <div class="container" id="main-content">
		<section>
			<div id="register" class="col spn_1_2">
				<h1>Create New Organization</h1>
				<h3></h3>
				
				<div class="form-blk">
					<form:form action="${pageContext.request.contextPath}/admin/saveNewOrganization" method="POST" modelAttribute="saveNewOrganization" name="regForm">
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>Organization Name :<input type="text" placeholder="Organization Name" name="orgName" /></div>
								<div>Website :<input type="text" placeholder="Website" name="website"/></div>
							</div>
						</div>
						
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>About Organization :<input type="text" placeholder="About" name="aboutOrganization"/></div>
								<div>Tagline :<input type="text" placeholder="Tagline" name="tagLine"/></div>
							</div>
						</div>
						
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>User Email :<input type="email" placeholder="xyz@abc.com" name="email"/></div>
								<div>Password :<input type="password" placeholder="password" name="password"/></div>
							</div>
						</div>
						
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>Subscription Plan Name :<input type="text" value="Standard" name="planName" readonly="readonly"/></div>
							</div>
						</div>
												
						<div class="form-actions align-center">
							<input type="button" value="Submit"  onclick="regForm.submit();" class="btnstyle-2" />							
						</div>
						</form:form>
						</div>		
					</div>				
				</div>
                   
				</div>
			<div class="col spn_1_2">
			</div>
		</section>	
	</div>
<jsp:include page="../include/footer.jsp"></jsp:include>
