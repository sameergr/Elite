<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
			<div class="table_views" >	
				<div class="form-blk" >			
					<div>
									
									<form:form enctype="multipart/form-data" name="elmsBook1" method="POST" action="${pageContext.request.contextPath}/org/elmsInsert"  modelAttribute="elmsBook" >
									<c:if test="${isOrgnization==true}">
									<input type="file" name="scormfile"  value=""/>
									<input type="text" name="discription"  value="Description:${mediaType}"/> 
									 <input type="hidden" value="${mediaType}" name="mediaType" />
									 <input type="button" value="upload" onclick="elmsBook1.submit()" />
									 </c:if>
									</form:form>
									<table  width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
									<tr>
										<th>Media Name</th>
										<th>Org Id</th>
										<th>Media Des.</th>
										<th>Publish Date</th>
										<th>Media Type</th>
										<th>Subcription Type</th>
										<th>Action</th>
									</tr>
									<c:forEach items="${mediaData}" var="mediaData" varStatus="mediaIndex">
									<tr>
										<td>${mediaData.mediaName}</td>
										<td>${mediaData.organization.id}</td>
										<td>${mediaData.mediaDescription}</td>
										<td>${mediaData.publishDate}</td>
										<td>${mediaData.mediaType}</td>
										<td>${mediaData.subscriptionType}</td>
										<td><table><tr>
										<c:if test="${isOrgnization==true}">
										<td style="background: #8FD1AA;"><a onclick="fn();" href="${pageContext.request.contextPath}/org/elmsEdit/${mediaData.id}">Edit</a></td>
										<td style="background: #F79577;"><a href="${pageContext.request.contextPath}/org/elmsDelete/${mediaData.id}">Delete</a></td>
										</c:if>
										<td style="background: #89B4E5;"><a href="${pageContext.request.contextPath}/org/elmsDownload/${mediaData.id}">Download</a></td></tr></table></td>
										
									</tr>
									</c:forEach>
									</table>
								</div></div></div>
							 
		</section>
	</div>
<jsp:include page="include/footer.jsp"></jsp:include>