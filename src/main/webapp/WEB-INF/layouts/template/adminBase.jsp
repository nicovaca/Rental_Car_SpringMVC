<%--
  Created by IntelliJ IDEA.
  User: nicol
  Date: 30/03/2023
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><tiles:insertAttribute name="titolo"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="../Homepage">Rental Car</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse text-white" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item text-white">
                    <a class="nav-link active" aria-current="page" href="../Homepage">Home</a>
                </li>
                <li class="nav-item text-white">
                    <a class="nav-link" href="../veicolo/veicoli"> <spring:message code="homepage.parcoauto.label"/> </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="../utente/profiloAdmin"> <spring:message
                            code="homepage.profiloutente.label"/> </a>


                </li>

                <li class="nav-item">
                    <a class="nav-link" href="../prenotazioni/listaPrenotazioni?id="> <spring:message
                            code="homepage.admin.elencoprenotazioni.label"/> </a>
                </li>


                <li class="nav-item">

                    <sec:authorize access="isAuthenticated()">
                        <c:url var="logout" value="/login/form/logout"/>
                <li><a href="${logout}" class="nav-link px-2 text-white">Logout</a></li>
                </sec:authorize>

                </li>


            </ul>

            <!--Internazionalizzazione-->
            <div style="float: right">
                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="?language=en"><img height="35px" width="35px"
                                                    src="<c:url value="/static/immagini/ingl.png"/>"></a></li>
                    &nbsp&nbsp&nbsp
                    <li><a href="?language=it"><img height="35px" width="35px"
                                                    src="<c:url value="/static/immagini/ita.png"/>"></a></li>
                </ul>
            </div>&nbsp;&nbsp;&nbsp;

            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>


<tiles:insertAttribute name="content"/>
<tiles:insertAttribute name="footer"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>

</body>
</html>

