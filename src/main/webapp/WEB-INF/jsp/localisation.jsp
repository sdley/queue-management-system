<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>Localisation</title>
</head>
<body>
<h1>Liste des Localisations</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="localisation" items="${localisations}">
        <tr>
            <td>${localisation.id}</td>
            <td>${localisation.nom}</td>
            <td>
                <a href="/localisation/edit/${localisation.id}">Edit</a>
                <a href="/localisation/delete/${localisation.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/localisation/add">Ajouter une Localisation</a>
</body>
</html>
