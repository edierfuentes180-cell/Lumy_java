<%-- 
    Document   : index
    Created on : 25/11/2026, 11:36:11 a. m.
    
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Iniciar sesión – Lumy Peluquería</title>
<link href="${pageContext.request.contextPath}/Public/css/styles_inicio_secion.css" rel="stylesheet">
</head>
<body>

<main class="auth">
  <div class="auth-card">

    <aside class="auth-lateral">
      <p>Más que un cambio, una mejor versión</p>
      <span>Reserva tu cita online en minutos.</span>
    </aside>

    <section class="auth-form">
      <h1>Bienvenida de nuevo</h1>
      <p class="subtitulo">Ingresa para ver y reservar tus citas.</p>

      <% if ("ok".equals(request.getParameter("registro"))) { %>
        <p class="exito" role="status">¡Cuenta creada! Ya puedes iniciar sesión.</p>
      <% } %>

      <form id="form-login" action="${pageContext.request.contextPath}/LoginServlet" method="post" novalidate>
        <div class="campo">
          <label for="correo">Correo electrónico</label>
          <input type="email" id="correo" name="correo" placeholder="tucorreo@ejemplo.com" autocomplete="email" required>
        </div>

        <div class="campo">
          <label for="clave">Contraseña</label>
          <input type="password" id="clave" name="clave" placeholder="Tu contraseña" autocomplete="current-password" required>
        </div>

        <div class="opciones">
          <label><input type="checkbox" name="recordar"> Recordarme</label>
          <a href="#">¿Olvidaste tu contraseña?</a>
        </div>

        <p class="error" id="error-login" role="alert" ${empty error ? 'hidden' : ''}>${error}</p>

        <button type="submit" class="btn-auth">Iniciar sesión</button>
      </form>

      <p class="auth-cambio">¿Aún no tienes cuenta? <a href="${pageContext.request.contextPath}/vista/Acouts/Registro.jsp">Regístrate</a></p>
    </section>

  </div>
</main>

<script src="${pageContext.request.contextPath}/Public/js/login.js"></script>
</body>
</html>