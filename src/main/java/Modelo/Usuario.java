/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class Usuario {
    private int id;
    private String nombre;
    private String correo;
    private String telefono;
    private String clave;
    private int rol; // 1 = administrador, 2 = cliente

    public Usuario() {
    }

    public Usuario(int id, String nombre, String correo, String telefono, String clave, int rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.clave = clave;
        this.rol = rol;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public int getRol() { return rol; }
    public void setRol(int rol) { this.rol = rol; }

}