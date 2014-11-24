<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
						
<jsp:include page="include/header1.jsp"></jsp:include>
<head>
<script type="text/javascript">
var arr = new Array();
function addMember(id,question){
	/* alert("levelname:"+document.getElementById("levelname").value);
	alert("question:"+document.getElementById("question").value);
	alert("series:"+document.getElementById("series").value);
	    alert(id + " , " + question ); */  
	   
	   var index = $.inArray(id, arr);
	   if(index < 0 ){
		   arr.push(id);
		$("#ques").append("<tr><td><input type='checkbox' checked='checked' id='questionIds' name='questionIds' value='"+id+"'/></td><td>"+question+"</td><td><a class=\"delStartTime\">Delete</a></td></tr>");
					 }
	   else{ 
		   alert("Question already selected.");
		    }	   
}

$(document).ready(function(	){

	$('body').on('click', '.delStartTime', function () {
	$(this).parents("tr").find(':checkbox').removeAttr('checked');
});


});


</script>
<style type="text/css">
	.form-actions div {
	    display: inline;
	    margin-right: 8px;
	}
</style>
 
<script type="text/javascript">

 function onchangeajaxfn(value)
{	 
	 var subCatId = $("#categoryId").val();
	 alert("Prof = "+value+" , SubCategoryId = " + subCatId);
	 var xmlhttp=null;
	 if (window.XMLHttpRequest)
	   {// code for IE7+, Firefox, Chrome, Opera, Safari
	   xmlhttp=new XMLHttpRequest();
	   }
	 else if(window.ActiveXObject)
	   {// code for IE6, IE5
	   xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	   }
	 document.getElementById("statediv").innerHTML='<img border="0" src="${pageContext.request.contextPath}/online-exam/images/loading.gif">'; 
	 
	 xmlhttp.onreadystatechange=function()
	  {  
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {  
		    document.getElementById("statediv").innerHTML=xmlhttp.responseText;
	    }
	  } 
		xmlhttp.open("post","${pageContext.request.contextPath}/${userType}/questionWithProficiency/"+value+"/"+subCatId,true);
		xmlhttp.send(value);
} 
 </script>
</head>
	<div class="container" id="main-content">
		<section>
		<%-- <p><c:out value="${err}"></c:out></p> --%>
			<div id="register" class="col spn_1_2">
				<h1>Create Level</h1>
					<form:form  action="${pageContext.request.contextPath}/${userType}/createLevel" modelAttribute="levelForm"  method="POST">
						<div class="form-blk">
						
						<!-- Subcategory -->
							<div><span style="color: black;"> Click on select box For changing SubCategory:</span><br><br>  
		                        <select name="categoryId" id="categoryId" onchange="fn(this.value);">
			                       <option value="${series.category.id}" selected="selected">${series.category.id}--${series.category.categoryName}</option>
				                   <c:forEach items="${subCategories}" var="Subcategory">
				                   		<c:if test="${Subcategory.id != series.category.id}">
				                   			<option value="${Subcategory.id}">${Subcategory.id}--${Subcategory.categoryName}</option>
				                   		</c:if>
					               </c:forEach>
			                    </select>
		                    </div><br>
						
						<!-- Proficiency Section -->
	                    <div class="form-item" style="color: #9CA000;">
							<div class="form-value ">
								<div>Level Name<input type="text"  name="levelname" id="levelname"></div>
								<div>Proficiency Level:<br>
	                    			<select name="proficiency" onchange="onchangeajaxfn(this.value);" id="proficiency">
	                    				<option value="0">Beginner</option>
	                    				<option value="1">Intermidiate</option>
	                    				<option value="2">Advance</option>
	                    				<option value="3">expert</option>
	                    			</select>
	                    		</div> 								
							</div>
						</div>
						
	                    <!-- Series Id Hidden -->      
	                    <input type="hidden" name="seriesId" id="seriesId" value="${series.id}">
	                                            
	                    <!-- Add Question -->
	                    <div class="form-label">Select Question</div>
							<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1"  id="ques">
		                 		<tr>
		                 	   		<th>Select</th>		                 	   
			                   		<th>Question</th>
			                   		<th>Action</th>                   
		              	    	</tr>
		                	</table><br>
		                	
		                <!-- Select and Cancel Button --> 
		                <div class="form-actions">
	                       <input type="submit" value="Submit" class="right button"/>
	                       <a style="text-decoration: none;" class="butmon" href="${pageContext.request.contextPath}/${userType}/dashboard">Cancel</a>
	                    </div><br>
	                    
	                </form:form>
            </div> 
    </div>
    
    <!-- List of Questions after proficiency -->
	<div class="col spn_1_2"  >	 
		<h3>Category id: ${series.category.id}/ Series name: ${series.seriesName}/ Series id: ${series.id}</h3>
			<h1>All Question List</h1>
				<div id="statediv"></div>
	</div>
		</section>	
	</div>
	
<jsp:include page="include/footer.jsp"></jsp:include>
