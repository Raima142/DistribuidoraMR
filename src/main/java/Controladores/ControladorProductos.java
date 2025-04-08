//Autor: MARIA ISABEL ROMANO
package Controladores;

import Modelos.*;
import Vistas.PanelPrincipal;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.*;

public class ControladorProductos implements ActionListener, MouseListener, KeyListener {

    private Productos pd;
    private ProductosDao pdDao;
    private PanelPrincipal vistas;
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp;

    public ControladorProductos(Productos pd, ProductosDao pdDao, PanelPrincipal vistas) {
        this.pd = pd;
        this.pdDao = pdDao;
        this.vistas = vistas;
        this.vistas.JLabelProductos.addMouseListener(this);
        this.vistas.btnModificarProd.addActionListener(this);
        this.vistas.btnRegistrarProd.addActionListener(this);
        this.vistas.btnNuevoProd.addActionListener(this);
        this.vistas.JMenuEliminarProducto.addActionListener(this);
        this.vistas.JMenuReingresarProducto.addActionListener(this);
        this.vistas.TablaProductos.addMouseListener(this);
        this.vistas.txtBuscarProd.addKeyListener(this);
        this.vistas.InfoProd.addMouseListener(this);
        this.vistas.txtCodigoNC.addKeyListener(this);
        this.vistas.txtCantidadNC.addKeyListener(this);
        this.vistas.txtPagarConNC.addKeyListener(this);
        this.vistas.btnComprar.addActionListener(this);
        this.vistas.JLabelNuevaCompra.addMouseListener(this);
        this.vistas.txtCodigoNV.addKeyListener(this);
        this.vistas.txtCantidadNV.addKeyListener(this);
        this.vistas.txtPagarConNV.addKeyListener(this);
        this.vistas.btnVender.addActionListener(this);
        this.vistas.JLabelNuevaVenta.addMouseListener(this);
        this.vistas.btnVerVentas.addActionListener(this);
        this.vistas.btnVerCompras.addActionListener(this);
        listarProductos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistas.btnRegistrarProd) {//Registrar producto a traves de la aplicacion
            if (vistas.txtCodigoProd.getText().equals("") || vistas.txtNombreProd.getText().equals("")
                    || vistas.txtPrecioCompraProd.getText().equals("") || vistas.txtPrecioVentaProd.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            } else {
                pd.setCodigo(vistas.txtCodigoProd.getText());
                pd.setNombre_producto(vistas.txtNombreProd.getText());
                pd.setPrecio_compra(Double.parseDouble(vistas.txtPrecioCompraProd.getText()));
                pd.setPrecio_venta(Double.parseDouble(vistas.txtPrecioVentaProd.getText()));
                //Llamar a la clase Combo para obtener los proveedores y categorias
                Combo itemProveedor = (Combo) vistas.cbxProveedorProd.getSelectedItem();
                Combo itemCategoria = (Combo) vistas.cbxCategoriaProd.getSelectedItem();
                //Luego obtenemos sus ids
                pd.setId_proveedor(itemProveedor.getId());
                pd.setId_categoria(itemCategoria.getId());
                String verificar = pdDao.registrar(pd);
                switch (verificar) {
                    case "codigo_existe":
                        JOptionPane.showMessageDialog(null, "Ya hay un producto con este codigo");
                        break;
                    case "nombre_existe":
                        JOptionPane.showMessageDialog(null, "Ya hay un producto con este nombre");
                        break;
                    case "registrado":
                        limpiarTabla();
                        listarProductos();
                        limpiarPanel();
                        JOptionPane.showMessageDialog(null, "Producto registrado con exito");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error al registrar Producto");
                        break;
                }
            }
        } else if (e.getSource() == vistas.btnModificarProd) {//Modificar Producto a traves de la aplicacion
            if (vistas.txtIdProd.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            } else {
                if (vistas.txtCodigoProd.getText().equals("") || vistas.txtNombreProd.getText().equals("")
                        || vistas.txtPrecioCompraProd.getText().equals("") || vistas.txtPrecioVentaProd.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                } else {
                    pd.setCodigo(vistas.txtCodigoProd.getText());
                    pd.setNombre_producto(vistas.txtNombreProd.getText());
                    pd.setPrecio_compra(Double.parseDouble(vistas.txtPrecioCompraProd.getText()));
                    pd.setPrecio_venta(Double.parseDouble(vistas.txtPrecioVentaProd.getText()));
                    //Llamar a la clase Combo para obtener los proveedores y categorias
                    Combo itemProveedor = (Combo) vistas.cbxProveedorProd.getSelectedItem();
                    Combo itemCategoria = (Combo) vistas.cbxCategoriaProd.getSelectedItem();
                    //Luego obtenemos sus ids
                    pd.setId_proveedor(itemProveedor.getId());
                    pd.setId_categoria(itemCategoria.getId());
                    pd.setId(Integer.parseInt(vistas.txtIdProd.getText()));
                    String verificar = pdDao.modificar(pd);
                switch (verificar) {
                    case "codigo_existe":
                        JOptionPane.showMessageDialog(null, "Ya hay un producto con este codigo");
                        break;
                    case "nombre_existe":
                        JOptionPane.showMessageDialog(null, "Ya hay un producto con este nombre");
                        break;
                    case "modificado":
                        limpiarTabla();
                        listarProductos();
                        limpiarPanel();
                        JOptionPane.showMessageDialog(null, "Producto modificado con exito");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error al modificar Producto");
                        break;
                }
                }
            }
        } else if (e.getSource() == vistas.JMenuEliminarProducto) {//Eliminar Producto a traves de la aplicacion
            if (vistas.txtIdProd.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
            } else {
                int id = Integer.parseInt(vistas.txtIdProd.getText());
                if (pdDao.accion("Inactivo", id)) {
                    limpiarTabla();
                    listarProductos();
                    limpiarPanel();
                    JOptionPane.showMessageDialog(null, "Producto eliminado con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar Producto");
                }
            }
        } else if (e.getSource() == vistas.JMenuReingresarProducto) {//Reingresar Producto a traves de la aplicacion
            if (vistas.txtIdProd.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para reingresar");
            } else {
                int id = Integer.parseInt(vistas.txtIdProd.getText());
                if (pdDao.accion("Activo", id)) {
                    limpiarTabla();
                    listarProductos();
                    limpiarPanel();
                    JOptionPane.showMessageDialog(null, "Producto reingresado con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al reingresar Producto");
                }
            }
        } else if (e.getSource() == vistas.btnComprar) {
            insertarCompra();
        } else if (e.getSource() == vistas.btnVender) {
            VentasDao vtDao = new VentasDao();
            String verificar = vtDao.verificarCaja(Integer.parseInt(vistas.txtIdUsuario.getText()));
            if(verificar.equals("caja_abierta")){
                insertarVenta();
            } else if (verificar.equals("caja_cerrada")){
                JOptionPane.showMessageDialog(null, "La caja está cerrada");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        } else if (e.getSource() == vistas.btnVerVentas) {
            vistas.JTabbedPaneDetMenus.setSelectedIndex(12);
        } else if (e.getSource() == vistas.btnVerCompras) {
            vistas.JTabbedPaneDetMenus.setSelectedIndex(10);
        } else {
            limpiarPanel();
            vistas.btnRegistrarProd.setEnabled(true);
        }
    }

