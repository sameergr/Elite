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
				<h1>Friend List</h1>
					<form:form  modelAttribute="followFriend" action="${pageContext.request.contextPath}/org/folowFriend" class="form-style-1">
	                 <table  width="100%" border="0" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" class="table_style1">
									<tr>
										<th>Email Id</th>
										<th>Organization</th>
										<th>Registration Date</th>
										<th>Last Login Time</th>
										<th>Role</th>
										<th>Approval</th>
										<th>Follow</th>
										<th>Active</th>
										<th>Action</th>
									</tr>
									<c:forEach items="${approvedFriends}" var="mediaData" varStatus="listOfMemberIndex">
										<tr>
											<td>${mediaData.friend.account.email}</td>
											<td>${mediaData.organization.name}</td>
											<td>${mediaData.friend.registrationDate}</td>
											<td>${mediaData.friend.lastLoginTime}</td>
											<td>${mediaData.friend.memberRole}</td>
											<td>${mediaData.approved}</td>
											<td>${mediaData.follow}</td>
											<td>${mediaData.active}</td>
											<td>
											<table>
												<tr>
												<c:if test="${mediaData.follow==false}">
													<td style="background: #8FD1AA;">
													<a href="${pageContext.request.contextPath}/org/getfollowFriend/${mediaData.id}">FollowFriend</a>
													</td>
												</c:if>
													<td style="background: #F79577;">
													<a href="${pageContext.request.contextPath}/org/deleteFollowFriend/${mediaData.id}">DeleteFriend</a>
													</td>
													<c:if test="${mediaData.follow==true}">
													<td style="background: #C6AD1E;">
													<a href="${pageContext.request.contextPath}/org/removeFollowFriend/${mediaData.id}">RemoveFollowFriend</a>
													</td>
													</c:if>
												</tr>
											</table>
											</td>
										</tr>
									</c:forEach>
						</table>
						<a href="${pageContext.request.contextPath}/org/approveFriend"><input type="button" name="approval" value="Approval"/></a>
						<a href="${pageContext.request.contextPath}/org/addInviteFriend"><input type="button" name="addFolowFriend" value="Add Friend"/></a>   
	                </form:form>

</div>
			</div>
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>