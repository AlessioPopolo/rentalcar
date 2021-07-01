<%@ page import="com.rentalcar.dao.TipologiaUtenteDao" %>
<%@ page import="com.rentalcar.entity.TipologiaUtente" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    TipologiaUtenteDao tipologiaUtenteDao;
    tipologiaUtenteDao = new TipologiaUtenteDao();
    List<TipologiaUtente> tipologiaUtenteList = tipologiaUtenteDao.getAllTipologie();
    request.setAttribute("listaRuoli", tipologiaUtenteList);
%>
<!DOCTYPE html>
<html>
<head>
    <title>Update customer</title>
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

            <input type="hidden" name="command" value="UPDATE"/>
            <input type="hidden" name="customerId" value="${customer.id}"/>

            <table class="table">

                <tbody>
                <tr>
                    <td><label class="col-form-label">Nome</label></td>
                    <td><input class="form-control" type="text" name="nome"
                               value="${customer.nome}" required/></td>
                </tr>

                <tr>
                    <td><label class="col-form-label">Cognome</label></td>
                    <td><input class="form-control" type="text" name="cognome"
                               value="${customer.cognome}" required/></td>
                </tr>

                <tr>
                    <td><label class="col-form-label">Data di nascita</label></td>


                    <td><input class="form-control" type="date" name="datadinascita"
                               value="<fmt:formatDate value="${customer.datadinascita}" type="date" pattern="yyyy-MM-dd"/>" required/></td>
                </tr>

                <tr>
                    <td><label class="col-form-label">Ruolo</label></td>
                    <td>
                        <select class="form-select" name="ruolo">
                            <c:forEach var="tempRuolo" items="${listaRuoli}">
                                <option value="${tempRuolo.ruolo}" ${tempRuolo.ruolo == ruoloSelected.ruolo ? 'selected="selected"' : ''}>
                                        ${tempRuolo.ruolo}
                                </option>
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

        <p><a href="RentalCarControllerServlet">Back to list</a></p>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>
