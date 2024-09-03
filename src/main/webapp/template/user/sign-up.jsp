
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<c:import url="../common/header.jsp" />
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-4">
				<div class="card sign_up">
					<div class="card-header text-center">
						<h3>Fill All Your Information</h3>
					</div>
					<div class="card-body">
						<c:if test="${not empty signupOk}">
							<c:choose>
								<c:when test="${signupOk}">
									<span class="alert alert-success">Successfully created.</span>
								</c:when>
								<c:otherwise>
									<span class="alert alert-danger">Failed</span>
								</c:otherwise>
							</c:choose>
						</c:if>
						<form action="user" method="post">
							<input type="hidden" name="mode" value="SIGNUP" />
							<div class="form-group">
								<label for="firstname">First Name</label> <input type="text"
									class="form-control" id="firstname" name="firstname" required>
							</div>
							<div class="form-group">
								<label for="lastname">Last Name</label> <input type="text"
									class="form-control" id="lastname" name="lastname" required>
							</div>
							<div class="form-group">
								<label for="username">Username</label> <input type="text"
									class="form-control" id="username" name="username" required>
							</div>
							<div class="form-group">
								<label for="password">Password</label> <input type="password"
									class="form-control" id="password" name="password" required>
							</div>
							<div class="form-group">
								<label for="email">Email</label> <input type="email"
									class="form-control" id="email" name="email" required>
							</div>
							<div class="form-group">
								<label for="profilePicture">Profile Picture URL</label> <input
									type="text" class="form-control" id="profilePicture"
									name="profilePicture" required>
							</div>
							<button type="submit" class="btn addnew float-end mt-3">Create</button>
						</form>

					</div>
					<div class="card-footer text-center">
						<p>
							Already have an account? Login <a href="login"
								class="text-decoration-none">Here</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>


<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> --%>
<%-- <%@ taglib uri="jakarta.tags.core" prefix="c"%> --%>
<!-- <!DOCTYPE html> -->
<!-- <html lang="en"> -->
<%-- <c:import url="../common/header.jsp"/> --%>
<!-- <body> -->


<!--     <div class="container"> -->
<!--         <div class="row justify-content-center"> -->
<!--             <div class="col-md-4"> -->
<!--                 <div class="card sign_up"> -->
<!--                     <div class="card-header text-center"> -->
<!--                         <h3>Fill All Your Information</h3> -->
<!--                     </div> -->
<!--                     <div class="card-body"> -->
<%--                     <c:if test="${not empty signupOk}"> --%>
<%-- 						<c:choose> --%>
<%-- 							<c:when test="${signupOk}"> --%>
<!-- 								<span class="alert alert-success">Successfully created.</span> -->
<%-- 							</c:when> --%>
<%-- 							<c:otherwise> --%>
<!-- 								<span class="alert alert-danger">Failed</span> -->
<%-- 							</c:otherwise> --%>
<%-- 						</c:choose> --%>
<%-- 					</c:if> --%>
<!--                         <form action="user" method="post"> -->
<!--                             <input type="hidden" name="mode" value="SIGNUP" /> -->
<!--                             <div class="form-group"> -->
<!--                                 <label for="firstname">First Name</label> -->
<!--                                 <input type="text" class="form-control" id="firstname" name="firstname" required> -->
<!--                             </div> -->
<!--                             <div class="form-group"> -->
<!--                                 <label for="lastname">Last Name</label> -->
<!--                                 <input type="text" class="form-control" id="lastname" name="lastname" required> -->
<!--                             </div> -->
<!--                             <div class="form-group"> -->
<!--                                 <label for="username">Username</label> -->
<!--                                 <input type="text" class="form-control" id="username" name="username" required> -->
<!--                             </div> -->
<!--                             <div class="form-group"> -->
<!--                                 <label for="password">Password</label> -->
<!--                                 <input type="password" class="form-control" id="password" name="password" required> -->
<!--                             </div> -->
<!--                             <div class="form-group"> -->
<!--                                 <label for="email">Email</label> -->
<!--                                 <input type="email" class="form-control" id="email" name="email" required> -->
<!--                             </div> -->

<!--                             <div class="form-group"> -->
<!-- 							<label for="profilePicture" class="form-label">Profile Picture</label>  -->
<!-- 							<input type="profilePicture" class="form-control" id="profilePicture" -->
<!-- 								name="profilePicture" required="required"> -->
<!-- 						</div> -->

<!--                             <button type="submit" class="btn addnew float-end mt-3">Create</button> -->
<!--                         </form> -->
<!--                     </div> -->
<!--                     <div class="card-footer text-center"> -->
<!--                         <p>Already have an account? Login  <a href="login" class="text-decoration-none">Here</a></p> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->



<!--     Include Bootstrap JS and dependencies (optional) -->
<!-- <!--     <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script> -->
-->
<!-- <!--     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script> -->
-->
<!-- <!--     <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> -->
-->
<!-- </body> -->
<!-- </html> -->
