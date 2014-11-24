<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="include/header1.jsp"></jsp:include>
         <script>
            $(document).ready(function(){
                $("#form").hide();
                $("#form").attr('disabled', 'disabled');
              	$("#button").click(function(){
                $("#form").toggle();
              	$("#button").attr('value', $(this).attr('value') == 'Add new Sub-Category' ? 'cancel' : 'Add new Sub-Category');
                $("form").removeAttr('disabled', 'disabled');
              });
            });
        </script>

	<div class="container" id="main-content">
		<section>
		<h1>Sub Categories</h1><br/><br/>
		<h3>Select Categories</h3>
		<div class="col spn_1_2">
				<div>
					<ul>
						<c:forEach var="Catagories" items="${subCategories}"><%-- items="${categoryMap.categoryList}" --%>
							<li><a href="${pageContext.request.contextPath}/${userType}/addQuestions?catid=${Catagories.id}">${Catagories.categoryName}</li></a>
						</c:forEach><br/>
						<li style="list-style: none;"><input type="button" class="btnstyle-2" value="Add new Sub-Category" id="button"/></li>
					</ul>
				</div>	
		</div>
		
		<form:form action="${pageContext.request.contextPath}/${userType}/addSubcategory" method="post" name="addcategoryform" id="form" modelAttribute="addcategoryform">
		 <input type="text"  name="id" value="${catid}" readonly="readonly"/>
            <input type="text" placeholder="Category Name" name="categoryName"/><br/><br/>
            <input type="submit" value="Add Category" class="btnstyle-2" id="submit">
        </form:form>
		
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>