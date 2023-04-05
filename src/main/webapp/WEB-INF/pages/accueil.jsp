<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<c:if test="${empty(errors)}">
    <jsp:useBean id="current_auctions" scope="request" type="java.util.List"/>
    <jsp:useBean id="pseudos" scope="request" type="java.util.HashMap"/>
</c:if>

<c:if test="${ loginCreated }">
    <p class="text-center text-success">Compte créé avec succès ! Vous pouvez vous connecter.</p>
</c:if><%--@elvariable id="loginUpdated" type="boolean"--%>


<h1 class="mb-5 text-center">Liste des enchères</h1>

<form class="row" action="${pageContext.request.contextPath}/" method="post" id="filter_auctions">
    <div class="offset-lg-3 col-lg-6 d-flex flex-column">
        <p class="m-0">Filtres&nbsp;:</p>
        <%--@elvariable id="stringFilter" type="java.lang.String"--%>
        <input name="string_filter" type="text" class="form-control m-2" placeholder="Le nom de l'article contient" value="${stringFilter}">
        <p class="d-flex flex-row">
            <label class="col-lg-3 pt-3" for="categories">Catégorie&nbsp;:</label>
            <select name="category_filter" id="categories" class="col-lg-9 mt-2 form-control">
                <option value="all">Toutes</option>
                <c:forEach items="${categories}" var="category">
                    <c:choose>
                        <c:when test="${categoryFilter != 'all'}">
                            <c:choose>
                            
                            
                                <c:when test="${categoryFilter == category.noCategorie}">
                                    <option selected value="${category.noCategorie}">${category.libelle}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${category.noCategorie}">${category.libelle}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <option value="${category.noCategorie}">${category.libelle}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </p>
        <c:if test="${isConnected}">
            <div class="form-check form-check-inline ml-3 ml-lg-0 row">
                <div class="offset-1 offset-lg-0 col-10 col-lg-6 d-flex flex-column text-left ml-0">
                    <div>
                        <input checked class="form-check-input guiFilterChoice" type="radio" id="currentBuying" name="checboxesChoice">
                        <label for="currentBuying" class="form-check-label">Achats</label>
                    </div>
                    <div class="d-flex flex-column text-left guiFilterChoiceRadios">
                        <div class="form-check">
                            <input type="radio" class="form-check-input radioFilterChoice" id="allCurrentAuctions" name="filterChoice" value="currentAuctions">
                            <label for="allCurrentAuctions" class="form-check-label">Enchères ouvertes</label>
                        </div>
                    </div>
                </div>
                <div class="offset-1 offset-lg-0 col-10 col-lg-6 d-flex flex-column text-left ml-0">
                    <div>
                        <input type="radio" id="currentSelling" class="form-check-input guiFilterChoice" name="checboxesChoice">
                        <label for="currentSelling" class="form-check-label">Mes ventes</label>
                    </div>
                    <div class="d-flex flex-column text-left guiFilterChoiceRadios">
                        <div class="form-check">
                            <input <c:if test="${filterByMyCurrentSales}">checked</c:if> disabled type="radio" class="form-check-input radioFilterChoice" id="myCurrentSales" name="filterChoice" value="myCurrentSales">
                            <label for="myCurrentSales" class="form-check-label">Mes ventes en cours</label>
                        </div>
                       
                    </div>
                </div>
            </div>
            <script type="text/javascript">
                var guiSelectChoice = $(".guiFilterChoice");
                var guiFilterChoiceRadios = $(".guiFilterChoiceRadios");
                var filterAuctions = $("#filter_auctions");
                guiSelectChoice.each(function (i) {
                    $(this).on("click", function () {
                        filterAuctions.find(".radioFilterChoice").prop("disabled", true);
                        filterAuctions.find(".radioFilterChoice").prop("checked", false);
                        guiFilterChoiceRadios.eq(i).find(".radioFilterChoice").prop("disabled", false);
                    });
                });
            </script>
        </c:if>
        <input type="submit" class="btn btn-info btn-lg btn-block mt-2" id="#searchButton" value="Rechercher"/>
    </div>
</form>
<%@include file="../templates/displayErrors.jsp"%>
<div class="container">
    <div class="row d-flex justify-content-between">
        <c:forEach items="${current_auctions}" var="articleVendu">
            <div class="d-flex flex-column col-md-5 border border-dark mt-3 mb-3">
                <p>${articleVendu.nomArticle}</p>
                <p>Prix : ${articleVendu.miseAPrix}</p>
                <p>
                    Statut de l'enchère :
                    <c:if test="${articleVendu.etatVente == 'EC'}">
                        En cours
                    </c:if>
                    <c:if test="${articleVendu.etatVente == 'PC'}">
                        Pas encore commencée
                    </c:if>
                    <c:if test="${articleVendu.etatVente == 'VE'}">
                        Finie
                    </c:if>
                    <c:if test="${articleVendu.etatVente == 'AN'}">
                        Annulée
                    </c:if>
                </p>
                <p>Fin de l'enchère : <fmt:formatDate value="${articleVendu.dateFinEncheres}" type="date" pattern="dd/MM/YYYY"/> </p>
                <p>Vendeur : ${pseudos[articleVendu.noArticle]}</p>
                <a class="text-center" href="${pageContext.request.contextPath}/auction?id=${articleVendu.noArticle}"><button class="btn btn-info mb-3">Voir l'enchère</button></a>
            </div>
        </c:forEach>
    </div>
</div>
