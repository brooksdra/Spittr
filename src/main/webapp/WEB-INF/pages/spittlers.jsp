<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head>
    <title>Spitter</title>
	<link rel="stylesheet" type="text/css"	href="<c:url value="/styles/style.css" />" >
  </head>
  <body>
    <div class="listTitle">
      <h1>Current Spittlers</h1>
      <ul class="spittlerList">
        <c:forEach items="${spittlerList}" var="spittler" >
          <li id="spittler_<c:out value="spittler.id"/>">
            <div class="spittlerUsername"><c:out value="${spitter.username}" /></div>
            <div>
              <span class="spittlerName"><c:out value="${spitter.firstName}" /> <c:out value="${spitter.lastName}" /></span>
              <span class="spittlerEmail"><c:out value="${spitter.email}" /></span>
            </div>
          </li>
        </c:forEach>
      </ul>
      <c:if test="${fn:length(spittleList) gt 20}">
        <hr />
        <s:url value="/spittles?count=${nextCount}" var="more_url" />
        <a href="${more_url}">Show more</a>
      </c:if>
    </div>
  </body>
</html>


    <br/>
    <br/>
    