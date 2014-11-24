<jsp:include page="include/header1.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
	<c:set var="columnVal" value="0"/>
	<div class="container" id="main-content">
		<section>
			<div class="dashboard">Organization Name: ${member.organization.name}
			<h1>Customize Dashboard </h1><input type="submit" value="save setting" class="button"/>
			<p style="margin-bottom: 0px;" >
				
				<div class="group">
					<c:forEach var="feature" varStatus="loop" items="${memberPermisssion.featureAccessLevelList}">
							<c:choose>
								<c:when test="${loop.index >= 0}">
								<c:if test="${feature.featureType != 'NOTALLOWED'}">
									
									<div class="col spn_1_5">
										<div class="list-block">
											<a href="${pageContext.request.contextPath}/${userType}${feature.feature.actionName}">
												<img src="${pageContext.request.contextPath}/online-exam/images${feature.feature.imageURL}" alt=""/>
												<div class="dash-title"><input type="checkbox"/>${feature.feature.featureName}</div>
											</a>
										</div>
									</div>
								</c:if>
								</c:when>
								
							</c:choose>
					</c:forEach>	
					<div class="col spn_1_5">
							<div class="list-block">
								<a href="${pageContext.request.contextPath}/org/viewCourse">
										<img src="${pageContext.request.contextPath}/online-exam/images/learninggroup-icon.png" alt=""/>
									<div class="dash-title"><input type="checkbox"/>Learning Courses</div>
								</a>
							</div>
					</div>
				</div>
			
			
		
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>