<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="http://xoxco.com/projects/code/tagsinput/jquery.tagsinput.css" />
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>

	<div class="container" id="main-content">
		<section>
		<p style="color:green;"><c:out value="${err}"></c:out></p>
			
			
					<div class="form-blk" >
						<h1>Enter Your Friend's Email Id</h1>
						
						<form:form name="inviteFriend" method="POST" modelAttribute="invitationForm" action="invitationSendFriend">
							<div class="form-item">
								<div class="form-value">
									<input type="email" name="friendEmailId" placeholder="abc@example.com" required id="demo-input-facebook-theme" name="blah2" />
								</div>
							</div>
							<div class="form-actions align-center">
								<input type="button" value="Send Mail" onclick="inviteFriend.submit();" class="button" />								
							</div>
						</form:form>
					</div>
					<div><span style="color: #FF2200; margin-top: 2%;" class="mt10">NOTE :&nbsp;&nbsp; Multiple Email Id's should separated by<span style="color: black; font: bold;"> , </span>(Comma)</span></div>
				
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>