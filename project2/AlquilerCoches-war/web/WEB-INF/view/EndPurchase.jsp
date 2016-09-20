<%-- 
    Document   : FinCompra
    Created on : 26-dic-2012, 17:29:31
    Author     : Carlos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/format.css">
        <title>Fin Reserva</title>
        <script type="text/JavaScript" src="js/jquery-1.11.0.min.js"></script>
        <script type="text/JavaScript" src="js/scripts.js"></script>
    </head>
    <body>
        <div class="bloque" style="width: 650px;">
        <h1>Usted ha reservado:</h1>
        <br>
        <table border="0px" style="text-align: center" align="center">
            <tr>
                <th>Matricula</th>
                <th>Marca</th>
                <th>Modelo</th>
                <th>Fecha de matriculacion</th>
                <th>Kilometraje</th>
                <th>Precio</th>
            </tr>
            <tr class="reg">
                <td>${v_pedido.getMatricula()}</td>
                <td>${v_pedido.getIdmodelo().getFabricante()}</td>
                <td>${v_pedido.getIdmodelo().getNombre()}</td>
                <td><fmt:formatDate value="${v_pedido.getFechaadquisicion()}" pattern="dd/MM/yyyy " /></td>
                <td>${v_pedido.getKilometraje()} km</td>
                <td>${v_pedido.getIdmodelo().getCostealquiler()} €</td>
            </tr>
        </table>
                <p>${fechaIni} ${fechaFin}</p>
        <form action="<%= response.encodeURL("reserva")%>" name="ejemplo" method="post">
            <input type="hidden" name="pagina" value="fin">
            <input type="submit" name="accion" value="Catalogo" class="button">
        </form>
        </div><%-- fin bloque --%>
    </body>
</html>
