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
				<h3>Update Style Form</h3>
			</div>
			<div class="card bgg">
				<div class="card-body">
					<c:if test="${not empty updateOk and not updateOk }">
						<span class="alert alert-danger">Updating style is Failed</span>
					</c:if>
					<form action="style" method="post">
						<input type="hidden" name="mode" value="UPDATE"> <input
							type="hidden" name="styleId" value="${style.id}">
						<div class="mb-3">
							<label for="exampleInputName1" class="form-label">Name</label> <input
								type="name" class="form-control" id="name" name="name"
								value="${style.name}" required="required">
						</div>

						<div class="mb-3">
							<label for="description" class="form-label">Description</label>
							<textarea class="form-control" id="description"
								name="description" rows="4" required="required">
								${style.description}
								</textarea>
						</div>

						<div class="mb-3">
							<label for="exampleInputImage1" class="form-label">Image
								URL</label> <input type="image_url" class="form-control" id="image_url"
								name="image_url" value="${style.image_url}" required="required">
						</div>

						<div class="mb-3">
							<label for="exampleInputCategory1" class="form-label">Category</label>
							<input type="category" class="form-control" id="category"
								name="category" value="${style.category}" required="required">
						</div>


						<input type="hidden" name="mode" value="UPDATE"> <input
							type="hidden" name="styleId" value="${style.id}">
						<!-- Other fields -->
						<label for="youtubeLink">YouTube Link:</label> <input type="text"
							id="youtubeLink" name="youtubeLink" value="${style.youtubeLink}">





						<button type="submit" class="btn more float-end">Update</button>
					</form>
				</div>

			</div>
		</div>
	</div>


<%-- 	<c:import url="../common/footer.jsp" /> --%>
</body>
</html>