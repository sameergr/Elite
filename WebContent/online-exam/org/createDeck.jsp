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
			<div>
				<div class="form-blk">
						<h1>Create Deck</h1>
				
		<div id="tabs">
			<ul>
				<li><a href="#tabs-1">Series Deck</a></li>
				<li><a href="#tabs-2">Course Deck</a></li>
			</ul>
		<div id="tabs-1">
			<form:form name="createDeck" modelAttribute="deckDetails" action="saveNewDeck">								
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">
								
									<div>Select Group :<select name="groupList" style="height: 37px;">
									<option>Choose Group</option>
									<c:forEach var="group" varStatus="loop" items="${groupList}">
										<option value="${group.id}">${group.groupName}</option>
									</c:forEach>
									</select>
									</div>
									
									<div>Select Series :<select name="seriesList" style="height: 37px;">
										<option>Choose Series</option>
									<c:forEach var="series" varStatus="loop" items="${seriesList}">
										<option value="${series.id}">${series.seriesName}</option>
									</c:forEach>
									</select>
									</div>
								</div>
							</div>
							
					<input type="button" value="Create Deck" onclick="createDeck.submit();" class="button" style="margin-top: 40px;"/>
			</form:form>
			</div>
				
		<div id="tabs-2">
		<form:form name="courseDeckForm" modelAttribute="saveDeckCourse" action="saveDeckCourses">
			<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">
									<div>Select Group :<select name="groupList" style="height: 37px;">
										<option>Choose Group</option>
										<c:forEach var="group" varStatus="loop" items="${groupList}">
											<option value="${group.id}">${group.groupName}</option>
										</c:forEach>
										</select>
									</div>
								</div>
								
								
								<div class="form-value ">
									<div>Select Course :<select name="courseList" style="height: 37px;">
										<option>Choose Series</option>
										<c:forEach var="course" varStatus="loop" items="${coursesList}">
											<option value="${course.id}">${course.courseName}</option>
										</c:forEach>
										</select>
									</div>
								</div>
			</div>
				<input type="button" value="CREATE COURSE DECK" class="button" onclick="courseDeckForm.submit();"/>
		</form:form>
		</div>
			
	</div>
</div>
</div>
		
		</section>
	</div>
		

<jsp:include page="include/footer.jsp"></jsp:include>
     
