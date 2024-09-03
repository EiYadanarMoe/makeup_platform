<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<c:import url="../common/header.jsp" />

<body>
<%-- <c:import url="../common/nav.jsp" /> --%>
	<!-- Container -->
	<!-- New Style form -->
	<div class="container-md mt-3">
		<div class="col-md-6 mx-auto">
			<div class="new">
				<h3>New Make-up Style Form</h3>
			</div>
			<div class="card addstyle">
				<div class="card-body">
					<c:if test="${not empty insertOk}">
						<c:choose>
							<c:when test="${insertOk}">
								<span class="alert alert-success">Successfully created.</span>
							</c:when>
							<c:otherwise>
								<span class="alert alert-danger">Failed</span>
							</c:otherwise>
						</c:choose>
					</c:if>
					<form action="style" method="post">
						<input type="hidden" name="mode" value="CREATE" />
						<div class="mb-3">
							<label for="exampleInputName1" class="form-label">Name</label> <input
								type="name" class="form-control" id="name" name="name"
								required="required">
						</div>

						<div class="mb-3">
							<label for="description" class="form-label">Description</label>
							<textarea class="form-control" id="description"
								name="description" rows="4" required="required"></textarea>
						</div>

						<div class="mb-3">
							<label for="exampleInputImage1" class="form-label">Image
								URL</label> <input type="image_url" class="form-control" id="image_url"
								name="image_url" required="required">
						</div>

						<div class="mb-3">
							<label for="exampleInputCategory1" class="form-label">Category</label>
							<input type="category" class="form-control" id="category"
								name="category" required="required">
						</div>

						<input type="hidden" name="mode" value="CREATE"> <input
							type="hidden" name="styleId" value="${style.id}">
						<!-- Other fields -->
						<div class="mb-3">
                        <label for="youtubeLink" class="form-label">YouTube Link</label>
                        <input type="text" class="form-control" id="youtubeLink" name="youtubeLink" value="${style.youtubeLink}">
                    </div>





						<button type="submit" class="btn float-end apple">Add</button>
					</form>
				</div>

			</div>
		</div>
	</div>


<%-- 	<c:import url="../common/footer.jsp" /> --%>
</body>
</html>