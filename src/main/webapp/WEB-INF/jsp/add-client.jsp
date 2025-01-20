<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Client</title>
</head>
<body>
<h1>Ajouter un Client</h1>
<form action="${pageContext.request.contextPath}/client/add" method="post">
    <div>
        <label for="prenom">Prenom:</label>
        <input type="text" id="prenom" name="prenom" required />
    </div>
    <div>
        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="nom" required />
    </div>
    <div>
        <input type="submit" value="Ajouter" />
    </div>
</form>
<p>
    <a href="${pageContext.request.contextPath}/client">Retour à la liste des Clients</a>
</p>
</body>
</html>