    public void listarProductos() {
        Tabla color = new Tabla();
        vistas.TablaProductos.setDefaultRenderer(vistas.TablaProductos.getColumnClass(0), color);
        List<Productos> lista = pdDao.ListaProductos(vistas.txtBuscarProd.getText());
        modelo = (DefaultTableModel) vistas.TablaProductos.getModel();
        Object[] ob = new Object[6]; //6 columnas de la tabla
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getCodigo();
            ob[2] = lista.get(i).getNombre_producto();
            ob[3] = lista.get(i).getPrecio_venta();
            ob[4] = lista.get(i).getCantidad();
            ob[5] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        //Personalizar encabezado de la tabla Productos
        vistas.TablaProductos.setModel(modelo);
        JTableHeader header = vistas.TablaProductos.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.blue);
        header.setForeground(Color.white);
        //No permitir que el usuario pueda modificar el id
        vistas.txtIdProd.setEditable(false);
    }

    public void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void limpiarPanel() {
        vistas.txtIdProd.setText("");
        vistas.txtCodigoProd.setText("");
        vistas.txtNombreProd.setText("");
        vistas.txtPrecioCompraProd.setText("");
        vistas.txtPrecioVentaProd.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vistas.TablaProductos) {//Elementos de la tabla
            int fila = vistas.TablaProductos.rowAtPoint(e.getPoint());
            vistas.txtIdProd.setText(vistas.TablaProductos.getValueAt(fila, 0).toString());
            pd = pdDao.buscarProd(Integer.parseInt(vistas.txtIdProd.getText()));
            vistas.txtCodigoProd.setText(pd.getCodigo());
            vistas.txtNombreProd.setText(pd.getNombre_producto());
            vistas.txtPrecioCompraProd.setText("" + pd.getPrecio_compra());
            vistas.txtPrecioVentaProd.setText("" + pd.getPrecio_venta());
            vistas.cbxProveedorProd.setSelectedItem(new Combo(pd.getId_proveedor(), pd.getNombre_proveedor()));
            vistas.cbxCategoriaProd.setSelectedItem(new Combo(pd.getId_categoria(), pd.getNombre_categoria()));
            String estado = vistas.TablaProductos.getValueAt(fila, 5).toString();
            if (estado.equals("Inactivo")) {
                vistas.JMenuEliminarProducto.setVisible(false);
                vistas.JMenuReingresarProducto.setVisible(true);
            } else {
                vistas.JMenuEliminarProducto.setVisible(true);
                vistas.JMenuReingresarProducto.setVisible(false);
            }
            vistas.btnRegistrarProd.setEnabled(false);
        } else if (e.getSource() == vistas.JLabelProductos) {
            //Si se hace click sobre el menu Productos, se muestra el detalle del menu que se encuentra en el tab con indice 7
            vistas.JTabbedPaneDetMenus.setSelectedIndex(8);
            limpiarTabla();
            listarProductos();
        } else if (e.getSource() == vistas.JLabelNuevaCompra) {
            vistas.JTabbedPaneDetMenus.setSelectedIndex(9);
        } else if (e.getSource() == vistas.JLabelNuevaVenta) {
            vistas.JTabbedPaneDetMenus.setSelectedIndex(11);
        } else if (e.getSource() == vistas.InfoProd) {
            JOptionPane.showMessageDialog(null, "Ver, buscar, registrar, modificar, establecer precios, eliminar y reingresar un Producto.", "FUNCION DEL MENU", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == vistas.txtCodigoNC) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                String cod = vistas.txtCodigoNC.getText();
                buscarProducto(vistas.txtCodigoNC, cod, vistas.txtIdNC, vistas.txtProductoNC, vistas.txtPrecioNC, vistas.txtCantidadNC, 0);
                if (vistas.txtCodigoNC.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Ingrese el codigo");
                }
            }
        } else if (e.getSource() == vistas.txtCodigoNV) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                String cod = vistas.txtCodigoNV.getText();
                buscarProducto(vistas.txtCodigoNV, cod, vistas.txtIdNV, vistas.txtProductoNV, vistas.txtPrecioNV, vistas.txtCantidadNV, 1);
                if (vistas.txtCodigoNV.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Ingrese el codigo");
                }
            }
        } else if (e.getSource() == vistas.txtCantidadNC) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (vistas.txtCantidadNC.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Ingrese una cantidad");
                } else {
                    int cant = Integer.parseInt(vistas.txtCantidadNC.getText());
                    String nombprod = vistas.txtProductoNC.getText();
                    double preciocompra = Double.parseDouble(vistas.txtPrecioNC.getText());
                    int id = Integer.parseInt(vistas.txtIdNC.getText());
                    agregarTemp(cant, nombprod, preciocompra, id, vistas.TablaNuevaCompra, vistas.txtCodigoNC);
                    limpiarCompras();
                    calcularTotal(vistas.TablaNuevaCompra, vistas.JLabelTotalAPagarCompra);
                }
            }
        } else if (e.getSource() == vistas.txtCantidadNV) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (vistas.txtCantidadNV.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Ingrese una cantidad");
                } else {
                    int cant = Integer.parseInt(vistas.txtCantidadNV.getText());
                    int stock = Integer.parseInt(vistas.txtStockNV.getText());
                    if (cant > stock) {
                        JOptionPane.showMessageDialog(null, "Stock Insuficiente");
                    } else {
                        String nombprod = vistas.txtProductoNV.getText();
                        double precioventa = Double.parseDouble(vistas.txtPrecioNV.getText());
                        int id = Integer.parseInt(vistas.txtIdNV.getText());
                        agregarTemp(cant, nombprod, precioventa, id, vistas.TablaNuevaVenta, vistas.txtCodigoNV);
                        limpiarVentas();
                        calcularTotal(vistas.TablaNuevaVenta, vistas.JLabelTotalAPagarVenta);
                    }
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //BUSCAR PRODUCTO MENU
        if (e.getSource() == vistas.txtBuscarProd) {
            limpiarTabla();
            listarProductos();
        } //COMPRAS
        else if (e.getSource() == vistas.txtCantidadNC) {
            int cantidad;
            double precio;
            if (vistas.txtCantidadNC.getText().equals("")) {
                cantidad = 1;
                precio = Double.parseDouble(vistas.txtPrecioNC.getText());
                vistas.txtTotalNC.setText("" + precio);
            } else {
                cantidad = Integer.parseInt(vistas.txtCantidadNC.getText());
                precio = Double.parseDouble(vistas.txtPrecioNC.getText());
                vistas.txtTotalNC.setText("" + cantidad * precio);
            }
        } else if (e.getSource() == vistas.txtPagarConNC) {
            int pagar;
            if (vistas.txtPagarConNC.getText().equals("")) {
                vistas.txtVueltoCompra.setText("");
            } else {
                pagar = Integer.parseInt(vistas.txtPagarConNC.getText());
                double total = Double.parseDouble(vistas.JLabelTotalAPagarCompra.getText());
                vistas.txtVueltoCompra.setText("" + (pagar - total));
            }
        } //VENTAS
        else if (e.getSource() == vistas.txtCantidadNV) {
            int cantidad;
            double precio;
            if (vistas.txtCantidadNV.getText().equals("")) {
                cantidad = 1;
                precio = Double.parseDouble(vistas.txtPrecioNV.getText());
                vistas.txtTotalNV.setText("" + precio);
            } else {
                cantidad = Integer.parseInt(vistas.txtCantidadNV.getText());
                precio = Double.parseDouble(vistas.txtPrecioNV.getText());
                vistas.txtTotalNV.setText("" + cantidad * precio);
            }
        } else if (e.getSource() == vistas.txtPagarConNV) {
            int pagar;
            if (vistas.txtPagarConNV.getText().equals("")) {
                vistas.txtVueltoVenta.setText("");
            } else {
                pagar = Integer.parseInt(vistas.txtPagarConNV.getText());
                double total = Double.parseDouble(vistas.JLabelTotalAPagarVenta.getText());
                vistas.txtVueltoVenta.setText("" + (pagar - total));
            }
        }
    }

    private void limpiarCompras() {
        vistas.txtCodigoNC.setText((""));
        vistas.txtIdNC.setText((""));
        vistas.txtProductoNC.setText((""));
        vistas.txtCantidadNC.setText((""));
        vistas.txtPrecioNC.setText((""));
        vistas.txtTotalNC.setText((""));
    }

    private void limpiarVentas() {
        vistas.txtCodigoNV.setText((""));
        vistas.txtIdNV.setText((""));
        vistas.txtProductoNV.setText((""));
        vistas.txtCantidadNV.setText((""));
        vistas.txtPrecioNV.setText((""));
        vistas.txtTotalNV.setText((""));
        vistas.txtStockNV.setText((""));
    }

    private void calcularTotal(JTable tabla, JLabel totalPagar) {
        double total = 0.00;
        int numFila = tabla.getRowCount();
        //Recorrer todas las filas y sumar el total
        for (int i = 0; i < numFila; i++) {
            total = total + Double.parseDouble(String.valueOf(tabla.getValueAt(i, 4)));
        }
        totalPagar.setText("" + total);
    }

    private void insertarCompra() {
        Combo id_p = (Combo) vistas.cbxProveedorNC.getSelectedItem();
        int id_proveedor = id_p.getId();
        int id_user = Integer.parseInt(vistas.txtIdUsuario.getText());
        String nomComprador = vistas.txtNomUsuario.getText();
        String total = vistas.JLabelTotalAPagarCompra.getText();
        if (pdDao.registrarCompra(id_proveedor, total, id_user)) {
            int id_compra = pdDao.id_compra();
            for (int i = 0; i < vistas.TablaNuevaCompra.getRowCount(); i++) {
                double precio = Double.parseDouble(vistas.TablaNuevaCompra.getValueAt(i, 3).toString());
                int cantidad = Integer.parseInt(vistas.TablaNuevaCompra.getValueAt(i, 2).toString());
                int id = Integer.parseInt(vistas.TablaNuevaCompra.getValueAt(i, 0).toString());
                double sub_total = precio * cantidad;
                pdDao.registrarDetalleCompra(id_compra, id, precio, cantidad, sub_total);
                pd = pdDao.buscarId(id);//obtener la cantidad
                int stockActual = pd.getCantidad() + cantidad;//ver la cantidad y sumar al stock la cantidad comprada
                pdDao.actualizarStock(stockActual, id);
            }
            limpiarDetalleTabla();
            JOptionPane.showMessageDialog(null, "Compra realizada");
            pdDao.generarFacturaCompra(id_compra, nomComprador);
            vistas.txtPagarConNC.setText("");
            vistas.txtVueltoCompra.setText("");
            vistas.JLabelTotalAPagarCompra.setText("--------");
        }
    }

    private void insertarVenta() {
        Combo id_c = (Combo) vistas.cbxClienteNV.getSelectedItem();
        int id_cliente = id_c.getId();
        int id_user = Integer.parseInt(vistas.txtIdUsuario.getText());
        String nomVendedor = vistas.txtNomUsuario.getText();
        String total = vistas.JLabelTotalAPagarVenta.getText();
        if (pdDao.registrarVenta(id_cliente, total, id_user) && pdDao.ventaRegistroTemporal(total, id_user)) { 
            int id_venta = pdDao.id_venta();
            for (int i = 0; i < vistas.TablaNuevaVenta.getRowCount(); i++) {
                double precio = Double.parseDouble(vistas.TablaNuevaVenta.getValueAt(i, 3).toString());
                int cantidad = Integer.parseInt(vistas.TablaNuevaVenta.getValueAt(i, 2).toString());
                int id = Integer.parseInt(vistas.TablaNuevaVenta.getValueAt(i, 0).toString());
                double sub_total = precio * cantidad;
                pdDao.registrarDetalleVenta(id_venta, id, precio, cantidad, sub_total);
                pd = pdDao.buscarId(id);//obtener la cantidad
                int stockActual = pd.getCantidad() - cantidad;//ver la cantidad y restar al stock la cantidad vendida
                pdDao.actualizarStock(stockActual, id);
            }
            limpiarDetalleTabla();
            JOptionPane.showMessageDialog(null, "Venta realizada");
            pdDao.generarFacturaVenta(id_venta, nomVendedor);
            vistas.txtPagarConNV.setText("");
            vistas.txtVueltoVenta.setText("");
            vistas.JLabelTotalAPagarVenta.setText("--------");
        }
    }
  
    public void limpiarDetalleTabla() {
        for (int i = 0; i < tmp.getRowCount(); i++) {
            tmp.removeRow(i);
            i = i - 1;
        }
    }

    private void agregarTemp(int cant, String nombProd, double precio, int id, JTable tabla, JTextField codigo) {
        if (cant > 0) {
            tmp = (DefaultTableModel) tabla.getModel();
            ArrayList lista = new ArrayList();
            int item = 1;
            lista.add(item);
            lista.add(id);
            lista.add(nombProd);
            lista.add(cant);
            lista.add(precio);
            lista.add(cant * precio);
            Object[] obj = new Object[5];
            obj[0] = lista.get(1);
            obj[1] = lista.get(2);
            obj[2] = lista.get(3);
            obj[3] = lista.get(4);
            obj[4] = lista.get(5);
            tmp.addRow(obj);
            tabla.setModel(tmp);
            codigo.requestFocus();
        }
    }

    //Buscar productos para la compra y venta
    private void buscarProducto(JTextField campo, String cod, JTextField id, JTextField prod, JTextField precio, JTextField cant, int accion) {
        if (campo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el codigo");
        } else {
            pd = pdDao.buscarCodigo(cod);
            if (pd.getId() > 0) {
                id.setText("" + pd.getId());
                prod.setText(pd.getNombre_producto());
                if (accion == 0) {
                    precio.setText("" + pd.getPrecio_compra());
                } else {
                    precio.setText("" + pd.getPrecio_venta());
                    vistas.txtStockNV.setText("" + pd.getCantidad());
                }
                cant.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "El producto no exite o esta inactivo");
            }
        }
    }
}
