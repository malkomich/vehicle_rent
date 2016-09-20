<%-- 
    Document   : index
    Created on : 02-dic-2012, 22:16:19
    Author     : Carlos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/format.css">
        <title>Pagina principal</title>
        <script type="text/JavaScript" src="js/jquery-1.11.0.min.js"></script>
        <script type="text/JavaScript" src="js/scripts.js"></script>
        <script type="text/javascript">
            $(document).keydown(function(tecla){
                if (tecla.keyCode === 13) {
                    defaultForm(formLogin);
                }
            });
        </script>
    </head>
    <body>
        <div class="bloque" style="width: 300px">
        <% if (session.isNew()) {%>
            <%--Miramos si se han enviado cookies--%>
            <% Cookie[] cookies = request.getCookies();
                if (cookies == null) { // no hay cookie previa %>
            <h1>BIENVENIDO</h1>
            <form action="login" name="formLogin" method="post">
                Username: <input type="text" name="usuario"><br>
                Password: <input type="password" name="clave"><br><br>
                <input type="button" value="Entrar" onclick="defaultForm(this.form)">
                <input type="reset" value="Vaciar">
            </form>
            <% } else {  // hay cookies
                String login = "";
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("MiTienda_user")) {
                        login = cookie.getValue();
                    }
                }%>
                <h1>Bienvenido</h1>
                <form action="<%= response.encodeURL("login")%>" name="formLogin" method="post">
                    Username: <input type="text" name="usuario" value="<%=login%>"><br>
                    Password: <input type="password" name="clave"><br><br>
                    <input type="button" value="Entrar" onclick="defaultForm(this.form)" class="button">
                    <input type="reset" value="Vaciar"  class="button">
                </form>
            <% }%>
        <% } else {%>
            <% response.sendRedirect("catalogo"); %>
        <% }%>
        </div><%-- fin bloque --%>
    </body>
</html>