<jsp:include page="include/header1.jsp"></jsp:include>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
	<SCRIPT language="javascript">
$(function(){
 
    // add multiple select / deselect functionality
    $("#selectall").click(function () {
          $('.case').attr('checked', this.checked);
    });
 
    // if all checkbox are selected, check the selectall checkbox
    // and viceversa
    $(".case").click(function(){
 
        if($(".case").length == $(".case:checked").length) {
            $("#selectall").attr("checked", "checked");
        } else {
            $("#selectall").removeAttr("checked");
        }
 
    });
});
</SCRIPT>
	<div class="container" id="main-content">
		<section>
			<div class="group">
				<div class="col spn_1_2">
				<h1>Send Request Follow Friend</h1>
					<form:form  modelAttribute="sendInvitation" action="${pageContext.request.contextPath}/org/sendInvitation" class="form-style-1">
	                 <table  width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
									<tr>
										<th><input type="checkbox" id="selectall"/>Email</th>
									</tr>
									<c:forEach items="${listOfMember}" var="mediaData" varStatus="listOfMemberIndex">
										<tr>
											<td><input type="checkbox" class="case" name="memberIds" value="${mediaData.memberId}" />
											${mediaData.account.email}</td>
										</tr>
									</c:forEach>
						</table>
						 <input type="submit" name="send" value="Invite"/> 
						 
	                </form:form>

</div>
			</div>
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>