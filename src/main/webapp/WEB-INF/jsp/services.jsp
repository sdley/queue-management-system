<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Service</title>
</head>
<body>
    <h1>Liste des Services</h1>
    <table>
        <tr>
            <th>Nom</th>
            <th>Description</th>
            <th>Localisations</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="service" items="${services}">
            <tr>
                <td>${service.nom}</td>
                <td>${service.description}</td>
                <td>
                    <c:forEach var="localisation" items="${localisations[service.nom]}">
                        ${localisation.nom}<br/>
                    </c:forEach>
                </td>
                <td>
                    <a href="/service/edit/${service.nom}">Edit</a>
                    <a href="/service/delete/${service.nom}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p>
        <a href="/services/add">Ajouter un Service</a>
    </p>
    <p>
        <a href="/">Retour à l'accueil</a>
    </p>

</body>
</html>
