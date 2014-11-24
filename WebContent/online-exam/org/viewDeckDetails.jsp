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
		<div class="form-blk">
			<h1>Deck's Details </h1>
				<div id="tabs">
				<ul>
					<c:choose>
						<c:when test="${type eq 'quiz'}">				
							<li><a href="#tabs-1">Deck Series </a></li>
						</c:when>
						<c:otherwise>
							<li><a href="#tabs-2">Deck Courses </a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			
			<c:choose>
				<c:when test="${type eq 'quiz'}">
					<div id="tabs-1">
					<h3>Series Name : <span style="color: green;">${deckSeries.series.seriesName}</span></h3>
					<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
	                 	<tr>
	                 		<th>Level's Id</th>
					        <th>Level's Name</th>
					        <th>Total Question</th>
					        <th>Passing Score</th>
					        <th>Min.Score for Mastery</th>
					        <th>Level's Order</th>
					        <th>Max. Hit or try</th>
                		</tr>                				 
                	 <c:forEach items="${levelList}" var="level" varStatus="status">
                		<tr>
							<td>${level.id}</td>
							<td>${level.levelname}</td>
							<td>${fn:length(level.questions)}</td>
							<td>${level.passingScore} %</td>
							<td>${level.masteryScore} %</td>
							<td>${level.levelOrder}</td>
							<td>${level.maxTryOrHit}</td>
						</tr>
					 </c:forEach>
               		</table>
					</div>
				</c:when>
			
			<c:otherwise>
				<div id="tabs-2">
				<h3>Deck Courses Details</h3>
				<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
									<tr>
					                 <th>Course Name</th>
					                 <th>Course Description</th>
					                 <th>Group Name</th>
					                 <th>Total Member In Group</th>
					                 <th>Assigned Date</th>
                 				 	</tr>
								 <tr>
									<td>${deckCourse.course.courseName}</td>
									<td>${deckCourse.course.couserDescription}</td>
									<td>${deckCourse.group.groupName}</td>
									<td>${fn:length(deckCourse.group.members)}</td>
									<td>${deckCourse.assignedOn}</td>
	 							 </tr>
               	</table>    
			</div>
			</c:otherwise>
			</c:choose>
		</div>
	</div>	
	<%-- </c:if> --%>		
		</section>
	</div>
	
<jsp:include page="include/footer.jsp"></jsp:include>