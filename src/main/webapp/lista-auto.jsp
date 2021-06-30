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

        <table class="table">
          <thead>
          <tr>
            <th>ID</th>
            <th>Marca</th>
            <th>Modello</th>
            <th>Data di immatricolazione</th>
            <th>Targa</th>
            <th>Categoria</th>
          </tr>
          </thead>

          <tbody>
          <c:forEach var="tempAuto" items="${listaAuto}">

            <tr>
              <td>${tempAuto.id}</td>
              <td>${tempAuto.casacostruttrice}</td>
              <td>${tempAuto.modello}</td>
              <td>${tempAuto.immatricolazione}</td>
              <td>${tempAuto.targa}</td>
              <td>${tempAuto.categoria}</td>
            </tr>
          </c:forEach>
          </tbody>


        </table>
      </div>
    </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>


</body>
</html>
