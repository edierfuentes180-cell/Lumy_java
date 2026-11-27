<%-- 
    Document   : Acounts
    Created on : 25/11/2026, 11:38:20 a. m.
    Author     : atues
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Crear cuenta – Lumy Peluquería</title>
<link href="${pageContext.request.contextPath}/Public/css/styles_inicio_secion.css" rel="stylesheet">
</head>
<body>

<main class="auth">
  <div class="auth-card">

    <aside class="auth-lateral">
      <p>Tu belleza, nuestra pasión</p>
      <span>Crea tu cuenta y agenda tu primera cita.</span>
    </aside>

    <section class="auth-form">
      <h1>Crea tu cuenta</h1>
      <p class="subtitulo">Solo toma un minuto.</p>

      <form action="${pageContext.request.contextPath}/RegistroServlet" method="post">
        <div class="campo">
          <label for="nombre">Nombre completo</label>
          <input type="text" id="nombre" name="nombre" autocomplete="name" required>
        </div>

        <div class="campo">
          <label for="correo">Correo electrónico</label>
          <input type="email" id="correo" name="correo" placeholder="tucorreo@ejemplo.com" autocomplete="email" required>
        </div>

        <div class="campo">
          <label for="telefono">Teléfono</label>
          <input type="tel" id="telefono" name="telefono" placeholder="300 000 0000" autocomplete="tel" required>
        </div>

        <div class="campo">
          <label for="clave">Contraseña</label>
          <input type="password" id="clave" name="clave" minlength="8" autocomplete="new-password" required>
        </div>

        <p class="error" role="alert" ${empty error ? 'hidden' : ''}>${error}</p>

        <button type="submit" class="btn-auth">Crear cuenta</button>
      </form>

      <p class="auth-cambio">¿Ya tienes cuenta? <a href="${pageContext.request.contextPath}/index.jsp">Inicia sesión</a></p>
    </section>

  </div>
</main>

</body>
</html>