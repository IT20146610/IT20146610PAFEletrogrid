<%@page import="model.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.6.0.min.js"></script>
	<script src="Components/user.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>User Management</h1>
				<form id="formUser" name="formUser" method="post" action="User.jsp">
					User Name:
					<input id="userName" name="userName" type="text" class="form-control form-control-sm">
					<br>
					User Address:
					<input id="userAddress" name="userAddress" type="text" class="form-control form-control-sm">
					<br>
					User Account No:
					<input id="userAccount" name="userAccount" type="text" class="form-control form-control-sm">
					<br>
					User Contact No:
					<input id="userContact" name="userContact" type="text" class="form-control form-control-sm">
					<br>
					Email:
					<input id="userEmail" name="userEmail" type="text" class="form-control form-control-sm">
					<br>
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 					<input type="hidden" id="hididSave" name="hididSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divUserGrid">
				<%
					User UserObj = new User();
					out.print(UserObj.readUser());
				%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>