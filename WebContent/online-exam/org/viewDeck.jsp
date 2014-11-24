<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/online-exam/js/jquery-1.9.1.js"></script>
<script src="${pageContext.request.contextPath}/online-exam/js/jquery.ui.core.js"></script>
<script src="${pageContext.request.contextPath}/online-exam/js/jquery.ui.widget.js"></script>
<script src="${pageContext.request.contextPath}/online-exam/js/jquery.ui.tabs.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/online-exam/css/jquery.ui.all.css">

<script>
	$(function() {
		$( "#tabs" ).tabs();
	});
</script>



	<div class="container" id="main-content">
		<section>
			<!-- <div class="table_views">	
					<div class="form-blk"> -->
				             <!-- <h1>List Of All Decks</h1> -->
				             <c:if test="${deckList == null || deckCoursesList == null}">
				             		${err}
				             </c:if>
				             
				 <c:if test="${deckList != null || deckCoursesList != null}">            
				             
 	<div class="form-blk">
						<h1>List Of All Decks</h1>
		
		<div id="tabs">
			<ul>
				<li><a href="#tabs-1">Quiz</a></li>
				<li><a href="#tabs-2">Course</a></li>
			</ul>
			<div id="tabs-1">
				<h3>Deck Series</h3>
				<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
									<tr>
					                 <th>Assigned On</th>
					                 <th>Group Details</th>
					                 <th>Organization Details</th>
					                 <th>Series</th>
					                 <th>View</th>
					                 <th>Edit</th>
					                 <th>Delete</th>                   
                 				 	</tr>
                 				 <c:forEach items="${deckList}" var="deck" varStatus="status">
								 <tr>
									<td>${deck.assignedOn}</td>
									<td>${deck.group.groupName}</td>
									<td>${deck.organization.name}</td>
									<td>${deck.series.seriesName}</td>
									<td><a href="${pageContext.request.contextPath}/${userType}/viewDeckDetail/${deck.id}?type=quiz">View Detail</a></td>
									<td><a href="${pageContext.request.contextPath}/${userType}/editDeckDetail/${deck.id}?type=quiz">Edit Detail</td>
									<td><a href="${pageContext.request.contextPath}/${userType}/deleteDeck/${deck.id}?type=quiz">Delete</td>						
	 							 </tr>
				 				 </c:forEach>
               	</table> 	
			</div>
			<div id="tabs-2">
				<h3>Deck Courses</h3>
				<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
									<tr>
					                 <th>Assigned On</th>
					                 <th>Group Details</th>
					                 <th>Organization Details</th>
					                 <th>Courses Name</th>
					                 <th>View</th>
					                 <th>Edit</th>
					                 <th>Delete</th>                   
                 				 	</tr>
                 				 <c:forEach items="${deckCoursesList}" var="deck" varStatus="status">
								 <tr>
									<td>${deck.assignedOn}</td>
									<td>${deck.group.groupName}</td>
									<td>${deck.organization.name}</td>
									<td>${deck.course.courseName}</td>
									<td><a href="${pageContext.request.contextPath}/${userType}/viewDeckDetail/${deck.id}?type=course">View Detail</a></td>
									<td><a href="${pageContext.request.contextPath}/${userType}/editDeckDetail/${deck.id}?type=course">Edit Detail</td>
									<td><a href="${pageContext.request.contextPath}/${userType}/deleteDeck/${deck.id}?type=course">Delete</td>						
	 							 </tr>
				 				 </c:forEach>
               	</table>    
			</div>
		</div>
	</div>	
	</c:if>		
			<div class="form-actions" style="margin-top: 22px;">
				<a style="text-decoration: none;" class="button" href="${pageContext.request.contextPath}/${userType}/createNewDeck">CREATE NEW DECK</a>
			</div>
			
		</section>
	</div>
	
<jsp:include page="include/footer.jsp"></jsp:include>