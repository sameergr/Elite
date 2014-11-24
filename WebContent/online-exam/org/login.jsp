<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">
						
<jsp:include page="include/header.jsp"></jsp:include>

       

	<div class="container" id="main-content">
		<section>
		<div class="group">
		<p><c:out value="${err}"></c:out></p>
			<div id="register" class="spn_1_2" style="margin: 0 auto;">
				<h1>Organization Login</h1>
				
				
				
				<div class="form-blk">
					<form:form action="${pageContext.request.contextPath}/org/login" method="POST" modelAttribute="adminLogin" name="loginForm">
						<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">
									<div><input type="text" name="username" required value="" placeholder="Username" /></div>
									<div><input type="password" name="password" required value="" placeholder="Password"/></div>									
								</div>
							</div>
												
							<div class="form-actions align-center">
								<input type="submit" value="Login" onclick="loginForm.submit();" class="btnstyle-2" />								
							</div>
						</form:form>
						</div>		
					</div>	
					</div>
					</section>			
				</div>
				
			
			

