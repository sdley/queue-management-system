<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Erreur</title>
    <link rel="stylesheet" href="<c:url value='/css/error.css' />"> <!-- Optional CSS -->
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</head>
<body>
<div class="container">
    <h1>Une erreur est survenue</h1>
    <p>
        <strong>Message d'erreur :</strong> ${error}
    </p>
    <p>
        Veuillez verifier bien les informations saisies et reessayer !
    </p>
    <p>
        <button onclick="goBack()">Retourner a la page precedente</button> <!-- Bouton pour revenir en arrière -->
    </p>

    <a href="<c:url value='/' />">Retourner a la page d'accueil</a>
</div>
</body>
</html>
