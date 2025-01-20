<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>Agent</title>
</head>
<body>
    <h1>Liste des Agents</h1>
    <table id="agentsTable">
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Actions</th>
        </tr>
<%--        <c:forEach var="agent" items="${agents}">--%>
<%--            <tr>--%>
<%--                <td>${agent.id}</td>--%>
<%--                <td>${agent.nom}</td>--%>
<%--                <td>--%>
<%--                    <a href="/agent/edit/${agent.id}">Edit</a>--%>
<%--                    <a href="/agent/delete/${agent.id}">Delete</a>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
        <script type="text/javascript">
            // Récupération de la chaîne JSON passée par le modèle
            var agentsJson = '${agents}';

            // Convertir la chaîne JSON en objet JavaScript
            var agents = JSON.parse(agentsJson);

            // Exemple d'affichage des données dans le DOM
            window.onload = function() {
                var table = document.getElementById("agentsTable");
                agents.forEach(function(agent) {
                    var row = table.insertRow();
                    var cellId = row.insertCell(0);
                    var cellPrenom = row.insertCell(1);
                    var cellNom = row.insertCell(2);
                    var cellActions = row.insertCell(3); // Nouvelle cellule pour les actions
                    cellId.innerHTML = agent.id;
                    cellNom.innerHTML = agent.nom;
                    cellPrenom.innerHTML = agent.prenom;
                    // Ajout des liens Edit et Delete
                    cellActions.innerHTML = `
                    <a href="/agent/edit/${agent.id}">Edit</a> |
                    <a href="/agent/delete/${agent.id}">Delete</a>
                    `;
                });
            };
        </script>
    </table>
    <a href="/agent/add">Ajouter un Agent</a>
    <p>
        <a href="/">Retour à l'accueil</a>
    </p>
</body>
</html>
