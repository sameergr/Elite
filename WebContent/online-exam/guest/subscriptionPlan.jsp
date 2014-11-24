<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
						
<jsp:include page="../include/header.jsp"></jsp:include>

	<div class="container" id="main-content">
		<section>
		<%-- <p><c:out value="${err}"></c:out></p>
		 --%>
			<div id="register" class="col spn_1_2">
				<h1>Subscribe our's plan</h1>
				<div class="form-blk">
					<form:form action="subscriptionPlan" method="POST" modelAttribute="subscribeplan" >
						<div class="form-item">
							<div class="form-value">
							Plan type
							<form:select path="planType">
							<form:options items="${Plan.planType}" />
							</form:select>
							</div>
						</div>
						<div class="form-item">
							<div class="form-value">
								Amount<input type="text" placeholder="amount"  name="amount" id="amount" value="${PLAN}" readonly="readonly"/>
							</div>
						</div>
						<div class="form-item">
							<div class="form-value">
								Offer Amount
								<input type="text" placeholder="Offer amount* $ 200" name="offerAmount" id="offerAmount" readonly="readonly" value="200"/>
							</div>
						</div>
						<div class="form-item">
							<div class="form-value">
							Category
								<input type="text" placeholder="Category" name="category" id="category" />
							</div>
						</div>
						<div class="form-item">
							<div class="form-value">
								<input type="checkbox"/>Terms & Conditions
							</div>
						</div>
						<div class="form-actions align-center">
							<input type="submit" value="Start" class="btnstyle-2"/>
						</div>						  
                   </form:form>
				</div>
			</div>
			<div class="col spn_1_2">
			</div>
		</section>	
	</div>
<jsp:include page="../include/footer.jsp"></jsp:include>
