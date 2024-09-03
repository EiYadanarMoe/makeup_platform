<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<head>
<c:import url="../common/header.jsp" />
</head>
<body>
	<c:import url="../common/nav.jsp" />

	<!-- Makeup styles list -->
	<div class="container text-center">
	<h3>All you want are Here</h3>
		<div class="makeup-styles mx-auto">
			<div class="row mx-auto">
				<c:forEach var="style" items="${styleList}">
					<c:url var="detailsLink" value="style">
						<c:param name="mode" value="SINGLE" />
						<c:param name="styleId" value="${style.id}" />
					</c:url>
					<div
						class="col-xs-12 col-sm-10 col-md-6 col-lg-4 col-xl-4 col-xxl-3">
						<div class="card list-card mt-3 mb-3">
							<img src="${style.image_url}"
								class="card-img-top list-card-image" alt="${style.name}">
							<div class="card-body text-center cardbodybg">
								<h5 class="card-title">${style.name}</h5>
								<p class="card-text">Category: ${style.category}</p>
								<c:if test="${not empty user && user.role == 'user'}">
									<form action="style" method="post" style="display: inline;">
										<input type="hidden" name="mode" value="LIKE"> <input
											type="hidden" name="styleId" value="${style.id}">
										<button type="submit" class="btn save-btn float-start">
											<c:choose>
												<c:when test="${style.liked}">
													<i class="fas fa-thumbtack"></i> Saved
                                                </c:when>
												<c:otherwise>
													<i class="far fa-thumbtack"></i> Save
                                                </c:otherwise>
											</c:choose>
										</button>
									</form>
								</c:if>
								<div class="showcase">
									<a href="${detailsLink}" class="btn">View
										Details</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<c:import url="../common/footer.jsp" />
	

</body>
</html>