<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
						
<jsp:include page="../include/header.jsp"></jsp:include>


      <div class="container" id="main-content">
		<section>
			<div id="register" class="col spn_1_2">
			<c:if test="${err == null}">
			
				<h1>Registration Form</h1>
			
				<p>${regerr}</p>
				<div class="form-blk">
					<form:form action="${pageContext.request.contextPath}/site/register" method="POST" modelAttribute="registration" name="regForm">
						<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">
								<c:forEach var="planids" items="${subscriptionPlanType}">
										<p> <input type="hidden" name="plan" value="${planids.id}"/></p>
										<input type="hidden" name="regType" value="${planids.subscriptionType}"/>
								</c:forEach>
									<input type="hidden" name="role" value="USER"/>
									<c:if test="${subscriptionPlanType == null}">
									<input type="hidden" name="plan" value="${subsPlan}"/>
									</c:if>
									<input type="hidden" name="inviteplan" value="${regPlan}"/>
									<input type="hidden" name="inviteeEmailAddress" value="${invitationDetail.friendEmailId}"/>
									<input type="hidden" name="organizationId" value="${invitationDetail.organizationId}"/>
									<input type="hidden" name="token" value="${invitationDetail.token}"/>
									
									
									<div>UserName :<input type="text" name="username" value="" placeholder="User Name" required/></div>
									<div>Password :<input type="password" name="password" value="" placeholder="Choose Password" required/></div>									
								</div>
							</div>
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value ">
								<div>First Name :<input type="text" placeholder="First Name" name="firstName" required/></div>
								<div>Last Name:<input type="text" placeholder="Last Name" name="lastName" required/></div>
							</div>
						</div>
						
						<div class="form-item" style="color: #9CA000;">
							<div class="form-value">
							<c:if test="${invitationDetail.friendEmailId != null}">
								<div>Email Address :<input type="email" placeholder="Email Address" name="email" value="${invitationDetail.friendEmailId}" disabled="disabled"/></div>
							</c:if>
							<c:if test="${invitationDetail.friendEmailId == null}">
								<div>Email Address :<input type="email" placeholder="Email Address" name="email"/></div>
							</c:if>
								<div>Phone No. :<input type="text" placeholder="Phone Number" name="phone"/></div>
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
								<div>About me :<textarea rows="3" cols="5" name="aboutYou"></textarea></div>
							</div>
						</div>

						
						
						<c:if test="${registrationType == 'PREMIUM'}">
							<div class="form-item" style="color: #9CA000;" id="subject">
								<div class="form-value ">
									<div>Organization Name :<input type="text" name="name" placeholder="Organization Name"/></div>
									<input type="hidden" name="regType" value="PREMIUM"/>
								</div>
							</div>
						</c:if>
						
						<c:if test="${registrationType == 'STANDARD'}">
							<div class="form-item" style="color: #9CA000;" id="subject">
								<div class="form-value ">
									<div>Organization Name :<input type="text" name="name" placeholder="Organization Name"/></div>
									<input type="hidden" name="regType" value="STANDARD"/>
								</div>
							</div>
						</c:if>

						<c:if test="${registrationType == 'FREE'}">
						<div class="form-item" style="color: #9CA000;" id="subject">
								<div class="form-value ">
									<p style="color: red; font-weight: bold;">Free Subscription Plan</p>
									<input type="hidden" name="regType" value="FREE"/>
								</div>
						</div>					
						</c:if>
						
							<div class="form-actions align-center">
								<input type="button" value="Submit"  onclick="regForm.submit();" class="btnstyle-2" />								
							</div>
						</form:form>
						</div>	
						
						</c:if>	
						
						<c:if test="${err != ''}">
							<p style="color: red;"><c:out value="${err}"></c:out></p>
						</c:if>
					</div>	
					</section>			
				</div>

			<div class="col spn_1_2">
			</div>
			
	
<jsp:include page="../include/footer.jsp"></jsp:include>
