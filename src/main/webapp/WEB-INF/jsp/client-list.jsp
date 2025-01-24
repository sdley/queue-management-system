<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>Client</title>
</head>
<body>
    <h1>Liste des Clients</h1>
    <table id="clientsTable">
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Actions</th>
        </tr>
<%--        <c:forEach var="client" items="${clients}">--%>
<%--            <tr>--%>
<%--                <td>${client.id}</td>--%>
<%--                <td>${client.nom}</td>--%>
<%--                <td>--%>
<%--                    <a href="/client/edit/${client.id}">Edit</a>--%>
<%--                    <a href="/client/delete/${client.id}">Delete</a>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>

<%--        JS --%>
        <script type="text/javascript">
            // Récupération de la chaîne JSON passée par le modèle
            var clientsJson = '${clients}';

            // Convertir la chaîne JSON en objet JavaScript
            var clients = JSON.parse(clientsJson);

            // Afficher les données dans la console pour vérification
            // console.log(admins);

            // Exemple d'affichage des données dans le DOM
            window.onload = function() {
                var table = document.getElementById("clientsTable");
                clients.forEach(function(client) {
                    var row = table.insertRow();
                    var cellId = row.insertCell(0);
                    var cellPrenom = row.insertCell(1);
                    var cellNom = row.insertCell(2);
                    var cellActions = row.insertCell(3); // Nouvelle cellule pour les actions
                    cellId.innerHTML = client.id;
                    cellNom.innerHTML = client.nom;
                    cellPrenom.innerHTML = client.prenom;
                    // Ajout des liens Edit et Delete
                    cellActions.innerHTML = `
                    <a href="/client/edit/${client.id}">Edit</a> |
                    <a href="/client/delete/${client.id}">Delete</a>
                    `;
                });
            };
        </script>
    </table>
    <a href="/client/add">Ajouter un Client</a>
    <p>
        <a href="/">Retour à l'accueil</a>
    </p>
</body>
</html>
