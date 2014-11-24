<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>


<div class="container" id="main-content">
	<section>
	<c:out value="${err}"></c:out>
		<div id="register" class="col spn_3_3">
			<h1>Create Series Automatically :</h1>
				
			<form:form action="${pageContext.request.contextPath}/${userType}/saveAutoGenSeries" modelAttribute="saveAutoSeries"  method="POST">
				<div class="form-blk">
				<!-- Series Block -->
	            	<div class="form-item" style="color: #9CA000;">
						<div class="form-value ">
							<div>Series Name:<input type="text"  name="seriesName" id="seriesName"></div>
							<div>Select Category:<br><select name="categoryId" id="categoryId"  onchange="onchangeajaxfn(this.value);" >
		                         <option value="0" selected="selected" style="width: 25%">------Please select---------</option>
			                     <c:forEach items="${categoryList}" var="category">
			                     <option value="${category.id}">${category.categoryName}</option>
				                 </c:forEach>
		                         </select>
		                    </div>
						 </div>
					</div>
					
					<!-- Level Block -->
					<h2 style="color: black;">Level Block:</h2>
					<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1"  id="createLevel">
		                 		<tr>
		                 	   		<th>Level Name:</th>		                 	   
			                   		<th>Proficiency Level :</th>
			                   		<th>Total No. Of Questions:</th>
			                   		<th>Add Button</th>                  
		              	    	</tr>
		              	    	<tr>
		              	    		<td><input type="text"  name="levelName" id="levelname" placeholder="level name...."></td>
		              	    		<td><select name="proficiency" onchange="onchangeajaxfn(this.value);" id="proficiency">
	                    					<option value="0">Beginner</option>
	                    					<option value="1">Intermidiate</option>
	                    					<option value="2">Advance</option>
	                    					<option value="3">expert</option>
	                    				</select>
	                    			</td>
		              	    		<td><input type="text"  name="totalQuestion" id="totalQuestion"></td>
		              	    		<td id="addAction"><input type="button" value="ADD"  onclick="addMember($(levelname).val(),$(proficiency).val(),$(totalQuestion).val());" style="text-decoration: none;"/></td>
		              	    	</tr>
		            </table><br>
		            
		            <!-- Add Levels -->
		            <table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1"  id="level">
		                 		<tr>
		                 			<th>Selceted Level:</th>
		                 	   		<th>Level Name:</th>		                 	   
			                   		<th>Proficiency Level :</th>
			                   		<th>Total No. Of Questions:</th>
			                   		<th>Add Button</th>                  
		              	    	</tr>
		            </table><br>
           		 	
           		 	<!-- Submit Button -->
                   	<div class="form-item" style="color: #9CA000;">
               			<div class="form-value ">
	                    	<input type="submit" value="Submit" class="button" style="text-decoration: none;"/>
	                    </div>
	                </div>
	                    
                </div>	
 			</form:form>
		</div>
	</section>	
</div>
			 
<script type="text/javascript">
	var arr = new Array();
	var count= 0;
	function addMember(levelName, proficiency, totalQues){
		alert("hi : "+levelName +" Pro : "+proficiency+ " NO : "+totalQues);
		arr.push(count);
	  	alert("hello" + arr +" count-->" +count);
		$("#level").append("<tr><td><input type='checkbox' checked='checked' id='levels["+count+"].levelname' name='levels["+count+"].levelname' value='"+levelName+"'/></td><td>"+levelName+"</td><td>"+proficiency+"<input type='hidden' id='levels["+count+"].proficiency' name='levels["+count+"].proficiency' value='"+proficiency+"'/></td><td>"+totalQues+"<input type='hidden' id='levels["+count+"].totatlQuestion' name='levels["+count+"].totatlQuestion' value='"+totalQues+"'/></td><td><a class=\"delStartTime\">Delete</a></td></tr>");
		count++;
	}

	$(document).ready(function(	){
		$('body').on('click', '.delStartTime', function () {
		$(this).parents("tr").find(':checkbox').removeAttr('checked');
	});
});
</script>		
	 
<jsp:include page="include/footer.jsp"></jsp:include>
