<%-- 
    Document   : recogida
    Created on : May 19, 2014, 1:29:55 AM
    Author     : malkomich
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/format.css">
        <title>Admin - Recogida</title>
        <script type="text/JavaScript" src="js/jquery-1.11.0.min.js"></script>
        <script type="text/JavaScript" src="js/scripts.js"></script>
        <script type="text/javascript">
            $(document).keydown(function(tecla){
                if (tecla.keyCode === 13) {
                    ajaxForm(formRecogida);
                }
            });
        </script>
    </head>
    <body>
        <div class="bloque" style="width: 650px;">
            <h1>CREAR ALQUILER</h1>
            <form action="<%= response.encodeURL("recogida")%>" name="formRecogida" method="post">
                Introduzca el NIF del cliente: <input type="text" name="cliente"><br><br>
                <div class="validationMessage">No se encuentran reservas pendientes del usuario solicitado.</div>
                <br>
                <input type="button" name="accion" value="Buscar Reservas" onclick="ajaxForm(this.form)" class="button">
            </form>
                
            <div class="contenedorReservas" style="display: none">
                <table border="0px" align="center">
                    <tr>
                        <th>Matricula</th>
                        <th>Fecha Fin</th>
                        <th>Modelo</th>
                        <th>Marca</th>
                    </tr>
                    <form name="formCrearAlquiler" action="crearAlquiler" method="post">
                        </table>
                        <br>
                        <div class="inputContainer"></div>
                        <input type="hidden" name="kilometraje" value="">
                        <input type="button" name="accion" value="Crear Alquiler" onclick="registrar(this.form)" class="button">
                    </form>
                
            </div>
        </div>
    </body>
</html>
