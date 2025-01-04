<!-- client-form.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Nouveau client</title>
</head>
<body>
    <h1>Nouveau client</h1>
    <form action="/client/save" method="post">
        <!-- Formulaire pour créer un nouveau client -->
        <button type="submit">Enregistrer</button>
    </form>
</body>
</html>