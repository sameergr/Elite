<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
			<div >
				<div class="form-blk">
						<div class="form-title">Edit Member's Detail accept Email Id and Member Id</div>
						<form:form name="editDetails" method="POST" modelAttribute="updateMemberDetails" action="${pageContext.request.contextPath}/${userType}/updateMemberDetail/${member.memberId}">
														
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">
									<div>Email Id :<input type="text" name="email" value="${member.account.email}" readonly="readonly"/></div>
									<div>User Name :<input type="text" value="${member.account.email}" readonly="readonly"/></div>									
								</div>
							</div>
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">									
									<div>First Name :<input type="text" name="firstName" value="${member.account.firstName}" readonly="readonly"/></div>
									<div>Last Name :<input type="text" name="lastName" value="${member.account.lastName}" readonly="readonly"/></div>
								</div>
							</div>							
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">
									<div>Phone Number :<input type="text" name="phone" value="${member.account.phone}" readonly="readonly"/></div>
									<div>Member Id :<input name="id" type="text" value="${member.memberId}" readonly="readonly"/></div>
								</div>
							</div>
							<div class="form-item" style="color: #9CA000;">
							<div class="form-value ">
							<div>
									<span style="color: black;">Default Member in your organization is :</span><p>
									<c:choose>
									<c:when test="${is==true}">
									<div ><input type="radio" name="active" value="true" checked="checked"/>Active</div><br>
									</c:when>
									<c:otherwise>
									<div "><input type="radio" name="active" value="true"/>Active</div><br>
									</c:otherwise>
									</c:choose>
									
									<c:choose>
									<c:when test="${is==false}">
									<div ><input type="radio" name="active" value="false" checked="checked"/>Inactive</div>
									</c:when>
									<c:otherwise>
									<div ><input type="radio" name="active" value="false"/>Inactive</div>
									</c:otherwise>
									</c:choose>
								</div>	
									<div>Member Role Type:
	       							<select name="memberRole" id="memberRole" >
									<option value="${member.memberRole}" selected="selected">${member.memberRole}</option>
				                   	<c:forEach items="${mFALPermission}" var="mFAL">
				                   		<c:if test="${mFAL.memberRole ne member.memberRole}">
				                   			<option value="${mFAL.memberRole}">${mFAL.memberRole}</option>
				                   		</c:if>
					               </c:forEach>
	       							</select>
	       							</div>
	       							</div>
							</div>
							<div class="form-actions align-center">
								<input type="button" value="Submit"  onclick="editDetails.submit();" class="btnstyle-2" />								
							</div>
						</form:form>
					</div>
			</div >
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>