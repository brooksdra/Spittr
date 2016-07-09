<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<a href='<s:url value="/" />' ><img src='<s:url value="/images/spitter_logo_50.png" />' border='0'/></a>

   <sec:authorize access="!isAuthenticated()">
       Hello There! Want to <a href='<s:url value="/login" />' >Login</a>?
   </sec:authorize>

   <sec:authorize access="isAuthenticated()">
       Hello <sec:authentication property="principal.username" />! <a href='<s:url value="/logout" />' >Logout</a>
   </sec:authorize>

