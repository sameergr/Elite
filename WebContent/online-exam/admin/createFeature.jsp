<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="../include/adminHeader.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
		<h1>Create New Feature</h1><br/>
		<p><c:out value="${status}"></c:out></p>
			
			<div class="col spn_1_3" style="margin-left: 35%">
					<div class="form-blk" >
						<form:form name="createFeature" method="POST" modelAttribute="features" action="saveFeatures">
						<input type="hidden" name="basePath" value="${pageContext.request.contextPath}/"/>
							<div class="form-item">
								<div class="form-value">
									<br/>Upload Image :<input type="text" name="imageURL" placeholder="Enter URL" required/>
									<p id="url">url must start with /</p>
								</div>
							</div>
							<div class="form-item">
								<div class="form-value">
									Action Name :<input type="text" name="actionName" placeholder="Action Name" required />
								</div>
							</div>
							<div class="form-item">
								<div class="form-value">
									Feature Name :<input type="text" name="featureName" placeholder="Feature Name" required />
								</div>
							</div>
							<div class="form-item">
								<div class="form-value">
									Description :<input type="text" name="description" placeholder="Description" required />
								</div>
							</div>
							
							<div class="form-item">
								<div class="form-value">
									View Type :
									<form:select path="viewType">
										<form:options items="${viewType.viewType}"/>
									</form:select>
								</div>
							</div>
							
							<c:if test="${cp==true}">
								<div class="form-actions align-center">
									<input type="button" value="Submit"  onclick="createFeature.submit();" class="btnstyle-2" />								
								</div>
							</c:if>
						</form:form>
					</div>
					
					<form:form>
						<div><input type="file" name="file"/></div>
					</form:form>
					<a href="${pageContext.request.contextPath}/admin/listOfFeature"><h2 style="color: green;"><u>View All Feature</u></h2></a>
		</section>
	</div>
	

<jsp:include page="../include/footer.jsp"></jsp:include>