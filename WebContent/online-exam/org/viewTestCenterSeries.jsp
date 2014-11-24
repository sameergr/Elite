<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- <script>
      $(document).ready(function() {
      alert("d");
      $('#popup_js').click(function(){
      $('#popup').abPopup('show', {'width' : '700'}) ;
      	});
      });
</script> -->	

<script>
		function openWinPreview(id){
			window.open("${pageContext.request.contextPath}/org/playLevelPreview/"+id+"","_blank",
					"top=100, left=300, width=600, height=400");
		}

		function openWinPlay(id){
		window.open("${pageContext.request.contextPath}/org/playLevel/"+id+"","_blank",
				"top=100, left=300, width=600, height=400");
		}
</script>
					
<jsp:include page="include/header1.jsp"></jsp:include>

	<div class="container" id="main-content">
		<section>
			<div class="form-blk" >
				             <h1>${series.seriesName}</h1>
				             	<form:form >
								<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
	                 			 <tr>
	                 			 	 <th>Level's Id</th>
					                 <th>Level's Name</th>
					                 <th>Total Question</th>
					                 <th>Passing Score</th>
					                 <th>Min.Score for Mastery</th>
					                 <th>Level's Order</th>
					                 <th>Max. Hit or try</th>
					                 <th>Score</th>
					                 <th>Tot.Attempts</th>
					                 <th>Play Level</th>
					                 
					                 <!-- <th>Levels</th>
					                 <th>Play</th> -->					                
                				 </tr>                				 
                				 <c:forEach items="${levels}" var="level" varStatus="status">
                				 	<tr>
										<td>${level.id}</td>
										<td>${level.levelname}</td>
										<td>${fn:length(level.questions)}</td>
										<td>${level.passingScore} %</td>
										<td>${level.masteryScore} %</td>
										<td>${level.levelOrder}</td>
										<td>${level.maxTryOrHit}</td>
										<td>${scoreMap[level.id].score}</td>
										<td>${scoreMap[level.id].totalTryOrHits}</td>
										<td>
										<a style="color: green;" id="popup_js"  onclick="openWinPlay('${level.id}')"> Play </a>
										<c:if test="${prview == true}">
										/ <a style="color: green;" id="popup_js"  onclick="openWinPreview('${level.id}')"> Preview </a>
										</c:if>
										</td>										
								  	</tr>
								  </c:forEach>
               					</table>  
               				</form:form>      							
		    		</div>			
		</section>
	</div> 
	
	
	<!-- <div class="popup hidden" id="popup">
      	<div class="popup-title">Sign In</div>
        	<div class="popup-content">
          			Lorem Ipsum is simply dummy text of the printing and 
        			typesetting industry. Lorem Ipsum has been the industry's 
        			standard dummy text ever since the 1500s, when an unknown 
        			printer took a galley of type and scrambled it to make a 
        			type specimen book. It has survived not only five centuries,
        			 but also the leap into electronic typesetting, remaining essentially 
        			 unchanged. It was popularised in the 1960s with the release of Letraset 
        			 sheets containing Lorem Ipsum passages, and more recently with desktop 
        			 publishing software like Aldus PageMaker including versions of Lorem Ipsum.
        	</div>
        <div class="popup-footer">Footer</div>
     </div> -->
      		
      		
<jsp:include page="include/footer.jsp"></jsp:include>
	
