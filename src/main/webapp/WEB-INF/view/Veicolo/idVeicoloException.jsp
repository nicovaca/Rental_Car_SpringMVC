<%--
  Created by IntelliJ IDEA.
  User: nicol
  Date: 06/04/2023
  Time: 09:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<br><br><br><br><br><br>
<div class="container" align="center">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>Oops!</h1>
                <h2>Veicolo NON trovato!</h2>
                <div class="error-details">
                    <P>Spiacente, l'articolo ${codice} non è presente!</P>
                    <p>${url}</p>
                    <p>${exception}</p>
                </div>

                <div class="error-actions">

                    <a href="<spring:url value="/" />"  class="btn btn-primary btn-lg">
                        <span class="oi oi-arrow-circle-left"></span> Indietro</a>
                </div>
            </div>
        </div>
    </div>
</div>