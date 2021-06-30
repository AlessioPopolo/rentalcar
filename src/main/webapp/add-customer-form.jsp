<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Aggiungi Customers</title>
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

        <h3>Aggiungi utente</h3>

        <form action="RentalCarControllerServlet" method="post">

            <input type="hidden" name="command" value="ADD"/>

            <table class="table">
                <tbody>
                    <tr>
                        <td><label class="col-form-label">Nome</label></td>
                        <td><input class="form-control" type="text" name="nome" required/></td>
                    </tr>

                    <tr>
                        <td><label class="col-form-label">Cognome</label></td>
                        <td><input class="form-control" type="text" name="cognome" required/></td>
                    </tr>

                    <tr>
                        <td><label class="col-form-label">Data di nascita</label></td>
                        <td><input class="form-control" type="date" name="datadinascita" required/></td>
                    </tr>

                    <tr>
                        <td><label class="col-form-label">Ruolo</label></td>
                        <td>
                            <select class="form-select" name="ruolo">
                                <option value="customer" >Customer</option>
                                <option value="superuser">Admin</option>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td><label></label></td>
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
