<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>


  
 <!--  <script>
  	$(document).ready(function(){
  		$('#addMembers').click(function() {
  		    $('#myMembers').slideToggle('fast');
  		    $('#addMembers').text(function(_,txt) {
  		        var ret='';
  		        if ( txt == '+ Add Members' ) {
  		           ret = '- Cancel';
  		           $(this).css('color','#717B6C');
  		        }else{
  		           ret = '+ Add Members';
  		           $(this).css('color','#717B6C');
  		        }
  		        return ret;
  		    });
  		    return false;
  		});
  	});
  </script> -->


<p><c:out value="${err}"></c:out></p>
	<div class="container" id="main-content">
		<section>
		<div class="form-blk">
			<h1>Edit Group</h1>			
				<div class="form-blk" >
				<c:choose>
					<c:when test="${err != null}">
						<p>${err}</p>
					</c:when>
					
					<c:when test="${err == null}">
								<form:form name="createGroup" method="Post" modelAttribute="saveEditGroup" action="${pageContext.request.contextPath}/${userType}/saveEditGroup/${gDetail.id}">
							   		<h1>Edit Group Details</h1>
							   		<div class="form-item">
										<div class="form-value">
												<div>Group Name :<input type="text" style="margin-top: 8px;" name="groupName" placeholder="Type here your's Group Name" disabled="disabled" value="${gDetail.groupName}"/></div>
										</div>
									
										<div id="myMembers" >
									  		<h3 style="margin-top: 85px;">Group Members</h3>
									  		<div class="form-item">
												<div class="form-value">
													<div style="color: green;"><p>Existing Members</p></div>
													<div style="color: red;"><p>Rest Members</p></div>
												</div>
											</div>
											
											<div class="form-item">
												<div class="form-value">
											   		<div>
												   		<ul>
								            	     		<c:forEach items="${gDetail.members}" var="groupMembers" varStatus="status">
																<li class="showList" >
																<input type="checkbox" value="${groupMembers.memberId}" name="memberId" checked="checked"/>
																<p><c:out value="${groupMembers.account.email}"></c:out></p>
																</li>	
																<input type="hidden" name="groupMembers" value="${groupMembers.account.email}"/>				
													 		</c:forEach>
													 		
														</ul>
													</div>
														
													<div>
														<ul id="allmembers">
								            	     		<c:forEach items="${allUserMembers}" var="Members" varStatus="status">
																	<li class="showList" style="margin-bottom: 32px;top: -15px;">
																	<input type="checkbox" value="${Members.memberId}" name="memberId"/>&nbsp;&nbsp;&nbsp;&nbsp;
																	<p><c:out value="${Members.account.username}"></c:out></p>
																</li>				
																<input type="hidden" name="allMembers" value="${Members.account.username}"/>
													 		</c:forEach>
														</ul>
													</div>
												</div>
								  		 </div>
								  </div>
								   <button style="text-decoration: none; display: none;" class="button" type="submit">Save Group</button>
						</form:form>
					</c:when>
				</c:choose>	 
		</div>		   
	</div>		   
</section>
</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>