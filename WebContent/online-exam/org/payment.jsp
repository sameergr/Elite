<jsp:include page="include/header1.jsp"></jsp:include>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<div class="container" id="main-content">
		<section>
			<div class="group">
				<div class="col spn_1_2">
				<h1>Payment</h1>
				<form:form  modelAttribute="paymentGateway" action="paymentGateway" class="form-style-1">
							
	                    <div class="form-item">
	                        <div class="form-label">Amount</div>
	                        <div class="form-value">
	                            <input type="text" name="amount" id="amount">
	                            <input type="text" name="currencycode" value="USD"/> 
	                        </div>                    
	                    </div>
	                    <div class="form-item">
	                        <div class="form-label">Payment Gateway</div>
	                        <div class="form-value">
	                            	<input type="text" name="paymentgateway" value="paypal"/>
	                        </div>                    
	                    </div>
	                     <div class="form-actions">
	                        <input type="submit" value="Submit" class="right btnstyle-2"/>
	                    </div>
	                    </form:form>

</div>
				<div class="col spn_1_2"></div>
			</div>
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>