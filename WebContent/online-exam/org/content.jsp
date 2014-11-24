<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="include/header1.jsp"></jsp:include>
<!DOCTYPE html>
<html>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script> -->
    </head>
    <body>
    
     		<script>
                function fn(){            
                    if(document.getElementById("correct").checked == true){
                    }
                    else if(document.getElementById("correct").checked == false){
                    	document.getElementById("correct").innerHTML="unchecked";
                        
                    }
                }
            </script>  
    
         <script>
            $(document).ready(function(){
                	$("#mcq").hide();
                	$("#subjective").hide();
	                	
    	          	$("#y").click(function(){
        	        	$('input[name="yesno"]').removeAttr('disabled', 'disabled');
            	    	$("li").toggle();
                		$("#mcq").toggle();
                		$("#subjective").toggle();
              		});
            });
        </script>
        <script>
            $(document).ready(function(){
              $("#m").click(function(){			// MCQ  radio button click
              $('input[name="yesno"]').attr('disabled', 'disabled');
              $('input[name="yesno"]').attr('value', 'yo');
              $("#mcq").toggle();
              $("li").toggle();
              });
            });
        </script>
        
        <script>
            $(document).ready(function(){
              $("#s").click(function(){			// Subjective  radio button click
           	  $("#mcq").hide();
              $('input[name="yesno"]').attr('disabled', 'disabled');
              $('input[name="yesno"]').attr('value', 'yo');
              $("#mcq").toggle();
              $("li").toggle();
              $("#subjective").toggle();
              });
            });
        </script>
        
        <script type="text/javascript">
					function addStartTimeJS(){
					}	
					$(document).ready(function(){
	
						$('body').on('click', '.delStartTime', function () {
						$(this).parents("tr").find(':checkbox').removeAttr('checked');
					});

						$("#mcqAdd").click(function(){
							$("#list").append("<li><p><input type='text' name='correctAnswer' value='' id='opt' required/><input type='checkbox' name='correctAnswer' value='1' id='correct' onclick='fn();' />Choose Correct Answer</p></li>");
						});
					});
		</script>
            
   	<div class="container" id="main-content content">
		<section>
            <form:form action="${pageContext.request.contextPath}/${userType}/submitQuestions" method="post" modelAttribute="submitQuestion">
          	  
          	  	<div>Min. Proficiency:
          	  		<select name="minProficiency">
          	  			<option value="0">Beginner</option>
          	  			<option value="1">Intermidiate</option>
          	  			<option value="2">Advanced</option>
          	  			<option value="3">Expert</option>
          	  		</select>
          	  		
          	  		<span style=" margin-left: 3%"></span>
          	  		
          	  		Max. Proficiency:
          	  		<select name="maxProficiency">
          	  			<option value="0">Beginner</option>
          	  			<option value="1">Intermidiate</option>
          	  			<option value="2">Advanced</option>
          	  			<option value="3">Expert</option>
          	  		</select>
		       	</div>
		       	
		       	<%-- <div>Proficiency :
	       			<form:select path="proficiencyType">
	       			<form:options items="${proficiencyType.proficiencyType}"/>
	       			</form:select>
	       		</div> --%>
		       	
            <div>
            	<p>Enter your Question  <input type="text" name="question" width="300" required/></p>
            	<p><input type="radio" name="questionType" value="0" checked="checked" id="y"/>Yes/No</p>
                <p style="margin-left: 8%; margin-top: -2.8%"><input type="radio" name="questionType" value="1" id="m"/>MCQ</p>
                <p style="margin-left: 16%; margin-top: -3.5%"><input type="radio" name="questionType" value="2" id="s"/></p>Subjective</p>
        		<ul>
            		<li id="yes"><p>Yes<input type="radio" name="answer" value="Yes" /></p></li>
            		<li id="yes"><p>No<input type="radio" name="answer" value="No" checked="checked" /></p></li>
        		</ul>
        	</div>
        	
            <div id="mcq">
                    <a href="#" name="check" value="ON" id="mcqAdd" ">Add options</a>
                    <ol id="list" type="a">
                    </ol>
            </div>
            
            <div id="subjective">
            	<textarea rows="10" cols="10" placeholder="Type Answer" name="subjectiveAnswer"></textarea>
            </div>
            
            <input type="hidden" value="${subCategoryId}" name="subCategoryId"/>
            <input type="hidden" value="${userType}" name="userType"/>
            <input type="submit" value="Save Question" class="btnstyle-2"/>
          
        </form:form>
        </section>
        
        </div>
    </body>
</html>

<jsp:include page="include/footer.jsp"></jsp:include>