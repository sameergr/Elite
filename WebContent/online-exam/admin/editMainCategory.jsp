<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="../include/adminHeader.jsp"></jsp:include>

<script type="text/javascript">
  $(function(){
    $("#tree").dynatree({
      persist: true,
      checkbox: true,
      selectMode: 3,
      onPostInit: function(isReloading, isError) {
         logMsg("onPostInit(%o, %o)", isReloading, isError);
         // Re-fire onActivate, so the text is update
         this.reactivate();
      },
      onActivate: function(node) {
    	  alert("hello");
        $("#echoActive").text(node.data.title);
      },
      onDeactivate: function(node) {
        $("#echoActive").text("-");
      },
      onDblClick: function(node, event) {
        logMsg("onDblClick(%o, %o)", node, event);
        node.toggleExpand();
      }
    });
  });
</script>




<div class="container" id="main-content content">
	
        <section>
        	<h1>Manage Content</h1>
            <form:form action="/editContents" modelAttribute="editContent" name="categoryDetails">
        	 <div id="tree">
	        	 <ul>
	        	 
	        	 <c:forEach var="seriesLoop" varStatus="looping" items="${allSeries}">
	        	 	<li id="id3" class="expanded">${seriesLoop.seriesname}
	        	 		
	        	 		<ul>
		        	 		<c:forEach var="categoryLoop" varStatus="looping1" items="${allSeries}">
		        	 			<li id="id3.1">${categoryLoop.category.description}		
		        	 		</c:forEach>
		        	 			
	        	 		</ul>

	        	</c:forEach>
	        	
	        	<li id="id3" class="folder">All Categories
	        		<ul>
		        	 			
		        	 			<c:set var="category" value="Category"></c:set>
	        				 	<c:set var="subcategory" value="SubCategory"></c:set>
	        	 
					        	 <c:forEach var="categories" varStatus="loop" items="${allCategories}">
					        	 	<c:set var="id" value="${categories.id}"></c:set>
						        	 	<c:if test="${categories.catagoryType == category}">
											<li id="id3.1">${categories.description}
						        	 	</c:if>
					        	 	
					        	 	<ul>
						        	 	<c:forEach var="subCat" varStatus="innLoop" items="${allCategories}">
						        	 		<c:if test="${subCat.parent_id == id}">
						        	 				<c:set var="subcatId" value="${subCat.id}"></c:set>
						        	 				<li id="id3.1">${subCat.description}
						        	 		</c:if>
						        	 		
						        	 		
						        	 		<ul>
							        	 		<c:forEach var="allContent" varStatus="quesLoop" items="${contentList}">
							        	 			<%-- <li id="id3.1.1"><c:out value="${allContent.subCatagories.id}"></c:out>
							        	 			<li id="id3.1.1"><c:out value="${subcatId}"></c:out> --%>
							        	 			
								        	 		<c:if test="${allContent.subCatagories.id == subcatId}">
								        	 			<li id="id3.1.1">${allContent.questions.question}
								        	 				<ul>
								        	 					<li id="id3.1.1">${allContent.answer.answer}		
								        	 				</ul>
								        	 		</c:if>
							        	 		</c:forEach>
						        	 		</ul>
						        	 	</c:forEach>
		        	 				</ul>
		        	 					<input type="button" value="Delete" name="" onclick="categoryDetails.submit();"/>
	        					 </c:forEach>
	        			</ul>

	    		</ul>
  			</div>
        	
        	</form:form>
        	
        	
        	
        </section>
        	
        </div>

  
<jsp:include page="../include/footer.jsp"></jsp:include>
