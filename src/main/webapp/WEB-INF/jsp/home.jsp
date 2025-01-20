<!-- home.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Gestion de file d'attente</title>
    <style>
        h1 {
            color: #b26148;
        }

        h2 {
            color: #00a8e2;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            display: inline;
            margin-right: 10px;
        }
    </style>
</head>
<body>
    <h1>Bienvenue sur le portail de gestion de file d'attente</h1>
    <h2>Veuillez choisir votre profil: </h2>
    <ul>
        <li><a href="/client">Client</a></li>
        <li><a href="/agent">Agent</a></li>
        <li><a href="/admin">Admin</a></li>
    </ul>
</body>
</html>