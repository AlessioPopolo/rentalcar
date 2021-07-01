<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light mb-3">
    <div class="container-fluid">
        <a class="navbar-brand" href="RentalCarControllerServlet">Rental Car</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <c:url var="autoLink" value="RentalCarControllerServlet">
                        <c:param name="command" value="AUTO"/>
                    </c:url>
                    <a class="nav-link" href="${autoLink}">Parco auto</a>
                </li>
                <li class="nav-item">
                    <c:url var="bookLink" value="RentalCarControllerServlet">
                        <c:param name="command" value="HOMECUSTOMER"/>
                    </c:url>
                    <a class="nav-link" href="${bookLink}">Profilo utente</a>
                </li>
            </ul>
        </div>
    </div>
</nav>