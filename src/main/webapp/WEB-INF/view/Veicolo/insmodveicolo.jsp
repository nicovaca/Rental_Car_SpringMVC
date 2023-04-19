<%--
  Created by IntelliJ IDEA.
  User: nicol
  Date: 30/03/2023
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<section class="container">


    <div class="container col-xxl-8 px-4 py-5">
        <div class="row flex-lg-row-reverse align-items-center g-5 py-5 w-75 align-middle">


            <form:form method="post" modelAttribute="Veicolo">
                <form:errors path="*" cssClass="alert alert-danger" element="div"/>

                <form:hidden path="id" value="${Veicolo.id}"/>

                <c:choose>
                    <c:when test="${Veicolo.id == null}">
                        <h2 class="display-6 fw-bold lh-1 mb-3">
                            Inserisci il nuovo veicolo
                        </h2>
                    </c:when>
                    <c:otherwise>
                        <h2 class="display-6 fw-bold lh-1 mb-3">
                            Modifica Veicolo
                        </h2>
                    </c:otherwise>
                </c:choose>

                <br>
                <div class="mb-3">
                    <label for="casaCostruttrice" class="form-label">Casa Costruttrice:</label>
                    <form:input path="casaCostruttrice" type="text" class="form-control" id="casaCostruttrice"
                                aria-describedby="emailHelp"
                                name="casaCostruttrice" value="${Veicolo.casaCostruttrice}"/>
                    <form:errors path="casaCostruttrice" cssClass="text-danger"/>
                </div>
                <div class="mb-3">
                    <label for="modello" class="form-label">Modello:</label>
                    <form:input path="modello" type="text" class="form-control" id="modello"
                                name="modello" value="${Veicolo.modello}"/>
                    <form:errors path="modello" cssClass="text-danger"/>
                </div>
                <div class="mb-3">
                    <label for="targa" class="form-label">Targa:</label>
                    <form:input path="targa" type="text" class="form-control" id="targa" name="targa"
                                value="${Veicolo.targa}"/>
                    <form:errors path="targa" cssClass="text-danger"/>
                </div>

                <div class="mb-3">
                    <label>Anno Immatricolazione:
                        <form:input path="annoImmatricolazione" type="number" name="annoImmatricolazione" min="1918"
                                    max="2023" step="1" value="${Veicolo.annoImmatricolazione}"/>
                        <form:errors path="annoImmatricolazione" cssClass="text-danger"/>
                    </label>
                </div>

                <div>
                    <label for="tipo">Seleziona il tipo:</label>
                    <form:select path="tipoVeicolo" id="tipo" name="tipo" value="${Veicolo.tipoVeicolo}"
                                 cssClass="form-select form-select-sm" aria-label=".form-select-sm example">
                        <form:option value="AUTO">Auto</form:option>
                        <form:option value="SUV">Suv</form:option>
                        <form:option value="FURGONE">Furgone</form:option>
                        <form:option value="MINIVAN">Minivan</form:option>
                    </form:select>
                </div>
                <br><br>
                <form:button type="submit" class="btn btn-primary mb-2">
                    <c:choose>
                        <c:when test="${Veicolo.id == null}">
                            Inserisci
                        </c:when>
                        <c:otherwise>
                            Modifica
                        </c:otherwise>
                    </c:choose>

                </form:button>
                <c:url var="veicoli" value="../veicolo/veicoli"/>
                <a href="${veicoli}" id="home" class="btn btn-secondary mb-2">Torna indietro</a>

            </form:form>
        </div>
    </div>


</section>