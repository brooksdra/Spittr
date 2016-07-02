<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    <div class="listTitle">
      <h1>Current Spitters</h1>
      <ul class="spitterList">
        <c:forEach items="${spitterList}" var="spitter" >
          <li id="spitter_<c:out value="spitter.id"/>">
            <div class="spitterUsername"><c:out value="${spitter.username}" /></div>
            <div>
              <span class="spitterName"><c:out value="${spitter.firstName}" /> <c:out value="${spitter.lastName}" /></span>
              <span class="spitterEmail"><c:out value="${spitter.email}" /></span>
            </div>
          </li>
        </c:forEach>
      </ul>
<%--    <c:if test="${fn:length(spitterList) gt 20}"> --%>
<%--      <hr /> --%> 
<%--      <s:url value="/spittles?count=${nextCount}" var="more_url" /> --%> 
<%--      <a href="${more_url}">Show more</a> --%> 
<%--    </c:if> --%> 
    </div>