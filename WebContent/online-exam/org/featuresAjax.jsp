<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>


<c:if test="${plan == null}">
	<div id="featuresDiv"> 
	</div>
</c:if>


<c:if test="${plan != null}">
	<div id="featuresDiv"> <a href="${pageContext.request.contextPath}/org/buyPlan?aPlans=${plan.id}" class="button">Buy Now</a>
		<h3 style="margin-top: 28px;">Subscription Plan Features</h3>
					<c:forEach var="FAL" varStatus="innerLoop" items="${plan.featureAccessLevelList}">
							<c:if test="${FAL.featureType != 'NOTALLOWED'}">
								<p>${FAL.feature.featureName}</p>      
							</c:if>
					</c:forEach>
	</div>	
</c:if>