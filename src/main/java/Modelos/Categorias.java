//Autor: MARIA ISABEL ROMANO
package Modelos;

public class Categorias {
    private int id;
    private String nombre_categoria;
    private String estado;

    public Categorias() {
    }

    public Categorias(int id, String nombre_categoria, String estado) {
        this.id = id;
        this.nombre_categoria = nombre_categoria;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}
