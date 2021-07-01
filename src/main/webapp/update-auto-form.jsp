<%@ page import="java.util.List" %>
<%@ page import="com.rentalcar.dao.TipologiaAutomezzoDao" %>
<%@ page import="com.rentalcar.entity.TipologiaAutomezzo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
  TipologiaAutomezzoDao tipologiaAutomezzoDao;
  tipologiaAutomezzoDao = new TipologiaAutomezzoDao();
  List<TipologiaAutomezzo> tipologiaAutomezzoList = tipologiaAutomezzoDao.getAllTipologie();
  request.setAttribute("listaTipologie", tipologiaAutomezzoList);
%>
<!DOCTYPE html>
<html>
<head>
  <title>Update auto</title>
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

    <h3>Aggiorna dati</h3>
    <form action="RentalCarControllerServlet" method="post">

      <input type="hidden" name="command" value="UPDATEAUTO"/>
      <input type="hidden" name="autoId" value="${auto.id}"/>

      <table class="table">

        <tbody>
        <tr>
          <td><label class="col-form-label">Nome</label></td>
          <td><input class="form-control" type="text" name="marca"
                     value="${auto.casacostruttrice}" required/></td>
        </tr>

        <tr>
          <td><label class="col-form-label">Modello</label></td>
          <td><input class="form-control" type="text" name="modello"
                     value="${auto.modello}" required/></td>
        </tr>

        <tr>
          <td><label class="col-form-label">Data di immatricolazione</label></td>
          <td><input class="form-control" type="date" name="immatricolazione"
                     value="<fmt:formatDate value="${auto.immatricolazione}" type="date" pattern="yyyy-MM-dd"/>" required/></td>
        </tr>

        <tr>
          <td><label class="col-form-label">Targa</label></td>
          <td><input class="form-control" type="text" name="targa"
                     value="${auto.targa}" required/></td>
        </tr>

        <tr>
          <td><label class="col-form-label">Categoria</label></td>
          <td>
            <select class="form-select" name="categoria">
              <c:forEach var="tempCategoria" items="${listaTipologie}">

                <option value="${tempCategoria.categoria}" >${tempCategoria.categoria}</option>

              </c:forEach>
            </select>
          </td>
        </tr>

        <tr>
          <td><label class="col-form-label"></label></td>
          <td><input class="btn btn-primary" type="submit" value="Save" class="save"/></td>
        </tr>
        </tbody>


      </table>
    </form>

    <div style="clear: both;"></div>

    <p><a href="lista-auto.jsp">Back to list</a></p>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>
