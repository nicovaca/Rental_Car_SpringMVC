<%--
  Created by IntelliJ IDEA.
  User: nicol
  Date: 02/04/2023
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<section class="container">

    <div class="container col-xxl-8 px-4 py-5">
        <div class="row flex-lg-row-reverse align-items-center g-5 py-5 w-75 align-middle">

            <h2 class="display-6 fw-bold lh-1 mb-3">
                <spring:message code="registrazione.label"/>
            </h2>

            <form:form method="post" modelAttribute="Utente">
                <form:errors path="*" cssClass="alert alert-danger" element="div"/>


                <div class="mb-3">
                    <label for="nomeUtente" class="form-label">Nome:</label>
                    <form:input path="nome" type="text" class="form-control" id="nomeUtente"
                                name="nome"/>
                    <form:errors path="nome" cssClass="text-danger"/>
                </div>
                <div class="mb-3">
                    <label for="cognomeUtente" class="form-label">Cognome:</label>
                    <form:input path="cognome" type="text" class="form-control" id="cognomeUtente"
                                name="cognome"/>
                    <form:errors path="cognome" cssClass="text-danger"/>
                </div>

                <div class="mb-3">
                    <label for="dataNascita">Data di Nascita:</label>
                    <form:input type="date" path="dataNascita" id="dataNascita" name="dataNascita"
                    />
                    <form:errors path="dataNascita" cssClass="text-danger"/>

                </div>

                <div class="mb-3">
                    <label for="usernameUtente" class="form-label">Username:</label>
                    <div class="input-group flex-nowrap">
                        <span class="input-group-text" id="usernameUtente">@</span>
                        <form:input type="text" path="username" class="form-control" placeholder="Username"
                                    aria-label="Username" aria-describedby="addon-wrapping"/>
                    </div>
                    <form:errors path="username" cssClass="text-danger"/>
                </div>

                <div class="mb-3">
                    <label for="passwordUtente" class="form-label">Password</label>
                    <form:input type="password" path="password" class="form-control" id="passwordUtente"
                                placeholder="password" aria-label="password"/>
                    <form:errors path="password" cssClass="text-danger"/>
                </div>


                <form:input type="hidden" path="ruolo" id="utenteRuolo" name="ruolo" value="CUSTOMER"/>

                <br><br>
                <form:button type="submit" class="btn btn-primary mb-2">Registrati
                </form:button>
                <c:url var="home" value="../Homepage"/>
                <a href="${home}" id="home" class="btn btn-secondary mb-2">Torna alla Home</a>

            </form:form>


        </div>
    </div>


</section>

