<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<h1>Väittämien hallinta</h1>
        <h2>
            <a href="/new">Lisää väittämä</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">Listaa väittämät</a>
             
        </h2>
    <div align="center">
        <table border="1">
            <caption>Väittämät</caption>
            <tr>
                <th>ID</th>
                <th>Teksti</th>
            </tr>
            <c:forEach var="vaittama" items="${list}">
                <tr>
                    <td><c:out value="${vaittama.id}" /></td>
                    <td><c:out value="${vaittama.teksti}" /></td>
             
                    <td>
                        <a href="/edit?id=<c:out value='${vaittama.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${vaittama.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>