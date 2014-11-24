<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/online-exam/css/jquery.ui.all.css">
	<script src="${pageContext.request.contextPath}/online-exam/js/jquery-1.9.1.js"></script>
	<script src="${pageContext.request.contextPath}/online-exam/js/jquery.ui.core.js"></script>
	<script src="${pageContext.request.contextPath}/online-exam/js/jquery.ui.widget.js"></script>
	<script src="${pageContext.request.contextPath}/online-exam/js/jquery.ui.mouse.js"></script>
	<script src="${pageContext.request.contextPath}/online-exam/js/jquery.ui.button.js"></script>
	<script src="${pageContext.request.contextPath}/online-exam/js/jquery.ui.position.js"></script>
	<script src="${pageContext.request.contextPath}/online-exam/js/jquery.ui.dialog.js"></script>

	<script>
	function f(){
		$(function() {
			$( "#dialog-message" ).dialog({
				modal: true,
				buttons: {
					Ok: function() {
						checkout.submit();
					},
					Cancel: function() {
						$( this ).dialog( "close" );
					}
				}
			});
		});
	}
	</script>
	
	
<div id="dialog-message" title="Checkout" style="display: none;">
	<p>
		<span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
		Press Ok to Buy the ${""} Plan.or Cancel for Cancellation.
	</p>
	<p>
		Your Current Total is <b>${planDetails.amount}</b>.
	</p>
</div>



     
	<div class="container" id="main-content">
		<section>
			<div >
			<p style="color: maroon;">${message}</p>
				<div class="form-blk">
						<h1>Buy Subscription Plan</h1>
						
						<form:form name="checkout" modelAttribute="planCheckout" action="checkOutPlan">								
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">
									<div>Subscription Plan Name :<input type="text" name="planName" value="${planDetails.planName}" disabled="disabled"/><input type="hidden" name="planName" value="${planDetails.planName}"/></div>
									<div>Amount :<input type="text" name="amount" value="${planDetails.amount}" disabled="disabled"/><input type="hidden" name="amount" value="${planDetails.amount}"/></div>
								</div>
							</div>
							
							<div class="form-actions align-center">
								<input type="button" value="Buy Now" onclick="f();" class="button" id="buynowButton" />
							</div>	
												
						</form:form>
					</div>
			</div >
		</section>
	</div>
	
	<!-- editProfile.submit(); -->
	

<jsp:include page="include/footer.jsp"></jsp:include>