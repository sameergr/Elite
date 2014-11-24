
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>


<div class="form-blk" >
	<tr>
		<td>Question :<input type="hidden" name="questionId" value="${question.id}" id="questionId"></td>${question.question}		
	</tr></br>
							 
	<tr>
		<td><input type="radio" name="answer" value="yes"/ id ="answerYes">Yes</td></br>
	</tr>
							 
	<tr>
		<td><input type="radio" name="answer" value="no" id ="answerNo"/>No</td>
	</tr>
</div>
