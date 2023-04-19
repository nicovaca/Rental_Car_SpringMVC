<%--
  Created by IntelliJ IDEA.
  User: nicol
  Date: 30/03/2023
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container col-xxl-8 px-4 py-5">
    <div class="row flex-lg-row-reverse align-items-center g-5 py-5 w-75 align-middle">

        <h1 class="display-6 fw-bold lh-1 mb-3">
            Effettua il Login
        </h1>

        <c:url var="loginUrl" value="/login"/>
        <form:form action="${loginUrl}" method="post">

            <c:if test="${param.error != null}">
                <div class="alert alert-danger">
                    <p><spring:message code="login.form.error"/> </p>
                </div>
            </c:if>

            <c:if test="${param.forbidden != null}">
                <div class="alert alert-danger">
                    <p><spring:message code="login.form.forbidden"/> </p>
                </div>
            </c:if>

            <c:if test="${param.logout != null}">
                <div class="alert alert-danger">
                    <p><spring:message code="login.form.logout"/> </p>
                </div>
            </c:if>



            <div class="mb-3">
                <label for="userId" class="form-label">Username</label>
                <input type="text" class="form-control" id="userId" aria-describedby="emailHelp"
                       name="userId" placeholder="username" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password"
                       placeholder="password" required>
            </div>

<%--            per evitare attacco csrf--%>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button type="submit" class="btn btn-primary mb-2">Accedi</button>
            <a href="../index" id="home" class="btn btn-secondary mb-2">Torna alla Home</a>
        </form:form>
    </div>
</div>