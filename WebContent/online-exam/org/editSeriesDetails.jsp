<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
						
<jsp:include page="include/header1.jsp"></jsp:include>

<%-- <div class="container" id="main-content">
		<section>
			<div class="form-blk" >
				             <h1>Edit Series</h1>
				             	<form:form method="post"  modelAttribute="updateSeries" action="${pageContext.request.contextPath}/${userType}/updateSeries" class="form-style-1">
								<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
	                 			 <tr>
					                 <th>Series Id</th>
					                 <th>Series Name</th>
					                 <th>Levels</th>
					                 <th>Questions</th>
                				 </tr>
                				 <tr>
	                				  <c:forEach items="${seriesList}" var="series" varStatus="status">
												<td><input type="text" value="${series.id}" name="seriesId" readonly="readonly"/></td>
												<td><input type="text" value="${series.seriesname}" name="seriesname"/></td>
												<td>
													  <c:forEach items="${series.levels}" var="level" varStatus="status">
													  	 <c:forEach items="${level.questionlist}" var="content" varStatus="status">
													  	   <input type="text" value="${level.levelname}" name="levelname"/> 
														</c:forEach>
													  </c:forEach>
												</td>
									 </c:forEach>
									  <c:forEach items="${seriesList}" var="series" varStatus="status">
												<td>
													  <c:forEach items="${series.levels}" var="level" varStatus="status">
													  	 <c:forEach items="${level.questionlist}" var="content" varStatus="status">
															 <input type="text" value="${content.questions.question}" name="questions" readonly="readonly"/> 
														</c:forEach>
													  </c:forEach>
												</td>
									 </c:forEach>
								  </tr>
								 	<tr><td></td><td></td><td><input type="submit" value="Edit"/></td><td></td></tr>
               					</table>     
               				</form:form>      							
		    		</div>			
		</section>
	</div> --%>
	<div class="container" id="main-content">
		<section>
			<div class="form-blk">
				             <h1>Edit Series</h1>
				             	<form:form method="post" id="viewseries" name="viewseriesForm"  modelAttribute="updateSeries" action="${pageContext.request.contextPath}/${userType}/updateSeries" class="form-style-1">
								<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
	                 			 <tr>
					                 <th>Series Id</th>
					                 <th>Series Name</th>
					                 <th>Levels</th>
					                 <th>Questions</th>
					                 <th>Answer</th>
                				 </tr>
                				 <tr>
	                				  <c:forEach items="${seriesList}" var="series" varStatus="status">
												<td><input type="text" value="${series.id}" name="seriesId" readonly="readonly"/></td>
												<td>
													<div align="left" >
														<input type="text" value="${series.seriesName}" name="seriesname" />
													</div>
												</td>
												<td>
													  <c:forEach items="${series.levels}" var="level" varStatus="status">
													  	<div>
														  	 <c:forEach items="${level.questions}" var="content" varStatus="status">
														  	  <input type="text" value="${level.levelname}" name="levelname"/> 
															 </c:forEach>
															 <div><table style="width: 5%;"><tr><td style="background: #888A04;"></td></tr></table></div>
														</div>
													  </c:forEach>
												</td>
									 </c:forEach>
									  <c:forEach items="${seriesList}" var="series" varStatus="status">
												<td>
													  <c:forEach items="${series.levels}" var="level" varStatus="status">
													  	 <c:forEach items="${level.questions}" var="content" varStatus="status">
															 <input type="text" value="${content.question.question}" name="questions" id="questions" /> 
														</c:forEach>
														<div><table style="width: 5%;"><tr><td style="background: #888A04;"></td></tr></table></div>
													  </c:forEach>
												</td>
												<td>
												 <c:forEach items="${series.levels}" var="level" varStatus="status">
													 <c:forEach items="${level.questions}" var="content" varStatus="contentIndex" >
												        <c:if test="${content.answer.discriminator=='MCQ'}">
												        	<c:forEach var="ans" varStatus="innerLoop" items="${content.answer.answerList}">
												        	 <input type="text" value="${ans}" name="answers" id="answers" /> 
												        	</c:forEach>
												        </c:if>
											        
												        <c:if test="${content.answer.discriminator=='YN'}">
												         <input type="text" value="${content.answer.answer}" name="answers" id="answers" /> 
												        </c:if>
											        </c:forEach>
											        
										        </c:forEach>
											</td>
									 </c:forEach>
									
										
											
								  </tr>
								   	
               					</table>  
               					<input type="submit" value="Save" style="display: none;" />
               				</form:form>      							
		    		</div>	
		    				
		</section>
	
<jsp:include page="include/footer.jsp"></jsp:include>
