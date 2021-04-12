<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="data.Vastausvaihtoehdot" %>

<!DOCTYPE html>
<html>

<head>
    <title>Vaalikone</title>
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
                    <h2>LISÄÄ, MUOKKAA JA POISTA VÄITTÄMIÄ</h2>
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
                            <li class="nav-item"><a class="nav-link" href="/hello">KAIKKI VÄITTÄMÄT</a></li>
                            <li class="nav-item"><a class="nav-link" href="/jsp/VaittamatNew.jsp">LISÄÄ VÄITTÄMÄ</a></li>
                            <li class="nav-item"><a class="nav-link" href="/list">LISTAA VÄITTÄMÄT</a></li>
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
                    <h1>Kaikki väittämät</h1>
                    <br>
                    <table class="table table-striped">
                        <tr>
                            <th><b>ID</b></th>
                            <th><b>Teksti</b></th>
                            <th></th>
                            <th></th>
                        </tr>

                        <c:forEach var="vaittama" items="${requestScope.list}">
                            <tr>
                                <th><c:out value="${vaittama.id}" /></th>
                                <th><c:out value="${vaittama.teksti}" /></th>
                                <th><a href="/readtoupdate?id=<c:out value='${vaittama.id}' />">Edit</a></th>
                                <th><a href="/delete?id=<c:out value='${vaittama.id}' />">Delete</a></th>

                            </tr>
                        </c:forEach>
                                   
                    </table>
                    <br> 
                    <a href="/jsp/VaittamatNew.jsp" class="btn">Lisää väittämä</a> 
                    <a href="/list" class="btn">Listaa väittämät</a>
                </article>

                <article>                
                    <h1>Kaikki väittämät ja vastausvaihtoehdot</h1>

                    <br>
                    <form action='answers' method='post'>
                        <c:forEach var="vaittama" items="${requestScope.list}">
                            <table class="table table-striped">
                                <tr>
                                    <th><b> <c:out value="${vaittama.teksti}" /></b></th>
                                </tr>
                            </table>
                            <table class="table">
                                <tr>
                                    <c:forEach var="vastausvaihtoehdot" items="${requestScope.list2}">
                                    	<th>
                                    		<input type='checkbox' name='vastausteksti' value='${vastausvaihtoehdot.vv}'>
                                        	<label> <c:out value="${vastausvaihtoehdot.vv}" /></label>
                                    	</th>
                                    </c:forEach> 
                                    <th><input type='hidden' name='vaittamaId' value='<c:out value="${vaittama.id}" />'>
                                    </th>
                                </tr>
                            </table>
                        </c:forEach>
                        <input type='submit' name='ok' value='Send'>
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