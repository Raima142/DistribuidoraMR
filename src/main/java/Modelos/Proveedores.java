//Autor: MARIA ISABEL ROMANO
package Modelos;

public class Proveedores {
    private int id;
    private String cuit;
    private String nombre_proveedor;
    private String celular;
    private String direccion;
    private String estado;

    public Proveedores() {
    }

    public Proveedores(int id, String cuit, String nombre_proveedor, String celular, String direccion, String estado) {
        this.id = id;
        this.cuit = cuit;
        this.nombre_proveedor = nombre_proveedor;
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

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
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
