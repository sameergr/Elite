<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="../include/adminHeader.jsp"></jsp:include>					
     <div class="container" id="main-content">
		<section>
			<div id="register" class="col spn_1_2">
				<h1>Edit Organization</h1>
				<h3></h3>
				
				<div class="form-blk">
					<form:form action="${pageContext.request.contextPath}/admin/saveEditOrganization/${org.id}" method="POST" modelAttribute="saveNewOrganization" name="regForm">
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>Organization Name :<input type="text" value="${org.name}" name="orgName"/></div>
								<div>Website :<input type="text" value="${org.website}" name="website"/></div>
							</div>
						</div>
						
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>About Organization :<input type="text" value="${org.aboutOrganization}" name="aboutOrganization"/></div>
								<div>Tagline :<input type="text" value="${org.tagLine}" name="tagLine"/></div>
							</div>
						</div>
						
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>User Email :<input type="text" value="${org.userProfile.email}" name="email"/></div>
								<div>Subscription Plan Name :<input type="text" value="${org.subscription.subscriptionPlan.planName}" name="planName" readonly="readonly"/></div>
							</div>
						</div>
						
						<div class="form-actions">
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
