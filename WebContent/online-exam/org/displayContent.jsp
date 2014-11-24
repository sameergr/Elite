<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>
<div class="container" id="main-content content">
   <section> 
     <div class="table_views" >	
		<div class="form-blk" > <h1>View Content</h1>
        	<table>
        		<thead>
        			<tr>
        				<td id="heading">Question</td>
        				<td id="heading">Question Type</td>
        				<td id="heading">Options</td>
        				<td id="heading">Answers</td>
        				<td id="heading">Sub-Categories</td>
        				<td id="heading">Edit</td>
        			</tr>
        		</thead>
		        	<tr>
		        		<td id="contents">
			        		<c:forEach var="no" varStatus="loop" items="${contents}">
			        			<p>Q: ${loop.index+1}   ${no.question.question}</p>
			        		</c:forEach>
		        		</td>
		
		        		<td id="contents">
			        		<c:forEach var="no" varStatus="loop" items="${contents}">
			        			<p>${no.answerType}</p>
			        		</c:forEach>
		        		</td>
		        		
		        		<td id="contents">
			        		<c:forEach var="no" varStatus="loop" items="${contents}">
						        <c:if test="${no.answer.discriminator=='MCQ'}">
						        	<p><span>Q: ${loop.index+1}</span>
						        		<c:forEach var="mcqOptions" varStatus="loop1" items="${no.answer.choiceList}">
						        			(${loop1.index+1}) ${mcqOptions}
						        		</c:forEach>
						        </c:if>
						        </p>
						        <c:if test="${no.answer.discriminator=='YN'}">
						        	<p><span>Q: ${loop.index+1}</span> (a) Yes (b) No</p>
						        </c:if>
					        </c:forEach>
		        		</td>
		        		<td id="contents">
		        			<c:forEach var="no" varStatus="loop" items="${contents}">
						        <c:if test="${no.answer.discriminator=='MCQ'}">
						        	<p><span>Ans: ${loop.index+1}</span>
						        	<c:forEach var="ans" varStatus="innerLoop" items="${no.answer.answerList}">
						        		  ${ans},
						        	</c:forEach>
						        </c:if>
						        </p>
						        <c:if test="${no.answer.discriminator=='YN'}">
						        	<p>Ans : ${loop.index+1} ${no.answer.answer}</p>
						        </c:if>
				        </c:forEach>
		        		</td>
		        		<td id="contents">
						      <c:forEach var="content" varStatus="loop" items="${contents}">
		        					<p>${content.id}</p>
		        			</c:forEach>
		        		</td>
		        		<td id="contents">
							<c:forEach var="no" varStatus="loop" items="${contents}">
		        					<a href="#" class="button">Edit</a> &nbsp;&nbsp; <a href="#" class="button">Delete</a>
		        			</c:forEach>        		
		        		</td>
		        	</tr>
        	</table>
        </div>
      </div>
   </section>
</div>
<jsp:include page="include/footer.jsp"></jsp:include>
