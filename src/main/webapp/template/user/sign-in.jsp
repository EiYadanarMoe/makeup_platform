<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<c:import url="../common/header.jsp"/>
<body>
    
    
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-4">
                <div class="card sign_in">
                    <div class="card-header text-center head">
                        <h3>Login Here</h3>
                    </div>
                    <div class="card-body login">
                    <c:if test="${not empty loginOk and not loginOk}">
					<span class="alert alert-danger" role="alert">Username or Password is incorrect</span>
					</c:if>
                        <form action="login" method="post">
                            <input type="hidden" name="mode" value="LOGIN" />
                            <div class="form-group">
                                <label for="username">Username or Email</label>
                                <input type="text" class="form-control" id="username" name="username" required>
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" id="password" name="password" required>
                            </div>
                            <button type="submit" class="btn addnew mt-3 float-end">Login</button>
                        </form>
                    </div>
                    <div class="card-footer text-center foot">
                        <p>Don't have an account? Sign up <a href="user" class="text-decoration-none here">Here</a></p>
                    </div>
                </div>
            </div>
            <i class="fa-regular fa-right-to-bracket"></i>
        </div>
    </div>
</body>
</html>
