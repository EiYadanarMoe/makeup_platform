<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../common/header.jsp" />



</head>
<body>
	<c:import url="../common/nav.jsp" />

	<div class="container">
		<table id="example" class="table table-striped" style="width: 100%">
			<thead>
				<tr>
					<th>ID</th>
					<th>FistName</th>
					<th>LastName</th>
					<th>UserName</th>
					<th>Email</th>
					<th>CreatedAt</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${userList}">
					<tr>
						<td>${user.id}</td>
						<td>${user.firstname}</td>
						<td>${user.lastname}</td>
						<td>${user.username}</td>
						<td>${user.email}</td>
						<td>${user.createdAt}</td>

						<td><c:choose>
								<c:when test="${user.enable}">
									<c:url var="disableLink" value="admin">
										<c:param name="mode" value="DISABLE" />
										<c:param name="userId" value="${user.id}" />
									</c:url>
									<a class="btn btn-danger" href="${disableLink}">Disable</a>
								</c:when>
								<c:otherwise>
									<c:url var="enableLink" value="admin">
										<c:param name="mode" value="ENABLE" />
										<c:param name="userId" value="${user.id}" />
									</c:url>
									<a class="btn btn-success" href="${enableLink}">Enable</a>
								</c:otherwise>
							</c:choose></td>

					</tr>

				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<th>ID</th>
					<th>FistName</th>
					<th>LastName</th>
					<th>UserName</th>
					<th>Email</th>
					<th>CreatedAt</th>
					<th>Action</th>
				</tr>
			</tfoot>
		</table>

	</div>


	<script type="text/javascript">
		new DataTable('#example');
	</script>
</body>
</html>