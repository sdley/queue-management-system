<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Agent</title>
    <style>
        table {
            width: 50%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .button-container {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }
        .button-container form {
            display: inline;
        }
    </style>
</head>
<body>
    <h1>Gestion des appels Agent</h1>

    <form action="/agent/choixService" method="post">
        <label for="serviceSelect"><strong>Service:</strong></label>
        <select id="serviceSelect" name="serviceId" onchange="this.form.submit()">
            <option value="">Selectionnez un Service</option>
            <c:forEach var="service" items="${services}">
                <option value="${service.nom}" ${service.nom == selectedService ? 'selected' : ''}>
                        ${service.nom}</option>
            </c:forEach>
        </select>

        <label for="locationSelect"><strong>Localisation:</strong></label>
        <select id="locationSelect" name="localisationId"
                onchange="updateHiddenLocalisation(this.value)">
            <option value="">Selectionnez une localisation</option>
            <c:if test="${not empty localisations}">
                <c:forEach var="localisation" items="${localisations}">
                    <option
                            value="${localisation.nom}"
                        ${localisation.nom == selectedLocalisation ? 'selected' : ''}>
                            ${localisation.nom}
                    </option>
                </c:forEach>
            </c:if>
        </select>

        <script>
            function updateHiddenLocalisation(value) {
                document.getElementById("lacationName").value = value;
            }
        </script>
    </form>

    <%--    Gestion clients--%>
    <form action="/agent/traitementClient" method="post">
        <input type="hidden" name="nomService" value="${selectedService}"/>
        <!-- Nous devons ajuster cela selon notre logique -->
        <input type="hidden" id="lacationName" name="nomLocalisation" value="${selectedLocalisation}"/><br>
        <label for="agentId"><strong>Agent:</strong></label>
        <select id="agentId" name="agentId">
            <option value="">Selectionnez un agent</option>
            <c:forEach var="agent" items="${agents}">
                <option value="${agent.id}">${agent.prenom} ${agent.nom}</option>
            </c:forEach>
        </select>
        <p>
            <input type="submit" value="Continuer"/>
        </p>
    </form>

    <!-- Section pour afficher la liste des agents [Optionel] -->
    <h3>Liste des Agents</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="agent" items="${agents}">
            <tr>
                <td>${agent.id}</td>
                <td>${agent.prenom} ${agent.nom}</td>
                <td>
                    <a href="/agent/edit/${agent.id}">Edit</a>
                    <a href="/agent/delete/${agent.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="/agent/add">Ajouter un Agent</a></p>

    <p style="margin-top: 15px;">
        <button onclick="history.back()">Retour</button>
    </p>

    <p>
        <a href="/">Retour A l'accueil</a>
    </p>

</body>
</html>
