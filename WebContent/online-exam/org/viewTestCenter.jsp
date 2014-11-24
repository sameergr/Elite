<jsp:include page="include/header1.jsp"></jsp:include>

<script src="${pageContext.request.contextPath}/online-exam/js/jquery-1.9.1.js"></script>
<script src="${pageContext.request.contextPath}/online-exam/js/jquery.ui.core.js"></script>
<script src="${pageContext.request.contextPath}/online-exam/js/jquery.ui.widget.js"></script>
<script src="${pageContext.request.contextPath}/online-exam/js/jquery.ui.tabs.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/online-exam/css/jquery.ui.all.css">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<script>
	$(function() {
		$( "#tabs" ).tabs();
	});
</script>

<script>
		function openWinLaunch(id){
			window.open("${pageContext.request.contextPath}/scorm/launch/"+id+"","_blank",
					"top=100, left=300, width=800, height=600");
		}

</script>


<div class="container" id="main-content">
		<section>
			<div class="form-blk" >
				             <h1>Test Center</h1>
				             
				        <div id="tabs">
								<ul>
									<li><a href="#tabs-1">Assigned Series</a></li>
									<li><a href="#tabs-2">Assigned Courses</a></li>
								</ul>
							<div id="tabs-1">
							<p>
				             	<form:form  class="form-style-1">
								<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
	                 			 <tr>
	                 			 	 <th>#</th>
					                 <th>Series Name</th>
					                 <th>Created On</th>
					                 <th>Credit Points</th>
					                 <th>Question Level Timing</th>
					                 <th>Total Time</th>
					                 <th>Levels</th>
					                 <th>View Level</th>					                
                				 </tr>                				 
                				 <c:forEach items="${memberDeck}" var="deckList" varStatus="status">
		                				 	<tr>
		                				 		<td>${status.index+1}</td>
												<td>${deckList.series.seriesName}</td>
												<td>${deckList.series.createOn}</td>
												<td>${deckList.series.creditPoints}</td>
												<td>${deckList.series.questionLevelTiming}</td>
												<td>${deckList.series.totalTime}</td>
												<td>${fn:length(deckList.series.levels)}</td>
												<td><a href="testCenterSeries/${deckList.series.id}" style="color: green;">View Existing Levels</a></td>
										  	</tr>
								  	</c:forEach>
               					</table>  
               					</form:form>
               					</p>
               				</div>
               				
               				<div id="tabs-2">
               				<p>
	               				<form:form  class="form-style-1">
									<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
		                 			 <tr>
		                 			 	 <th>#</th>
						                 <th>Course Name</th>
						                 <th>Course Description</th>
						                 <th>Publish Date</th>
						                 <th>File Name</th>
						                 <th>File Type</th>
						                 <th>View Course Details</th>					                
	                				 </tr>                				 
	                				 <c:forEach items="${courseList}" var="courseList" varStatus="status">
	                				 		<tr>
	                				 			<td>${status.index+1}</td>
												<td>${courseList.course.courseName}</td>
												<td>${courseList.course.couserDescription}</td>
												<td>${courseList.course.publishDate}</td>
												<td>${courseList.course.uploadedScorm.fileName}</td>
												<td>${courseList.course.uploadedScorm.scormType.message}</td>
												<td><a onclick="openWinLaunch(${courseList.course.id});">Launch Course</a></td>
											</tr>
	                				 </c:forEach>
	                				 </table>
	                			</form:form>
	                			</p>
               				</div>      							
		    		</div>		
		    	</div>	
		</section>
	</div> 
<jsp:include page="include/footer.jsp"></jsp:include>
	

	