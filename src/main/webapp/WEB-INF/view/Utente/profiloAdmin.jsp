<%--
  Created by IntelliJ IDEA.
  User: nicol
  Date: 30/03/2023
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<br><br>

<section class="container">
    <c:url var="utente" value="/utente/nuovoUtente"></c:url>
    <a href="${utente}" class="btn btn-success float-right">
        <span class="glyphicon glyphicon-plus-sign"></span> Aggiungi un nuovo Utente

    </a>

    <br><br>
    <!-- Search Box -->
    <nav class="navbar bg-body-tertiary">
        <div class="container-fluid">
            <form:form class="d-flex" id="search" role="search" method="GET" action="cercaUtente">
                <input type="text" onClick="this.select();" class="form-control me-2" name="filtro" value="${filtro}"
                       placeholder="Cerca Utenti" style="width: 550px">
                <button class="btn btn-outline-success" type="submit">Cerca</button>
                &nbsp&nbsp
                <a href="profiloAdmin" type="button" class="btn btn-outline-warning">Reset</a>
            </form:form>
        </div>
    </nav>


    <br>

    <table class="table table-striped table-bordered">
        <thead>
        <tr class="table-dark">
            <th scope="row">Username</th>
            <th scope="col">Nome</th>
            <th scope="col">Cognome</th>
            <th scope="col">Data di Nascita</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="utente" items="${Utenti}">
            <c:set var="tipoUtente" value="${utente.ruolo}"/>
            <c:if test="${tipoUtente == 'CUSTOMER'}">
                <tr>

                    <th scope="row">${utente.username}</th>
                    <td>${utente.nome}</td>
                    <td>${utente.cognome}</td>

                    <td style="width: 300px"><fmt:formatDate type="date" dateStyle="short"
                                                             value="${utente.dataNascita}"/></td>
                    <td style="width: 325px"><a href="modificaUtente?id=${utente.id}"
                                                class="btn btn-warning table-buttons">
                        <span class="oi oi-pencil"></span> Modifica
                    </a>&nbsp&nbsp

                        <a href="eliminaUtente?id=${utente.id}"
                           onclick="return confirm('Sei sicuro di voler cancellare l\'utente?')"
                           class="btn btn-danger table-buttons">
                            <span class="oi oi-trash"/></span> Elimina
                        </a>&nbsp&nbsp
                        <c:url value="../prenotazioni/listaPrenotazioni?id=${utente.id}"
                               var="prenotazioniUtente"/>
                        <a href="${prenotazioniUtente}" class="btn btn-info table-buttons">
                            <span class="oi oi-trash"/></span> Prenotazioni
                        </a></td>

                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>

</section>
