<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>

<h3>Login with Username and Password</h3>
<form name='f' action='login' method='POST'>
	<table>
	<tr><td>User:</td><td><input type='text' name='username' value=''></td></tr>
	<tr><td>Password:</td><td><input type='password' name='password'/></td></tr>
	<tr><td>&nbsp;</td><td><input id="remember_me" name="remember-me" type="checkbox"/><label for="remember_me" class="inline">Remember me</label></td></tr>
	<tr><td colspan='2'>
	<input name="submit" type="submit" value="Login"/></td></tr>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</table>
</form>

