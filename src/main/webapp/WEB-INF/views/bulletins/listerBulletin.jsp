<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
      
       <a class="navbar-brand active" href="<c:url value='/mvc/employes/lister' />">Employés</a>
       <a class="navbar-brand" href="<c:url value='/mvc/bulletins/lister' />">Bulletins</a>
    </nav>
    <div class="container-fluid">
      <div class="row">
        <div class="col">
          <h1>Lister des Bulletins</h1>
        </div>
      </div>
      <div class="row justify-content-end">
         <a href="<c:url value='/mvc/bulletins/creer' />" class="btn">Ajouter un bulletin</a>
      </div>
      </div>
      <div class="container-fluid">
      	<div class="row">
	      <table>
	      	<tr>
	      		<th class="col-4">Date/heure création</th>
	      		<th class="col-4">Matricule</th>
	      		<th class="col-4">Grade</th>
	      	</tr>
	      
	      	<c:forEach var="remunerationEmploye" items="${remunerationEmployes}">
	      		<tr>
	      		<th><c:out value="${remunerationEmploye.dateCreation}" /></th>
	      		<th><c:out value="${remunerationEmploye.matricule}" /></th>
	      		<th><c:out value="${remunerationEmploye.grade}" /></th>
	      	</tr>
	      	</c:forEach>
	      </table>
		</div>
    </div>
  </body>
</html>