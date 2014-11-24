<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
					
<jsp:include page="../include/header.jsp"></jsp:include>

     <div class="container" id="main-content">
		<section>
		<p style="font-size: 15px;font-weight: bold;margin-bottom: 15px;margin-left: 19px;"><c:out value="${userEmail}"></c:out></p>
			<div id="register" class="col spn_1_2">
				<h1>Create New Organization</h1>
				<h3></h3>
				<!-- <div class="form-blk" id="student">
					<h3>Default Features List</h3>
					<p>Find Tutor, Find Series, Add Friend, Invite Friend, Loyalty Points, Follow Friends, Test Score.</p>
				</div>  -->
				
				<div class="form-blk">
					<form:form action="${pageContext.request.contextPath}/site/saveUserOrganization" enctype="multipart/form-data" method="POST" modelAttribute="registration" name="regForm">
					<input type="hidden" value="${organizationName}" name="organizationName"/>
					<input type="hidden" value="${id}" id="showId" name="planId"/>
					<input type="hidden" value="${userEmail}" name="userEmail"/>
	
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>Organization Name :<input type="text" placeholder="Organization Name" name="oName" value="${organizationName}" disabled="disabled"/></div>
								<div>Organization URL :<input type="text" placeholder="Ex : abc" name="website"/>@elite.com</div>
							</div>
						</div>
						
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>About Organization :<input type="text" placeholder="About" name="aboutOrganization"/></div>
							</div>
						</div>
						
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>Tagline :<textarea rows="3" cols="5" placeholder="Tagline" name="tagLine"></textarea></div>
							</div>
						</div>
						
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div class="title">WorkProfile</div>
									<div>Previous Organization Name :<input type="text" placeholder="Previous Organization Name" name="previousOrgName"/></div>
									<div>Role :<input type="text" placeholder="Role" name="role"/></div>
								</div>
						</div>
						
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>Start Date :<input type="text" placeholder="Start Date" name="startDate"/></div>
								<div>End Date :<input type="text" placeholder="End Date" name="endDate"/></div>
							</div>
						</div>
						
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>Organization City :<input type="text" placeholder="Organization City" name="organizationCity"/></div>
							</div>
						</div>
						
						
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
							<div class="title">Address</div>
								<div>Address :<textarea rows="3" cols="5" placeholder="Address" name="address"></textarea></div>
								<div>Street :<input type="text" placeholder="Street No." name="street"/></div>
							</div>
						</div>
						
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>City :<input type="text" placeholder="City" name="city"/></div>
								<div>LandMark :<input type="text" placeholder="Landmark" name="landmark"/></div>
							</div>
						</div>
						
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>ZipCode :<input type="text" placeholder="ZipCode" name="zipCode"/></div>
								<div>Country :<input type="text" placeholder="Country" name="country"/></div>
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
