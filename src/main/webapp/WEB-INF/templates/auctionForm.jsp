<%@ page contentType="text/html;charset=UTF-8" %>
<div class="form-row">
    <label for="name" class="col-2 offset-md-3 col-md-2">Article</label>
    <input required name="name" type="text" class="form-control offset-1 col-8 col-md-3" id="name"
    <%--@elvariable id="auctionToUpdate" type="fr.eni.projet_encheres.bo.ArticleVendu"--%>
    <c:if test="${!empty(auctionToUpdate)}">
        value="${auctionToUpdate.nomArticle}"
    </c:if>
    >
</div>
<div class="form-row mt-2">
    <label for="description" class="form col-2 offset-md-3 col-md-2">Description</label>
    <textarea name="description" id="description" cols="30" rows="10" class="form-control offset-1 col-8 col-md-3"><c:if test="${!empty(auctionToUpdate)}">${auctionToUpdate.description}</c:if></textarea>
</div>
<div class="form-row mt-2">
    <label class="col-2 offset-md-3 col-md-2" for="category">Catégorie</label>
    <select name="category" id="category" class="form-control offset-1 col-8 col-md-3">
        <c:forEach items="${categories}" var="category">
            <c:choose>
                <c:when test="${category.noCategorie == auctionToUpdate.noCategorie}">
                    <option selected value="${category.noCategorie}">${category.libelle}</option>
                </c:when>
                <c:otherwise>
                    <option value="${category.noCategorie}">${category.libelle}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
</div>
<div class="form-row mt-2">
    <label for="picture" class="col-4 offset-md-3 col-md-2">Photo de l'article</label>
    <input type="file" class="offset-1 col-7 col-md-3 form-control-file" id="picture">
</div>
<div class="form-row mt-2">
    <label for="starting_price" class="col-4 offset-md-3 col-md-2">Mise à prix</label>
    <input required type="number" name="starting_price" id="starting_price" class="offset-1 col-6 col-md-3 form-control" min="1" value="1"
    <c:if test="${!empty(auctionToUpdate)}">
           value="${auctionToUpdate.prixInitial}"
    </c:if>
    >
</div>
<div class="form-row mt-3">
    <label for="start_auction_date" class="col-4 offset-md-3 col-md-2">Début de l'enchère</label>
    <div class="offset-1 col-6 col-md-3">
        <div class="form-group">
            <div class="input-group date" id="datetimepicker1" data-target-input="nearest">
                <input required id="start_auction_date" name="start_auction_date" type="text" class="form-control datetimepicker-input" data-target="#datetimepicker1"/>
                <div class="input-group-append" data-target="#datetimepicker1" data-toggle="datetimepicker">
                    <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $(function () {
            $('#datetimepicker1').datetimepicker({
                format: 'L',
                locale: 'fr'
                <c:if test="${!empty(auctionToUpdate)}">
                ,
                defaultDate:
                        value="${auctionToUpdate.dateDebutEncheres}"
                </c:if>
            });
        });
    </script>
</div>
<div class="form-row">
    <label for="end_auction_date" class="col-4 offset-md-3 col-md-2">Fin de l'enchère</label>
    <div class="offset-1 col-6 col-md-3">
        <div class="form-group">
            <div class="input-group date" id="datetimepicker2" data-target-input="nearest">
                <input required id="end_auction_date" name="end_auction_date" type="text" class="form-control datetimepicker-input" data-target="#datetimepicker2"/>
                <div class="input-group-append" data-target="#datetimepicker2" data-toggle="datetimepicker">
                    <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $(function () {
            $('#datetimepicker2').datetimepicker({
                format: 'L',
                locale: 'fr'
                <c:if test="${!empty(auctionToUpdate)}">
                ,
                defaultDate:
                        value="${auctionToUpdate.dateFinEncheres}"
                </c:if>
            });
        });
    </script>
</div>
<fieldset class="form-group">
    <div class="form-row">
        <legend class="col-form offset-md-3 col-md-1">Retrait</legend>
        <div class="form-group offset-md-1 col-md-7">
            <div class="form-row">
                <label class="col-3" for="street">Rue</label>
                <input required type="text" id="street" name="street" class="form-control d-inline offset-1 col-6 col-md-3" value="<jsp:getProperty name="utilisateurSession" property="rue"/>">

            </div>
            <div class="form-row mt-2">
                <label class="col-3" for="postal_code">Code postal</label>
                <input required type="text" id="postal_code" name="postal_code" class="form-control d-inline offset-1 col-6 col-md-3" value="<jsp:getProperty name="utilisateurSession" property="codePostal"/>">
            </div>
            <div class="form-row mt-2">
                <label class="col-3" for="city">Ville</label>
                <input required type="text" id="city" name="city" class="form-control d-inline offset-1 col-6 col-md-3" value="<jsp:getProperty name="utilisateurSession" property="ville"/>">
            </div>
        </div>
    </div>
</fieldset>
<%@include file="displayErrors.jsp"%>
<div class="form-row">
    <input type="submit" class="btn btn-lg btn-block btn-success offset-1 col-4 offset-md-4 col-md-2">
    <a href="${pageContext.request.contextPath}/home" class="offset-1 col-4 offset-md-1 col-md-2">
        <button type="button" class="btn btn-lg btn-block btn-danger">Annuler</button>
    </a>
</div>
</div>
</form>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js" type="text/javascript"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/js/tempusdominus-bootstrap-4.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/locale/fr.js"></script>