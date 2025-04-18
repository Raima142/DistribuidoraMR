//Autor: MARIA ISABEL ROMANO
package Modelos;
//Esta clase sirve para extraer los datos de combobox
public class Combo {
    private int id;
    private String nombre;

    public Combo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
    
    
}
