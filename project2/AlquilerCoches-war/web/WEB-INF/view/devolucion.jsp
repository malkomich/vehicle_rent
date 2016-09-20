<%-- 
    Document   : devolucionç
    Created on : May 19, 2014, 1:30:01 AM
    Author     : malkomich
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/format.css">
        <title>Admin - Devolución</title>
                <script type="text/JavaScript" src="js/jquery-1.11.0.min.js"></script>
        <script type="text/JavaScript" src="js/scripts.js"></script>
        <script type="text/javascript">
            $(document).keydown(function(tecla){
                if (tecla.keyCode === 13) {
                    ajaxForm(formDevolucion);
                }
            });
        </script>
    </head>
    <body>
        <div class="bloque" style="width: 650px;">
            <h1>PROCESAR DEVOLUCION</h1>
            <form action="<%= response.encodeURL("devolucion")%>" name="formDevolucion" method="post">
                Introduzca la matrícula del vehículo: <input type="text" name="vehiculo"><br><br>
                <div class="validationMessage">El vehículo no se encuentra alquilado.</div>
                <br>
                <input type="button" name="accion" value="Mostrar" onclick="ajaxForm(this.form)" class="button">
            </form>
                
            <div class="contenedorDetalles" style="display: none">
                <table border="0px" align="center">
                    <tr>
                        <th>Nombre Cliente</th>
                        <th>NIF Cliente</th>
                        <th>Fecha Inicio</th>
                        <th>Fecha Fin</th>
                        <th>Matrícula del vehículo</th>
                        <th>Kilometraje de Salida</th>
                    </tr>
                    <form name="formProcesarDevolucion" action="devolver" method="post">
                        </table>
                        <br>
                        <div class="inputContainer"></div>
                        <input type="hidden" name="kilometraje" value="">
                        <input type="button" name="accion" value="Devolucion" onclick="registrar(this.form)" class="button">
                    </form>
                
            </div>
        </div>
    </body>
</html>
