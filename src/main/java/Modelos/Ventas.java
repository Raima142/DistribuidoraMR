//Autor: MARIA ISABEL ROMANO
package Modelos;

public class Ventas {
    private int id;
    private int id_cliente;
    private String nombreCliente;
    private Double total;
    private String fecha;

    public Ventas() {
    }

    public Ventas(int id, int id_cliente, String nombreCliente, Double total, String fecha) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.nombreCliente = nombreCliente;
        this.total = total;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
