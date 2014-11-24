<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
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
		<h1>New Categories</h1><br/>
		<div class="col spn_1_2">
			<c:out value="${err}"></c:out>
				<div style="color: black; font: bold; margin-top: 2%">
					<c:out value="${newCategory.categoryName}"></c:out>${newCategory.id}
				</div>
				
				<div style="margin-left: 2%">
					<ul>
						<c:forEach var="Catagories" items="${subCategories}"><%-- items="${categoryMap.categoryList}" --%>
							<li>${Catagories.categoryName}</li></a>
						</c:forEach><br/>
						<li style="list-style: none;"><input type="button" class="btnstyle-2" value="Add new Sub-Category" id="button"/></li>
					</ul>
				</div>	
				
		</div>
		
		<form:form action="${pageContext.request.contextPath}/${userType}/saveSubCategory" method="post" name="saveSubCategory" id="form" modelAttribute="saveSubCategory">
		 	<input type="text"  name="id" value="${newCategory.id}" readonly="readonly"/>
            	<input type="text" placeholder="Sub Category Name" name="categoryName"/><br/><br/>
            	<input type="text" placeholder="Description" name="description"/><br/><br/>
            	<input type="submit" value="Add SubCategory" class="btnstyle-2" id="submit">
        </form:form>
		
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>