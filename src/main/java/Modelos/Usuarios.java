//Autor: MARIA ISABEL ROMANOS
package Modelos;

public class Usuarios {
    private int id;
    private int id_caja; //caja
    private String usuario;
    private String clave;
    private String nombre;
    private String tipo_caja; //nombre_caja
    private String rol;
    private String estado;

    public Usuarios() {
    }

    public Usuarios(int id, String usuario, String clave, String nombre, int id_caja, String tipo_caja, String rol, String estado) {
        this.id = id;
        this.usuario = usuario;
        this.clave = clave;
        this.nombre = nombre;
        this.id_caja = id_caja;
        this.tipo_caja = tipo_caja;
        this.rol = rol;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_caja() {
        return id_caja;
    }

    public void setId_caja(int id_caja) {
        this.id_caja = id_caja;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_caja() {
        return tipo_caja;
    }

    public void setTipo_caja(String tipo_caja) {
        this.tipo_caja = tipo_caja;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}