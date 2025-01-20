<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin</title>
</head>
<body>
    <h1>Liste des Admins</h1>
    <table id="adminsTable">
        <tr>
            <th>ID</th>
            <th>Prenom</th>
            <th>Nom</th>
            <th>Actions</th>
        </tr>
<%--        <c:forEach var="admin" items="${admins}">--%>

<%--        <c:forEach var="admin" items="${adminsList}">--%>
<%--            <tr>--%>
<%--                <td>${admin.getId()}</td>--%>
<%--                <td>${admin.getNom()}</td>--%>
<%--                <td>${admin.prenom}</td>--%>
<%--                <td>--%>
<%--                    <a href="/admin/edit/${admin.id}">Edit</a>--%>
<%--                    <a href="/admin/delete/${admin.id}">Delete</a>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>

<%--        <c:forEach var="admin" items="${adminsList}">--%>
<%--            <tr>--%>
<%--                <td>${admin.id}</td>--%>
<%--                <td>${admin.nom}</td>--%>
<%--                <td>${admin.prenom}</td>--%>
<%--                <td>--%>
<%--                    <a href="/admin/edit/${admin.id}">Edit</a>--%>
<%--                    <a href="/admin/delete/${admin.id}">Delete</a>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>

<%--        JS  --%>


        <script type="text/javascript">
            // Récupération de la chaîne JSON passée par le modèle
            var adminsJson = '${admins}';

            // Convertir la chaîne JSON en objet JavaScript
            var admins = JSON.parse(adminsJson);

            // Afficher les données dans la console pour vérification
            console.log(admins);

            // Exemple d'affichage des données dans le DOM
            window.onload = function() {
                var table = document.getElementById("adminsTable");
                admins.forEach(function(admin) {
                    var row = table.insertRow();
                    var cellId = row.insertCell(0);
                    var cellPrenom = row.insertCell(1);
                    var cellNom = row.insertCell(2);
                    var cellActions = row.insertCell(3); // Nouvelle cellule pour les actions
                    cellId.innerHTML = admin.id;
                    cellNom.innerHTML = admin.nom;
                    cellPrenom.innerHTML = admin.prenom;
                    // Ajout des liens Edit et Delete
                    cellActions.innerHTML = `
                    <a href="/admin/edit/${admin.id}">Edit</a> |
                    <a href="/admin/delete/${admin.id}">Delete</a>
                    `;
                });
            };
        </script>
    </table>
    <p>
        <a href="/admin/add">Ajouter un Admin</a>
    </p>
    <a href="/">Retour à l'accueil</a>
</body>
</html>
