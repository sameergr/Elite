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
		<!-- <h1>Create Subscription Offer</h1><br/> -->	
		<p><c:out value="${status}"></c:out></p>
			
			<div class="col spn_2_3" style="margin-left: 20%">
				<a href="${pageContext.request.contextPath}/admin/listOfSubOffers"><h2 style="color: green;"><u>View All Subscription Offers</u></h2></a>
					<div class="form-blk" >
					
						<h1><u>Subscription Offer Form</u></h1>
						
							<form:form name="createSubOffer" method="POST" modelAttribute="subOffer" action="saveSubOffer">
							
								<input type="hidden" name="basePath" value="${pageContext.request.contextPath}/"/>
								
									<div  class="form-item" style="margin-top: 5%; border:#DDE0C6 solid 1px; border-radius:5px; padding: 4px; ">
										<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" >
											<tr>
												<td>Offer Type :</td>
												<th>
													<form:select path="offerType">
	       											<form:options items="${planType.offerType}" />
	       											</form:select>
	       										</th>       								
	       								
	       										<td>Member Type :</td>       								
												<th>
													<form:select path="registrationType">
	       											<form:options items="${planType.registrationType}" />
	       											</form:select>
	       										</th>
	       								
	       										<td>Plan Type :</td>       								
	       										<th>
	       											<form:select path="planType">
	       											<form:options items="${planType.planType}" />
	       											</form:select>
	       										</th>
	       									</tr>
	       								</table>							
								   </div>
								
								   <div class="form-item" style="margin-top: 3%">
								   		<div class="form-value">
								   			<div>Plan Time :<input type="text" name="planTime" placeholder="Plan Time" required /></div>																			
											<div>Offer Code :<input type="text" name="offerCode" placeholder="Offer Code" required /></div>
										</div>
								   </div>						
															
								   <div class="form-item">
										<div class="form-value">
											<div>Offer Amount :<input type="text" name="offerAmount" placeholder="Offer Amount" required /></div>
											<div>Upload Image :<input type="text" name="offerImageURL" placeholder="Enter URL" required/></div>																				
										</div>
								   </div>
															
								   <div class="form-item">
										<div class="form-value">
											Description :<input type="text" name="description" placeholder="Description" required />										
										</div>								
								   </div>	
								   
								   <div class="form-item">
										<div class="form-value">											
											<div>Expiry Date:<input type="text" name="expiryDate" >
												<a href="#" onClick="setYears(2014, 2022);
	       															 showCalender(this, 'expiryDate');">
	      											<img src="${pageContext.request.contextPath}/online-exam/images/calenderJavaScript/calender.png"></a>
											</div>
										</div>								
								   </div>					 
									
									<c:if test="${cp==true}">												
									   <div class="form-actions align-center">
											<input type="button" value="Submit"  onclick="createSubOffer.submit();" class="btnstyle-2" />								
									   </div>
								   </c:if>
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