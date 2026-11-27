<%-- 
    Document   : index
    Created on : 25/11/2026, 4:04:54 p. m.
    Author     : atues
--%>

<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Protege la página si no hay sesión, redirigir al login
    Usuario usuarioLogeado = (Usuario) session.getAttribute("usuario");
    if (usuarioLogeado == null || usuarioLogeado.getRol() != 1) {
        response.sendRedirect(request.getContextPath() + "/index.jsp?error=acceso");
        return;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lumy salon de belleza</title>
    </head>
    <body>
        <h1>Bienvenido, Administrador: <%= usuarioLogeado.getNombre() %></h1>
        <a href="<%= request.getContextPath() %>/LogoutServlet">Cerrar sesión</a>
    </body>
</html>

