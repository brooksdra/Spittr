<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

   <div class="listTitle">
      <h1>Spittle ${spittle.id}</h1>
      <div class="spittleMessage"><a href="<c:url value="/spittles/${spittle.id}" />"><c:out value="${spittle.message}" /></a></div>
            <div>
              <span class="spittleTime"><c:out value="${spittle.time}" /></span>
              <span class="spittleLocation">(<c:out value="${spittle.latitude}" />, <c:out value="${spittle.longitude}" />)</span>
            </div>
    </div>
  </div>
