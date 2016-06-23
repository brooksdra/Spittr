<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Spittr</title>
	<link rel="stylesheet" type="text/css"	href="<c:url value="/styles/style.css" />" >
</head>
<body>
	<h1>Register</h1>
	<!-- No action parameter means it will be posted back to the same URL path that displayed -->
	<form method="POST">	
		email: <input type="text" name="email" /><br/>
		First Name: <input type="text" name="firstName" /><br/>
		Last Name: <input type="text" name="lastName" /><br/>
		Username: <input type="text" name="username" /><br/>
		Password: <input type="password" name="password" /><br/>
		Password Confirmed: <input type="password" name="passwordConfirm" /><br/>
		<input type="submit" value="Register" />
	</form>
</body>
</html>