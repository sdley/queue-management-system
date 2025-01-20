<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>Service</title>
</head>
<body>
<h1>Liste des Services</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="service" items="${services}">
        <tr>
            <td>${service.id}</td>
            <td>${service.nom}</td>
            <td>
                <a href="/service/edit/${service.id}">Edit</a>
                <a href="/service/delete/${service.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/service/add">Ajouter un Service</a>
</body>
</html>
