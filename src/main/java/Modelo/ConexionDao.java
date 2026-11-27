/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDao {

    private static final String url = "jdbc:mysql://gateway01.ap-northeast-1.prod.aws.tidbcloud.com:4000/lumy?sslMode=VERIFY_IDENTITY";
    public static final String user = "4ArwXHfnj2nnGnw.root";
    public static final String pass = "cNaX7cg8n1wPA2bR";

    // Función para conectarnos a la base de datos lumy en TiDB Cloud
    public static Connection ObtenerConexion() throws ClassNotFoundException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión exitosa a TiDB Cloud (lumy)");
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró el driver JDBC de MySQL: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        try {
            Connection test = ConexionDao.ObtenerConexion();
            if (test != null) {
                System.out.println("Conexión probada correctamente desde main.");
            } else {
                System.out.println("No se pudo establecer la conexión.");
            }
        } catch (ClassNotFoundException ex) {
            System.getLogger(ConexionDao.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}