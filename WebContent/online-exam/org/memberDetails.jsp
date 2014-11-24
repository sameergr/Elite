<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
			<div >
				<div class="form-blk">
						<h1>Member's Details</h1>
						<form>
														
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">
									<div>Email Id :<input type="text" value="${details.account.email}" disabled="disabled"/></div>
									<div>User Name :<input type="text" value="${details.account.email}" disabled="disabled"/></div>									
								</div>
							</div>
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">									
									<div>First Name :<input type="text" value="${details.account.firstName}" disabled="disabled"/></div>
									<div>Last Name :<input type="text" value="${details.account.lastName}" disabled="disabled"/></div>
								</div>
							</div>							
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">									
									<div>Member Role Type :<input type="text" value="${details.memberRole}" disabled="disabled"/></div>
									<div>Organization Id :<input type="text" value="${details.organization.name}" disabled="disabled"/></div>
								</div>
							</div>
							<div class="form-item" style="color: #9CA000;">
								<div class="form-value ">									
									<div>Member Active :<input type="text" value="${details.active}" disabled="disabled"/></div>
									<div>Member Id :<input type="text" value="${details.memberId}" disabled="disabled"/></div>
								</div>
							</div>					
						</form>
					</div>
			</div >
			<%-- <div style="color: #9CA000;">Total Members : ${fn:length(memberList)}</div> --%>
		</section>
	</div>
	

<jsp:include page="include/footer.jsp"></jsp:include>