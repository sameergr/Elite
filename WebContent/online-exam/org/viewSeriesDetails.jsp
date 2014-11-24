<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="include/header1.jsp"></jsp:include>

<div class="container" id="main-content">
		<section>
			<div class="form-blk" >
				             <h1>View Series</h1><br>
				             	<form:form modelAttribute="viewSeries" >
				             	<div><h2 style="color: black;">Series Name : <span style="color: red;">${series.seriesName}</span>
				             	<span style="margin-left: 38%">Category Name : <span style="color: red;">${series.category.categoryName}</span> </span>
				             	</h2></div><h1></h1>
				             	
								<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
	                 			 <tr>
					                 <th>Level Name</th>
					                 <th>Total Question</th>
					                 <th>View Levels</th>
                				 </tr>
                				<c:forEach items="${series.levels}" var="level" varStatus="status">
								 <tr>	
									<td>${level.levelname}"</td>
									<td>${fn:length(level.questions)}</td>
									<td><a href="">View</a></td>
								 </tr>
								</c:forEach>
							    
               					</table>     
               				</form:form>      							
		    		</div>			
		</section>
	</div>

						



<jsp:include page="include/footer.jsp"></jsp:include>
	
