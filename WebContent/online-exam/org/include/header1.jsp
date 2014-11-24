<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en" dir="ltr">
<head>
	<title></title>
	<meta http-equiv="Content-Style-Type" content="text/css" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    
	<link type="text/css" rel="stylesheet" media="all" href="${pageContext.request.contextPath}/online-exam/css/responsive.css" />
    <link type="text/css" rel="stylesheet" media="all" href="${pageContext.request.contextPath}/online-exam/css/ab_library.css" />	
    <link type="text/css" rel="stylesheet" media="all" href="${pageContext.request.contextPath}/online-exam/css/nav.css" />
    <link type="text/css" rel="stylesheet" media="all" href="${pageContext.request.contextPath}/online-exam/css/common.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/online-exam/css/bootstrap.css" />	
    <link rel="stylesheet" href="${pageContext.request.contextPath}/online-exam/css/dark/css/dark.css" type="text/css" media="screen" /> 
	<link type="text/css" rel="stylesheet" media="all" href="${pageContext.request.contextPath}/online-exam/css/style.css" />
	<link href="${pageContext.request.contextPath}/online-exam/css/ui.dynatree.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/online-exam/css/jquery.pageslide.css" />
	<link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600,700' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/online-exam/css/component.css" />
	<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/online-exam/css/jquery.jgrowl.css'>
	<link href="${pageContext.request.contextPath}/online-exam/css/selectstyle.css" rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="${pageContext.request.contextPath}/online-exam/js/jquery-1.7.1.min.js"></script>

	<script type="text/javascript" src="${pageContext.request.contextPath}/online-exam/js/jquery-1.8.2.min.js"></script> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/online-exam/js/ab_library.js"></script>	 	

	<script type="text/javascript" src="${pageContext.request.contextPath}/online-exam/js/script1.js"></script>

	<script src="${pageContext.request.contextPath}/online-exam/js/jquery.js" type="text/javascript"></script>
	
  	<script src="${pageContext.request.contextPath}/online-exam/js/jquery-ui.custom.js" type="text/javascript"></script>
  	
  	<script src="${pageContext.request.contextPath}/online-exam/js/jquery.cookie.js" type="text/javascript"></script>

  	<script src="${pageContext.request.contextPath}/online-exam/js/jquery.dynatree.js" type="text/javascript"></script>
  	
  	<script type="text/javascript" src="${pageContext.request.contextPath}/online-exam/js/bootstrap-collapse.js"></script>
  	 
  	<script type="text/javascript" src="${pageContext.request.contextPath}/online-exam/js/tiny_mce/tiny_mce.js"></script>
  

	<%-- <script src="${pageContext.request.contextPath}/online-exam/js/classie.js"></script> --%>
	
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
	
	

<!--[if IE]> <link href="css/ie.css" rel="stylesheet" type="text/css"> <![endif]-->
<!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script> -->

<script type="text/javascript" src="${pageContext.request.contextPath}/online-exam/js/plugins/forms/jquery.uniform.js"></script>


<script type='text/javascript'>
    // On load, style typical form elements
    $(function () {
        $("select, input, button").uniform();
    });
</script>	

	<style type="text/css">
		#modal {
		    display: none;
		}
	</style>

</head>

