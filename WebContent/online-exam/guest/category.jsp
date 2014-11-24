<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="container" id="main-content">
		<section>
			<div class="group">
				<h1>Category</h1>
				<ul id="category-tabs">
					<li id="category1" class="active">A</li>
					<li id="category2">B</li>
					<li id="category3">C</li>
					<li id="category4">D</li>
					<li id="category5">E</li>
					<li id="category6">F</li>
					<li id="category7">G</li>
					<li id="category8">H</li>
					<li id="category9">I</li>
					<li id="category10">J</li>
					<li id="category11">K</li>
					<li id="category12">L</li>
					<li id="category13">M</li>
					<li id="category14">N</li>
					<li id="category15">O</li>
					<li id="category16">P</li>
					<li id="category17">Q</li>
					<li id="category18">R</li>
					<li id="category19">S</li>
					<li id="category20">T</li>
					<li id="category21">U</li>
					<li id="category22">V</li>
					<li id="category23">W</li>
					<li id="category24">X</li>
					<li id="category25">Y</li>
					<li id="category26">Z</li>
				</ul>
				
				<div class="category-block">
					<c:forEach varStatus="loop1" var="categoryMapData" items="${categoryMapData}">
						<div id="category${loop1.index+1}-block" class="category-blk <c:out value="${loop1.index eq 0 ? 'active': 'group'}"/>">
							<div class="categorytitle"><c:out value="${categoryMapData.key}"></c:out> -</div>
							<div class="group">	
								<c:forEach varStatus="loop" var="categoryMap" items="${categoryMapData.value}">
									<div class="col spn_1_3">
										<h3><c:out value="${categoryMap.category}"></c:out></h3>
										<ul>
											<c:forEach var="category" items="${categoryMap.categoryList}">
												<li><a href="#">${category.subCategory}</a></li>
											</c:forEach>
										</ul>	
									</div>
								</c:forEach>
							</div>
						</div>	
					</c:forEach>
				</div>
			</div>
		</section>
	</div>
	

<jsp:include page="../include/footer.jsp"></jsp:include>