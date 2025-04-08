//Autor: MARIA ISABEL ROMANO
package Modelos;

public class Productos {
    private int id;
    private int cantidad;
    private int id_proveedor;
    private int id_categoria;
    private double precio_compra;
    private double precio_venta;
    private String codigo;
    private String nombre_producto;
    private String estado;
    //Declarar las variables de la tabla proveedores y categorias para acceder a sus datos
    private String nombre_proveedor;
    private String nombre_categoria;
    
    
    public Productos() {
    }

    public Productos(int id, int cantidad, int id_proveedor, int id_categoria, double precio_compra, double precio_venta, String codigo, String nombre_producto, String estado, String nombre_proveedor, String nombre_categoria) {
        this.id = id;
        this.cantidad = cantidad;
        this.id_proveedor = id_proveedor;
        this.id_categoria = id_categoria;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.codigo = codigo;
        this.nombre_producto = nombre_producto;
        this.estado = estado;
        this.nombre_proveedor = nombre_proveedor;
        this.nombre_categoria = nombre_categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    
}
