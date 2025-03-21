<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .red-text {
            color: red;
            font-weight: bold;
        }
        .green-text {
            color: green;
            font-weight: bold;
        }

        .service-non-demarre {
             color: red;
             font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>Tableau de Bord de l'Administrateur</h1>
    <h2>Vue d'ensemble des Files d'Attente</h2>

    <table border="1">
        <thead>
        <tr>
            <th>Service</th>
            <th>Localisation</th>
            <th>Clients en Attente</th>
            <th>Ticket en Cours</th>
            <th>Prochain Ticket</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="queue" items="${queues}">
            <tr>
                <td>${queue.serviceNom}</td>
                <td>${queue.localisation}</td>
                <td>${queue.clientsEnAttente}</td>
                <td>${queue.numeroTicketEnCours}</td>
                <td>${queue.numeroProchainTicket}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <p>
        <button onclick="history.back()"
                style="background-color: blue; color: white; padding: 10px;"
        >Retour</button>
    </p>

</body>
</html>
