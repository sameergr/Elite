<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="../include/adminHeader.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
		<h1>Feature Detail</h1><br/>
		<p><c:out value="${status}"></c:out></p>
			
			<div class="col spn_1_3" style="margin-left: 35%">
					<div class="form-blk" >
						<form:form >
						<input type="hidden" name="basePath" value="${pageContext.request.contextPath}/"/>
							<div class="form-item">
								<div class="form-value">
									<br/>Upload Image :<input type="text" name="imageURL"  value="${feature.imageURL}" readonly="readonly"/>
									<p id="url">url must not start with /</p>
								</div>
							</div>
							<div class="form-item">
								<div class="form-value">
									Action Name :<input type="text" name="actionName" value="${feature.actionName}" readonly="readonly"/>
								</div>
							</div>
							<div class="form-item">
								<div class="form-value">
									Author Name :<input type="text" name="description" value="${feature.authername}" readonly="readonly" />
								</div>
							</div>
							<div class="form-item">
								<div class="form-value">
									Feature Name :<input type="text" name="featureName" value="${feature.featureName}" readonly="readonly"/>
								</div>
							</div>
							<div class="form-item">
								<div class="form-value">
									Description :<input type="text" name="description" value="${feature.description}" readonly="readonly"/>
								</div>
							</div>							
						</form:form>
					</div>					
		</section>
	</div>
	

<jsp:include page="../include/footer.jsp"></jsp:include>