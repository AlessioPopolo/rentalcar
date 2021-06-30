<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Visualizza prenotazioni</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div id="wrapper" class="container">
    <header id="header" class="header-panel">
        <jsp:include page="header.html"></jsp:include>
    </header>
</div>

<div id="container" class="container">
    <div id="content" class="content">

        <h3>Prenotazioni dell'utente ${customer.nome} ${customer.cognome}</h3>

        <table class="table">
            <thead>
            <tr>
                <th>Automezzo</th>
                <th>Categoria</th>
                <th>Inizio noleggio</th>
                <th>Fine noleggio</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="tempPrenotazioni" items="${listaPrenotazioni}">

                <tr>
                    <td>${tempPrenotazioni.automezzo.casacostruttrice} ${tempPrenotazioni.automezzo.modello} ${tempPrenotazioni.automezzo.targa}</td>
                    <td>${tempPrenotazioni.automezzo.categoria.categoria}</td>
                    <td><fmt:formatDate value="${tempPrenotazioni.startdate}" type="date" pattern="dd-MM-yyyy"/></td>
                    <td><fmt:formatDate value="${tempPrenotazioni.enddate}" type="date" pattern="dd-MM-yyyy"/></td>
                </tr>

            </c:forEach>
            </tbody>


        </table>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
