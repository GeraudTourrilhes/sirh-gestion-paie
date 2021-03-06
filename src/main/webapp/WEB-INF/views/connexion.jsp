<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Paie - App</title>
        <link rel="stylesheet" href='<c:url value="/bootstrap-4.1.1-dist/css/bootstrap.min.css"></c:url>'>
    </head>
    <body class="container">        

        <h1>Connexion</h1>

        <!-- Spring Security s'attend aux param�tres "username" et "password" -->
        <form method="post">
            <input name="username">
            <input name="password">
            <input type="submit" value="Se connecter">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <!-- en cas d'erreur un param�tre "error" est cr�� par Spring Security -->
        <c:if test="${param.error !=null}">
            Erreur d'authentification
        </c:if>
    </body>
</html>