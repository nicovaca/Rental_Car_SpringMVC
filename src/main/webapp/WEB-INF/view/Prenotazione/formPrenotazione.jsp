<%--
  Created by IntelliJ IDEA.
  User: nicol
  Date: 03/04/2023
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<section class="container">


    <div class="container col-xxl-8 px-4 py-5">
        <div class="row flex-lg-row-reverse align-items-center g-5 py-5 w-75 align-middle">


            <form:form method="post" modelAttribute="Prenotazione">


                <form:hidden path="id" value="${Veicolo.id}"/>

                <c:choose>
                    <c:when test="${Prenotazione.id == null}">
                        <h2 class="display-6 fw-bold lh-1 mb-3">
                            Effettua una nuova prenotazione
                        </h2>
                    </c:when>
                    <c:otherwise>
                        <h2 class="display-6 fw-bold lh-1 mb-3">
                            Modifica prenotazione
                        </h2>
                    </c:otherwise>
                </c:choose>

                <br>
                <form:errors path="*" cssClass="alert alert-danger" element="div"/>

                <form:hidden path="utente.id" value="${Utente.id}"/>


                <br>
                <div class="mb-3">
                    <label for="dataInizio">Data inizio prenotazione:</label>
                    <form:input type="date" path="periodoPrenotazione.dataInizio" id="dataInizio" name="dataInizio"
                                value="${Prenotazione.periodoPrenotazione.dataInizio}"/>
                    <form:errors path="periodoPrenotazione.dataInizio" cssClass="text-danger"/>
                </div>


                <div class="mb-3">
                    <label for="dataFine">Data fine prenotazione:</label>
                    <form:input type="date" path="periodoPrenotazione.dataFine" id="dataFine" name="dataFine"
                                value="${Prenotazione.periodoPrenotazione.dataFine}"/>
                    <form:errors path="periodoPrenotazione.dataFine" cssClass="text-danger"/>
                </div>

                <div>
                    <label for="veicolo">Seleziona il veicolo da prenotare:</label>



                    <form:select path="veicolo.id" id="veicolo" name="veicolo" value="${Prenotazione.veicolo.id}"
                               cssClass="form-select form-select-sm" aria-label=".form-select-sm example">

                        <c:forEach var="veicolo" items="${Veicoli}">

                            <c:set var="nomeVeicolo"
                                   value="${veicolo.casaCostruttrice} ${veicolo.modello}"/>

                            <form:option value="${veicolo.id}" label="${nomeVeicolo}"/>

                        </c:forEach>
                    </form:select>


                </div>

                <form:hidden path="approvazione" value="${approvazione}" default="FALSE"/>

                <br><br>
                <form:button type="submit" class="btn btn-primary mb-2">
                    <c:choose>
                        <c:when test="${Prenotazione.id == null}">
                            Inserisci
                        </c:when>
                        <c:otherwise>
                            Modifica
                        </c:otherwise>
                    </c:choose>

                </form:button>
                <c:url var="prenotazione" value="../utente/profiloCustomer"/>
                <a href="${prenotazione}" id="home" class="btn btn-secondary mb-2">Torna indietro</a>

            </form:form>



        </div>
    </div>


</section>

