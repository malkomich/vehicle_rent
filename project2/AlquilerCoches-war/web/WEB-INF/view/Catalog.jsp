<%-- 
    Document   : Comprar
    Created on : 07-dic-2012, 22:53:57
    Author     : Carlos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/format.css">
        <title>Página Catalogo</title>
        <script type="text/JavaScript" src="js/jquery-1.11.0.min.js"></script>
        <script type="text/JavaScript" src="js/scripts.js"></script>
    </head>
    <body>
        <div class="bloque" style="width: 650px;">
        <h1>Bienvenido ${usuario.getNombre()}</h1>
        <h2>Escoja un vehículo:</h2>
        <table border="0px" style="text-align: left" align="center">
            <tr>
                <th>Marca</th>
                <th>Modelo</th>
                <th>Fecha de matriculacion</th>
                <th>Kilometraje</th>
                <th>Precio</th>
            </tr>
            <c:forEach var="key" items="${catalogo}">
                <tr class="reg">
            <form action="<%= response.encodeURL("reserva")%>" name="productos" method="post">
                <td style="width:3cm">
                    ${key.getIdmodelo().getFabricante()}
                </td>
                <td style="width:3cm">
                    ${key.getIdmodelo().getNombre()}
                </td>
                <td style="width:3cm">
                    <fmt:formatDate value="${key.getFechaadquisicion()}" pattern="dd/MM/yyyy " />
                </td>
                <td style="width:3cm">
                    ${key.getKilometraje()} km
                </td>
                <td style="width:3cm">
                    ${key.getIdmodelo().getCostealquiler()} €
                    <input type="hidden" name="Precio" value="${key.getIdmodelo().getCostealquiler()}">
                </td>
            </tr>
            <tr>
                <td colspan="6" align="center">
                    <input type="hidden" name="v_index" value="${catalogo.indexOf(key)}">
                    <input type="hidden" name="pagina" value="catalogo">
                    <input type="submit" name="accion" value="Reservar">
                </td>
            </tr>
         </form>
            </c:forEach>
        </table>
        </div><%-- fin bloque --%>
    </body>
</html>
