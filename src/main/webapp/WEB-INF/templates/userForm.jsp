<%@ page contentType="text/html;charset=UTF-8" %>

    <h2 class="mb-md-5 text-center">Mon profil</h2>
<div class="form-group-md row">
        <label class="col-form-label col-2 col-md-1" for="pseudo">Pseudo    <i class="text-danger">*</i></label>
        <div class="col-md-4 offset-1 offset-md-0 col-9 mb-2 mb-md-0">
            <input required type="text" class="form-control" name="pseudo" id="pseudo"
               <c:choose>
                    <c:when test="${page == 'updateProfile'}">
                        value="<jsp:getProperty name="utilisateurSession" property="pseudo"/>"
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${!empty(utilisateurError)}">
                                value="${utilisateurError.pseudo}"
                            </c:when>
                            <c:otherwise>
                                placeholder="Pseudo (Caractères alphanumériques)"
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
               </c:choose>
            >
        </div>
        <label class="col-form-label col-2 offset-md-1 col-md-1" for="name">Nom    <i class="text-danger">*</i></label>
        <div class="col-md-4 offset-1 offset-md-0 col-9 mb-2 mb-md-0">
            <input required type="text" class="form-control" name="name" id="name"
               <c:choose>
                    <c:when test="${page == 'updateProfile'}">
                        value="<jsp:getProperty name="utilisateurSession" property="nom"/>"
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${!empty(utilisateurError)}">
                                value="${utilisateurError.nom}"
                            </c:when>
                            <c:otherwise>
                                placeholder="Nom"
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
               </c:choose>
            >
        </div>
    </div>
    <div class="form-group-md-md row">
        <label class="col-form-label col-2 col-md-1" for="first_name">Prénom    <i class="text-danger">*</i></label>
        <div class="col-md-4 offset-1 offset-md-0 col-9 mb-2 mb-md-0">
            <input required type="text" class="form-control" name="first_name" id="first_name"
               <c:choose>
                    <c:when test="${page == 'updateProfile'}">
                        value="<jsp:getProperty name="utilisateurSession" property="prenom"/>"
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${!empty(utilisateurError)}">
                                value="${utilisateurError.prenom}"
                            </c:when>
                            <c:otherwise>
                                placeholder="Prénom"
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
               </c:choose>
            >
        </div>
        <label class="col-form-label col-2 offset-md-1 col-md-1" for="mail">Email    <i class="text-danger">*</i></label>
        <div class="col-md-4 offset-1 offset-md-0 col-9 mb-2 mb-md-0">
            <input required type="text" class="form-control" name="mail" id="mail"
               <c:choose>
                    <c:when test="${page == 'updateProfile'}">
                        value="<jsp:getProperty name="utilisateurSession" property="email"/>"
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${!empty(utilisateurError)}">
                                value="${utilisateurError.email}"
                            </c:when>
                            <c:otherwise>
                                placeholder="Email"
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
               </c:choose>
            >
        </div>
    </div>
    <div class="form-group-md row">
        <label class="col-form-label col-2 col-md-1" for="phone">Téléphone    <i class="text-danger">*</i></label>
        <div class="col-md-4 offset-1 offset-md-0 col-9 mb-2 mb-md-0">
            <input required type="text" class="form-control" name="phone" id="phone"
               <c:choose>
                    <c:when test="${page == 'updateProfile'}">
                        value="<jsp:getProperty name="utilisateurSession" property="telephone"/>"
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${!empty(utilisateurError)}">
                                value="${utilisateurError.telephone}"
                            </c:when>
                            <c:otherwise>
                       p            placeholder="Téléphone (format : 0699999999)"
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
               </c:choose>
            >
        </div>
        <label class="col-form-label col-2 offset-md-1 col-md-1" for="street">Rue    <i class="text-danger">*</i></label>
        <div class="col-md-4 offset-1 offset-md-0 col-9 mb-2 mb-md-0">
            <input required type="text" class="form-control" name="street" id="street"
               <c:choose>
                    <c:when test="${page == 'updateProfile'}">
                        value="<jsp:getProperty name="utilisateurSession" property="rue"/>"
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${!empty(utilisateurError)}">
                                value="${utilisateurError.rue}"
                            </c:when>
                            <c:otherwise>
                                placeholder="Rue"
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
               </c:choose>
            >
        </div>
    </div>
    <div class="form-group-md row">
        <label class="col-form-label col-2 col-md-1" for="post_code">Code Postal    <i class="text-danger">*</i></label>
        <div class="col-md-4 offset-1 offset-md-0 col-9 mb-2 mb-md-0">
            <input required type="text" class="form-control" name="post_code" id="post_code"
               <c:choose>
                    <c:when test="${page == 'updateProfile'}">
                        value="<jsp:getProperty name="utilisateurSession" property="codePostal"/>"
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${!empty(utilisateurError)}">
                                value="${utilisateurError.codePostal}"
                            </c:when>
                            <c:otherwise>
                                placeholder="Code Postal"
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
               </c:choose>
            >
        </div>
        <label class="col-form-label col-2 offset-md-1 col-md-1" for="city">Ville    <i class="text-danger">*</i></label>
        <div class="col-md-4 offset-1 offset-md-0 col-9 mb-2 mb-md-0">
            <input required type="text" class="form-control" name="city" id="city"
               <c:choose>
                    <c:when test="${page == 'updateProfile'}">
                        value="<jsp:getProperty name="utilisateurSession" property="ville"/>"
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${!empty(utilisateurError)}">
                                value="${utilisateurError.ville}"
                            </c:when>
                            <c:otherwise>
                                placeholder="Ville"
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            >
        </div>
    </div>