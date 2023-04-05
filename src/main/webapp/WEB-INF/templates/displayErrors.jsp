<%@ page contentType="text/html;charset=UTF-8" %>

<c:if test="${!empty errors }">
    <ul class="text-center list-group mt-2 mb-2">
        <c:forEach var="error" items="${errors }">
            <li class="text-danger list-group-item border-0">
                    ${error }
            </li>
        </c:forEach>
    </ul>
</c:if>