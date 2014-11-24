<jsp:include page="../include/header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c"%>

	<div class="container" id="main-content">
		<section>
			<div class="group">
				<h1>Subscription</h1>
				<div class="subscription-blk group">
					<table class="tablestyle1" width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<th></th>
							<th>
								<div class="trialfree"></div>
								<div class="detail-title">Free</div>
							</th>
							<th><div class="detail-title">Standard</div></th>
							<th><div class="detail-title">Premium</div></th>
							<!-- <th><div class="detail-title">Colleges/ Schools/Corporate</div></th> -->
						</tr>
						
						
					<c:forEach var="subscription" varStatus="loop" items="${subscriptions}">
						<tr>

							<td>${subscription.key}</td>							
							<c:forEach var="subVal" varStatus="listLoop" items="${subscription.value}">
								<td>${subVal}</td>
							</c:forEach>
							
						</tr>
					</c:forEach>						
					
											
						<c:forEach var="subsPlan" varStatus="subsLoop" items="${planDetails}">
						<c:choose>
						
							<c:when test="${subsPlan.registrationType == 'FREE'}">
								<c:set var="free" value="${subsPlan.id}"></c:set>
							</c:when>
							
							<c:when test="${subsPlan.registrationType == 'STANDARD'}">
								<c:set var="standard" value="${subsPlan.id}"></c:set>
							</c:when>
							
							<c:otherwise>
								<c:set var="premium" value="${subsPlan.id}"></c:set>
							</c:otherwise>
					
						</c:choose>
						</c:forEach>
						
						<tr>
							<td></td>
							<td>
								<div class="price">
									<span class="style1"></span>
									<span class="style2">Free</span>
									<span class="style3"></span>
								</div>
								<a class="button" href="${pageContext.request.contextPath}/site/signUp?p=${free}">Sign Up</a>
							</td>
							<td>
								<div class="price">
									<span class="style1">$</span>
									<span class="style2">5</span>
									<span class="style3">Monthly</span>
								</div>
								<a class="button" href="${pageContext.request.contextPath}/site/signUp?p=${standard}">Sign Up</a>
							</td>
							<td>
								<div class="price">
									<span class="style1">$</span>
									<span class="style2">10</span>
									<span class="style3">Monthly</span>
								</div>
								<a class="button" href="${pageContext.request.contextPath}/site/signUp?p=${premium}">Sign Up</a>
							</td>
							
						</tr>
					</table>
				</div>
				
			</div>
			
		</section>
	</div>
	

<jsp:include page="../include/footer.jsp"></jsp:include>