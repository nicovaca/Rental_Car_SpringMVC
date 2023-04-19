<%--
  Created by IntelliJ IDEA.
  User: nicol
  Date: 12/04/2023
  Time: 09:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div class="container" align="center">
  <div class="py-5">
    <h1 class="display-5 fw-bold text-black">ERRORE!</h1>
    <div class="col-lg-6 mx-auto">
      <p class="fs-5 mb-4">Non è possibile eliminare una prenotazione nei due giorni antecedenti alla data di inizio della stessa.</p>
      <a href="../utente/profiloCustomer" class="btn btn-primary mb-2">Torna indietro</a>
      <a href="../index" id="home" class="btn btn-secondary mb-2">Torna alla Home</a>
    </div>
  </div>
</div>

