<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s"  uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>

<h1>Register</h1>
<!-- No action parameter means it will be posted back to the same URL path that displayed -->
<sf:form method="POST" commandName="spitter" enctype="multipart/form-data">
    <sf:errors path="*" element="div" cssClass="errors" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<sf:label path="firstName" cssErrorClass="error">First Name</sf:label>: <sf:input path="firstName" /><sf:errors path="firstName" cssClass="error" /><br/>
	<sf:label path="lastName" cssErrorClass="error">Last Name</sf:label>: <sf:input path="lastName" /><sf:errors path="lastName" cssClass="error" /><br/>
	<sf:label path="email" cssErrorClass="error">Email</sf:label>: <sf:input path="email" type="email" /><sf:errors path="email" cssClass="error" /><br/>
	<sf:label path="username" cssErrorClass="error">Username</sf:label>: <sf:input path="username" /><sf:errors path="username" cssClass="error" /><br/>
	<sf:label path="password" cssErrorClass="error">Password</sf:label>: <sf:password path="password" /><sf:errors path="password" cssClass="error" /><br/>
	<label>Profile Picture</label>:: 
			<input name="profilePicture" type="file" accept="image/jpeg,image/png,image/gif" /><br/>
	<input type="submit" value="Register" />
</sf:form>
