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
      
       <a class="navbar-brand active" href="<c:url value='/mvc/employes/lister' />">Employés</a>
       <a class="navbar-brand" href="<c:url value='/mvc/bulletins/lister' />">Bulletins</a>
    </nav>
    <div class="container-fluid">
      <div class="row">
        <div class="col">
          <h1>Bulletin de salaire</h1>
        </div>
      </div>
      <c:forEach var="bulletinSalaire" items="${bulletinsSalaire}">
      <div class="row">
        <div class="col">
          <strong>Entreprise</strong>
          <p>DEV ENTREPRISE</p>
          <p>SIRET : <c:out value="${bulletinSalaire.key.remunerationEmploye.entreprise.siret}" /></p>
        </div>
        <div class="col">
          <strong>Période</strong>
          <p><c:out value="${bulletinSalaire.key.periode}" /></p>
          </br>
          <p>Matricule : <c:out value="${bulletinSalaire.key.remunerationEmploye.matricule}" /></p>
        
        </div>
      </div>
      <c:set var="montantSalarialSalaire" value="${paieUtils.formaterBigDecimal(bulletinSalaire.key.remunerationEmploye.grade.nbHeuresBase * bulletinSalaire.key.remunerationEmploye.grade.tauxBase)}" />
      <div class="row">
        <div class="col">
          <strong>Salaire</strong>
          <table class="table">
          	<tr>
          		<th>Rubriques</th>
          		<th>Base</th>
          		<th>Taux Salarial</th>
          		<th>Montant Salarial</th>
          		<th>Taux patronal</th>
          		<th>Cot. patronales</th>
          	</tr>
          	<tr>
          		<td>Salaire de base</td>
          		<td><c:out value="${bulletinSalaire.key.remunerationEmploye.grade.nbHeuresBase}" /></td>
          		<td><c:out value="${bulletinSalaire.key.remunerationEmploye.grade.tauxBase}" /></td>
          		<td><c:out value="${montantSalarialSalaire}" /></td>
          		<td></td>
          		<td></td>
          	</tr>
          	<tr>
          		<td>Prime Except.</td>
          		<td></td>
          		<td></td>
          		<td><c:out value="${bulletinSalaire.key.primeExceptionnelle}" /></td>
          		<td></td>
          		<td></td>
          	</tr>
          	<tr>
          		<td></td>
          		<td></td>
          		<td></td>
          		<td></td>
          		<td></td>
          		<td></td>
          	</tr>
          	<tr>
          		<td>Salaire Brut</td>
          		<td></td>
          		<td></td>
          		<td><c:out value="${bulletinSalaire.value.salaireBrut}" /></td>
          		<td></td>
          		<td></td>
          	</tr>
          </table>
         </div>
   
      </div>
      <div class="row">
        <div class="col">
          <strong>Cotisation</strong>
           <table class="table">
          	<tr>
          		<th>Rubriques</th>
          		<th>Base</th>
          		<th>Taux Salarial</th>
          		<th>Montant Salarial</th>
          		<th>Taux patronal</th>
          		<th>Cot. patronales</th>
          	</tr>
          	<c:forEach var="cotisationNonImposable" items="${bulletinSalaire.key.remunerationEmploye.profilRemuneration.cotisationsNonImposables}">
	          	<c:set var="montantSalarialCotisation" value="${paieUtils.formaterBigDecimal(cotisationNonImposable.tauxSalarial * bulletinSalaire.value.salaireBrut)}" />
     			<c:set var="montantPatronalCotisation" value="${paieUtils.formaterBigDecimal(cotisationNonImposable.tauxPatronal * bulletinSalaire.value.salaireBrut)}" />
      			<tr>
	          		<td><c:out value="${cotisationNonImposable.code} ${cotisationNonImposable.libelle}" /></td>
	          		<td><c:out value="${bulletinSalaire.value.salaireBrut}" /></td>
	          		<td><c:out value="${cotisationNonImposable.tauxSalarial}" /></td>
	          		<td><c:out value="${montantSalarialCotisation}" /></td></td>
	          		<td><c:out value="${cotisationNonImposable.tauxPatronal}" /></td>
	          		<td><c:out value="${montantPatronalCotisation}" /></td>
	          	</tr>
          	</c:forEach>
          	<tr>
          		<td></td>
          		<td></td>
          		<td></td>
          		<td></td>
          		<td></td>
          		<td></td>
          	</tr>
          	<tr>
          		<td>Total Retenue</td>
          		<td></td>
          		<td></td>
          		<td><c:out value="${bulletinSalaire.value.totalRetenueSalarial}" /></td>
          		<td></td>
          		<td><c:out value="${bulletinSalaire.value.totalCotisationsPatronales}" /></td>
          	</tr>
          </table>
         </div>
   
      </div>
      <div class="row">
        <div class="col">
          <strong>Net Imposable : <c:out value="${bulletinSalaire.value.netImposable}" /></strong>
           <table class="table">
          	<tr>
          		<th>Rubriques</th>
          		<th>Base</th>
          		<th>Taux Salarial</th>
          		<th>Montant Salarial</th>
          		<th>Taux patronal</th>
          		<th>Cot. patronales</th>
          	</tr>
          	<c:forEach var="cotisationImposable" items="${bulletinSalaire.key.remunerationEmploye.profilRemuneration.cotisationsImposables}">
          	<tr>
          		<c:set var="montantSalarialCotisation" value="${paieUtils.formaterBigDecimal(cotisationImposable.tauxSalarial * bulletinSalaire.value.salaireBrut)}" />
     			<c:set var="montantPatronalCotisation" value="${paieUtils.formaterBigDecimal(cotisationImposable.tauxPatronal * bulletinSalaire.value.salaireBrut)}" />
      			<tr>
	          		<td><c:out value="${cotisationImposable.code} ${cotisationImposable.libelle}" /></td>
	          		<td><c:out value="${bulletinSalaire.value.salaireBrut}" /></td>
	          		<td><c:out value="${cotisationImposable.tauxSalarial}" /></td>
	          		<td><c:out value="${montantSalarialCotisation}" /></td></td>
	          		<td><c:out value="${cotisationImposable.tauxPatronal}" /></td>
	          		<td><c:out value="${montantPatronalCotisation}" /></td>
	          	</tr>
          	</tr>
          	</c:forEach>
          </table>
         </div>
   
      </div>
      <div class="row">
        <div class="offset-md-9 col-md-3 ">
          <strong>NET A PAYER : <c:out value="${bulletinSalaire.value.netAPayer}" />
        </strong>
         </div>
   
      </div>
	</c:forEach>
    </div>
  </body>
</html>