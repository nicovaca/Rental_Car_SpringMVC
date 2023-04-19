<%--
  Created by IntelliJ IDEA.
  User: nicol
  Date: 31/03/2023
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<section class="container">


    <div class="container col-xxl-8 px-4 py-5">
        <div class="row flex-lg-row-reverse align-items-center g-5 py-5 w-75 align-middle">

            <form:form method="post" modelAttribute="Utente">
                <form:errors path="*" cssClass="alert alert-danger" element="div"/>


                <form:hidden path="id" value="${Utente.id}"/>

                <c:choose>
                    <c:when test="${Utente.id == null}">
                        <h2 class="display-6 fw-bold lh-1 mb-3">
                            Inserisci il nuovo Utente
                        </h2>
                    </c:when>

                    <c:otherwise>
                        <sec:authorize access="hasRole('SUPERUSER')">
                            <h2 class="display-6 fw-bold lh-1 mb-3">
                                Modifica Utente
                            </h2>
                        </sec:authorize>
                        <sec:authorize access="hasRole('CUSTOMER')">
                            <h2 class="display-6 fw-bold lh-1 mb-3">
                                Modifica i tuoi dati
                            </h2>
                        </sec:authorize>
                    </c:otherwise>
                </c:choose>


                <div class="mb-3">
                    <label for="nomeUtente" class="form-label">Nome:</label>
                    <form:input path="nome" type="text" class="form-control" id="nomeUtente"
                                name="nome" value="${Utente.nome}"/>
                    <form:errors path="nome" cssClass="text-danger"/>
                </div>


                <div class="mb-3">
                    <label for="cognomeUtente" class="form-label">Cognome:</label>
                    <form:input path="cognome" type="text" class="form-control" id="cognomeUtente"
                                name="cognome" value="${Utente.cognome}"/>
                    <form:errors path="cognome" cssClass="text-danger"/>
                </div>


                <div class="mb-3">
                    <label for="dataNascita">Data di Nascita:</label>
                    <form:input type="date" path="dataNascita" id="dataNascita" name="dataNascita"
                                value="${Utente.dataNascita}"/>
                    <form:errors path="dataNascita" cssClass="text-danger"/>
                </div>


                <div class="mb-3">
                    <label for="usernameUtente" class="form-label">Username:</label>
                    <div class="input-group flex-nowrap">
                        <span class="input-group-text" id="usernameUtente">@</span>
                        <form:input type="text" path="username" class="form-control" placeholder="Username"
                                    aria-label="Username" aria-describedby="addon-wrapping" value="${Utente.username}"/>
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
                <form:button type="submit" class="btn btn-primary mb-2">
                    <c:choose>
                        <c:when test="${Utente.id == null}">
                            Inserisci
                        </c:when>
                        <c:otherwise>
                            Modifica
                        </c:otherwise>
                    </c:choose>


                </form:button>

                <sec:authorize access="hasRole('SUPERUSER')">
                <c:url var="utenti" value="../utente/profiloAdmin"/>
                <a href="${utenti}" id="home" class="btn btn-secondary mb-2">Torna indietro</a>
                </sec:authorize>
                <sec:authorize access="hasRole('CUSTOMER')">
                    <c:url var="utenti" value="../utente/dettagliUtente"/>
                    <a href="${utenti}" id="home" class="btn btn-secondary mb-2">Torna indietro</a>
                </sec:authorize>
            </form:form>


        </div>
    </div>


</section>

