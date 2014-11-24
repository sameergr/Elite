<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="../include/adminHeader.jsp"></jsp:include>

	<div class="container" id="main-content">
		<section>
		<p>${message}</p>
			<div>
				<div class="form-blk">
						<h1>Payment Details</h1>
						
						<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
							                 			 <tr>
							                 			 	 <th>#</th>
							                 			 	 <th>User Name</th>
							                 			 	 <th>Item Name</th>
											                 <th>Order Time</th>
											                 <th>Payment Type</th>
											                 <th>Service Fee</th>
											                 <th>Time Zone</th>
											                 <th>Fee Amount</th>
											                 <th>Total Amount</th>
											                 <th>Transaction ID</th> 
											             </tr>
											             <c:forEach var="payment" varStatus="loop" items="${allPayments}">
											             	 <tr>
								                 			 	 <td>${loop.index+1}</td>
								                 			 	 <td>${payment.username}</td>
								                 			 	 <td>${payment.itemName}</td>
												                 <td>${payment.ORDERTIME}</td>
												                 <td>${payment.paymentType}</td>
												                 <td>${payment.serviceFee}</td>
												                 <td>${payment.timeZone}</td>
												                 <td>${payment.feeAmount}</td>
												                 <td>${payment.totalAmount}</td>
												                 <td>${payment.transactionID}</td> 
											            	 </tr>
											             </c:forEach>
						</table>
						
				</div>
			</div>
		</section>
	</div>