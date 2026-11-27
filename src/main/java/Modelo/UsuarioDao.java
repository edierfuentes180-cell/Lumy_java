/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manja
 */
public class UsuarioDao {

    public Usuario validar(String correo, String pass) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND clave = ?";

        try (Connection conn = ConexionDao.ObtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);
            pstmt.setString(2, pass);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setTelefono(rs.getString("telefono"));
                    usuario.setRol(rs.getInt("rol"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al validar usuario: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            System.getLogger(UsuarioDao.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return usuario;
    }

    // Registra una nueva cuenta; por defecto el rol es 2 (cliente)
    public int nuevaCuenta(Usuario user) {
        int result = 0;
        String sql = "INSERT INTO usuarios(nombre, correo, telefono, clave, rol) VALUES (?,?,?,?,?)";

        try (Connection conn = ConexionDao.ObtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getNombre());
            pstmt.setString(2, user.getCorreo());
            pstmt.setString(3, user.getTelefono());
            pstmt.setString(4, user.getClave());
            pstmt.setInt(5, user.getRol());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            System.getLogger(UsuarioDao.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return result;
    }

    public List<Usuario> listarClientes() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE rol = 2";

        try (Connection conn = ConexionDao.ObtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setCorreo(rs.getString("correo"));
                u.setTelefono(rs.getString("telefono"));
                u.setRol(rs.getInt("rol"));
                lista.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            System.getLogger(UsuarioDao.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return lista;
    }

    public boolean existeCorreo(String correo) {
        boolean existe = false;
        String sql = "SELECT 1 FROM usuarios WHERE correo = ?";

        try (Connection conn = ConexionDao.ObtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);
            try (ResultSet rs = pstmt.executeQuery()) {
                existe = rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar correo: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            System.getLogger(UsuarioDao.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return existe;
    }
}