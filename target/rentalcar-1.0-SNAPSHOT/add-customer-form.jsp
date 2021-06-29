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
        <h2>Aggiungi utente</h2>
    </header>
</div>

<div id="container" class="container">
    <div id="content" class="content">

        <h3>Lista Customers</h3>

        <form action="RentalCarControllerServlet" method="post">

            <input type="hidden" name="command" value="ADD"/>

            <table>
                <tbody>
                    <tr>
                        <td><label>Nome</label></td>
                        <td><input type="text" name="nome" required/></td>
                    </tr>

                    <tr>
                        <td><label>Cognome</label></td>
                        <td><input type="text" name="cognome" required/></td>
                    </tr>

                    <tr>
                        <td><label>Data di nascita</label></td>
                        <td><input type="date" name="datadinascita" required/></td>
                    </tr>

                    <tr>
                        <td><label>Ruolo</label></td>
                        <td>
                            <select name="ruolo">



                                <option value="customer" selected>Customer</option>
                                <option value="admin">Admin</option>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td><label></label></td>
                        <td><input type="submit" value="Save" class="save"/></td>
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
