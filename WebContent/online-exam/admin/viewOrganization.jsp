<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="../include/adminHeader.jsp"></jsp:include>					

     <div class="container" id="main-content">
		<section>
			<div id="register" class="col spn_1_2">
				<h1>View Organization Details</h1>
				<h3></h3>
				
				<div class="form-blk">
					<form:form >
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>Organization Name :<input type="text" value="${org.name}" readonly="readonly"/></div>
								<div>Website :<input type="text" value="${org.website}" readonly="readonly"/></div>
							</div>
						</div>
						
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>About Organization :<input type="text" value="${org.aboutOrganization}" readonly="readonly"/></div>
								<div>Tagline :<input type="text" value="${org.tagLine}" readonly="readonly"/></div>
							</div>
						</div>
						
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>User Email :<input type="text" value="${org.userProfile.email}"/></div>
								<div>Subscription Plan Name :<input type="text" value="${org.subscription.subscriptionPlan.planName}"/></div>
							</div>
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
