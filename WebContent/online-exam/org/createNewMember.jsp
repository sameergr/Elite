<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
			<div >
			<c:out value="${err}"></c:out>
				<div class="form-blk">
						<h1>Create New Member</h1>
						<form:form name="createNewMember" method="Post" modelAttribute="saveNewMember" action="saveNewMember">
														
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">
									<div>Email Id :<input type="email" name="email"/></div>
									<div>Password :<input type="password" name="password"/></div>
								</div>
							</div>
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">									
									<div>First Name :<input type="text" name="firstName"/></div>
	       							<div>Member Role Type:
	       								<form:select path="memberRole">
	       								<form:options items="${memberRole.memberRole}"/>
	       								</form:select>
	       							</div>
								</div>
							</div>	
							<div class="form-actions align-center">
							<input type="button" value="Create"  onclick="createNewMember.submit();" class="btnstyle-2" />							
						</div>					
						</form:form>
					</div>
			</div >
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>