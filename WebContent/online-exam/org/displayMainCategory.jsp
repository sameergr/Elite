<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>

<jsp:include page="include/header1.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
		<h1>Main Categories</h1><br/><%-- ==userId1${userId}===emailId2${emailId}==userName3${userName}===userType4${userType} --%>
		<h3>Choose Category</h2>
		<div class="col spn_1_2">
		<%-- <input type="text" name="subCategories" value="${subCategoryData}" > --%>
						<c:forEach varStatus="loop1" var="categoryMapData" items="${categoryMapData}">
						
						<div>
								<ul>
								<c:forEach varStatus="loop" var="categoryMap" items="${categoryMapData.value}">
									<c:forEach var="category" items="${categoryMap.categoryList}"><c:set var="catid" value="${category.catId}"></c:set></c:forEach>
									<li><a href="${pageContext.request.contextPath}/${userType}/showSubcategory?catid=${catid}"><c:out value="${categoryMap.category}"></c:out></a></li>
										<%-- <li><a href="${pageContext.request.contextPath}/${userType}/showSubcategory?catid=${catid}&subCategories=${subCategoryData}"><c:out value="${categoryMap.category}"></c:out></a></li> --%>
								</c:forEach>
								</ul>
						</div>
						</c:forEach>
		</div>
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>