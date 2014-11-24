
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
	                   
	                   	 	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
			<div id="register" class="col spn_1_2">
                   <div class="form-label">Sub-Category</div> 
                      <select name="subCategoryId" id="subCategoryId" >
                      		<option value="0" selected="selected">------Please select---------</option>
		                       <c:forEach items="${subCategoryList}" var="subCategory">
		                       	  <option value="${subCategory.id}">${subCategory.id} - ${subCategory.categoryName}</option>
		                   	   </c:forEach>
                      </select>
			</div>	
             

