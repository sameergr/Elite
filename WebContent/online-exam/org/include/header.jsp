<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en" dir="ltr">
<head>
	<title></title>
		<meta http-equiv="Content-Style-Type" content="text/css" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
	<link type="text/css" rel="stylesheet" media="all" href="${pageContext.request.contextPath}/online-exam/css/responsive.css" />
    <link type="text/css" rel="stylesheet" media="all" href="${pageContext.request.contextPath}/online-exam/css/ab_library.css" />	
    <link type="text/css" rel="stylesheet" media="all" href="${pageContext.request.contextPath}/online-exam/css/nav.css" />	
	<link type="text/css" rel="stylesheet" media="all" href="${pageContext.request.contextPath}/online-exam/css/style.css" />	
	<script type="text/javascript" src="${pageContext.request.contextPath}/online-exam/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/online-exam/js/ab_library.js"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/online-exam/js/script.js"></script>	
	<link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600,700' rel='stylesheet' type='text/css'>
	<script>
		$(function() {
			var pull 		= $('#pull');
				menu 		= $('nav ul');
				menuHeight	= menu.height();

			$(pull).on('click', function(e) {
				e.preventDefault();
				menu.slideToggle();
			});

			$(window).resize(function(){
        		var w = $(window).width();
        		if(w > 320 && menu.is(':hidden')) {
        			menu.removeAttr('style');
        		}
    		});
		});
	</script>
	<!-- 
	<script src="http://wcetdesigns.com/assets/javascript/jquery.js"></script>
<script src="http://wcetdesigns.com/assets/javascript/jquery/cookie-plugin.js"></script>
	 -->
<script src="${pageContext.request.contextPath}/online-exam/js/jquery.cookie.js"></script>
<script>
function remember_me(){
   var c = $("#rememberme"); //INPUT CHECKBOX

   //IF CHECKBOX IS SET, COOKIE WILL BE SET
   if(c.is(":checked")){
     var u = $("#username").val(); //VALUE OF USERNAME
     var p = $("#password").val(); //VALUE OF PASSWORD
     $.cookie("username", u, { expires: 3650 }); //SETS IN DAYS (10 YEARS)
     $.cookie("password", p, { expires: 3650 }); //SETS IN DAYS (10 YEARS)
   }
}
//NEXT PAGE LOAD, THE USERNAME & PASSWORD WILL BE SHOWN IN THEIR FIELDS
function load_em(){
   var u = $.cookie("username"); //"USERNAME" COOKIE
   var p = $.cookie("password"); //"PASSWORD" COOKIE

   $("#username").val(u); //FILLS WITH "USERNAME" COOKIE
   $("#password").val(p); //FILLS WITH "PASSWORD" COOKIE
}
</script>
</head>
<body onLoad="load_em()">
	<div class="container" id="header">
		<section>
			<div class="col spn_1_4">
				<div class="logo"><a href="${pageContext.request.contextPath}/site/home">Online Exam</a></div>
			</div>
			<div class="col spn_3_4 align-right">
				<div class="login-wrp">
					<div class="login-blk">
						<form:form action="${pageContext.request.contextPath}/org/login" method="POST" modelAttribute="login" name="loginForm">
							<div class="form-value">
								<input title="Username / Email Address" type="text" name="username" id="username" placeholder="Username/Email Address">
							</div>
							<div class="form-value">
								<input title="Password" type="text" name="password" id="password" placeholder="Password">
							</div>
							<div class="form-value">
								<input title="Remember me" type="checkbox" name="rememberme" id="rememberme" onClick="remember_me()"/>
							</div>
							<div class="form-actions">
								<input type="submit" class="btnstyle-1 yellow" value="Login"/>
								<div class="signup-blk"><a class="btnstyle-1" href="${pageContext.request.contextPath}/site/subscription">Register</a></div>
							</div>
						</form:form>
					</div>
				</div>
					
			</div>
		</section>	
	</div>
	<div class="container" id="nav">
		<section>
			<nav class="clearfix">
				<ul class="clearfix">
					<li class="info"><a href="${pageContext.request.contextPath}/site/home">Home</a></li>
					<li class="info hwuse"><a href="${pageContext.request.contextPath}/site/howtous">How to Use</a></li>
					<li class="info category"><a href="${pageContext.request.contextPath}/site/category">Category</a></li>
					<li class="info subscription"><a href="${pageContext.request.contextPath}/site/subscription">Subscription</a></li>
					<li class="info contact"><a href="${pageContext.request.contextPath}/site/contactus">Contact US</a></li>
					<li class="info category"><a href="${pageContext.request.contextPath}/site/article">Create Article</a></li>	
				</ul>
				<a href="#" id="pull">Menu</a>
			</nav>
		</section>	
	</div>
	
	

