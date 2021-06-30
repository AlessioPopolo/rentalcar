<%@ page import="com.rentalcar.dao.AutomezzoDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.rentalcar.entity.Automezzo" %>
<%@ page import="com.rentalcar.dao.TipologiaAutomezzoDao" %>
<%@ page import="com.rentalcar.entity.TipologiaAutomezzo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
  AutomezzoDao automezzoDao;
  automezzoDao = new AutomezzoDao();
  List<Automezzo> automezzi = automezzoDao.getAllAutomezzi();
  request.setAttribute("listaAuto", automezzi);

  TipologiaAutomezzoDao tipologiaAutomezzoDao;
  tipologiaAutomezzoDao = new TipologiaAutomezzoDao();
  List<TipologiaAutomezzo> tipologiaAutomezzoList = tipologiaAutomezzoDao.getAllTipologie();
  request.setAttribute("listaTipologie", tipologiaAutomezzoList);
%>
<!DOCTYPE html>
<html>
<head>
    <title>Parco auto</title>
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

        <h3>Parco auto</h3>

        <!--  add a search box -->
        <form action="RentalCarControllerServlet" method="GET">

          <input type="hidden" name="command" value="SEARCHAUTO" />

          <div class="mb-3">
            <label class="form-label">Cerca auto per tipologia:</label>
            <select class="form-select" name="searchAuto">
              <c:forEach var="tempCategoria" items="${listaTipologie}">

                <option value="${tempCategoria.categoria}" >${tempCategoria.categoria}</option>

              </c:forEach>
            </select>
          </div>

          <div class="mb-3">
            <input type="submit" value="search" class="btn btn-secondary" />
          </div>

        </form>

        <input type="button" value="Aggiungi auto"
               onclick="window.location.href='add-auto-form.jsp'; return false;" />

        <table class="table">
          <thead>
          <tr>
            <th>ID</th>
            <th>Marca</th>
            <th>Modello</th>
            <th>Data di immatricolazione</th>
            <th>Targa</th>
            <th>Categoria</th>
            <th>Azioni</th>
          </tr>
          </thead>

          <tbody>
          <c:forEach var="tempAuto" items="${listaAuto}">

            <!-- link per UPDATE -->
            <c:url var="loadLink" value="RentalCarControllerServlet">
              <c:param name="command" value="LOADAUTO"/>
              <c:param name="autoId" value="${tempAuto.id}"/>
            </c:url>

            <!-- link per DELETE -->
            <c:url var="deleteLink" value="RentalCarControllerServlet">
              <c:param name="command" value="DELETEAUTO"/>
              <c:param name="autoId" value="${tempAuto.id}"/>
            </c:url>

            <tr>
              <td>${tempAuto.id}</td>
              <td>${tempAuto.casacostruttrice}</td>
              <td>${tempAuto.modello}</td>
              <td><fmt:formatDate value="${tempAuto.immatricolazione}" type="date" pattern="MM-yyyy"/></td>
              <td>${tempAuto.targa}</td>
              <td>${tempAuto.categoria.categoria}</td>
              <td>
                <a href="${loadLink}">Aggiorna</a>
                |
                <a href="${deleteLink}"
                   onclick="if (!(confirm('Vuoi eliminare questo automezzo?'))) return false">
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
