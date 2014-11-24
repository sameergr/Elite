<jsp:include page="include/header1.jsp"></jsp:include>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<div class="container" id="main-content">
		<section>
			<div class="group">
				<div class="col spn_1_2">
				 	<h1>Edit </h1>
					<form:form  modelAttribute="editMediaFile" action="${pageContext.request.contextPath}/org/updateMediaFile/${mediaData.id}" class="form-style-1">
						  <c:forEach items="${mediaData}" var="mediaData" varStatus="mediaIndex">
							Media Description:<input type="text" name="discription" value="${mediaData.mediaDescription}"/>
							Subscription Type:<input type="text" name="subtype" value="${mediaData.subscriptionType}"/>
							Media Type:<input type="text" name="mediaType" value="${mediaData.mediaType}"/>
							<input type="text" name="ids" value="${mediaData.id}"/>
		                  </c:forEach>
		                        <input type="submit" value="Submit" class="right btnstyle-2"/>
		            </form:form>
				</div>
			</div>
		</section>
	</div>
<jsp:include page="include/footer.jsp"></jsp:include>