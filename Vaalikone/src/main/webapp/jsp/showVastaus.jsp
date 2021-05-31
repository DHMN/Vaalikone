<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="data.Vastausvaihtoehdot"%>

<!DOCTYPE html>
<html>

<head>
<title>Vaalikone</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<!-- Omat tyylit-->
<link
	href="https://fonts.googleapis.com/css2?family=Mandali&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Oswald&display=swap"
	rel="stylesheet">

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
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarNav" aria-controls="navbarNav"
						aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse justify-content-center"
						id="navbarNav">
						<ul class="navbar-nav">
							<li class="nav-item"><a class="nav-link"
								href="../index.html">ETUSIVU</a></li>
							<li class="nav-item"><a class="nav-link" href="/hello">VASTAA</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/jsp/VaittamatNew.jsp">LISÄÄ VÄITTÄMÄ</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/jsp/EhdokasNew.jsp">LISÄÄ EHDOKAS</a></li>
							<li class="nav-item"><a class="nav-link" href="/ehdokaslist">EHDOKKAAT</a></li>
							<li class="nav-item"><a class="nav-link" href=""></a></li>
							<li class="nav-item"><a class="nav-link"
								href="/jsp/login.jsp"><%=(request.getSession(false).getAttribute("Admin") == null) ? "LOGIN"
		: "LOGGED IN AS " + session.getAttribute("Admin")%></a></li>
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
				<article>
					<h2>Vastausten vertailu</h2>

					<c:set var="totals" value="${999}" />
					<c:set var="en" value="${999}" />
					<c:set var="en2" value="${999}" />
					<c:set var="en3" value="${999}" />

					<c:forEach var="ehdokas" items="${requestScope.ehdokaslist}">
						<table class="table table-striped">
							<tr>
								<th><b> ${ehdokas.ehdokasNro}. ${ehdokas.puolue}
										${ehdokas.etuNimi} ${ehdokas.sukuNimi} <a
										href='../showinfo?id=${ehdokas.id}'>Tiedot</a> <a
										href='../showanswers?id1=${ehdokas.id}'>Vastaukset</a>
								</b></th>
							</tr>
						</table>
						<c:set var="total" value="${0}" />

						<c:forEach var="fish" items="${ehdokas.liitokset}"
							varStatus="counter">
							<c:forEach var="vaittama" items="${requestScope.list}"
								varStatus="counterr">
								<c:forEach var="kerays" items="${requestScope.yhdistyslist}"
									varStatus="strike">
									<c:choose>
										<c:when
											test="${fish.vastaus <= kerays.vastausteksti && counter.index == strike.index && counter.index == counterr.index}">
											<table class="table">
												<tr>
													<th><b><c:out value="${counter.count}" /> <c:out
																value="${vaittama.teksti}" /><input type='hidden'
															name='vaittamaId${counter.count}' value='${vaittama.id}'></b></th>
												</tr>
											</table>
											<c:forEach var="vastausvaihtoehdot"
												items="${requestScope.list2}">
												<c:choose>
													<c:when test="${vastausvaihtoehdot.id == fish.vastaus}">													
														<p>Ehdokas vastasi: ${vastausvaihtoehdot.vv}</p>
													</c:when>
												</c:choose>
											</c:forEach>
											<c:forEach var="vastausvaihtoehdot"
												items="${requestScope.list2}">
												<c:choose>
													<c:when
														test="${vastausvaihtoehdot.id == kerays.vastausteksti}">
														<p>Sinä vastasit: ${vastausvaihtoehdot.vv}</p>
													</c:when>
												</c:choose>
											</c:forEach>

											<c:set var="total"
												value="${total + (kerays.vastausteksti - fish.vastaus)}" />
										</c:when>
										<c:when
											test="${fish.vastaus > kerays.vastausteksti && counter.index == strike.index && counter.index == counterr.index}">
											<table class="table">
												<tr>
													<th><b><c:out value="${counter.count}" /> <c:out
																value="${vaittama.teksti}" /><input type='hidden'
															name='vaittamaId${counter.count}' value='${vaittama.id}'></b></th>
												</tr>
											</table>

											<c:forEach var="vastausvaihtoehdot"
												items="${requestScope.list2}">
												<c:choose>
													<c:when test="${vastausvaihtoehdot.id == fish.vastaus}">
														<p>Ehdokas vastasi: ${vastausvaihtoehdot.vv}</p>
													</c:when>
												</c:choose>
											</c:forEach>
											<c:forEach var="vastausvaihtoehdot"
												items="${requestScope.list2}">
												<c:choose>
													<c:when
														test="${vastausvaihtoehdot.id == kerays.vastausteksti}">
														<p>Sinä vastasit: ${vastausvaihtoehdot.vv}</p>
													</c:when>
												</c:choose>
											</c:forEach>

											<c:set var="total"
												value="${total + (fish.vastaus - kerays.vastausteksti)}" />
										</c:when>
									</c:choose>
								</c:forEach>
							</c:forEach>
						</c:forEach>
						<!-- AVAA TESTEJÄ VARTEN
						<table class="table table-striped">
							<tr>
								<th>Yhteispisteet ${total}</th>
							</tr>
						</table>

						<c:if test="${total < totals}">
							<c:set var="totals" value="${total}" />
							<c:set var="en" value="${ehdokas.ehdokasNro}" />
						</c:if>
						<c:if
							test="${total == totals && en != ehdokas.ehdokasNro && en2 == en3}">
							<c:set var="en2" value="${ehdokas.ehdokasNro}" />
						</c:if>
						<c:if
							test="${total == totals && en2 != ehdokas.ehdokasNro && en != ehdokas.ehdokasNro}">
							<c:set var="en3" value="${ehdokas.ehdokasNro}" />
						</c:if>

						<li>Totaalit: ${total}</li>
						<li>Totaalsit: ${totals}</li>
						<li>en: ${en}</li>
						<li>en2: ${en2}</li>
						<li>en3: ${en3}</li>
						-->
						<br>
						<br>
					</c:forEach>

					<h1>Parhaat ehdokkaat sinulle:</h1>
					<c:forEach var="ehdokas" items="${requestScope.ehdokaslist}">
						<c:if test="${ehdokas.ehdokasNro eq en}">
							<li>${ehdokas.ehdokasNro}.${ehdokas.puolue}
								${ehdokas.etuNimi} ${ehdokas.sukuNimi} <a
								href='../showinfo?id=${ehdokas.id}'>Tiedot</a> <a
								href='../showanswers?id1=${ehdokas.id}'>Vastaukset</a>
							</li>
						</c:if>
						<c:if test="${ehdokas.ehdokasNro eq en2}">
							<li>${ehdokas.ehdokasNro}.${ehdokas.puolue}
								${ehdokas.etuNimi} ${ehdokas.sukuNimi} <a
								href='../showinfo?id=${ehdokas.id}'>Tiedot</a> <a
								href='../showanswers?id1=${ehdokas.id}'>Vastaukset</a>
							</li>
						</c:if>
						<c:if test="${ehdokas.ehdokasNro eq en3}">
							<li>${ehdokas.ehdokasNro}.${ehdokas.puolue}
								${ehdokas.etuNimi} ${ehdokas.sukuNimi} <a
								href='../showinfo?id=${ehdokas.id}'>Tiedot</a> <a
								href='../showanswers?id1=${ehdokas.id}'>Vastaukset</a>
							</li>
						</c:if>

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
		<script
			src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
			integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
			integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
			crossorigin="anonymous"></script>
</body>

</html>
