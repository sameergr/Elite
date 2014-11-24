<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>


  
  <script>
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
  </script>


<p><c:out value="${err}"></c:out></p>
	<div class="container" id="main-content">
		<section>
			<h1>Create Group</h1>			
				<div class="form-blk" >
				<c:choose>
					<c:when test="${err != null}">
						<p>${err}</p>
					</c:when>
					
					<c:when test="${err == null}">
								<form:form name="createGroup" method="Post" modelAttribute="saveGroups" action="saveNewGroup">
							   		<h1>Create New Group</h1>
							   		<div class="form-item">
										<div class="form-value">
												<div>Group Name :<input type="text" style="margin-top: 8px;" name="groupName" placeholder="Type here your's Group Name" required/></div>
												<div><br/><a href="#" id="addMembers"><span id="addValue" style="color: #717B6C">+ Add Members</span></a></div><br/><br/>
										</div>
									
										<div id="myMembers" style="display: none;">
									  		<h3>Your Members</h3>	
									   		<ul>
					            	     		<c:forEach items="${memberList}" var="membersL" varStatus="status">
													<li class="showList" >
													<input type="checkbox" value="${membersL.memberId}" name="memberId"/>&nbsp;&nbsp;&nbsp;&nbsp;
													<c:out value="${membersL.account.email}"></c:out>
													</li>				
										 		</c:forEach>
											</ul>
										 <button style="text-decoration: none; display: none;" class="button" type="submit">Create Group</button>
					                <!-- </table> -->
										</div>
			
																					
											
								   </div>
						</form:form>
					</c:when>
					
				</c:choose>	 
					
					
		  
		</div>		   
						   
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>