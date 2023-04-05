<%@ page contentType="text/html;charset=UTF-8" %>
<form id="form_creation_login" method="post" action="${pageContext.request.contextPath}/createLogin" class="ml-1 ml-md-5">
    <%@include file="../templates/userForm.jsp"%>
    <div class="form-group row" id="password_block">
        <label class="col-form-label col-md-1" for="password">Mot&nbsp;de&nbsp;passe<i class="text-danger">*</i></label></label>
        <div class="col-md-4">
            <input required type="password" class="form-control" name="password" id="password" placeholder="Mot de passe">
        </div>
        <label class="col-form-label offset-md-1 col-md-1" for="confirm_password">Confirmation<i class="text-danger">*</i></label></label>
        <div class="col-md-4">
            <input required type="password" class="form-control" name="confirm_password" id="confirm_password" placeholder="Répétez le mot de passe">
        </div>
    </div>
    <%@include file="../templates/displayErrors.jsp"%>
    <div class="row mt-md-5">
        <input type="submit" value="Créer" class="offset-1 col-4 offset-md-2 col-md-3 btn btn-success btn-block btn-lg">
       
    </div>
</form>
