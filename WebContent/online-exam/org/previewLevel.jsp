<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.util.TimeZone"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
	<title></title>
	<meta http-equiv="Content-Style-Type" content="text/css" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    
	<link type="text/css" rel="stylesheet" media="all" href="${pageContext.request.contextPath}/online-exam/css/responsive.css" />
	<link type="text/css" rel="stylesheet" media="all" href="${pageContext.request.contextPath}/online-exam/css/style.css" />
	<link href="${pageContext.request.contextPath}/online-exam/css/selectstyle.css" rel="stylesheet" type="text/css" />	
	<script src="${pageContext.request.contextPath}/online-exam/js/classie.js"></script>

	<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	<script type="text/javascript">
   		 var max_time = 5;
   	    var a;
		$( document ).ready(function() {
			alert("ready");
			$("#nextQ").show();
			$("#finish").hide();
		});
		
	    var jsonObj = null;
		function loadQuiz(id) {
	        $.ajax({
	            type: "GET",
	            url: "${pageContext.request.contextPath}/rest/play/quiz/"+id,
	            data:"id="+id,
	            success: function(response) {
	            	jsonObj = response;
	    			//document.getElementById("response").innerHTML = JSON.stringify(jsonObj); 
	    			max_time = jsonObj.series.questionLevelTiming;
	    			a= jsonObj.questions.length;
	    			cinterval = setInterval('countdown_timer()', 1000);
	                //$("#statediv").html(JSON.stringify(response));
	                nextQuestion();
	            }
	        });
	    }
		
		var qIndex = -1;
		function nextQuestion(){
			qIndex++;
			if(jsonObj.questions!= null && jsonObj.questions != undefined && (qIndex+1)  < jsonObj.questions.length){
				//alert(qIndex);
				$("#questionId").val(jsonObj.questions[qIndex].question.id);
				$("#ques").html(jsonObj.questions[qIndex].question.question);
				
			}else{
				alert("finish");
				$("#nextQ").hide();
				$("#finish").show();
				$("#questionId").val(jsonObj.questions[qIndex].question.id);
				$("#ques").html(jsonObj.questions[qIndex].question.question);
			}
		}
		
		var submitRequest = false;
		function submitData(){
			if(submitRequest == true){
				return;
			}	
			submitRequest = true;
			var resultData = createData();
			alert("Submit... " );
	        $.ajax({
	            type: "POST",
	            url: "${pageContext.request.contextPath}/rest/play/saveUserQueAnsPreview",
	            data:JSON.stringify(resultData),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                mimeType: 'application/json',
 	            success: function(response) {
 	            	alert(response.status);
	            },
	            error:function(data,status,er) { 
	                alert("error: "+data+" status: "+status+" er:"+er);
	            }
	        });
		}
		
		function createData(){
			var dataObj = {
				levelId : $("#levelId").val(),
				seriesId : $("#seriesId").val(),
				userId : $("#userId").val(),
				result : jsonArray
				
			};
			return dataObj;
		}
		
	</script>

</head>	

<body onload="loadQuiz(${levelId});">
<div class="container" id="main-content">
		<section>
			<div class="form-blk" align="left" >
				<h1>${levelId} - ${pageContext.request.contextPath}/rest/play/quiz/${levelId}(Demo)</h1>
               		
			</div>
<!--  <script language="JavaScript">
TargetDate = "02/31/2014 5:00 AM";
BackColor = "orange";
ForeColor = "navy";
CountActive = true;
CountStepper = -1;
LeadingZero = true;
DisplayFormat = "%%D%% Days, %%H%% Hours, %%M%% Minutes, %%S%% Seconds.";
FinishMessage = "It is finally here!";
</script>
<script language="JavaScript" src="http://scripts.hashemian.com/js/countdown.js"></script> -->
<script language="javascript">

    var cinterval;
    var counter = 0;
    function countdown_timer(){
	    max_time--;
	    document.getElementById('countdown').innerHTML = max_time;
	    if(max_time == 0){
	    	
	    	max_time = jsonObj.series.questionLevelTiming;
	    	 a--;
	    	if(a==0){
	    		clearInterval(cinterval);
	    		//createJsonData();
	    	    //submitData(); 
	    	}else{
	    		createJsonData();
		    	nextQuestion();
	    	}
	    }
    }
    // 1,000 means 1 second.
    
    </script>
    <script>

</script>
            <form:form method="post"  modelAttribute="saveUserQueAnsPreview" action="${pageContext.request.contextPath}/guest-users/saveUserQueAnsPreview">
				<!-- LevelId --><input type="hidden" name="levelId" value="${levelId}" id="levelId">
				<!-- SeriesId --><input type="hidden" name="seriesId" value="${seriesId}" id="seriesId">
				<!-- UserId --><input type="hidden" name="userId" value="${userId}" id="userId">
				<!-- QuesId --><input type="hidden" name="result" value=""  id="result">
				<!-- Question Level Time --><input type="hidden" name="questionLevelTime" value="${questionLevelTime}" id="questionLevelTime">
				<!-- NoOfQuestion --><input type="hidden" name="noq" value="${fn:length(level.questions)}" id="noq">
				<span style="color:red;font-weight: bolder;" id="countdown"></span>
				<span style="color:black;font-weight: bolder;">minutes</span>
		                    
					<div class="form-blk" >
						<tr>
							<td><b>Question :</b><input type="hidden" name="questionId" value="" id="questionId"></td>
							<div id="ques"></div>
						</tr></br>
						<tr>
							<td><input type="radio" name="answer" value="yes" id ="answerYes">Yes</td></br>
						</tr>
												 
						<tr>
							<td><input type="radio" name="answer" value="no" id ="answerNo"/>No</td>
						</tr>
					</div>	            
	            
					
	             <input id="nextQ" type="button" value="Next" onclick="createJsonData(),nextQuestion();">
	             <input id="finish" type="button" value="Finish" onclick="createJsonData()" >
			 </form:form>
			 
			 <div id="abc"></div>
			 <div id="abcd"></div>
			 <div id="response"></div>
		</section>
		
		
		<div id = "placeholder"></div>
</div>
</body> 
<script type="text/javascript">
		
		var jsonArray = [];
		
        function createJsonData(){
        	
        	var radios = document.getElementsByName('answer');
        	var answer = '';
        	for(var i=0;i<radios.length;i++)
        		{
        	   		if(radios[i].checked)
        		   	answer = radios[i].value;
        		}
        	
        	var JSONObject={
        	"questionId" : document.getElementById("questionId").value,        
        	"answer" : answer,
        	"correct" : jsonObj.questions[qIndex].answer.answer
    		};

        	jsonArray.push(JSONObject);
        	//jsonArray.push(JSON.stringify(jsonObj.questions[qIndex].answer));
        	var jsonstr=JSON.stringify(jsonArray);
        	
        	//var jobjson=JSON.parse(jsonstr);
			document.getElementById("abc").innerHTML = jsonstr ;
			
        }
		
    </script>
