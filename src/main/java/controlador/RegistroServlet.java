/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import Modelo.Usuario;
import Modelo.UsuarioDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RegistroServlet", urlPatterns = {"/RegistroServlet"})
public class RegistroServlet extends HttpServlet {

    private static final String PAGINA_REGISTRO = "/vista/Acouts/Registro.jsp";

    // Si alguien abre la URL directamente, lo mandamos al formulario
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + PAGINA_REGISTRO);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nombre = valor(request, "nombre");
        String correo = valor(request, "correo");
        String telefono = valor(request, "telefono").replaceAll("\\s+", "");
        String clave = request.getParameter("clave");
        String confirmar = request.getParameter("confirmar"); // opcional, solo si tu formulario lo tiene

        String error = null;
        if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty() || clave == null || clave.isEmpty()) {
            error = "Completa todos los campos.";
        } else if (!telefono.matches("\\d{7,15}")) {
            error = "El teléfono solo debe tener números (entre 7 y 15 dígitos).";
        } else if (clave.length() < 6) {
            error = "La contraseña debe tener al menos 6 caracteres.";
        } else if (confirmar != null && !confirmar.equals(clave)) {
            error = "Las contraseñas no coinciden.";
        }

        UsuarioDao dao = new UsuarioDao();

        if (error == null && dao.existeCorreo(correo)) {
            error = "Ya existe una cuenta con ese correo.";
        }

        if (error == null) {
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setCorreo(correo);
            usuario.setTelefono(telefono);
            usuario.setClave(clave);
            usuario.setRol(2); // 2 = cliente

            if (dao.nuevaCuenta(usuario) > 0) {
                response.sendRedirect(request.getContextPath() + "/index.jsp?registro=ok");
                return;
            }
            error = "No se pudo crear la cuenta. Intenta de nuevo.";
        }

        // Si hubo error, volvemos al formulario mostrando el mensaje
        request.setAttribute("error", error);
        request.getRequestDispatcher(PAGINA_REGISTRO).forward(request, response);
    }

    private String valor(HttpServletRequest request, String nombre) {
        String v = request.getParameter(nombre);
        return v == null ? "" : v.trim();
    }

    @Override
    public String getServletInfo() {
        return "Registro de clientes de Lumy";
    }
}
