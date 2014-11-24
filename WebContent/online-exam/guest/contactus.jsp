<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
			<div class="group">
			<p style="color: red;"><c:out value="${err}"></c:out></p>
				<div class="col spn_1_2">
					<h1>Contact Us</h1>
					<form:form class="form-style-1" name="contactReqForm" action="${pageContext.request.contextPath}/site/saveContactReq" method="POST" modelAttribute="saveContactReq">
	                    <div class="form-item">
	                        <div class="form-label">Your Name</div>
	                        <div class="form-value">
	                            <input type="text" value="" name="userName" >
	                        </div>                    
	                    </div>
	                    <div class="form-item">
	                        <div class="form-label">Email</div>
	                        <div class="form-value">
	                            <input type="email" value="" name="email" >
	                        </div>                    
	                    </div>
	                    <div class="form-item">
	                        <div class="form-label">Mobile</div>
	                        <div class="form-value">
	                            <input type="text" value="" name="mobile">
	                        </div>                    
	                    </div>
	                    <div class="form-item">
	                        <div class="form-label">Query</div>
	                        <div class="form-value">
	                            <textarea rows="5" name="description"></textarea>
	                        </div>                    
	                    </div>
	                    <div class="form-actions">
	                        <input type="button" class="right btnstyle-2" value="Submit"  onclick="contactReqForm.submit();" />
	                    </div>
	                </form:form>
				</div>
				<div class="col spn_1_2"></div>
			</div>
		</section>
	</div>
	

<jsp:include page="../include/footer.jsp"></jsp:include>