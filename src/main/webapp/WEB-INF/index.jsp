<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="fr">
<%@include file="templates/head.jsp"%>
<body class="m-2">
  <%@include file="templates/nav.jsp"%>
  <%--@elvariable id="page" type="string"--%>
  <c:if test="${page == 'login'}">
    <%@include file="pages/login.jsp"%>
  </c:if>
  <c:if test="${page == 'createLogin'}">
    <%@include file="pages/inscrire.jsp"%>
  </c:if>
  <c:if test="${page == 'home'}">
    <%@include file="pages/accueil.jsp"%>
  </c:if>
  <c:if test="${page == 'profile'}">
    <%@include file="pages/profile.jsp"%>
  </c:if>
  
<!--  <c:if test="${page == 'updateAuction'}">
  
  </c:if>--> 
</body>
</html>