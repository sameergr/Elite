<jsp:include page="include/header1.jsp"></jsp:include>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<div class="container" id="main-content">
		<section>
			<div class="group">
				<div class="col spn_1_2">
				<h1>Create Article</h1>
				<form:form  modelAttribute="article" action="createArticle" class="form-style-1">
	                    <div class="form-item">
	                        <div class="form-label">Article Name</div>
	                        <div class="form-value">
	                            <input type="text" value="" name="articleName" id="articleName">
	                        </div>                    
	                    </div>
	                    <div class="form-item">
	                        <div class="form-label">Content</div>
	                        <div class="form-value">
	                            <textarea rows="5" name="articleContent" id="articleContent"></textarea>
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