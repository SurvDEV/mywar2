<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<link rel="stylesheet" type="text/css" href="style.css" />
	<title>Pendu</title>
</head>
<body>
	<c:if test="${empty PenduBean}">
		<p>
			Bienvenue sur le jeu du pendu !!<br/>
			Voulez-vous commencer une nouvelle partie?<br/>
		</p>
		<form action="Proposition" method="get">
			<input type="submit" value="Ok!" />
		</form>
	</c:if>
	<c:if test="${not empty PenduBean}">
		<table>
			<tr>
			<c:forEach var="lettre" items="${PenduBean.mot}" varStatus="status">
				<td>
					<c:if test="${PenduBean.lettresTrouvees[status.index]}">
						${lettre}
					</c:if>
					<c:if test="${not PenduBean.lettresTrouvees[status.index]}">
						_
					</c:if>
				</td>
			</c:forEach>
			</tr>
		</table>
		<br />
		<p>
			Lettres Jouées :
			<table>
				<tr>
					<c:forEach var="lettre" items="${PenduBean.lettresJouees}">
						<td>${lettre}</td>
					</c:forEach>
				</tr>
			</table>
		</p>
		<form method="post" action="Proposition">
			Quelle lettre voulez-vous jouer?  <input type="text" name="lettre" /><br />
			<input type="submit" value="Jouer" />
		</form>
	</c:if>
	<c:if test="${!empty param.gagne}">
		<span class="error">GAGNE !! Voulez-vous rejouer?</span>
		<form action="Proposition" method="get">
			<input type="submit" value="Ok!" />
		</form>
	</c:if>
	<c:if test="${!empty param.error}">
		<span class="error">Erreur : ${param.error}</span>
	</c:if>
</body>
</html>