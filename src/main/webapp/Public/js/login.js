/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


// Validación del inicio de sesión de Lumy
const form = document.getElementById('form-login');
const error = document.getElementById('error-login');

// Esta página solo tiene el formulario de login; en otras (como Registro) no hacemos nada
if (form && error) {

  function mostrarError(mensaje, campo) {
    error.textContent = mensaje;
    error.hidden = false;
    campo.focus();
  }

  form.addEventListener('submit', (e) => {
    const correo = form.correo.value.trim();
    const clave = form.clave.value.trim();

    if (!correo) {
      e.preventDefault();
      mostrarError('Escribe tu correo electrónico.', form.correo);
      return;
    }

    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(correo)) {
      e.preventDefault();
      mostrarError('Escribe un correo válido, por ejemplo tucorreo@ejemplo.com.', form.correo);
      return;
    }

    if (!clave) {
      e.preventDefault();
      mostrarError('Escribe tu contraseña.', form.clave);
      return;
    }

    // Todo bien: no se llama a preventDefault, así el formulario se envía a LoginServlet
    error.hidden = true;
  });

  // Quita el mensaje apenas la persona vuelve a escribir
  form.addEventListener('input', () => { error.hidden = true; });
}