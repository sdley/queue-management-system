<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Client</title>
</head>
<body>
    <h1>Selectionnez un Service et une Localisation</h1>
    <form action="/client/choixService" method="post">
        <label for="serviceSelect">Service:</label>
        <select id="serviceSelect" name="serviceId" onchange="this.form.submit()">
            <option value="">Selectionnez un Service</option>
            <c:forEach var="service" items="${services}">
                <option value="${service.nom}" ${service.nom == selectedService ? 'selected' : ''}>
                        ${service.nom}</option>
            </c:forEach>
        </select>

        <label for="locationSelect">Localisation:</label>
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
                document.getElementById("hiddenNomLocalisation").value = value;
            }
        </script>
    </form>

    <%--    Generation de ticket--%>
    <form action="/ticket/genererTicket" method="post">
        <input type="hidden" name="nomService" value="${selectedService}"/>
        <!-- Nous devons ajuster cela selon notre logique -->
        <input type="hidden" id="hiddenNomLocalisation" name="nomLocalisation" value="${selectedLocalisation}"/><br>
        <label for="clientId">Selectionnez un Client:</label>
        <select id="clientId" name="clientId">
            <option value="">Selectionnez un Client</option>
            <c:forEach var="client" items="${clients}">
                <option value="${client.id}">${client.prenom} ${client.nom}</option>
            </c:forEach>
        </select>
        <p>
            <input type="submit" value="Generer un Ticket"/>
        </p>
    </form>


    <%--    Liste des clents--%>
    <h3>Liste des Clients</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="client" items="${clients}">
            <tr>
                <td>${client.id}</td>
                <td>${client.prenom} ${client.nom}</td>
                <td>
                    <a href="/client/edit/${client.id}">Edit</a>
                    <a href="/client/delete/${client.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <a href="/client/add">Ajouter un Client</a>
    <p>
        <a href="/">Retour A l'accueil</a>
    </p>
</body>
</html>
