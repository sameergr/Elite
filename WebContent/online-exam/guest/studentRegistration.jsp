<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
						
<jsp:include page="../include/header.jsp"></jsp:include>



      <div class="container" id="main-content">
		<section>
		<p><c:out value="${err}"></c:out></p>
			<div id="register" class="col spn_1_2">
				<h1>Student Registration Form</h1>
			
				<div class="form-blk" id="student">
					<h3>Features List</h3>
					<p>Find Tutor, Find Series, Add Friend, Invite Friend, Loyalty Points, Follow Friends, Test Score.</p>
				</div> 
				
				<div class="form-blk">
					<form:form action="${pageContext.request.contextPath}/guest-users/registerStudent" method="POST" modelAttribute="registration" name="regForm">
					<input type="hidden" value="${student}" name="userDetails">
					<input type="hidden" value="${id}" id="showId" name="planId"/>
					
					
					
					
					<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>Current Address :<textarea rows="3" cols="5" placeholder="Current Address" name="currentAddress"></textarea></div>
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
						
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>Permanent Address :<textarea rows="3" cols="5" placeholder="Permanent Address" name="permanentAddress"></textarea></div>
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
					
					
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>Work Profile :<input type="text" placeholder="Work Profile" name="phone"/></div>
								
								</div>
							</div>
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
								<div>How do you know about our website :<br/>
									<input type="radio" value="0" name="howDoYouKnowAboutSite"/>&nbsp;&nbsp;Internet<br/>
									<input type="radio" value="1" name="howDoYouKnowAboutSite"/>&nbsp;&nbsp;Television<br/>
									<input type="radio" value="2" name="howDoYouKnowAboutSite"/>&nbsp;&nbsp;NewsPaper<br/>
									<input type="radio" value="3" name="howDoYouKnowAboutSite"/>&nbsp;&nbsp;Other Websites<br/>
									<input type="radio" value="4" name="howDoYouKnowAboutSite" checked="checked"/>&nbsp;&nbsp;Others<br/>
								</div>
							</div>
						</div>
						
						
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value ">
							<div>Subscription Offer :<input type="text" name="" placeholder="Subscription Offer"/></div>
								<div>Subscription Type :
									<select name="">
									
									</select>
								</div>
								
							</div>
						</div>

						
							<div class="form-actions align-center">
								<input type="button" value="Submit"  onclick="regForm.submit();" class="btnstyle-2" />
								<a href="${pageContext.request.contextPath}/${userType}/dashboard?name=${name}"><input type="button" value="Skip" class="btnstyle-2" /></a>								
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
