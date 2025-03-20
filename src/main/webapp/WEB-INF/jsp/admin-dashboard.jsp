<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tableau de Bord Administrateur</title>
    <style>
        .container { width: 80%; margin: auto; margin-top: 50px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }
        th { background-color: #f4f4f4; }
        .red { color: red; font-weight: bold; } /* ✅ Message en rouge */
        .back-button {
            margin-top: 20px;
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Tableau de Bord de l'Administrateur</h1>
    <h2>Vue d'ensemble des Files d'Attente</h2>
    <table>
        <tr>
            <th>Service</th>
            <th>Localisation</th>
            <th>Clients en Attente</th>
            <th>Ticket en Cours</th>
            <th>Prochain Ticket</th>
        </tr>
        <c:forEach var="queue" items="${queues}">
            <tr>
                <td>${queue.serviceNom}</td>
                <td>${queue.localisation}</td>
                <td>
                    <c:choose>
                        <c:when test="${queue.clientsEnAttente > 0}">
                            ${queue.clientsEnAttente}
                        </c:when>
                        <c:otherwise>
                            <span class="red">File Attente Terminee</span> <!-- ✅ Message rouge -->
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${not empty queue.numeroTicketEnCours}">
                            ${queue.numeroTicketEnCours}
                        </c:when>
                        <c:otherwise>
                            <span class="red">Service Non Demarre</span> <!-- ✅ Message rouge -->
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${queue.numeroProchainTicket != 'Aucun'}">
                            ${queue.numeroProchainTicket}
                        </c:when>
                        <c:otherwise>
                            <span class="red">Aucun Ticket Disponible</span> <!-- ✅ Message rouge -->
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/admin" class="back-button">Retour</a>
</div>
</body>
</html>
