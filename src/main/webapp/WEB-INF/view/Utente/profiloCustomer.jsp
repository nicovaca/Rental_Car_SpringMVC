<%--
  Created by IntelliJ IDEA.
  User: nicol
  Date: 04/04/2023
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="now" class="java.util.Date"/>


<section class="container">

    <br>
    <h3 class="display-10 fw-bold lh-1 mb-3"><spring:message code="profiloCustomer.welcome.label"/> ${Utente.nome} ${Utente.cognome}</><br>

    <h2 class="display-8 fw-bold lh-1 mb-3">
        <spring:message code="profiloCustomer.prenotazioni.label"/>
    </h2>

    <c:url var="nuovaPrenotazione" value="/prenotazioni/nuovaPrenotazione"/>
    <a href="${nuovaPrenotazione}" class="btn btn-success">
        <span class="glyphicon glyphicon-plus-sign"></span> Nuova prenotazione

    </a>

    <%--<c:url var="modificaUtente" value="/utente/modificaUtente?id=${Utente.id}"/>
    <a href="${modificaUtente}" class="btn btn-success float-right">
        <span class="glyphicon glyphicon-plus-sign"></span> Modifica i tuoi dati

    </a>--%>
    <c:url var="dettagliUtente" value="/utente/dettagliUtente"/>
    <a href="${dettagliUtente}" class="btn btn-info">
        <span class="glyphicon glyphicon-plus-sign"></span> Visualizza i tuoi dati

    </a>

    <!-- Search Box -->
    <nav class="navbar bg-body-tertiary">
        <div class="container-fluid">
            <form:form class="d-flex" id="search" role="search" method="GET"
                       action="../prenotazioni/cercaPrenotazioniCustomer">
                <input type="text" onClick="this.select();" class="form-control me-2" name="filtro" value="${filtro}"
                       placeholder="Cerca Prenotazioni" style="width: 550px">
                <button class="btn btn-outline-success" type="submit">Cerca</button>
                &nbsp&nbsp
                <a href="../utente/profiloCustomer" type="button" class="btn btn-outline-warning">Reset</a>
            </form:form>
        </div>
    </nav>
    <br><br>

    <table class="table table-striped table-bordered">
        <thead>
        <tr class="table-dark">
            <th scope="row">Data Inizio</th>
            <th scope="col">Data Fine</th>
            <th scope="col">Veicolo</th>
            <th scope="col">Approvata</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="prenotazioni" items="${Prenotazioni}">

            <tr>

                <td><fmt:formatDate type="date" dateStyle="short"
                                    value="${prenotazioni.periodoPrenotazione.dataInizio}"/></td>
                <td><fmt:formatDate type="date" dateStyle="short"
                                    value="${prenotazioni.periodoPrenotazione.dataFine}"/></td>
                <c:set value="${prenotazioni.veicolo.casaCostruttrice} ${prenotazioni.veicolo.modello}"
                       var="veicolo"/>
                <td>${veicolo}</td>
                <td>
                    <c:choose>
                        <c:when test="${prenotazioni.approvazione == true}">
                            Si
                        </c:when>
                        <c:otherwise>
                            No
                        </c:otherwise>
                    </c:choose>
                </td>

                <c:set value="${now}" var="dataCorrente"/>
                <c:choose>
                <c:when test="${prenotazioni.periodoPrenotazione.dataFine.before(now)}">
                    <td class="text-center">Prenotazione Scaduta</td>
                </c:when>
                <c:otherwise>


                <td style="width: 200px">
                    <c:choose>
                    <c:when test="${prenotazioni.approvazione == true}">
                        <button class="btn btn-warning table-buttons" disabled>
                            <span class="oi oi-pencil"></span> Modifica
                        </button>

                    </c:when>
                    <c:otherwise>
                    <a href="../prenotazioni/modificaPrenotazione?id=${prenotazioni.id}"
                       class="btn btn-warning table-buttons">
                        <span class="oi oi-pencil"></span> Modifica
                    </a>
                    </c:otherwise>
                    </c:choose>

                    &nbsp&nbsp

                    <a href="../prenotazioni/eliminaPrenotazione?id=${prenotazioni.id}"
                       onclick="return confirm('Sei sicuro di voler cancellare l\'utente?')"
                       class="btn btn-danger table-buttons">
                        <span class="oi oi-trash"/></span> Elimina
                    </a>
                    </c:otherwise>
                    </c:choose>


            </tr>

        </c:forEach>
        </tbody>
    </table>

</section>
