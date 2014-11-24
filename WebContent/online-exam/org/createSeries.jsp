<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>
<script type="text/javascript">


 function onchangeajaxfn(Id)
{	 
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
		xmlhttp.open("post","${pageContext.request.contextPath}/${userType}/loadSubCategory/"+Id,true);
		xmlhttp.send(Id);
} 
 </script>
	<div class="container" id="main-content">
		<section>
			<div id="register" class="col spn_1_2">
				<h1>Create Series</h1>
				
					<form:form action="${pageContext.request.contextPath}/${userType}/saveSeries" modelAttribute="createcSeries"  method="POST">
					<div class="form-blk">
							<table>
								<tr>
									<td>
										<div class="form-item">
											<div class="form-label">Series&nbsp;Name:</div>
											<div class="form-value">
												<input type="text"  name="seriesname" id="seriesname">
											</div>                    
										</div>
				                    </td>
				                    
				                    <td>
				                    	 <div class="form-item">
					                        <div class="form-label">Question&nbsp;Time</div>
					                        <div class="form-value">
					                            <input type="text"  name="oneQuestionTime" id="oneQuestionTime">
					                        </div>                    
					                    </div>
				                    </td>
				                </tr>
			                    
			                    <tr>
				                    <td>
				                     <div class="form-item">
										<div class="form-label">Category:</div> 
										 <div class="form-value">
											  <select name="categoryId" id="categoryId"  onchange="onchangeajaxfn(this.value);" >
													<option value="0" selected="selected">------Please select---------</option>
												 <c:forEach items="${categoryList}" var="category">
													<option value="${category.id}">${category.categoryName}</option>
												 </c:forEach>
											 </select>
										 </div>
									 </div>
				                    </td>
				                    	
				                    <td>
				                    	<div class="form-item">
					                        <div class="form-label">Total&nbsp;Time:</div> 
					                         <div class="form-value">
					                            <input type="text"  name="totalQuestionTime" id="totalQuestionTime">
					                        </div>
				                   		 </div>
				                   	</td>
				                    </tr>
			                    <tr>
			                    	<td> <div  id="statediv" >
				                     	
			                   		 </div></td><td></td>
			                    
			                    </tr>
			                    <tr>
			                    	<td><a style="text-decoration: none;" class="button" 
							href="${pageContext.request.contextPath}/${userType}/seriesAutoGen">Series Auto Generate</a></td>
				                    <td><input type="submit" value="Submit" style="text-decoration: none;"  class="button"/></td>
				                    
			                    </tr>
			                   	</table>
                   		 </div>  	
 				</form:form>
				</div></section>	
			</div>
			 
		
	 
<jsp:include page="include/footer.jsp"></jsp:include>
