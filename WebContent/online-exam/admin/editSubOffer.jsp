<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="../include/adminHeader.jsp"></jsp:include>

<head>
   <title>Java Script Calender Date Picker</title>
   <meta http-equiv="Content-Type" content="text/html;">
   <script language="javaScript" type="text/javascript" src="${pageContext.request.contextPath}/online-exam/images/calenderJavaScript/calendar.js"></script>
   <link href="${pageContext.request.contextPath}/online-exam/images/calenderJavaScript/calendar.css" rel="stylesheet" type="text/css">
</head>	
<body>

	<div class="container" id="main-content">
		<section>	
		<p><c:out value="${err}"></c:out></p>		
			<div class="col spn_2_3" style="margin-left: 20%">			
					<div class="form-blk" >					
						<h1><u>Edit Subscription Offer Detail</u></h1>						
							<form:form name="editSubOffer" method="POST" modelAttribute="subOffer" action="${pageContext.request.contextPath}/admin/saveEditSubOffer/${offer.id}">
								   <div class="form-item" >
								   		<div class="form-value">
								   			<div>Member Type :<input type="text" value="${offer.registrationType}"  readonly="readonly" /></div>																			
											<div>Plan Type :<input type="text" value="${offer.planType}"   readonly="readonly"/></div>
										</div>
								   </div>
								   
								   <div class="form-item" >
								   		<div class="form-value">
								   			<div>Offer Type :<input type="text" value="${offer.offerType}" readonly="readonly"  /></div>																			
											<div>Offer Status :<input type="text" value="${offer.valid}"   /></div>
										</div>
								   </div>
																								
								   <div class="form-item" >
								   		<div class="form-value">
								   			<div>Plan Time :<input type="text" value="${offer.planTime}"  name="planTime" /></div>																			
											<div>Offer Code :<input type="text" value="${offer.code}"  name="offerCode" /></div>
										</div>
								   </div>						
															
								   <div class="form-item">
										<div class="form-value">
											<div>Offer Amount :<input type="text" value="${offer.offerAmount}" name="offerAmount"  /></div>
											<div>Created By :<input type="text" value="${offer.createdBy.firstName}" readonly="readonly" /></div>																				
										</div>
								   </div>
															
								   <div class="form-item">
										<div class="form-value">
											<div>Description :<input type="text" value="${offer.description}" name="description"  /></div>
											<div>Image Path :<input type="text" value="${offer.adImageURL}" name="offerImageURL" /></div>										
										</div>								
								   </div>
								   
								   <div class="form-item">
										<div class="form-value">											
											<div>Expiry Date:<input type="text" name="expiryDate" value="${offer.expiryDate}" >
												<a href="#" onClick="setYears(2014, 2022);
	       															 showCalender(this, 'expiryDate');">
	      											<img src="${pageContext.request.contextPath}/online-exam/images/calenderJavaScript/calender.png"></a>
											</div>
										</div>								
								   </div>	
								   
								   <div class="form-actions align-center">
										<input type="button" value="Submit"  onclick="editSubOffer.submit();" class="btnstyle-2" />								
								   </div>
								   
								   							   						
							</form:form>	
							<table id="calenderTable">
							        <tbody width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
							          <tr>
							            <td colspan="4" align="center">
								          <select onChange="showCalenderBody(createCalender(document.getElementById('selectYear').value,
								           this.selectedIndex, false));" id="selectMonth">
								              <option value="0">Jan</option>
								              <option value="1">Feb</option>
								              <option value="2">Mar</option>
								              <option value="3">Apr</option>
								              <option value="4">May</option>
								              <option value="5">Jun</option>
								              <option value="6">Jul</option>
								              <option value="7">Aug</option>
								              <option value="8">Sep</option>
								              <option value="9">Oct</option>
								              <option value="10">Nov</option>
								              <option value="11">Dec</option>
								          </select>
							            </td>
							            <td colspan="2" align="center">
										    <select onChange="showCalenderBody(createCalender(this.value, 
											document.getElementById('selectMonth').selectedIndex, false));" id="selectYear">
											</select>
										</td>
							            <td align="center">
										    <a href="#" onClick="closeCalender();"><font color="#003333" size="+1">X</font></a>
										</td>
									  </tr>
							       </tbody>
							       <!-- <tbody id="calenderTableDays"> -->
							       <tbody width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
							         <tr style="">
							           <td>Sun</td><td>Mon</td><td>Tue</td><td>Wed</td><td>Thu</td><td>Fri</td><td>Sat</td>
							         </tr>
							       </tbody>
							       <tbody id="calender"></tbody>
							    </table>							
					</div>
				 </div>				 
			</section>
	</div>
	</body>

<jsp:include page="../include/footer.jsp"></jsp:include>