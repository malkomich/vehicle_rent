<%-- 
    Document   : VerFactura
    Created on : 21-mar-2014, 20:12:41
    Author     : malkomich
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/format.css">
        <title>Pagina Factura</title>
        <script type="text/JavaScript" src="js/jquery-1.11.0.min.js"></script>
        <script type="text/JavaScript" src="js/scripts.js"></script>
    </head>
    <body>
        <div class="bloque" style="width: 650px;">
        <h1>Su reserva</h1>
        <h2>${usuario.getNombre()}:</h2>
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
            <br>
        <form action="<%= response.encodeURL("confirmar")%>" name="productos" method="post">
            Fecha Inicio: <input type="date" name="fechaIni">
            <br>
            &nbsp;&nbsp; Fecha Fin: <input type="date" name="fechaFin">
            <br><br>
            <input type="hidden" name="pagina" value="factura">
            <input type="button" name="accion" value="Confirmar" class="button" onclick="defaultForm(this.form)">
            <input type="submit" name="accion" value="Catalogo" class="button">
        </form>
        </div><%-- fin bloque --%>
    </body>
</html>
