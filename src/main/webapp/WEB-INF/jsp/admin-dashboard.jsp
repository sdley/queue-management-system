<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        table {
            width: 80%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        .container {
            text-align: center;
            margin-top: 30px;
        }
        .header {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="header">Dashboard Administrateur</h1>

        <h2>Vue Globale des Files d'Attente</h2>
        <table>
            <tr>
                <th>Service</th>
                <th>Localisation</th>
                <th>Nombre de Clients en Attente</th>
                <th>Numero du Prochain Ticket</th>
            </tr>
            <c:forEach var="queue" items="${queues}">
                <tr>
                    <td>${queue.serviceNom}</td>
                    <td>${queue.localisation}</td>
                    <td>${queue.clientsEnAttente}</td>
                    <td>${queue.numeroProchainTicket}</td>
                </tr>
            </c:forEach>
        </table>

        <p style="margin-top: 20px;">
            <a href="/admin">Retour</a>
        </p>
    </div>
</body>
</html>
