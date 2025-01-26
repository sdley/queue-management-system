<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Ticket Genere</title>
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</head>
<body>

    <h1>Ticket Genere</h1>
    <p><strong>ID du Ticket:</strong> ${ticket.id}</p>
    <p><strong>Service:</strong> ${ticket.service.nom}</p>
<%--    <p><strong>Localisation:</strong> ${ticket.localisation.nom}</p>--%>
    <p><strong>Client:</strong> ${ticket.client.prenom} ${ticket.client.nom}</p>

    <p>
        <!-- Bouton pour revenir en arrière -->
        <button onclick="goBack()">Retourner a la page precedente</button>
    </p>

    <a href="/client">Retour a la liste des Clients</a>
</body>
</html>
