<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<c:if test="${featureList == null}">
	<div id="featureTable" style="margin-top: 30px;">
	</div>
</c:if>



<c:if test="${featureList != null}">
<div id="featureTable" style="margin-top: 30px;">
						<table  width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
		                 <tr>
		                   <th>Feature Name</th>
		                   <th>Feature Type</th>
		                   <th>View Type</th>
		                   <th>Status</th>
		                 </tr>
							<c:forEach var="memberFAL" varStatus="loop" items="${featureList}">
								<c:if test="${memberFAL.featureType != 'NOTALLOWED'}">
									<tr>
											<td>${memberFAL.feature.featureName}</td>
											<td>${memberFAL.featureType}</td>
											<td>${memberFAL.feature.viewType}</td>
											<td>${memberFAL.feature.active}</td>
									</tr>
								</c:if>
								</c:forEach>
		                   
		                 </table>
</div>

</c:if>