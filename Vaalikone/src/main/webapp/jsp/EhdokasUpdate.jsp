<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <title>Vaalikone - Muokkaa väittämiä</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

    <!-- Omat tyylit-->
    <link href="https://fonts.googleapis.com/css2?family=Mandali&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Oswald&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="css/styles.css">

</head>

<body>

    <!-- CONTAINER ALKAA -->
    <div class="container-fluid">

        <!-- HEADER ALKAA -->
        <div class="row">
            <div class="col-sm-12 header">
                <header class="bg-text">
                    <h1>Väittämien hallinta</h1>
                    <h2>MUOKKAA VÄITTÄMIÄ</h2>
                </header>
            </div>
        </div>
        <!-- HEADER LOPPUU -->

        <!-- NAVIGOINTI ALKAA -->      
        <div class="row">
            <div class="col-md-12 fw">
                <nav class="navigointi navbar navbar-expand-md navbar-light">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
                        <ul class="navbar-nav">
                            <li class="nav-item"><a class="nav-link" href="../index.html">ETUSIVU</a></li>
                            <li class="nav-item"><a class="nav-link" href="/hello">VASTAA</a></li>
                            <li class="nav-item"><a class="nav-link" href="/jsp/VaittamatNew.jsp">LISÄÄ VÄITTÄMÄ</a></li>
                            <li class="nav-item"><a class="nav-link" href="/jsp/EhdokasNew.jsp">LISÄÄ EHDOKAS</a></li>
                            <li class="nav-item"><a class="nav-link" href="/ehdokaslist">EHDOKKAAT</a></li>
                            <li class="nav-item"><a class="nav-link" href=""></a></li>
                            <li class="nav-item"><a class="nav-link" href="/jsp/login.jsp"><%=(request.getSession(false).getAttribute("Admin") == null) ? "LOGIN" : "LOGGED IN AS " + session.getAttribute("Admin")%></a></li>
                            <li class="nav-item"><a class="nav-link" href="/logout">LOGOUT</a></li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
        <!-- NAVIGOINTI LOPPUU -->

        <!-- PÄÄSISÄLTÖ ALKAA -->
        <div class="row narrow">
            <div class="col-md-12 article">
                <article>
                    <h1>Edit Ehdokas</h1>
                    <br>
<form action='../updateehdokas' method='post'>
<input type='text' name='id' value='${requestScope.ehdokas.id }'>
<input type='number' name='ehdokasNro' value='${requestScope.ehdokas.ehdokasNro }'>
<input type='text' name='puolue' value='${requestScope.ehdokas.puolue }'>
<input type='text' name='etuNimi' value='${requestScope.ehdokas.etuNimi }'>
<input type='text' name='sukuNimi' value='${requestScope.ehdokas.sukuNimi }'>
<input type='text' name='osoite' value='${requestScope.ehdokas.osoite }'>
<input type='text' name='postiNro' value='${requestScope.ehdokas.postiNro }'>
<input type='text' name='postiPka' value='${requestScope.ehdokas.postiPka }'>
<input type='text' name='miksi' value='${requestScope.ehdokas.miksi }'>
<input type='submit' name='ok' value='OK'>
</form>
                </article>
            </div>
            <!-- PÄÄSISÄLTÖ LOPPUU -->

            <!-- FOOTER ALKAA-->
            <div class="row">
                <div class="col-md-12">
                    <footer>
                        <p>© Tomi Lehto / Eemi Karhu 2021</p>
                    </footer>
                </div>
            </div>
            <!-- FOOTER LOPPUU-->

        </div>
        <!-- CONTAINER LOPPUU -->

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->

        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
</body>

</html>