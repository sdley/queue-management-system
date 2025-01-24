<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Service</title>
</head>
<body>
<h1>Ajouter un Service</h1>
<form action="${pageContext.request.contextPath}/services/add" method="post">
    <div>
        <input type="text" name="nom" placeholder="Nom du service" required/>
        <input type="text" name="description" placeholder="Description"/>
    </div>
    <div>
        <label for="localisations">Localisations (comma-separated):</label>
        <input type="text" id="localisations" name="localisations" required>
    </div>

    <div>
        <input type="submit" value="Ajouter Service" />
    </div>
</form>
<p>
    <a href="${pageContext.request.contextPath}/services">Retour à la liste des Services</a>
</p>
</body>
</html>
