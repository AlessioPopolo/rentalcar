<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
  <title>Home Customer</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div id="wrapper" class="container">
  <header id="header" class="header-panel">
    <jsp:include page="header.jsp"></jsp:include>
  </header>
</div>

<div id="container" class="container">
  <div id="content" class="content">

    <h3>Lista Prenotazioni di ${customer.nome} ${customer.cognome}</h3>

    <input type="button" value="Aggiungi prenotazione"
           onclick="window.location.href='add-booking-form.jsp'; return false;" />

    <table class="table">
      <thead>
      <tr>
        <th>Automezzo</th>
        <th>Inizio noleggio</th>
        <th>Fine noleggio</th>
        <th>Azioni</th>
      </tr>
      </thead>

      <tbody>
      <c:forEach var="tempBooking" items="${listaPrenotazioni}">

        <!-- link per UPDATE -->
        <c:url var="loadLink" value="RentalCarControllerServlet">
          <c:param name="command" value="LOADBOOKING"/>
          <c:param name="bookingId" value="${tempBooking.id}"/>
        </c:url>

        <!-- link per DELETE -->
        <c:url var="deleteLink" value="RentalCarControllerServlet">
          <c:param name="command" value="DELETEBOOKING"/>
          <c:param name="bookingId" value="${tempBooking.id}"/>
        </c:url>

        <tr>
          <td>${tempBooking.automezzo.casacostruttrice} ${tempBooking.automezzo.modello}</td>
          <td>
            <fmt:formatDate value="${tempBooking.startdate}" type="date" pattern="dd-MM-yyyy"/>
          </td>
          <td>
            <fmt:formatDate value="${tempBooking.enddate}" type="date" pattern="dd-MM-yyyy"/>
          </td>
          <td>
            <a href="${loadLink}">Aggiorna</a>
            |
            <a href="${deleteLink}"
               onclick="if (!(confirm('Vuoi eliminare questa prenotazione?'))) return false">
              Elimina
            </a>
          </td>
        </tr>
      </c:forEach>
      </tbody>


    </table>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
