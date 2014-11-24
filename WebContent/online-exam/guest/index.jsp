<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="../include/header.jsp"></jsp:include>
<div class="container">
		<section>
			<div class="group">
				<div class="col spn_2_3" id="slider-wrapper">
					<div id="slider">
						<img src="${pageContext.request.contextPath}/online-exam/images/slide1.jpg" alt=""/>
					</div>
				</div>
				<div class="col spn_1_3">
					<p style="color: #008000;"><c:out value="${err}"></c:out></p>					
					<!-- <div class="form-blk">
						<div class="form-title">Start Your Free Trial</div>
						<form>
							<div class="form-item">
								<div class="form-value">
									<input type="text" placeholder="User Name/Email"/>
								</div>
							</div>
							
							<div class="form-item">
								<div class="form-value ">
									<div><input type="text" placeholder="First Name"/></div>
									<div><input type="text" placeholder="Last Name"/></div>
								</div>
							</div>
							<div class="form-item">
								<div class="form-value">
									<div><input type="text" placeholder="Phone Number"/></div>
									<div><input type="text" placeholder="Grade Level"/></div>
								</div>
							</div>
							<div class="form-item">
								<div class="form-value">
									<input type="text" placeholder="School Name"/>
								</div>
							</div>
							<div class="form-item">
								<div class="form-value">
									<input type="checkbox"/>Terms & Conditions
								</div>
							</div>
							<div class="form-actions align-center">
								<input type="button" value="Start" class="btnstyle-2"/>
							</div>
						</form>
					</div> -->
				</div>
			</div>
		</div>
		</div>
		<div class="group shadow-blk"></div>
	</section>	
</div>
<div class="container" id="quote">
		<section>
			<div class="col spn_1_3">
				<div class="blk">
					<h1><span class="students"></span>Students</h1>
					<p>Aenean ut dui massa. Nam lacinia turpis at ornare posuere. Nulla id urna pellentesque, molestie lectus in, bibendum diam. Aliquam venenatis non ligula sit amet condimentum, Integer in pretium libero.</p>
					<a href="" class="more-tab">Start Now <span></span></a>
				</div>
			</div>
			<div class="col spn_1_3">
				<div class="blk">
					<h1><span class="teachers"></span>Teachers</h1>
					<p>Aenean ut dui massa. Nam lacinia turpis at ornare posuere. Nulla id urna pellentesque, molestie lectus in, bibendum diam. Aliquam venenatis non ligula sit amet condimentum, Integer in pretium libero.</p>
					<a href="" class="more-tab">Know Now <span></span></a>
				</div>
			</div>
			<div class="col spn_1_3">
				<div class="blk">
					<h1><span class="stories"></span>Success Stories</h1>
					<p>Aenean ut dui massa. Nam lacinia turpis at ornare posuere. Nulla id urna pellentesque, molestie lectus in, bibendum diam. Aliquam venenatis non ligula sit amet condimentum, Integer in pretium libero.</p>
					<a href="" class="more-tab">View All <span></span></a>
				</div>
			</div>
		</section>
	</div>
	

<jsp:include page="../include/footer.jsp"></jsp:include>

