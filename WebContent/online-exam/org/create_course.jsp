<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
		
		<form:form modelAttribute="course" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/org/createCourse">
            <fieldset>
                <legend>Create Course</legend>
 
                <p>
                    Course Name<br/>
                    <input name="courseName"/>
                </p>
 				<p>
                    Course Description<br/>
                    <textarea name="courseDescription"></textarea>
                </p>
                <p>
                    Choose File<br/>
                    <input name="courseFile" type="file"/>
                </p>
				<p>
                    Course Type<br/>
                    <form:select path="courseType">
						<form:options items="${courseType}" />
					</form:select>
                </p>
                <p>
                    <input type="submit" />
                </p>
 
            </fieldset>
        </form:form>
        
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>        

</body>
</html>