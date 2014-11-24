<%@page import="com.innverse.elearn.model.UserAccount"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>

     
    <script>
     function loadFeatures(Id)
     {
     	 var xmlhttp=null;
     	 if (window.XMLHttpRequest)
     	   {// code for IE7+, Firefox, Chrome, Opera, Safari
     	   xmlhttp=new XMLHttpRequest();
     	   }
     	 else if(window.ActiveXObject)
     	   {// code for IE6, IE5
     	   xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
     	   }
     	 document.getElementById("featuresDiv").innerHTML='<img border="0" src="${pageContext.request.contextPath}/online-exam/images/loading.gif">'; 
     	 
     	 xmlhttp.onreadystatechange=function()
     	  {  
     	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
     	    {  
     		    document.getElementById("featuresDiv").innerHTML=xmlhttp.responseText;
     	    }
     	  } 
     		xmlhttp.open("post","${pageContext.request.contextPath}/org/loadFeatures/"+Id,true);
     		xmlhttp.send(Id);
     } 
     </script>
     
     
     
	<div class="container" id="main-content">
		<section>
			<div >
				<div class="form-blk" >
						<h1>Edit Profile</h1>
						
						<form:form name="editProfile" modelAttribute="editProfile" action="saveProfile" enctype="multipart/form-data">
						<h3>User Details</h3>			
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">
									<div>User Name :<input type="text" name="username" value="${member.account.username}"/></div>
									<div>Email Id :<input type="text" name="email" value="${member.account.email}" readonly="readonly"/></div>
								</div>
							</div>
							
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">
									<div>First Name :<input type="text" name="firstName" value="${member.account.firstName}"/></div>
									<div>Last Name :<input type="text" name="lastName" value="${member.account.lastName}"/></div>
								</div>
							</div>
							
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">
									<div>Phone :<input type="text" name="phone" value="${member.account.phone}"/></div>
								</div>
							</div>
							
						<c:if test="${member.organization != null}">
							
							<h3>Organization Details</h3>
							
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">
									<div>Organization Name :<input type="text" name="name" value="${member.organization.name}"/></div>
									<div>Organization Logo :<input type="file" name="logo"/></div>
								</div>
							</div>
							
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">
									<div>Tagline :<input type="text" name="tagLine" value="${member.organization.tagLine}"/></div>
									<div>Website :<input type="text" name="website" value="${member.organization.website}@elite.com" readonly="readonly"/></div>
								</div>
							</div>
							
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">
									<div>About Organization :<input type="text" name="aboutOrganization" value="${member.organization.aboutOrganization}"/></div>
								</div>
							</div>
						</c:if>
							
							
							
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value">
									<div>Available Plans :<select name="aPlans" id="avaliablePlans">
										<option onclick="loadFeatures(0);">Choose Plan</option>
											<c:forEach var="avaliablePlans" varStatus="loop" items="${allPlans}">
												<c:if test="${avaliablePlans.planName != member.organization.subscription.subscriptionPlan.planName}">
													<option value="${avaliablePlans.id}" onclick="loadFeatures(${avaliablePlans.id});">${avaliablePlans.planName}</option>	
												</c:if>
											</c:forEach>
										</select> </div>
								</div>
							</div>
							
							<div class="form-item" style="color: #9CA000;" id="feature">
								<div class="form-value">
									<div id="featuresDiv">
									</div>					
								</div>
							</div>
							<div class="form-actions align-center">
								<input type="button" value="Save Profile"  onclick="editProfile.submit();" class="btnstyle-2" />								
							</div>						
						</form:form>
					</div>
			</div >
			<%-- <div style="color: #9CA000;">Total Members : ${fn:length(memberList)}</div> --%>
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>