<body>
	<!-- <div class="container" id="header" id="content">
		<section>
			<div class="col spn_1_12"> -->
				<div class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left" id="cbp-spmenu-s1">
					<div class="srch-wrp">
						<div class="search_icon"></div>
						<input type="text" placeholder="Search.."/>
					</div>
					<div class="group compy-blk">
						<div class="inner-img">
						</div>
						<div class="title"><a href="${pageContext.request.contextPath}/org/organization?key=${user.userProfile.organization.id}">${user.userProfile.organization.name}</a></div>
						<div class="title" style="font-weight: normal;">Your Organizations</div>
					</div>
					<div class="submenu-links">
						<ul>
							<c:forEach var="oragnizations" varStatus="loop" items="${userMemberOragnizations}">
								<a href="${pageContext.request.contextPath}/org/organization?key=${oragnizations.organization.id}"><li>${oragnizations.organization.name}</li></a>
								<c:set var="organizationName" value="${oragnizations.organization.name}"></c:set>
							</c:forEach>
						</ul>
					</div>
				</div>
				<img id="showLeft" src="${pageContext.request.contextPath}/online-exam/images/menu.png">
		
		<div class="container" id="header" id="content">
			<section>				
			<div class="col spn_3_12">
			<c:if test="${image != null}">
			<div class="logo">
					<a href="${pageContext.request.contextPath}/org/signOut">
						<img src="${pageContext.request.contextPath}/resources/images/${image}" alt="${image}" />
					</a>
			</div>
			</c:if>
			<c:if test="${image == null}">
				<div class="logo">
					<a href="${pageContext.request.contextPath}/org/signOut">Online Exam</a>
				</div>
			</c:if>
			</div>
			<div class="col spn_9_12">
				<ul class="menu">
					<li>
						<a href="${pageContext.request.contextPath}/org/dashboard">
							<div class="home_icon"></div>
						</a>
					</li>
					<li>
						<a href="">
							<div class="use_icon"></div>
						</a>
					</li>
					<li>
						<a href="">
							<div class="category_icon"></div>
						</a>
					</li>
					<li>
						<a href="">
							<div class="subscription_icon"></div>
						</a>
					</li>
					<li>
						<a href="">
							<div class="contactus_icon"></div>
						</a>
					</li>
				</ul>
				<ul class="user-functions">
					<li>
						<a href="">
							<div class="icons help_icon"></div>
						</a>
					</li>
					<li>
						<a href="">
							<div class="icons srch_icon"></div>
						</a>
					</li>
					<li class="account-wrp">
						<a href="">
							<div class="icons account_icon"></div>
						</a>
		                <div class="account-blk">
		                  	<div class="user-blk">
			                  	<div><img src="${pageContext.request.contextPath}/online-exam/images/defaultavatar_small.png"/></div>
			                    <div>Hi&nbsp; <span name="fname" value="${member.account.firstName}">${member.account.firstName}</span></div><!-- .userProfile.firstName -->
		                	</div>
			                <ul class="user-tabs">
			                    <li>
			                        <a href="${pageContext.request.contextPath}/org/editProfile">Complete your profile</a>
			                    </li>
			                    <li>
			                      <a href="my-account.jsp">Account Settings</a>
			                    </li>
			                    <li>
			                      <a href="">Billing</a>
			                    </li>
			                    <li>
			                      <a href="">Create another organization</a>
			                    </li>
			                </ul>
	                      	<div class="signout-blk">
	                        	<div class="signout-icon"></div>
	                        	<a href="${pageContext.request.contextPath}/org/signOut">Sign out</a>
	                      	</div>
		                </div>
					</li>
					<li>
						<a href="notifications.jsp">
							<div class="icons notification_icon"></div>
						</a>
					</li>
					<li href="#modal" class="second">
						<a>
							<div class="icons chat_icon"></div>
						</a>
					</li>
				</ul>
			</div>
		</section>	
	</div>
	<div class="container" id="nav content">
		<section>
			<!--<nav class="clearfix">
				<ul class="clearfix">
					<li class="info"><a href="index.jsp">Home</a></li>
					<li class="info hwuse"><a href="howtous.jsp">How is use</a></li>
					<li class="info category"><a href="category.jsp">Category</a></li>
					<li class="info subscription"><a href="subscription.jsp">Subscription</a></li>
					<li class="info contact"><a href="contactus.jsp">Contact US</a></li>	
				</ul>
				<a href="#" id="pull">Menu</a>
			</nav>-->


 			<?jsp $currentPage = substr($_SERVER["SCRIPT_NAME"],strrpos($_SERVER["SCRIPT_NAME"],"/")+1);if($currentPage != 'dashboard.jsp'){ ?>



<!--  			<?jsp $currentPage = substr($_SERVER["SCRIPT_NAME"],strrpos($_SERVER["SCRIPT_NAME"],"/")+1);if($currentPage != 'dashboard.jsp'){ ?> -->

				<ul class="dashboard-menu">
					<li>
						<div class="db-blk">
							<a href="${pageContext.request.contextPath}/org/expertRecommendation">
								<div class="account-img"></div>
								<div class="dash-title">Expert Recommendation</div>
							</a>
						</div>
					</li>
					<li class="active">
						<div class="db-blk">
									<a href="${pageContext.request.contextPath}/org/mysubscription">
									<div class="subscription-img"></div>
									<div class="dash-title">My Subscription</div>
								</a>
							</div>
					</li>
					<li>
						<div class="db-blk">
							<a href="${pageContext.request.contextPath}/org/customizeDashboard">
								<div class="testscore-img"></div>
								<div class="dash-title">Customize Dashboard</div>
							</a>
						</div>
					</li>
					
					<li>
						<div class="db-blk">
							<a href="${pageContext.request.contextPath}/org/alertNotification">
								<div class="usergroup-img"></div>
								<div class="dash-title">Alert/Notification</div>
							</a>
						</div>
					</li>
					
					<li>
						<div class="db-blk">
							<a href="${pageContext.request.contextPath}/org/appCenter">
								<div class="apps-img"></div>
								<div class="dash-title">Apps Center</div>
							</a>
						</div>
					</li>
					<li>
						<div class="db-blk">
							<a href="${pageContext.request.contextPath}/org/followFriend">
								<div class="testcenter-img"></div>
								<div class="dash-title">Follow Friend</div>
							</a>
						</div>
					</li>
					<li>
						<div class="db-blk">
							<a href="${pageContext.request.contextPath}/org/testCenter">
								<div class="testcenter-img"></div>
								<div class="dash-title">Test Center</div>
							</a>
						</div>
					</li>
					<li>
						<div class="db-blk">
							<a href="${pageContext.request.contextPath}/org/calendar">
								<div class="testscore-img"></div>
								<div class="dash-title">Calendar</div>
							</a>
						</div>
					</li>
				</ul>
			<?jsp } ?>
<!-- Chat Box -->

	<div id="modal">
		<div class="contact-list">
			<div class="contact_list"></div>
		</div>
		<div class="chat-wrp">
			<div class="arrow-top"></div>
	      	<div class="search-wrapper">
		      	<div class="search-field">
		      		<div class="srch_img"></div>
		        	<input type="text" placeholder="Search contacts">
		        	<a href="javascript:$.pageslide.close()">
						<img src="${pageContext.request.contextPath}/online-exam/images/close_icon.png"/>
					</a>
		        </div>
		      	
	    	</div> 
	    	<div class="people-list">
    			<ul class="">
    				<li>
    					<div class="chat-list">
		    				<div class="img avatar">
		    					<img src="${pageContext.request.contextPath}/online-exam/images/defaultavatar_small.png">
					    	</div>
						    <div class="user_name">
						    	Vibha Yadav
						    </div>
						    <div class="onlinestatus">
						      	<div class="presence-status offline"></div>
						    </div>
					    </div>
			  		</li>
			  		<li>
    					<div class="chat-list">
		    				<div class="img avatar">
		    					<img src="${pageContext.request.contextPath}/online-exam/images/defaultavatar_small.png">
					    	</div>
						    <div class="user_name">
						    	Vibha Yadav
						    </div>
						    <div class="onlinestatus">
						      	<div class="presence-status offline"></div>
						    </div>
					    </div>
			  		</li>
			  		<li>
    					<div class="chat-list">
		    				<div class="img avatar">
		    					<img src="${pageContext.request.contextPath}/online-exam/images/defaultavatar_small.png">
					    	</div>
						    <div class="user_name">
						    	Vibha Yadav
						    </div>
						    <div class="onlinestatus">
						      	<div class="presence-status offline"></div>
						    </div>
					    </div>
			  		</li>
			  		<li>
    					<div class="chat-list">
		    				<div class="img avatar">
		    					<img src="${pageContext.request.contextPath}/online-exam/images/defaultavatar_small.png">
					    	</div>
						    <div class="user_name">
						    	Vibha Yadav
						    </div>
						    <div class="onlinestatus">
						      	<div class="presence-status busy"></div>
						    </div>
					    </div>
			  		</li>
			  		<li>
    					<div class="chat-list">
		    				<div class="img avatar">
		    					<img src="${pageContext.request.contextPath}/online-exam/images/defaultavatar_small.png">
					    	</div>
						    <div class="user_name">
						    	Vibha Yadav
						    </div>
						    <div class="onlinestatus">
						      	<div class="presence-status online"></div>
						    </div>
					    </div>
			  		</li>
			  		<li>
    					<div class="chat-list">
		    				<div class="img avatar">
		    					<img src="${pageContext.request.contextPath}/online-exam/images/defaultavatar_small.png">
					    	</div>
						    <div class="user_name">
						    	Vibha Yadav
						    </div>
						    <div class="onlinestatus">
						      	<div class="presence-status online"></div>
						    </div>
					    </div>
			  		</li>
			  		<li>
    					<div class="chat-list">
		    				<div class="img avatar">
		    					<img src="${pageContext.request.contextPath}/online-exam/images/defaultavatar_small.png">
					    	</div>
						    <div class="user_name">
						    	Vibha Yadav
						    </div>
						    <div class="onlinestatus">
						      	<div class="presence-status busy"></div>
						    </div>
					    </div>
			  		</li>
			  		<li>
    					<div class="chat-list">
		    				<div class="img avatar">
		    					<img src="${pageContext.request.contextPath}/online-exam/images/defaultavatar_small.png">
					    	</div>
						    <div class="user_name">
						    	Vibha Yadav
						    </div>
						    <div class="onlinestatus">
						      	<div class="presence-status busy"></div>
						    </div>
					    </div>
			  		</li>
			  	</ul>
			</div>
			<div class="new-conversation">
				<div class="conversation">
				    <div class="add_icon"></div>
				    <div class="user_addnew">New message</div>
				</div>
			</div>
		</div>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/online-exam/js/jquery.pageslide.min.js"></script>
		</div>
	</section>	
</div>
		
