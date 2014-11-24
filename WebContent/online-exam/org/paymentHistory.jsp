<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
		<p style="color:green; display: none;"><c:out value="${err}"></c:out></p>
			<div class="table_views" >	
				<div class="form-blk" >			
				             <h1>Payment History</h1>
					<div>
						<table  width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
			                 <tr >
			                   <th>Item Name</th>
			                   <th>Order Time</th>
			                   <th>Actual Price</th>
			                   <th>Fee Amount</th>
			                   <th>Total Amount</th>
							   <th>Payer ID</th>			                   
			                   <th>Transaction ID</th>
			                 </tr>
			                 
			                 	<c:forEach var="payments" varStatus="loop" items="${userPayments}">
			                 		<tr>
			                 			<td>${payments.itemName}</td>
			                 			<td>${payments.ORDERTIME}</td>
			                 			<td>${payments.sellerAmount}</td>
			                 			<td>${payments.feeAmount}</td>
			                 			<td>${payments.totalAmount}</td>
			                 			<td>${payments.payerId}</td>
			                 			<td>${payments.transactionID}</td>
			                 		</tr>
			                 			
			                 	</c:forEach>
		                 </table>
				    </div>
			</div>
		</div>
		</section>
		
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>