<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <c:import url="../common/header.jsp"/>
    <title>Liked Styles</title>
    <style>
        .card {
            position: relative; 
        }

        .showcase {
            position: absolute;
            bottom: 20px; 
            left: 50%;
            transform: translateX(-50%);
            height: 40px;
            opacity: 0; 
            visibility: hidden; 
            z-index: 600;
            transition: opacity 0.3s, visibility 0.3s; 
        }

        .card:hover .showcase {
            opacity: 1;
            visibility: visible; 
        }

        .save-btn {
            background-color: #E60023; 
            color: white;
            border: none;
            padding: 0.5em 1em;
            font-size: 1em;
            cursor: pointer;
            display: flex;
            align-items: center;
            transition: background-color 0.3s;
        }

        .save-btn i {
            margin-right: 5px; 
            font-size: 1.2em; 
        }

        .save-btn:hover {
            background-color: #c9001d; 
        }

        .save-btn:focus {
            outline: none; 
        }
    </style>
</head>
<body>
    <c:import url="../common/nav.jsp"/>

    <div class="container text-center">
        <h1>Liked Styles</h1>
        <c:if test="${not empty likedStyles}">
            <div class="makeup-styles mx-auto">
                <div class="row mx-auto">
                    <c:forEach var="style" items="${likedStyles}">
                        <c:url var="detailsLink" value="style">
                            <c:param name="mode" value="SINGLE"/>
                            <c:param name="styleId" value="${style.id}"/>
                        </c:url>
                        <div class="col-xs-12 col-sm-10 col-md-6 col-lg-4 col-xl-4 col-xxl-3">
                            <div class="card list-card mt-3 mb-3">
                                <img src="${style.image_url}" class="card-img-top list-card-image" alt="${style.name}">
                                <div class="card-body text-center">
                                    <h5 class="card-title">${style.name}</h5>
                                    <p class="card-text">Category: ${style.category}</p>

                                    <div class="showcase">
                                        <a href="${detailsLink}" class="btn more float-end">View Details</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>
        <c:if test="${empty likedStyles}">
            <p>You have not liked any styles yet.</p>
        </c:if>
    </div>

    <c:import url="../common/footer.jsp"/>
</body>
</html>


