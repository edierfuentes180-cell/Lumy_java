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
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final String PAGINA_LOGIN = "/index.jsp";
    private static final String PAGINA_ADMIN = "/vista/Administrador/index.jsp";
    private static final String PAGINA_CLIENTE = "/vista/Usuarios/index.jsp";;

    // Si abren la URL directamente, los mandamos al formulario de inicio de sesión
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + PAGINA_LOGIN);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");
        correo = (correo == null) ? "" : correo.trim();

        if (correo.isEmpty() || clave == null || clave.isEmpty()) {
            volverConError(request, response, "Ingresa tu correo y tu contraseña.");
            return;
        }

        Usuario usuario = new UsuarioDao().validar(correo, clave);

        if (usuario == null) {
            volverConError(request, response, "Correo o contraseña incorrectos.");
            return;
        }

        // Sesión nueva por seguridad (descarta cualquier sesión anterior)
        HttpSession anterior = request.getSession(false);
        if (anterior != null) {
            anterior.invalidate();
        }
        HttpSession sesion = request.getSession(true);
        sesion.setAttribute("usuario", usuario);

        // Según el rol: 1 = administrador, 2 = cliente
        String destino = (usuario.getRol() == 1) ? PAGINA_ADMIN : PAGINA_CLIENTE;
        response.sendRedirect(request.getContextPath() + destino);
    }

    private void volverConError(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws ServletException, IOException {
        request.setAttribute("error", mensaje);
        request.getRequestDispatcher(PAGINA_LOGIN).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Inicio de sesión de Lumy";
    }
}
