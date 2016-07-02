<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
	<title>Spittr</title>
	<link rel="stylesheet" type="text/css"	href="<c:url value="/styles/style.css" />" >
</head>
<body>
	<h1><s:message code="spittr.welcome" /></h1>
	<a href="<c:url value="/spittles" />">Spittles</a> |
	<a href="<c:url value="/spitter/register" />">Register</a> |
	<a href="<c:url value="/spitter/spittlers" />">Spittlers</a>
</body>
</html>