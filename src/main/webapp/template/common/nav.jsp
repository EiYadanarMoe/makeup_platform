<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-lg">
	<div class="container-fluid">
		<h3 class="navbar-brand">
			<a href="style" class="text-decoration-none rose"> <img
				src="https://i.pinimg.com/564x/96/d3/db/96d3db381467813a9ebf66abc90a4b4f.jpg"
				alt="Logo"
				style="width: 70px; height: 70px; vertical-align: middle; margin-right: 5px; border-radius: 40%">
				Rosiness
			</a>
		</h3>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item dropdown moree"><a
					class="nav-link dropdown-toggle" href="#">${user.username}</a>
					<ul class="dropdown-menu">
						<c:if test="${user.role == 'admin'}">
							<li><a class="dropdown-item" href="admin">Admin
									Dashboard</a></li>
						</c:if>
						<li><a class="dropdown-item" href="login?mode=LOGOUT">Logout</a></li>
						<c:if test="${user.role == 'user'}">
							<li><a class="dropdown-item" href="likedStyles">Liked
									Styles</a></li>
<!-- 							<li><a class="dropdown-item" href="profile">My Account</a></li> -->
						</c:if>
					</ul></li>
				<c:if test="${user.role == 'admin'}">
					<li class="nav-item"><a class="nav-link addnew"
						href="style?mode=STYLE_FORM">Add New Style</a></li>
				</c:if>
			</ul>
			
			<form class="d-flex ml-auto search-form" action="style" method="get">
				<input type="hidden" name="mode" value="SEARCH"> <input
					class="form-control me-2" name="query" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form>
		</div>
	</div>
</nav>
