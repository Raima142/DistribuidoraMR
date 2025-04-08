//Autor: MARIA ISABEL ROMANO
package Modelos;

public class Configuracion {
    private int id;
    private String cuit;
    private String nombre_empresa;
    private String celular;
    private String direccion;

    public Configuracion() {
    }

    public Configuracion(int id, String cuit, String nombre_empresa, String celular, String direccion) {
        this.id = id;
        this.cuit = cuit;
        this.nombre_empresa = nombre_empresa;
        this.celular = celular;
        this.direccion = direccion;
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

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
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
}
