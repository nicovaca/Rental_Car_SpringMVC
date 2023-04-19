<%--
  Created by IntelliJ IDEA.
  User: nicol
  Date: 29/03/2023
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="container col-xxl-8 px-4 py-5">
    <div class="row flex-lg-row-reverse align-items-center g-5 py-5">

        <div class="text-center">
            <h1 class="display-5 fw-bold lh-1 mb-3">
                <spring:message code="homepage.messaggio1"/>
            </h1>

            <sec:authorize access="hasRole('ANONYMOUS')">


            <p class="lead"><spring:message code="homepage.messaggio2"/> </p>

            <a href="login/form" id="Accedi" class="btn btn-primary btn-lg"> <spring:message code="homepage.button.label.accedi"/> </a>
            <c:url value="/utente/registrazione" var="registrazioneUtente"></c:url>
            <a href="${registrazioneUtente}" id="Registrati" class="btn btn-secondary btn-lg"> <spring:message code="homepage.button.label.registrati"/></a>
            </sec:authorize>

            <br><br>
            <sec:authorize access="hasAnyRole('SUPERUSER','CUSTOMER')">
                <c:url value="/veicolo" var="veicolo"/>
                <c:url value="/utente/profilo" var="profilo"/>
                <h4><a href="${veicolo}" class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover"><spring:message code="homepage.link.label.parcoAuto"/></a></h4><spring:message code="homepage.link.label.oppure"/>
                <h4><a href="${profilo}" class="link-secondary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover"><spring:message code="homepage.link.label.profilo"/></a></h4>
            </sec:authorize>

        </div>

    </div>
</div>
