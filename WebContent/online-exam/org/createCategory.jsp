<jsp:include page="include/header1.jsp"></jsp:include>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
	<div class="container" id="main-content">
		<section>
			<c:out value="${err}"></c:out>
			<div class="group">
				<div class="col spn_1_2">
				<h1>Create Category</h1>
				<form:form  modelAttribute="saveCategory" action="${pageContext.request.contextPath}/${userType}/saveCategory" class="form-style-1">
	                    <div class="form-item">
	                        <div class="form-label">Category Name</div>
	                        <div class="form-value">
	                            <input type="text" name="categoryName" required="required"/>
	                        </div>                    
	                    </div>
	                    <div class="form-item">
	                        <div class="form-label">Description</div>
	                        <div class="form-value">
	                          <input type="text" name="description" required="required"/>
	                        </div>                    
	                    </div>
	                     <div class="form-actions">
	                        <input type="submit" value="Submit" class="right btnstyle-2"/>
	                    </div>
	                    </form:form>

</div>
				<div class="col spn_1_2"></div>
			</div>
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>