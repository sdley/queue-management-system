<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>File d'Attente</title>
</head>
<body>
<h1>Liste des Files d'Attente</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Service</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="fileAttente" items="${fileAttentes}">
        <tr>
            <td>${fileAttente.id}</td>
            <td>${fileAttente.service}</td>
            <td>
                <a href="/fileAttente/edit/${fileAttente.id}">Edit</a>
                <a href="/fileAttente/delete/${fileAttente.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/fileAttente/add">Ajouter une File d'Attente</a>
</body>
</html>
