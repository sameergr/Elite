<jsp:include page="include/header1.jsp"></jsp:include>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
.ac{
width: 100px; 
height: 100px; 
background: #DFB389; 
margin: 5px 5px 5px 5px;
}
</style>
	<div class="container" id="main-content">
		<section>
			<div class="group">
				<div><div class="form-blk" align="center" >
						<table>
					<tr>
						<td><a href="${pageContext.request.contextPath}/org/mediaData?id=book"><div class="ac">Book's</div></a></td>
						<td><a href="${pageContext.request.contextPath}/org/mediaData?id=video"><div class="ac">Video's</div></a></td>
						<td><a href="${pageContext.request.contextPath}/org/mediaData?id=tutorial"><div class="ac"> Tutorials's</div></a></td>
					</tr>
					
					
				</table>
				</div>
			</div></div>
		</section>
	</div>
<jsp:include page="include/footer.jsp"></jsp:include>