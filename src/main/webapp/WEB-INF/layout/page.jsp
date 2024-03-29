<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %> 
<%@ page session="false"%>
<html>
<head>
	<title>Spittr</title>
	<link rel="stylesheet" type="text/css"	href="<c:url value="/styles/style.css" />" >
</head>
<body>
	<div id="header">
		<t:insertAttribute name="header" />
	</div>
	<div id="content">
		<t:insertAttribute name="body" />
	</div>
	<div id="footer">
		<t:insertAttribute name="footer" />
	</div>
</body>
</html>