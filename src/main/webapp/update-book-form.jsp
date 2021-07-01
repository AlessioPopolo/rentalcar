<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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

            <input type="hidden" name="command" value="UPDATEBOOKING"/>
            <input type="hidden" name="customerId" value="${book.id}"/>

            <table class="table">

                <tbody>
                <tr>
                    <td><label class="col-form-label">Automezzo</label></td>
                    <td>
                        <select name="auto">
                            <c:forEach var="tempAuto" items="${listaAuto}">
                                <option value="${tempAuto.id}" ${tempAuto.id == autoSelected.id ? 'selected="selected"' : ''}>
                                        ${tempAuto.casacostruttrice} ${tempAuto.modello}
                                </option>
                            </c:forEach>
                        </select>
                </tr>

                <tr>
                    <td><label class="col-form-label">Inizio noleggio</label></td>
                    <td><input class="form-control" type="date" name="startdate"
                               value="<fmt:formatDate value="${book.startdate}" type="date" pattern="yyyy-MM-dd"/>" required/></td>
                </tr>

                <tr>
                    <td><label class="col-form-label">Fine noleggio</label></td>
                    <td><input class="form-control" type="date" name="enddate"
                               value="<fmt:formatDate value="${book.enddate}" type="date" pattern="yyyy-MM-dd"/>" required/></td>
                </tr>

                <tr>
                    <td><label class="col-form-label"></label></td>
                    <td><input class="btn btn-primary" type="submit" value="Save" /></td>
                </tr>
                </tbody>


            </table>
        </form>

        <div style="clear: both;"></div>

        <p><a href="home-customer.jsp">Back to list</a></p>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>
