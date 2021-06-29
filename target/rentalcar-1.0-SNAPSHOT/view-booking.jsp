<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Visualizza prenotazioni</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div id="wrapper" class="container">
    <header id="header" class="header-panel">
        <h2>Prenotazioni dell'utente ${customer.nome} ${customer.cognome}</h2>
    </header>
</div>

<div id="container" class="container">
    <div id="content" class="content">

        <h3>Lista prenotazioni</h3>

        <table class="table">
            <thead>
            <tr>
                <th>Automezzo</th>
                <th>Inizio noleggio</th>
                <th>Fine noleggio</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="tempPrenotazioni" items="${listaPrenotazioni}">

                <tr>
                    <td>${tempPrenotazioni.automezzo}</td>
                    <td>${tempPrenotazioni.startdate}</td>
                    <td>${tempPrenotazioni.enddate}</td>
                </tr>

            </c:forEach>
            </tbody>


        </table>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
