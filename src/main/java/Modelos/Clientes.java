//Autor: MARIA ISABEL ROMANO
package Modelos;

public class Clientes {
    private int id;
    private String nombre_cliente;
    private String celular;
    private String direccion;
    private String estado;

    public Clientes() {
    }

    public Clientes(int id, String nombre_cliente, String celular, String direccion, String estado) {
        this.id = id;
        this.nombre_cliente = nombre_cliente;
        this.celular = celular;
        this.direccion = direccion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
}
