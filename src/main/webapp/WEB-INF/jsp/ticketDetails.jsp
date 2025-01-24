<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Ticket Genere</title>
</head>
<body>

    <h1>Ticket Genere</h1>
    <p><strong>ID du Ticket:</strong> ${ticket.id}</p>
    <p><strong>Service:</strong> ${ticket.service.nom}</p>
    <p><strong>Localisation:</strong> ${ticket.localisation.nom}</p>
    <p><strong>Client:</strong> ${ticket.client.prenom} ${ticket.client.nom}</p>

    <a href="/client">Retour a la liste des Clients</a>
</body>
</html>
