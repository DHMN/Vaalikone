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
<% //In case, if Admin session is not set, redirect to Login page
if((request.getSession(false).getAttribute("Admin") == null) )
{
%>
<jsp:forward page="/jsp/BasicList.jsp"></jsp:forward>
<%} %>
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
                            <li class="nav-item"><a class="nav-link" href="/jsp/EhdokasNew.jsp">LISÄÄ EHDOKAS</a></li>
                            <li class="nav-item"><a class="nav-link" href=""></a></li>
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
         <h1>Kiitos vastauksesta!</h1>
                    <br>
                    <c:set var="count" value="0" scope="page" />
                        <c:forEach var="vaittama" items="${requestScope.list}" varStatus="counter">
                            <table class="table table-striped">
                                <tr>
                                    <th><b><c:out value="${counter.count}" /> <c:out value="${vaittama.teksti}" /><input type='hidden' name='vaittamaId${counter.count}' value='${vaittama.id}'></b></th>
                                </tr>
                            </table>
                            <table class="table">
                                <tr>
                                    <c:forEach var="vastausvaihtoehdot" items="${requestScope.list2}" varStatus="counter">
                                    	<th>
                                    	<input type='checkbox' name='vastausteksti${vaittama.id}' value='${vastausvaihtoehdot.vv}'>

                                        	<label> <c:out value="${vastausvaihtoehdot.vv}" /></label>
                                        	<p> <c:out value="${vastausvaihtoehdot.id}" /></p>
                                        	<th><input type='hidden' name='vaittamanArvo${vaittama.id}' value='${vastausvaihtoehdot.id}'></th>
                                    	</th>
                                    </c:forEach> 
                                </tr>
                            </table>
                        </c:forEach>
        

                <article>                
                    <h1>Vastauksesi</h1>
                    <br>                    
                    <c:forEach var="kerays" items="${requestScope.yhdistyslist}">
	<li>Vaittama: <c:out value="${kerays.vaittamaid}"/>  Vastaus: <c:out value="${kerays.vastausteksti}"/></li>
</c:forEach>
                    <c:forEach var="vastaukset" items="${paramValues.vastausteksti}">
						<li><font color="#008000"><c:out value="${vastaukset}"/></font></li>
					</c:forEach>
                    
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