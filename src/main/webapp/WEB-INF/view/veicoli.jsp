<%--
  Created by IntelliJ IDEA.
  User: nicol
  Date: 30/03/2023
  Time: 09:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<section class="container">
    <br>
    <h2 class="display-8 fw-bold lh-1 mb-3"><spring:message code="parcoAuto.label.listaVeicoli"/></h2>


    <sec:authorize access="hasRole('SUPERUSER')">
        <!--Bottone Aggiungi Veicolo-->
        <c:url var="veicolo" value="/veicolo/nuovoVeicolo"></c:url>
        <a href="${veicolo}" class="btn btn-success float-right">
            <span class="glyphicon glyphicon-plus-sign"></span> Aggiungi un nuovo Veicolo

        </a>
        <!-- Search Box -->
        <nav class="navbar bg-body-tertiary">
            <div class="container-fluid">
                <form:form class="d-flex" id="search" role="search" method="get" action="cercaVeicolo">
                    <input type="text" onClick="this.select();" class="form-control me-2" name="filtro" value="${filtro}"
                           placeholder="Cerca Veicolo" style="width: 550px">
                    <button class="btn btn-outline-success" type="submit">Cerca</button>
                    &nbsp&nbsp
                    <c:url var="veicolo" value="../veicolo/veicoli"/>
                    <a href="${veicolo}" type="button" class="btn btn-outline-warning">Reset</a>
                </form:form>
            </div>
        </nav>
    </sec:authorize>



    <br>
    <table class="table table-striped table-bordered">
        <thead>
        <tr class="table-dark">
            <th scope="col">Id</th>
            <th scope="col">Casa Costruttrice</th>
            <th scope="col">Modello</th>
            <th scope="col">Anno Immatricolazione</th>
            <th scope="col">Targa</th>
            <th scope="col">Tipo Veicolo</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="veicoli" items="${Veicoli}">
            <tr>
                <th scope="row">${veicoli.id}</th>
                <td>${veicoli.casaCostruttrice}</td>
                <td>${veicoli.modello}</td>
                <td style="width: 200px">${veicoli.annoImmatricolazione}</td>
                <td>${veicoli.targa}</td>
                <td>${veicoli.tipoVeicolo}</td>

                <sec:authorize access="hasRole('SUPERUSER')">
                <td style="width: 200px"><a href="modificaVeicolo?id=${veicoli.id}"
                                            class="btn btn-warning table-buttons">
                    <span class="oi oi-pencil"></span> Modifica
                </a>&nbsp&nbsp

                    <a href="eliminaVeicolo?id=${veicoli.id}"
                       onclick="return confirm('Sei sicuro di voler cancellare il veicolo?')"
                       class="btn btn-danger table-buttons">
                        <span class="oi oi-trash"/></span> Elimina
                    </a>
                </sec:authorize>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>