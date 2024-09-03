<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
 <c:import url="../common/header.jsp" />
</head>
<body>
    <c:import url="../common/nav.jsp" />
    <!-- style details card -->
    <div class="container">
        <div class="row justify-content-center align-items-center mt-3 mb-3">
            <!-- Image Card -->
            <div class="col-md-3 d-flex">
                <div class="card">
                    <img src="${style.image_url}" class="card-img-top details-card-image" alt="${style.name}">
                </div>
            </div>
            
            <!-- Text Container -->
            <div class="col-md-9 d-flex">
                <div class="container container-text">
                    <h5 class="card-title">${style.name}</h5>
                    <p class="card-text">Category: ${style.category}</p>
                    <h6>Style Description</h6>
                    <p class="card-text">${style.description}</p>

                    <!-- Display YouTube Links -->
                    <c:if test="${not empty style.youtubeLink}">
                        <p>
                            YouTube Link: <a href="${style.youtubeLink}" target="_blank">${style.youtubeLink}</a>
                        </p>
                    </c:if>
                    <c:if test="${empty style.youtubeLink}">
                        <p>No YouTube Link provided.</p>
                    </c:if>

                    <c:if test="${user.role == 'admin'}">
                        <c:url var="updateLink" value="style">
                            <c:param name="mode" value="LOAD" />
                            <c:param name="styleId" value="${style.id}" />
                        </c:url>

                        <c:url var="deleteLink" value="style">
                            <c:param name="mode" value="DELETE" />
                            <c:param name="styleId" value="${style.id}" />
                        </c:url>
                        <a href="${updateLink}" class="btn update float-center">Update</a>
                        <a href="${deleteLink}" class="btn delete float-center">Delete</a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <!-- style details card end-->

   
</body>
</html>
