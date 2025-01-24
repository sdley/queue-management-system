<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        <c:forEach var="admin" items="${admins}">
            <tr>
                <td>${admin.id}</td>
                <td>${admin.nom}</td>
                <td>${admin.prenom}</td>
                <td>
                    <a href="/admin/edit/${admin.id}">Edit</a>
                    <a href="/admin/delete/${admin.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>

    </table>
    <p>
        <a href="/admin/add">Ajouter un Admin</a>
    </p>
    <a href="/">Retour à l'accueil</a>
</body>
</html>
