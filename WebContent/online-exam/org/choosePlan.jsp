<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="include/header1.jsp"></jsp:include>
<div class="container" id="main-content content">
        <section>
			<div id="register" class="col spn_1_2">
				<h1>Current Plan</h1>
        	<table>
        		<thead>
        			<tr>
        				<td id="heading">Plan Id</td>
        				<td id="heading">Plan Type</td>
        				<td id="heading">Plan Created By</td>
        				<td id="heading">Plan Created On</td>
        			</tr>
        		</thead>
        	<tr>
        		
        		<td id="contents">
					<p>1001</p>
        		</td>

        		<td id="contents">
        			<p>Default (FREE)</p>
        		</td>
        		
        		<td id="contents">
        			<p>Administrator</p>
        		</td>
        		
        		<td id="contents">
        			<p>31-December-2013</p>
        		</td>
        	</tr>
        	</table>
        	<div class="form-blk">
        		<p>Plan A                   - $1/Month/10 Member<a href="${pageContext.request.contextPath}/guest-users/subscription"><input type="button" class="btnstyle-2" value="Buy"/></a></a> </p>  
        	</div>
        			<a href="${pageContext.request.contextPath}/guest-users/home"><input type="button" class="btnstyle-2" value="Home"/></a>
        			<a href="${pageContext.request.contextPath}/guest-users/subscriptionStart"><input type="button" class="btnstyle-2" value="Buy Plan"/></a> 
        	</section>
        	
        </div>

  
<jsp:include page="include/footer.jsp"></jsp:include>
