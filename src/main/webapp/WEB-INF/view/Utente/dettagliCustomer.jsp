<%--
  Created by IntelliJ IDEA.
  User: nicol
  Date: 14/04/2023
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container col-xxl-8 px-4 py-5">
    <div class="row flex-lg-row-reverse align-items-center g-5 py-5 w-75 align-middle">


        <h2 class="display-6 fw-bold lh-1 mb-3" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            Ecco i tuoi dati:
        </h2>

        <div class="col-lg-8">
            <div class="card mb-4">
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-3">
                            <p class="mb-0">Nome</p>
                        </div>
                        <div class="col-sm-9">
                            <p class="text-muted mb-0">${Utente.nome}</p>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-sm-3">
                            <p class="mb-0">Cognome</p>
                        </div>
                        <div class="col-sm-9">
                            <p class="text-muted mb-0">${Utente.cognome}</p>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-sm-3">
                            <p class="mb-0">Username</p>
                        </div>
                        <div class="col-sm-9">
                            <p class="text-muted mb-0">${Utente.username}</p>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-sm-3">
                            <p class="mb-0">Data di Nascita</p>
                        </div>
                        <div class="col-sm-9">
                            <p class="text-muted mb-0"><fmt:formatDate value="${Utente.dataNascita}" pattern="dd MMMM yyyy"/></p>
                        </div>
                    </div>

                </div>

            </div>

          <c:url var="modificaUtente" value="/utente/modificaUtente?id=${Utente.id}"/>
          <a href="${modificaUtente}" class="btn btn-primary float-right">
            <span class="glyphicon glyphicon-plus-sign"></span> Modifica i tuoi dati

          </a>
          <c:url var="profiloUtente" value="/utente/profiloCustomer"/>
          <a href="${profiloUtente}" class="btn btn-secondary float-right">
            <span class="glyphicon glyphicon-plus-sign"></span> Torna indietro

          </a>
        </div>
    </div>
</div>


</div>
</div>