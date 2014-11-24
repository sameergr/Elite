<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


	<table width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
		<tr >
			<th>Question</th>
			<th>QuestionId</th>
			<th>Action</th>                   
		</tr>
		<c:forEach items="${question}" var="content" >
		<tr>
			<td id="que">${content.question.question}</td>
			<td id="queid">${content.question.id}</td>
			<td id="addAction"><input type="button" value="ADD"  onclick="addMember('${content.question.id}','${content.question.question}');"/></td>
		</tr>
		</c:forEach>
	</table>