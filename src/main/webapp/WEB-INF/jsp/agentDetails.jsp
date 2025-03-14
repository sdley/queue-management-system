<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Agent Details</title>
</head>
<body>
    <h1>Gestion des appels Agent</h1>

    <h3>Agent Informations</h3>
    <p><strong>Agent:</strong> ${agent.prenom} ${agent.nom}</p>
    <p><strong>Service:</strong> ${service.nom}</p>
    <p><strong>Localisation:</strong> ${localisation}</p>

    <!-- Affichage du message si aucun client en attente -->
    <c:if test="${not empty errorMessage}">
        <p style="color: red; font-weight: bold;">${errorMessage}</p>
    </c:if>

    <!-- Affichage du client en cours de traitement -->
    <h3>Client Actuellement en Cours</h3>
    <c:if test="${not empty currentClient}">
        <p><strong>Nom :</strong> ${currentClient.prenom} ${currentClient.nom}</p>
        <p><strong>Numero de Ticket :</strong> ${currentTicket.numero}</p>
        <p><strong>Status :</strong> ${currentTicket.status}</p>
    </c:if>
    <c:if test="${empty currentClient}">
        <p>Aucun client en cours de traitement.</p>
    </c:if>

    <!-- Boutons de navigation entre clients -->
    <div style="display: flex; gap: 10px; margin-top: 15px;">
        <form action="/agent/previousClient" method="post">
            <input type="hidden" name="serviceId" value="${service.nom}"/>
            <input type="hidden" name="nomLocalisation" value="${localisation}"/>
            <input type="hidden" name="agentId" value="${agent.id}"/>
            <button type="submit">Client Precedent</button>
        </form>

        <form action="/agent/nextClient" method="post">
            <input type="hidden" name="serviceId" value="${service.nom}"/>
            <input type="hidden" name="nomLocalisation" value="${localisation}"/>
            <input type="hidden" name="agentId" value="${agent.id}"/>
            <button type="submit">Client Suivant</button>
        </form>
    </div>

    <p style="margin-top: 30px;">
        <button onclick="history.back()">Retour</button>
    </p>

    <p>
        <a href="/">Retour A l'accueil</a>
    </p>

</body>
</html>
