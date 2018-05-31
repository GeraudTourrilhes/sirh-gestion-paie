<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<!doctype html>
<html lang="fr">
	<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="<c:url value='/bootstrap-4.1.1-dist/css/bootstrap.css'/>">
	<script src="<c:url value='/bootstrap-4.1.1-dist/js/bootstrap.js'/>"> </script>
	<link rel="stylesheet" href="<c:url value='/css/index.css'/>">

    <title>SGP App</title>
  </head>
  <body>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      
       <a class="navbar-brand active" href="<c:url value='/mvc/employes/lister' />">Employés</a>
       <a class="navbar-brand" href="<c:url value='/mvc/bulletins/lister' />">Bulletins</a>
    </nav>
    <div class="container-fluid">
      <div class="row">
        <div class="col">
          <h1>Créer Bulletin de Salaire</h1>
        </div>
      </div>
      <form:form method="POST" modelAttribute="bulletinSalaire">
      
       <div class="form-group">
          <label>Période</label>
          <form:select path="periode.id" items="${periodes}" itemValue="id"/>
        </div>
        
        <div class="form-group">
          	<label>Matricule</label>
        	<form:select path="remunerationEmploye.id" items="${remunerationEmployes}" itemValue="id" itemLabel="matricule"/>
        </div>
        
        <div class="form-group">
          <label>Prime exceptionnelle</label>
          <form:input type="text" class="form-control"  path="primeExceptionnelle" />
        </div>
 
        
        <div class="row justify-content-end">
          <form:button type="submit" class="btn btn-primary">Créer</form:button>
        </div>

      </form:form>

    </div>
  </body>
</html>