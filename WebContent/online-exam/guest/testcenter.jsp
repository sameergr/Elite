<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
						
<jsp:include page="../include/header.jsp"></jsp:include>
<head>
<script type="text/javascript">
/* function fn(){
	alert("hi");
} */

</script>
<script>
/* $(document).ready(function(){
  $("select").change(function(){
    alert("The text has been changed.");
  });
}); */
 
$(document).ready(function(){
	alert("1");
  $("select").change(function(){alert("2");
    $.ajax({url:"${pageContext.request.contextPath}/guest-users/subCategories",success:function(result){
      $("#div1").html(result);
    }});
  });
});
 
</script>


</head>
	<div class="container" id="main-content">
		<section>
		<%-- <p><c:out value="${err}"></c:out></p>
		 --%>
			<div id="register" class="col spn_1_2">
				<h1>Create Test Center</h1>
				<div class="form-blk">
					<form:form  action="testCenter" modelAttribute="testcenter"  method="GET">
						<div class="form-item">
							<div class="form-value" >
							Plan type
							<%-- <form:select path="" onchange="fn()" id="pType" >
								<form:option  value="a" label="a"/>
								<form:option value="a1" label="a1"/>
							</form:select>  --%>
							
							<select id="pType">
								<option value="1" >a1</option>
								<option value="2">a2</option>
								<option value="3">a3</option>
							</select>
							categoryMapData:==${categoryMapData }
							</div>
						</div>
						<div class="form-item">
							<div class="form-value">
								Amount<input type="text" placeholder="amount"   />
							</div>
						</div>				  
                   </form:form>
				</div>
			</div>
			<div class="col spn_1_2" id="div1">
			</div>
		</section>	
	</div>
<jsp:include page="../include/footer.jsp"></jsp:include>
