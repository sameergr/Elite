<jsp:include page="include/header1.jsp"></jsp:include>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
.ac{
width: 100px; 
height: 100px; 
background: #BDD0AE; 
margin: 5px 5px 5px 5px;
}
</style>
	<div class="container" id="main-content">
		<section>
			<div class="group">
				<div><div class="form-blk" align="center" >
				<h1>Application Center</h1>
				<table>
					<tr>
						<td><a href="elearningCourses"><div class="ac">Elearning-Courses</div></a></td>
						<td><a href="books"><div class="ac">Books</div></a></td>
						<td><a href="reporting"><div class="ac">Reporting</div></a></td>
						<td><a href="iOSApp"><div class="ac">iOS App</div></a></td>
					</tr>
					
					<tr>
						<td><a href="whiteBoard"><div class="ac">Whiteboard</div></a></td>
						<td><a href="calendar"><div class="ac">Calendar</div></a></td>
						<td><a href="webinars"><div class="ac">Webinars</div></a></td>
						<td><a href="androidApp"><div class="ac">Android App</div></a></td>
					</tr>
					
				</table>

</div>
				<div class="col spn_1_2"></div>
			</div></div>
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>