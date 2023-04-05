<%@ page contentType="text/html;charset=UTF-8" %>
<%-- Use JavaBean to display informations --%>
<h1 class="text-center mb-5">Profil</h1>

<jsp:useBean id="utilisateurRequest"
             scope="request"
             class="encheres.bo.Utilisateur">
</jsp:useBean>
<table class="table table-borderless">
    <tbody>
        <tr class="row">
            <th class="offset-1 col-3 offset-md-5 col-md-2" scope="row">Pseudo</th>
            <td class="offset-1 col-7 col-md-3"><jsp:getProperty name="utilisateurRequest" property="pseudo"/></td>
        </tr>
        <tr class="row">
            <th class="offset-1 col-3 offset-md-5 col-md-2" scope="row">Nom</th>
            <td class="offset-1 col-7 col-md-3"><jsp:getProperty name="utilisateurRequest" property="nom"/></td>
        </tr>
        <tr class="row">
            <th class="offset-1 col-3 offset-md-5 col-md-2" scope="row">Prénom</th>
            <td class="offset-1 col-7 col-md-3"><jsp:getProperty name="utilisateurRequest" property="prenom"/></td>
        </tr>
        <tr class="row">
            <th class="offset-1 col-3 offset-md-5 col-md-2" scope="row">Email</th>
            <td class="offset-1 col-7 col-md-3"><jsp:getProperty name="utilisateurRequest" property="email"/></td>
        </tr>
        <tr class="row">
            <th class="offset-1 col-3 offset-md-5 col-md-2" scope="row">Téléphone</th>
            <td class="offset-1 col-7 col-md-3"><jsp:getProperty name="utilisateurRequest" property="telephone"/></td>
        </tr>
        <tr class="row">
            <th class="offset-1 col-3 offset-md-5 col-md-2" scope="row">Rue</th>
            <td class="offset-1 col-7 col-md-3"><jsp:getProperty name="utilisateurRequest" property="rue"/></td>
        </tr>
        <tr class="row">
            <th class="offset-1 col-3 offset-md-5 col-md-2" scope="row">Code&nbsp;Postal</th>
            <td class="offset-1 col-7 col-md-3"><jsp:getProperty name="utilisateurRequest" property="codePostal"/></td>
        </tr>
        <tr class="row">
            <th class="offset-1 col-3 offset-md-5 col-md-2" scope="row">Ville</th>
            <td class="offset-1 col-7 col-md-3"><jsp:getProperty name="utilisateurRequest" property="ville"/></td>
        </tr>
    </tbody>
</table>
<%--@elvariable id="canUpdate" type="boolean"--%>
<c:if test="${ canUpdate }">
    <a class="" href="${pageContext.request.contextPath}/updateProfile">
        <button class="btn btn-success d-block mx-auto">Modifier mon profil</button>
    </a>
</c:if>