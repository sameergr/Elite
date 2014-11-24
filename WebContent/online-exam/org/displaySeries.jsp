<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>

	<div class="container" id="main-content">
		<section>
			<div class="form-blk" >
                 			
							 	<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
		                 			 <c:forEach items="${seriesList}" var="series" varStatus="seriesIndex">
		                 			<tr>
					                 <th>Id</th>
					                 <th>Series Name</th>
					                 <th>Level Name</th>
					                 <th>Question</th>
					                 <th>AnswerType</th>
					                 <th>Options</th>
					                  <th>Answer</th>
                 				 	</tr>
                 				 	
							 			<div id="${series.id}">
                 				 	<c:forEach items="${series.levels}" var="level" varStatus="levelIndex">
                 				 		<c:forEach items="${level.questions}" var="content" varStatus="contentIndex" >
                 				 		<tr>
                 				 			<td>${level.id}</td>
                 				 			<td>${series.seriesName}</td>
											<td>${level.levelname}</td>
											<td>${content.question.question}</td>
											<td>${content.answerType}</td>
											<td>
										        <c:if test="${content.answer.discriminator=='MCQ'}">
										        <p>
										        	<span>Q: ${contentIndex.index+1}</span>
										        		<c:forEach var="mcqOptions" varStatus="loop1" items="${content.answer.choiceList}">
										        			(${loop1.index+1}) ${mcqOptions}
										        		</c:forEach>
												</p>
										        </c:if>
										        <c:if test="${content.answer.discriminator=='YN'}">
										        	<p><span>Q: ${contentIndex.index+1}</span> (a) Yes (b) No</p>
										        </c:if>
											</td>
											<td>
										        <c:if test="${content.answer.discriminator=='MCQ'}">
										        	<p><span>Ans: ${contentIndex.index+1}</span>
										        	<c:forEach var="ans" varStatus="innerLoop" items="${content.answer.answerList}">
										        		  ${ans},
										        	</c:forEach>
										        </p>
										        </c:if>
										        
										        <c:if test="${content.answer.discriminator=='YN'}">
										        	<p>Ans : ${contentIndex.index+1} ${content.answer.answer}</p>
										        </c:if>
											</td>
                 				 		</tr>
                 				 		</c:forEach>
                 				 	</c:forEach>
							 </div>
							 </c:forEach></table>
								
				             <h1>List Of All Series</h1>
								<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
		                 			<tr>
					                 <th>Id</th>
					                 <th>Series Name</th>
					                 <th>Category Name</th>
					                 <th>View Series</th>
					                 <th>Edit Series</th>
					                 <th>Delete Series</th>     
                 				 	</tr>
                 				 <c:forEach items="${seriesList}" var="series" varStatus="status">
								 <tr>
									<td>${series.id}</td>
									<td>${series.seriesName}</td>
									<td>${series.category.description}</td>
									<td><a href="${pageContext.request.contextPath}/${userType}/viewSeriesDetail/${series.id}">View</a></td>
									<td><a href="${pageContext.request.contextPath}/${userType}/editSeriesDetail/${series.id}">Edit</a></td>						
									<td><a href="${pageContext.request.contextPath}/${userType}/deleteSeries/${series.id}">DeleteSeries</a></td>
	 							 </tr>
				 				 </c:forEach>
               					</table>
               					<div style="color: #FF2200; margin-top: 20px;" class="mt10">Total Series : ${fn:length(seriesList)}</div>               							
		    		</div>			
			<div style="margin-top: 2%">
				<a href="${pageContext.request.contextPath}/${userType}/createSeries" style="color: #FF2200;" class="mt10" >CREATE NEW SERIES</a>
			</div>
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>

<script>
	var oldSeriesId;
	function displayLevel(seriesId){
	}
</script>