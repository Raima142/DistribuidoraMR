//Autor: MARIA ISABEL ROMANO
package Modelos;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class ProductosDao {

    //Conectar a Conexion
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //Registrar Productos
    public String registrar(Productos pd) {
        String verificarCodigo = "SELECT * FROM productos WHERE codigo = ?";
        String verificarNombre = "SELECT * FROM productos WHERE nombre_producto = ?";
        String registrar = "INSERT INTO productos (codigo, nombre_producto, precio_compra, precio_venta, id_proveedor, id_categoria) VALUES (?,?,?,?,?,?)";

        try (Connection con = cn.getConexion(); 
            PreparedStatement psc = con.prepareStatement(verificarCodigo);
            PreparedStatement psn = con.prepareStatement(verificarNombre)) {
            psc.setString(1, pd.getCodigo());
            try (ResultSet rs = psc.executeQuery()) {
                if (rs.next()) {
                    return "codigo_existe";
                }
            }
            psn.setString(1, pd.getNombre_producto());
            try (ResultSet rs = psn.executeQuery()) {
                if (rs.next()) {
                    return "nombre_existe";
                }
            }
            //Registrar
            try (PreparedStatement psr = con.prepareStatement(registrar)) {
                psr.setString(1, pd.getCodigo());
                psr.setString(2, pd.getNombre_producto());
                psr.setDouble(3, pd.getPrecio_compra());
                psr.setDouble(4, pd.getPrecio_venta());
                psr.setInt(5, pd.getId_proveedor());
                psr.setInt(6, pd.getId_categoria());
                psr.executeUpdate();
            }
            return "registrado";
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return "error";
        }
    }

    //Listar Productos
    public List ListaProductos(String valor) {
        List<Productos> listaProd = new ArrayList();
        String sql = "SELECT * FROM productos ORDER BY estado ASC";
        String buscar = "SELECT * FROM productos WHERE codigo LIKE '%" + valor + "%' OR nombre_producto LIKE '%" + valor + "%'";
        try {
            con = cn.getConexion();
            if (valor.equalsIgnoreCase("")) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            } else {
                ps = con.prepareStatement(buscar);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                Productos pd = new Productos();
                pd.setId(rs.getInt("id"));
                pd.setCodigo(rs.getString("codigo"));
                pd.setNombre_producto(rs.getString("nombre_producto"));
                pd.setPrecio_venta(rs.getInt("precio_venta"));
                pd.setCantidad(rs.getInt("cantidad"));
                pd.setEstado(rs.getString("estado"));
                listaProd.add(pd);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaProd;
    }

    //Modificar Productos    
    public String modificar(Productos pd) {
        String verificarCodigo = "SELECT * FROM productos WHERE codigo = ? AND id != ?";
        String verificarNombre = "SELECT * FROM productos WHERE nombre_producto = ? AND id != ?";
        String modificar = "UPDATE productos SET codigo = ?, nombre_producto = ?, precio_compra = ?, precio_venta = ?, id_proveedor = ?, id_categoria = ? WHERE id = ?";

        try (Connection con = cn.getConexion(); 
            PreparedStatement psc = con.prepareStatement(verificarCodigo);
            PreparedStatement psn = con.prepareStatement(verificarNombre)) {
            psc.setString(1, pd.getCodigo());
            psc.setInt(2, pd.getId());
            try (ResultSet rs = psc.executeQuery()) {
                if (rs.next()) {
                    return "codigo_existe";
                }
            }
            psn.setString(1, pd.getNombre_producto());
            psn.setInt(2, pd.getId());
            try (ResultSet rs = psn.executeQuery()) {
                if (rs.next()) {
                    return "nombre_existe";
                }
            }
            //Modificar
            try (PreparedStatement psr = con.prepareStatement(modificar)) {
                psr.setString(1, pd.getCodigo());
                psr.setString(2, pd.getNombre_producto());
                psr.setDouble(3, pd.getPrecio_compra());
                psr.setDouble(4, pd.getPrecio_venta());
                psr.setInt(5, pd.getId_proveedor());
                psr.setInt(6, pd.getId_categoria());
                psr.setInt(7, pd.getId());
                psr.executeUpdate();
            }
            return "modificado";
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return "error";
        }
    }

    //Actualizar Productos
    public boolean accion(String estado, int id) {
        String sql = "UPDATE productos SET estado = ? WHERE id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            ps.setInt(2, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }

    //Metodo que sirve para llenar el panel con todos los datos incluido los cbx
    public Productos buscarProd(int id) {
        //Podemos proporcionar el alias que quieramos a cada la tabla
        /*SELECT: Seleccionar de la tabla productos con alias p todas las columnas,
        seleccionar de la tabla proveedores con alias pr las columnas id y nombre_proveedor,
        seleccionar de la tabla categorias con alias c las columnas id y nombre_categoria,
        FROM: Extraer los datos INNER JOIN: Combinando productos p y proveedores pr ON: para que el id_proveedor de p y el id de pr sean iguales,
        y combinando esta misma tabla con categorias c ON: para que el id_categoria de p y el id de c sean iguales,
        WHERE: Donde el id de p será igual a la fila que se solicite*/
        String sql = "SELECT p.*, pr.id, pr.nombre_proveedor, c.id, c.nombre_categoria FROM productos p INNER JOIN proveedores pr ON p.id_proveedor = pr.id INNER JOIN categorias c ON p.id_categoria = c.id WHERE p.id = ?";
        Productos pd = new Productos();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                //Mostramos los datos de acuerdo a su orden en el panel de Nuevo Producto
                pd.setCodigo(rs.getString("codigo"));
                pd.setNombre_producto(rs.getString("nombre_producto"));
                pd.setPrecio_compra(rs.getDouble("precio_compra"));
                pd.setPrecio_venta(rs.getDouble("precio_venta"));
                //Para los cbx especificamos el Id y luego su nombre
                pd.setId_proveedor(rs.getInt("id_proveedor"));
                pd.setId_categoria(rs.getInt("id_categoria"));
                pd.setNombre_proveedor(rs.getString("nombre_proveedor"));
                pd.setNombre_categoria(rs.getString("nombre_categoria"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return pd;
    }

    public Productos buscarCodigo(String codigo) {
        String sql = "SELECT * FROM productos WHERE codigo = ? AND estado = 'Activo'";
        Productos pd = new Productos();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                pd.setId(rs.getInt("id"));
                pd.setNombre_producto(rs.getString("nombre_producto"));
                pd.setPrecio_compra(rs.getDouble("precio_compra"));
                pd.setPrecio_venta(rs.getDouble("precio_venta"));
                pd.setCantidad(rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return pd;
    }

    //METODO PARA COMPRAS Y VENTAS
    public Productos buscarId(int id) {
        String sql = "SELECT * FROM productos WHERE id = ?";
        Productos pd = new Productos();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                pd.setCantidad(rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return pd;
    }

    /*Crear relaciones restrictas de detalle_compra.id_compra --> compras.id, 
      Crear relaciones restrictas de compras.id_proveedor --> proveedores.id*/
    //COMPRAS
    public boolean registrarCompra(int id, String total, int id_user) {
        String sql = "INSERT INTO compras (id_proveedor, total, id_user) VALUES (?,?,?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, total);
            ps.setInt(3, id_user);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public int id_compra() {
        int id = 0;
        String sql = "SELECT MAX(id) AS id FROM compras";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public boolean registrarDetalleCompra(int id_compra, int id_producto, double precio, int cant, double sub_total) {
        String sql = "INSERT INTO detalle_compra (id_compra, id_producto, precio, cantidad, sub_total) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_compra);
            ps.setInt(2, id_producto);
            ps.setDouble(3, precio);
            ps.setInt(4, cant);
            ps.setDouble(5, sub_total);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public List ListaDetalleCompra(int id_compra) {
        List<Productos> listaProd = new ArrayList();
        String sql = "SELECT c.*, d.*, p.id, p.nombre_producto FROM compras c INNER JOIN detalle_compra d ON d.id_compra = c.id INNER JOIN productos p ON p.id = d.id_producto WHERE c.id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_compra);
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos pd = new Productos();
                pd.setCantidad(rs.getInt("cantidad"));
                pd.setNombre_producto(rs.getString("nombre_producto"));//Tabla productos
                pd.setPrecio_compra(rs.getDouble("precio"));//Tabla detalle_compra
                pd.setPrecio_venta(rs.getDouble("sub_total"));//Tabla detalle_compra
                listaProd.add(pd);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaProd;
    }

    public void generarFacturaCompra(int id_compra, String nomComprador) {
        String fecha = "";
        String nomProveedor = "";
        String dirProveedor = "";
        String celProveedor = "";
        try {
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.WHITE);
            String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            FileOutputStream archivo;
            File salida = new File(url + File.separator + "factura-compra-N" + id_compra);
            archivo = new FileOutputStream(salida);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            //EMPRESA
            //Tabla superior con 2 columnas
            PdfPTable empresa = new PdfPTable(3);
            empresa.setWidthPercentage(100);
            float[] tamanioEncabezado = new float[]{10f, 45f, 45f};
            empresa.setWidths(tamanioEncabezado);
            empresa.setHorizontalAlignment(Element.ALIGN_CENTER);
            empresa.getDefaultCell().setBorder(0);
            //Logotipo de la empresa
            InputStream imageStream = getClass().getClassLoader().getResourceAsStream("refresco.png");
            byte[] imageBytes = imageStream.readAllBytes();
            Image refresco = Image.getInstance(imageBytes);
            empresa.addCell(refresco);
            //Consulta los datos de la empresa
            String consultaEmpresa = "SELECT * FROM configuracion";
            try {
                con = cn.getConexion();
                ps = con.prepareStatement(consultaEmpresa);
                rs = ps.executeQuery();
                if (rs.next()) {
                    //Mostrar los datos de la empresa
                    empresa.addCell("CUIT: " + rs.getString("cuit") + "\nNombre: " + rs.getString("nombre_empresa")
                            + "\nCelular: " + rs.getString("celular") + "\nDireccion: " + rs.getString("direccion"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            //PROVEEDOR
            //Consulta los datos del proveedor y numero y fecha de la compra
            String consultaProveedor = "SELECT p.nombre_proveedor, p.celular, p.direccion, c.total, c.fecha FROM compras c INNER JOIN proveedores p ON c.id_proveedor = p.id WHERE c.id = " + id_compra;
            try {
                con = cn.getConexion();
                ps = con.prepareStatement(consultaProveedor);
                rs = ps.executeQuery();
                if (rs.next()) {
                    //Extraer los datos del proveedor y fecha de la compra
                    nomProveedor = rs.getString("nombre_proveedor");
                    celProveedor = rs.getString("celular");
                    dirProveedor = rs.getString("direccion");
                    fecha = rs.getString("fecha");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            //COMPRADOR
            //Mostrar comprador y numero de compra en la segunda columna de la tabla superior
            empresa.addCell("N° Compra: " + id_compra + "\nComprador: " + nomComprador + "\nFecha: " + fecha);
            doc.add(empresa);
            doc.add(Chunk.NEWLINE);
            Paragraph titProveedor = new Paragraph();
            titProveedor.add("DATOS DEL PROVEEDOR");
            titProveedor.setAlignment(Element.ALIGN_CENTER);
            doc.add(titProveedor);
            doc.add(Chunk.NEWLINE);
            //Tabla del proveedor con 3 columnas
            PdfPTable proveedor = new PdfPTable(3);
            empresa.setWidthPercentage(100);
            float[] tamanioProveedor = new float[]{40f, 30f, 30f};
            proveedor.setWidths(tamanioProveedor);
            proveedor.setHorizontalAlignment(Element.ALIGN_CENTER);
            proveedor.getDefaultCell().setBorder(10);
            //Encabezado proveedor
            PdfPCell nomPr = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell celPr = new PdfPCell(new Phrase("Celular", negrita));
            PdfPCell dirPr = new PdfPCell(new Phrase("Direccion", negrita));
            nomPr.setBorder(0);
            celPr.setBorder(0);
            dirPr.setBorder(0);
            //Background del encabezado
            nomPr.setBackgroundColor(BaseColor.DARK_GRAY);
            celPr.setBackgroundColor(BaseColor.DARK_GRAY);
            dirPr.setBackgroundColor(BaseColor.DARK_GRAY);
            //Agregar los encabezados del proveedor
            proveedor.addCell(nomPr);
            proveedor.addCell(celPr);
            proveedor.addCell(dirPr);
            //Agregar datos del proveedor
            proveedor.addCell(nomProveedor);
            proveedor.addCell(celProveedor);
            proveedor.addCell(dirProveedor);
            doc.add(proveedor);
            doc.add(Chunk.NEWLINE);
            //COMPRA
            Paragraph titProducto = new Paragraph();
            titProducto.add("DETALLE DE LA COMPRA");
            titProducto.setAlignment(Element.ALIGN_CENTER);
            doc.add(titProducto);
            doc.add(Chunk.NEWLINE);
            //Tabla compra con 4 columnas
            PdfPTable producto = new PdfPTable(4);
            producto.setWidthPercentage(100);
            float[] tamanioProducto = new float[]{40f, 20f, 20f, 20f};
            producto.setWidths(tamanioProducto);
            producto.setHorizontalAlignment(Element.ALIGN_LEFT);
            producto.getDefaultCell().setBorder(10);
            //Encabezado compra
            PdfPCell nomProd = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell cantProd = new PdfPCell(new Phrase("Cantidad", negrita));
            PdfPCell precioProd = new PdfPCell(new Phrase("Precio", negrita));
            PdfPCell subtotProd = new PdfPCell(new Phrase("SubTotal", negrita));
            nomProd.setBorder(0);
            cantProd.setBorder(0);
            precioProd.setBorder(0);
            subtotProd.setBorder(0);
            //Background del encabezado
            nomProd.setBackgroundColor(BaseColor.DARK_GRAY);
            cantProd.setBackgroundColor(BaseColor.DARK_GRAY);
            precioProd.setBackgroundColor(BaseColor.DARK_GRAY);
            subtotProd.setBackgroundColor(BaseColor.DARK_GRAY);
            //Agregar los encabezados de los productos
            producto.addCell(nomProd);
            producto.addCell(cantProd);
            producto.addCell(precioProd);
            producto.addCell(subtotProd);
            //Consulta los datos del producto y la compra
            String consultaProducto = "SELECT d.precio, d.cantidad, d.sub_total, p.nombre_producto FROM compras c INNER JOIN detalle_compra d ON c.id = d.id_compra INNER JOIN productos p ON d.id_producto = p.id WHERE c.id = " + id_compra;
            try {
                con = cn.getConexion();
                ps = con.prepareStatement(consultaProducto);
                rs = ps.executeQuery();
                //Mostrar todos los productos comprados
                while (rs.next()) {
                    //Agregar los datos del producto
                    producto.addCell(rs.getString("nombre_producto"));
                    producto.addCell(String.valueOf(rs.getInt("cantidad")));
                    producto.addCell(String.valueOf("$ " + rs.getDouble("precio")));
                    producto.addCell(String.valueOf("$ " + rs.getDouble("sub_total")));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            doc.add(producto);
            doc.add(Chunk.NEWLINE);
            //Consultar el Total de la compra
            String consultaTotal = "SELECT total FROM compras ORDER BY id DESC LIMIT 1";
            try {
                con = cn.getConexion();
                ps = con.prepareStatement(consultaTotal);
                rs = ps.executeQuery();

                if (rs.next()) {
                    double totalCompra = rs.getDouble("total");
                    Paragraph total = new Paragraph();
                    total.add("Total a pagar: $ " + totalCompra);
                    total.setAlignment(Element.ALIGN_RIGHT);
                    doc.add(total);
                    doc.add(Chunk.NEWLINE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(salida);
        } catch (DocumentException | HeadlessException | IOException e) {
        }
    }

    //VENTAS
    public boolean registrarVenta(int id, String total, int id_user) {
        String sql = "INSERT INTO ventas (id_cliente, total, id_user) VALUES (?,?,?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, total);
            ps.setInt(3, id_user);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public boolean ventaRegistroTemporal(String total, int id_user) {
        String sql = "INSERT INTO ventas_reg_tmp (total, id_user) VALUES (?,?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, total);
            ps.setInt(2, id_user);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public int id_venta() {
        int id = 0;
        String sql = "SELECT MAX(id) AS id FROM ventas";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public boolean registrarDetalleVenta(int id_venta, int id_producto, double precio, int cant, double sub_total) {
        String sql = "INSERT INTO detalle_venta (id_venta, id_producto, precio, cantidad, sub_total) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_venta);
            ps.setInt(2, id_producto);
            ps.setDouble(3, precio);
            ps.setInt(4, cant);
            ps.setDouble(5, sub_total);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public List ListaDetalleVenta(int id_venta) {
        List<Productos> listaProd = new ArrayList();
        String sql = "SELECT v.*, d.*, p.id, p.nombre_producto FROM ventas v INNER JOIN detalle_venta d ON d.id_venta = v.id INNER JOIN productos p ON p.id = d.id_producto WHERE v.id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_venta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos pd = new Productos();
                pd.setCantidad(rs.getInt("cantidad"));
                pd.setNombre_producto(rs.getString("nombre_producto"));
                pd.setPrecio_compra(rs.getDouble("precio"));
                pd.setPrecio_venta(rs.getDouble("sub_total"));
                listaProd.add(pd);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaProd;
    }

    public void generarFacturaVenta(int id_venta, String nomVendedor) {
        String fecha = "";
        String nomCliente = "";
        String dirCliente = "";
        String celCliente = "";
        try {
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.WHITE);
            String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            FileOutputStream archivo;
            File salida = new File(url + File.separator + "factura-venta-N" + id_venta);
            archivo = new FileOutputStream(salida);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            //EMPRESA
            //Tabla empresa con 2 columnas
            PdfPTable empresa = new PdfPTable(3);
            empresa.setWidthPercentage(100);
            float[] tamanioEncabezado = new float[]{10f, 45f, 45f};
            empresa.setWidths(tamanioEncabezado);
            empresa.setHorizontalAlignment(Element.ALIGN_LEFT);
            empresa.getDefaultCell().setBorder(0);
            //Logotipo de la empresa
            InputStream imageStream = getClass().getClassLoader().getResourceAsStream("refresco.png");
            byte[] imageBytes = imageStream.readAllBytes();
            Image refresco = Image.getInstance(imageBytes);
            empresa.addCell(refresco);
            //Consulta los datos de la empresa
            String consultaEmpresa = "SELECT * FROM configuracion";
            try {
                con = cn.getConexion();
                ps = con.prepareStatement(consultaEmpresa);
                rs = ps.executeQuery();
                if (rs.next()) {
                    //Extraer datos de la empresa
                    empresa.addCell("CUIT: " + rs.getString("cuit") + "\nNombre: " + rs.getString("nombre_empresa")
                            + "\nCelular: " + rs.getString("celular") + "\nDireccion: " + rs.getString("direccion"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            //CLIENTE
            //Consulta los datos del cliente y numero y fecha de la venta
            String consultaCliente = "SELECT c.nombre_cliente, c.celular, c.direccion, v.fecha FROM ventas v INNER JOIN clientes c ON v.id_cliente = c.id WHERE v.id = " + id_venta;
            try {
                con = cn.getConexion();
                ps = con.prepareStatement(consultaCliente);
                rs = ps.executeQuery();
                if (rs.next()) {
                    //Extraer datos del cliente y fecha de la venta
                    nomCliente = rs.getString("nombre_cliente");
                    celCliente = rs.getString("celular");
                    dirCliente = rs.getString("direccion");
                    fecha = rs.getString("fecha");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            //VENDEDOR
            //Mostrar vendedor y numero de venta en la segunda columna de la tabla superior
            empresa.addCell("N° Venta: " + id_venta + "\nVendedor: " + nomVendedor + "\nFecha: " + fecha);
            doc.add(empresa);
            doc.add(Chunk.NEWLINE);
            Paragraph titCliente = new Paragraph();
            titCliente.add("DATOS DEL CLIENTE");
            titCliente.setAlignment(Element.ALIGN_CENTER);
            doc.add(titCliente);
            doc.add(Chunk.NEWLINE);
            //Tabla cliente con 3 columnas
            PdfPTable cliente = new PdfPTable(3);
            empresa.setWidthPercentage(100);
            float[] tamanioCliente = new float[]{40f, 20f, 40f};
            cliente.setWidths(tamanioCliente);
            cliente.setHorizontalAlignment(Element.ALIGN_CENTER);
            cliente.getDefaultCell().setBorder(10);
            //Encabezado tabla
            PdfPCell nomCli = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell celCli = new PdfPCell(new Phrase("Celular", negrita));
            PdfPCell dirCli = new PdfPCell(new Phrase("Direccion", negrita));
            nomCli.setBorder(0);
            celCli.setBorder(0);
            dirCli.setBorder(0);
            //Background del encabezado
            nomCli.setBackgroundColor(BaseColor.DARK_GRAY);
            celCli.setBackgroundColor(BaseColor.DARK_GRAY);
            dirCli.setBackgroundColor(BaseColor.DARK_GRAY);
            //Agregar los encabezados del cliente
            cliente.addCell(nomCli);
            cliente.addCell(celCli);
            cliente.addCell(dirCli);
            //Agregar datos del cliente
            cliente.addCell(nomCliente);
            cliente.addCell(celCliente);
            cliente.addCell(dirCliente);
            doc.add(cliente);
            doc.add(Chunk.NEWLINE);
            //VENTAS
            Paragraph titProducto = new Paragraph();
            titProducto.add("DETALLES DE LA VENTA");
            titProducto.setAlignment(Element.ALIGN_CENTER);
            doc.add(titProducto);
            doc.add(Chunk.NEWLINE);
            //Tabla ventas con 4 columnas
            PdfPTable producto = new PdfPTable(4);
            producto.setWidthPercentage(100);
            float[] tamanioProducto = new float[]{40f, 20f, 20f, 20f};
            producto.setWidths(tamanioProducto);
            producto.setHorizontalAlignment(Element.ALIGN_LEFT);
            producto.getDefaultCell().setBorder(10);
            //Encabezado tabla
            PdfPCell nomProd = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell cantProd = new PdfPCell(new Phrase("Cantidad", negrita));
            PdfPCell precioProd = new PdfPCell(new Phrase("Precio", negrita));
            PdfPCell subtotProd = new PdfPCell(new Phrase("SubTotal", negrita));
            nomProd.setBorder(0);
            cantProd.setBorder(0);
            precioProd.setBorder(0);
            subtotProd.setBorder(0);
            //Background del encabezado
            nomProd.setBackgroundColor(BaseColor.DARK_GRAY);
            cantProd.setBackgroundColor(BaseColor.DARK_GRAY);
            precioProd.setBackgroundColor(BaseColor.DARK_GRAY);
            subtotProd.setBackgroundColor(BaseColor.DARK_GRAY);
            //Agregar los encabezados de los productos
            producto.addCell(nomProd);
            producto.addCell(cantProd);
            producto.addCell(precioProd);
            producto.addCell(subtotProd);
            //Consulta los datos del producto y la venta
            String consultaProducto = "SELECT d.precio, d.cantidad, d.sub_total, p.nombre_producto FROM ventas v INNER JOIN detalle_venta d ON v.id = d.id_venta INNER JOIN productos p ON d.id_producto = p.id WHERE v.id = " + id_venta;
            try {
                con = cn.getConexion();
                ps = con.prepareStatement(consultaProducto);
                rs = ps.executeQuery();
                //Mostrar todos los productos vendidos
                while (rs.next()) {
                    //Extraer los datos del producto vendido
                    producto.addCell(rs.getString("nombre_producto"));
                    producto.addCell(String.valueOf(rs.getInt("cantidad")));
                    producto.addCell(String.valueOf("$ " + rs.getDouble("precio")));
                    producto.addCell(String.valueOf("$ " + rs.getDouble("sub_total")));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            doc.add(producto);
            doc.add(Chunk.NEWLINE);
            //Consultar el Total de la venta
            String consultaTotal = "SELECT total FROM ventas ORDER BY id DESC LIMIT 1";
            try {
                con = cn.getConexion();
                ps = con.prepareStatement(consultaTotal);
                rs = ps.executeQuery();

                if (rs.next()) {
                    double totalVenta = rs.getDouble("total");
                    Paragraph total = new Paragraph();
                    total.add("Total a pagar: $ " + totalVenta);
                    total.setAlignment(Element.ALIGN_RIGHT);
                    doc.add(total);
                    doc.add(Chunk.NEWLINE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(salida);
        } catch (DocumentException | HeadlessException | IOException e) {
        }
    }

    //METODO PARA COMPRAS Y VENTAS
    public boolean actualizarStock(int cant, int id) {
        String sql = "UPDATE productos SET cantidad = ? WHERE id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setInt(2, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
}
