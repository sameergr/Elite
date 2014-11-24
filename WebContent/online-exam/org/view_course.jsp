<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script>
		function openWinLaunch(id){
			window.open("${pageContext.request.contextPath}/scorm/launch/"+id+"","_blank",
					"top=100, left=300, width=800, height=600");
		}

</script>

<jsp:include page="include/header1.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
			<div class="form-blk" >					
				             <h1>List Of All Courses</h1>				                
								<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
		                 			<tr>
					                 <th>Course Name</th>
					                 <th>Course Description</th>
					                 <th>File Name</th>
					                 <th>Active</th>
					                 <th>Course Type</th>
					                 <th>View</th>
					                 <th>Edit</th>
					                 <th>Delete</th>					                 
                 				 	</tr>
                 				 <c:forEach items="${courses}" var="course" varStatus="status">
								 <tr >
									<td>${course.courseName}</td>
									<td>${course.couserDescription}</td>
									<td>${course.uploadedScorm.fileName}</td>
									<td>True</td>
									<td>${course.uploadedScorm.scormType.message}</td>																	
									<td><a onclick="openWinLaunch(${course.id});">Launch</a></td>
									<td><a></a></td>						
									<td><a></a></td>
	 							 </tr>
				 				 </c:forEach>
               					</table>
               					<div style="color: #FF2200;">Total Courses : ${fn:length(courses)}</div>
               					<br/>
               					<div class="form-actions">
									<a style="text-decoration: none;" class="button" href="${pageContext.request.contextPath}/org/createCourse">CREATE NEW Course</a>
								</div>
               										
		    </div>
		    				
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>