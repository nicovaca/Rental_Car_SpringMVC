<%--
  Created by IntelliJ IDEA.
  User: nicol
  Date: 02/04/2023
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<br><br>

<section class="container">

    <!-- Search Box -->
    <nav class="navbar bg-body-tertiary">
        <div class="container-fluid">
            <form:form class="d-flex" id="search" role="search" method="GET" action="../prenotazioni/cercaPrenotazioni">
                <input type="text" onClick="this.select();" class="form-control me-2" name="filtro" value="${filtro}"
                       placeholder="Cerca Prenotazioni" style="width: 550px">
                <button class="btn btn-outline-success" type="submit">Cerca</button>
                &nbsp&nbsp
                <a href="listaPrenotazioni?id=" type="button" class="btn btn-outline-warning">Reset</a>
            </form:form>
        </div>
    </nav>
    <br>

    <table class="table table-striped table-bordered">
        <thead>
        <tr class="table-dark">
            <th scope="col">Username</th>
            <th scope="col">Utente</th>
            <th scope="col">Veicolo</th>
            <th scope="col">Data Inizio</th>
            <th scope="col">Data Fine</th>
            <th scope="col">Approva</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="prenotazioni" items="${Prenotazioni}">
            <c:set value="${prenotazioni.utente.nome} ${prenotazioni.utente.cognome}" var="nomeCompleto"></c:set>
            <tr>
                <th scope="row">${prenotazioni.utente.username}</th>
                <td>${nomeCompleto}</td>
                <td>${prenotazioni.veicolo.casaCostruttrice} ${prenotazioni.veicolo.modello}</td>
                <td>${prenotazioni.periodoPrenotazione.dataInizio}</td>
                <td>${prenotazioni.periodoPrenotazione.dataFine}</td>
                <td style="width: 300px">
                        <form:form method="post" action="gestisciPrenotazione">
                            <input path="id" type="hidden" name="id" value="${prenotazioni.id}"/>
                            <select class="form-select" style="width: 150px" name="approva">
                                <c:if test="${prenotazioni.approvazione == true}">
                                <option selected disabled>Approvata</option>
                                </c:if>
                                <option value="false">No</option>
                                <option value="true">Si</option>
                            </select>
                            <input class="btn btn-dark" type="submit" value="Approva">
                            <a href="eliminaPrenotazione?id=${prenotazioni.id}"
                               onclick="return confirm('Sei sicuro di voler eliminare la prenotazione?')"
                               class="btn btn-danger table-buttons">
                                <span class="oi oi-trash"/></span> Elimina
                            </a>
                        </form:form>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</section>
