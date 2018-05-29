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
	<script src="<c:url value='/bootstrap-4.1.1-dist/ks/bootstrap.ks'/>"> </script>
	<link rel="stylesheet" href="<c:url value='/css/index.css'/>">

    <title>SGP App</title>
  </head>
  <body>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      
       <a class="navbar-brand active" href="<c:url value='lister' />">Employés</a>
       <a class="navbar-brand" href="<c:url value='#' />">Bulletins</a>
    </nav>
    <div class="container-fluid">
      <div class="row">
        <div class="col">
          <h1>Ajouter un employé</h1>
        </div>
      </div>
      <form:form method="POST" modelAttribute="remunerationEmploye">
        <div class="form-group">
          <label for="matriule">Matricule</label>
          <form:input type="text" class="form-control" id="matricule" path="matricule" />
        </div>
 
        <div class="form-group">
          <label>Entreprise</label>
          <form:select path="entreprise.id" items="${entreprises}" itemValue="id"/>
        </div>
        
        <div class="form-group">
          	<label>Profil</label>
        	<form:select path="profilRemuneration.id" items="${profilsRemuneration}" itemValue="id"/>
        </div>
        
        <div class="form-group">
          <label>Grade</label>
          <form:select path="grade.id" items="${grades}" itemValue="id" itemLabel="code"/>
        </div>
        
        <div class="row justify-content-end">
          <form:button type="submit" class="btn btn-primary">Ajouter</form:button>
        </div>

      </form:form>

    </div>
  </body>
</html>