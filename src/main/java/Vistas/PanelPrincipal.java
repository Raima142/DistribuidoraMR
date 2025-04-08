//Autor: MARIA ISABEL ROMANO
package Vistas;

import Controladores.*;
import Modelos.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelPrincipal extends javax.swing.JFrame implements ActionListener {

    Configuracion conf = new Configuracion();
    ConfiguracionDao confDao = new ConfiguracionDao();
    Usuarios us = new Usuarios();
    UsuariosDao usDao = new UsuariosDao();
    Cajas cj = new Cajas();
    CajasDao cjDao = new CajasDao();
    Clientes cl = new Clientes();
    ClientesDao clDao = new ClientesDao();
    Proveedores pr = new Proveedores();
    ProveedoresDao prDao = new ProveedoresDao();
    Categorias ca = new Categorias();
    CategoriasDao caDao = new CategoriasDao();
    Productos pd = new Productos();
    ProductosDao pdDao = new ProductosDao();
    Compras cp = new Compras();
    ComprasDao cpDao = new ComprasDao();
    Ventas vt = new Ventas();
    VentasDao vtDao = new VentasDao();

    public PanelPrincipal() {
    }

    public PanelPrincipal(int id, String nombre, String rol) {
        initComponents();
        btnActualizarPanel.addActionListener(this);
        //Conectar el ControladorPanelPrincipal al PanelPrincipal
        ControladorPanelPrincipal panel = new ControladorPanelPrincipal(this);
        //Conectar el ControladorConfiguracion al PanelPrincipal
        ControladorConfiguracion configuracion = new ControladorConfiguracion(conf, confDao, this);
        //Conectar el ControladorUsuarios al PanelPrincipal
        ControladorUsuarios users = new ControladorUsuarios(us, usDao, this);
        //Conectar el ControladorCajas al PanelPrincipal
        ControladorCajas caja = new ControladorCajas(cj, cjDao, this);
        //Conectar el ControladorClientes al PanelPrincipal
        ControladorClientes cliente = new ControladorClientes(cl, clDao, this);
        //Conectar el ControladorProveedores al PanelPrincipal
        ControladorProveedores proveedor = new ControladorProveedores(pr, prDao, this);
        //Conectar el ControladorCategorias al PanelPrincipal
        ControladorCategorias categoria = new ControladorCategorias(ca, caDao, this);
        //Conectar el ControladorProductos al PanelPrincipal
        ControladorProductos producto = new ControladorProductos(pd, pdDao, this);
        //Conectar el ControladorCompras al PanelPrincipal
        ControladorCompras compra = new ControladorCompras(cp, cpDao, this);
        //Conectar el ControladorVentas al PanelPrincipal
        ControladorVentas venta = new ControladorVentas(vt, vtDao, this);
        txtIdUsuario.setText("" + id);
        txtNomUsuario.setText("" + nombre);
        txtRolUsuario.setText("" + rol);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnActualizarPanel) {//Refrescar Panel
            this.dispose();
            int id = Integer.parseInt(txtIdUsuario.getText());
            String nombre = txtNomUsuario.getText();
            String rol = txtRolUsuario.getText();
            PanelPrincipal ppA = new PanelPrincipal(id, nombre, rol);
            ppA.setVisible(true);
            //LIMITAR PRIVILEGIOS SI NO ES ADMINISTRADOR
            if (ppA.txtRolUsuario.getText().equals("Usuario")) {
                ppA.LabelAdministrador.setVisible(false);
                ppA.LabelUsuario.setVisible(true);
                //CONFIGURACION
                ppA.btnModificarEmpresa.setEnabled(false);
                //USUARIOS
                ppA.btnModificarUser.setEnabled(false);
                //CAJA
                ppA.btnModificarCaja.setEnabled(false);
                //CLIENTES
                ppA.btnModificarCli.setEnabled(false);
                //PROVEEDORES
                ppA.btnModificarProv.setEnabled(false);
                //CATEGORIAS
                ppA.btnModificarCat.setEnabled(false);
                //PRODUCTOS
                ppA.btnModificarProd.setEnabled(false);
            } else if (ppA.txtRolUsuario.getText().equals("Administrador")) {
                ppA.LabelAdministrador.setVisible(true);
                ppA.LabelUsuario.setVisible(false);
            }
            ppA.JTabbedPaneDetMenus.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPopupUsuarios = new javax.swing.JPopupMenu();
        JMenuEliminarUsuario = new javax.swing.JMenuItem();
        JMenuReingresarUsuario = new javax.swing.JMenuItem();
        JPopupClientes = new javax.swing.JPopupMenu();
        JMenuEliminarCliente = new javax.swing.JMenuItem();
        JMenuReingresarCliente = new javax.swing.JMenuItem();
        JPopupProveedores = new javax.swing.JPopupMenu();
        JMenuEliminarProveedor = new javax.swing.JMenuItem();
        JMenuReingresarProveedor = new javax.swing.JMenuItem();
        JPopupCategorias = new javax.swing.JPopupMenu();
        JMenuEliminarCategoria = new javax.swing.JMenuItem();
        JMenuReingresarCategoria = new javax.swing.JMenuItem();
        JPopupProductos = new javax.swing.JPopupMenu();
        JMenuEliminarProducto = new javax.swing.JMenuItem();
        JMenuReingresarProducto = new javax.swing.JMenuItem();
        JPopupCajas = new javax.swing.JPopupMenu();
        JMenuEliminarCaja = new javax.swing.JMenuItem();
        JMenuReingresarCaja = new javax.swing.JMenuItem();
        JPanelAdmin = new javax.swing.JPanel();
        JTabbedPaneDetMenus = new javax.swing.JTabbedPane();
        jPanelBienvenido = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jPanelConfiguracion = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btnModificarEmpresa = new javax.swing.JButton();
        txtNombreEmpresa = new javax.swing.JTextField();
        txtCelularEmpresa = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtDireccionEmpresa = new javax.swing.JTextPane();
        jLabel23 = new javax.swing.JLabel();
        txtIdEmpresa = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtCuitEmpresa = new javax.swing.JTextField();
        InfoConf = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaEmpresa = new javax.swing.JTable();
        jPanelUsuarios = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnModificarUser = new javax.swing.JButton();
        btnNuevoUser = new javax.swing.JButton();
        btnRegistrarUser = new javax.swing.JButton();
        txtUsuarioUser = new javax.swing.JTextField();
        txtNombreUser = new javax.swing.JTextField();
        txtClaveUser = new javax.swing.JPasswordField();
        jLabel20 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cbxRolUser = new javax.swing.JComboBox<>();
        cbxCajaUser = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        txtIdUser = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        txtBuscarUser = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        TablaUsuarios = new javax.swing.JTable();
        jPanelCaja = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        btnModificarCaja = new javax.swing.JButton();
        btnNuevoCaja = new javax.swing.JButton();
        btnRegistrarCaja = new javax.swing.JButton();
        txtNombreCaja = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        txtBuscarCaja = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        txtIdCaja = new javax.swing.JTextField();
        InfoCaja = new javax.swing.JLabel();
        btnVerAC = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        TablaCajas = new javax.swing.JTable();
        jPanelCaja1 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        btnCerrarCajaAC = new javax.swing.JButton();
        btnNuevaCajaAC = new javax.swing.JButton();
        btnAbrirCajaAC = new javax.swing.JButton();
        txtMontoInicialAC = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        txtBuscarCajaAC = new javax.swing.JTextField();
        InfoAC = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        TablaAperturaYCierre = new javax.swing.JTable();
        jPanelClientes = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnModificarCli = new javax.swing.JButton();
        btnNuevoCli = new javax.swing.JButton();
        btnRegistrarCli = new javax.swing.JButton();
        txtNombreCli = new javax.swing.JTextField();
        txtCelularCli = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDireccionCli = new javax.swing.JTextPane();
        jLabel47 = new javax.swing.JLabel();
        txtBuscarCli = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        txtIdCli = new javax.swing.JTextField();
        InfoCli = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        TablaClientes = new javax.swing.JTable();
        jPanelProveedores = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnModificarProv = new javax.swing.JButton();
        btnNuevoProv = new javax.swing.JButton();
        btnRegistrarProv = new javax.swing.JButton();
        txtCuitProv = new javax.swing.JTextField();
        txtCelularProv = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDireccionProv = new javax.swing.JTextPane();
        jLabel14 = new javax.swing.JLabel();
        txtNombreProv = new javax.swing.JTextField();
        txtBuscarProv = new javax.swing.JTextField();
        txtIdProv = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        InfoProv = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaProveedores = new javax.swing.JTable();
        jPanelCategorias = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        btnModificarCat = new javax.swing.JButton();
        btnNuevoCat = new javax.swing.JButton();
        btnRegistrarCat = new javax.swing.JButton();
        txtNombreCat = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        txtBuscarCat = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        txtIdCat = new javax.swing.JTextField();
        InfoCat = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        TablaCategorias = new javax.swing.JTable();
        jPanelProductos = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPrecioVentaProd = new javax.swing.JTextField();
        cbxCategoriaProd = new javax.swing.JComboBox<>();
        btnModificarProd = new javax.swing.JButton();
        btnNuevoProd = new javax.swing.JButton();
        txtCodigoProd = new javax.swing.JTextField();
        txtNombreProd = new javax.swing.JTextField();
        txtPrecioCompraProd = new javax.swing.JTextField();
        cbxProveedorProd = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        txtBuscarProd = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        txtIdProd = new javax.swing.JTextField();
        btnRegistrarProd = new javax.swing.JButton();
        InfoProd = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProductos = new javax.swing.JTable();
        jPanelNC = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        txtCodigoNC = new javax.swing.JTextField();
        txtProductoNC = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtCantidadNC = new javax.swing.JTextField();
        txtPrecioNC = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtIdNC = new javax.swing.JTextField();
        btnComprar = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        TablaNuevaCompra = new javax.swing.JTable();
        jLabel42 = new javax.swing.JLabel();
        cbxProveedorNC = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        txtPagarConNC = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        txtVueltoCompra = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        JLabelTotalAPagarCompra = new javax.swing.JLabel();
        txtTotalNC = new javax.swing.JTextField();
        btnVerCompras = new javax.swing.JButton();
        jPanelCompras = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        TablaCompras = new javax.swing.JTable();
        txtBuscarCompra = new javax.swing.JTextField();
        btnHistorialCompras = new javax.swing.JButton();
        txtIdCompra = new javax.swing.JTextField();
        jPanelNV = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        TablaNuevaVenta = new javax.swing.JTable();
        txtVueltoVenta = new javax.swing.JTextField();
        txtCodigoNV = new javax.swing.JTextField();
        txtProductoNV = new javax.swing.JTextField();
        txtCantidadNV = new javax.swing.JTextField();
        txtPrecioNV = new javax.swing.JTextField();
        txtTotalNV = new javax.swing.JTextField();
        btnVerVentas = new javax.swing.JButton();
        txtIdNV = new javax.swing.JTextField();
        txtPagarConNV = new javax.swing.JTextField();
        cbxClienteNV = new javax.swing.JComboBox<>();
        JLabelTotalAPagarVenta = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtStockNV = new javax.swing.JTextField();
        btnVender = new javax.swing.JButton();
        jPanelVentas = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        TablaVentas = new javax.swing.JTable();
        btnHistorialVentas = new javax.swing.JButton();
        txtIdVenta = new javax.swing.JTextField();
        txtBuscarVenta = new javax.swing.JTextField();
        JPanelEmpresa = new javax.swing.JPanel();
        btnMinimizarPanel = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        btnSalirDelPanel = new javax.swing.JButton();
        btnActualizarPanel = new javax.swing.JButton();
        LabelAdministrador = new javax.swing.JLabel();
        LabelUsuario = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        txtNomUsuario = new javax.swing.JTextField();
        txtRolUsuario = new javax.swing.JTextField();
        JPanelMenusVertical = new javax.swing.JPanel();
        JPanelNuevaVenta = new javax.swing.JPanel();
        JLabelNuevaVenta = new javax.swing.JLabel();
        JPanelNuevaCompra = new javax.swing.JPanel();
        JLabelNuevaCompra = new javax.swing.JLabel();
        JPanelProductos = new javax.swing.JPanel();
        JLabelProductos = new javax.swing.JLabel();
        JPanelCategorias = new javax.swing.JPanel();
        JLabelCategorias = new javax.swing.JLabel();
        JPanelProveedores = new javax.swing.JPanel();
        JLabelProveedores = new javax.swing.JLabel();
        JPanelClientes = new javax.swing.JPanel();
        JLabelClientes = new javax.swing.JLabel();
        JPanelCaja = new javax.swing.JPanel();
        JLabelCaja = new javax.swing.JLabel();
        JPanelUsuarios = new javax.swing.JPanel();
        JLabelUsuarios = new javax.swing.JLabel();
        JPanelConfiguracion = new javax.swing.JPanel();
        JLabelConfiguracion = new javax.swing.JLabel();

        JMenuEliminarUsuario.setText("Eliminar");
        JMenuEliminarUsuario.setToolTipText("");
        JMenuEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuEliminarUsuarioActionPerformed(evt);
            }
        });
        JPopupUsuarios.add(JMenuEliminarUsuario);

        JMenuReingresarUsuario.setText("Reingresar");
        JPopupUsuarios.add(JMenuReingresarUsuario);

        JMenuEliminarCliente.setText("Eliminar");
        JPopupClientes.add(JMenuEliminarCliente);

        JMenuReingresarCliente.setText("Reingresar");
        JPopupClientes.add(JMenuReingresarCliente);

        JMenuEliminarProveedor.setText("Eliminar");
        JPopupProveedores.add(JMenuEliminarProveedor);

        JMenuReingresarProveedor.setText("Reingresar");
        JMenuReingresarProveedor.setToolTipText("");
        JPopupProveedores.add(JMenuReingresarProveedor);

        JMenuEliminarCategoria.setText("Eliminar");
        JPopupCategorias.add(JMenuEliminarCategoria);

        JMenuReingresarCategoria.setText("Reingresar");
        JPopupCategorias.add(JMenuReingresarCategoria);

        JMenuEliminarProducto.setText("Eliminar");
        JPopupProductos.add(JMenuEliminarProducto);

        JMenuReingresarProducto.setText("Reingresar");
        JPopupProductos.add(JMenuReingresarProducto);

        JMenuEliminarCaja.setText("Eliminar");
        JPopupCajas.add(JMenuEliminarCaja);

        JMenuReingresarCaja.setText("Reingresar");
        JMenuReingresarCaja.setToolTipText("");
        JPopupCajas.add(JMenuReingresarCaja);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPanelAdmin.setBackground(java.awt.SystemColor.controlHighlight);
        JPanelAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTabbedPaneDetMenus.setBackground(java.awt.SystemColor.controlHighlight);
        JTabbedPaneDetMenus.setForeground(new java.awt.Color(0, 0, 0));
        JTabbedPaneDetMenus.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        jPanelBienvenido.setBackground(java.awt.SystemColor.controlLtHighlight);
        jPanelBienvenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel51.setFont(new java.awt.Font("Ubuntu", 1, 60)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("¡BIENVENIDO!");
        jPanelBienvenido.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, -1, -1));

        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bienvenido.jpg"))); // NOI18N
        jLabel52.setToolTipText("");
        jPanelBienvenido.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, -60, -1, -1));

        JTabbedPaneDetMenus.addTab("Bienvenido", jPanelBienvenido);

        jPanelConfiguracion.setBackground(new java.awt.Color(153, 153, 153));
        jPanelConfiguracion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel20.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la Empresa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Nombre");
        jPanel20.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Celular");
        jPanel20.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Direccion");
        jPanel20.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        btnModificarEmpresa.setBackground(java.awt.SystemColor.controlHighlight);
        btnModificarEmpresa.setForeground(new java.awt.Color(0, 0, 0));
        btnModificarEmpresa.setText("Modificar");
        btnModificarEmpresa.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnModificarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarEmpresaActionPerformed(evt);
            }
        });
        jPanel20.add(btnModificarEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, 100, 30));

        txtNombreEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreEmpresa.setForeground(new java.awt.Color(0, 0, 0));
        jPanel20.add(txtNombreEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 220, -1));

        txtCelularEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        txtCelularEmpresa.setForeground(new java.awt.Color(0, 0, 0));
        jPanel20.add(txtCelularEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 220, -1));

        txtDireccionEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        txtDireccionEmpresa.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane6.setViewportView(txtDireccionEmpresa);

        jPanel20.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 220, 140));

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Id");
        jPanel20.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        txtIdEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        txtIdEmpresa.setForeground(new java.awt.Color(0, 0, 0));
        jPanel20.add(txtIdEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 50, -1));

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("CUIT");
        jPanel20.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        txtCuitEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        txtCuitEmpresa.setForeground(new java.awt.Color(0, 0, 0));
        jPanel20.add(txtCuitEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 220, -1));

        InfoConf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/informacion.png"))); // NOI18N
        InfoConf.setToolTipText("");
        InfoConf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel20.add(InfoConf, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 590, -1, -1));

        jPanelConfiguracion.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 380, 650));

        TablaEmpresa.setBackground(java.awt.SystemColor.controlHighlight);
        TablaEmpresa.setForeground(new java.awt.Color(0, 0, 0));
        TablaEmpresa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "CUIT", "Nombre", "Celular", "Direccion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaEmpresa.setGridColor(java.awt.SystemColor.controlHighlight);
        TablaEmpresa.setSelectionBackground(java.awt.SystemColor.controlHighlight);
        TablaEmpresa.setSelectionForeground(java.awt.SystemColor.controlHighlight);
        jScrollPane2.setViewportView(TablaEmpresa);

        jPanelConfiguracion.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 1100, 650));

        JTabbedPaneDetMenus.addTab("Configuracion", jPanelConfiguracion);

        jPanelUsuarios.setBackground(new java.awt.Color(102, 102, 255));
        jPanelUsuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo Usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Usuario");
        jPanel18.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Contraseña");
        jPanel18.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        btnModificarUser.setBackground(java.awt.SystemColor.controlHighlight);
        btnModificarUser.setForeground(new java.awt.Color(0, 0, 0));
        btnModificarUser.setText("Modificar");
        btnModificarUser.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnModificarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarUserActionPerformed(evt);
            }
        });
        jPanel18.add(btnModificarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 100, 30));

        btnNuevoUser.setBackground(java.awt.SystemColor.controlHighlight);
        btnNuevoUser.setForeground(new java.awt.Color(0, 0, 0));
        btnNuevoUser.setText("Nuevo");
        btnNuevoUser.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel18.add(btnNuevoUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 100, 30));

        btnRegistrarUser.setBackground(java.awt.SystemColor.controlHighlight);
        btnRegistrarUser.setForeground(new java.awt.Color(0, 0, 0));
        btnRegistrarUser.setText("Registrar");
        btnRegistrarUser.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel18.add(btnRegistrarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, 100, 30));

        txtUsuarioUser.setBackground(new java.awt.Color(255, 255, 255));
        txtUsuarioUser.setForeground(new java.awt.Color(0, 0, 0));
        jPanel18.add(txtUsuarioUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 220, -1));

        txtNombreUser.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreUser.setForeground(new java.awt.Color(0, 0, 0));
        jPanel18.add(txtNombreUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 220, -1));

        txtClaveUser.setBackground(new java.awt.Color(255, 255, 255));
        txtClaveUser.setForeground(new java.awt.Color(0, 0, 0));
        txtClaveUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveUserActionPerformed(evt);
            }
        });
        jPanel18.add(txtClaveUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 220, -1));

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Nombre");
        jPanel18.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Rol");
        jPanel18.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, -1, -1));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Caja");
        jPanel18.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, -1));

        cbxRolUser.setBackground(new java.awt.Color(255, 255, 255));
        cbxRolUser.setForeground(new java.awt.Color(0, 0, 0));
        cbxRolUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elija una opcion", "Administrador", "Usuario" }));
        cbxRolUser.setToolTipText("");
        jPanel18.add(cbxRolUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 460, 220, -1));

        cbxCajaUser.setBackground(new java.awt.Color(255, 255, 255));
        cbxCajaUser.setEditable(true);
        cbxCajaUser.setForeground(new java.awt.Color(0, 0, 0));
        cbxCajaUser.setMaximumRowCount(3);
        cbxCajaUser.setToolTipText("");
        cbxCajaUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCajaUserActionPerformed(evt);
            }
        });
        jPanel18.add(cbxCajaUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 220, -1));

        jLabel41.setBackground(new java.awt.Color(255, 255, 255));
        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Id");
        jPanel18.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        txtIdUser.setBackground(new java.awt.Color(255, 255, 255));
        txtIdUser.setForeground(new java.awt.Color(0, 0, 0));
        jPanel18.add(txtIdUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 50, -1));

        jLabel46.setBackground(new java.awt.Color(255, 255, 255));
        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Buscar");
        jPanel18.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        txtBuscarUser.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarUser.setForeground(new java.awt.Color(0, 0, 0));
        jPanel18.add(txtBuscarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 220, -1));

        jPanelUsuarios.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 380, 650));

        TablaUsuarios.setBackground(java.awt.SystemColor.controlHighlight);
        TablaUsuarios.setForeground(new java.awt.Color(0, 0, 0));
        TablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Usuario", "Nombre", "Id Caja", "Tipo Caja", "Rol", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaUsuarios.setComponentPopupMenu(JPopupUsuarios);
        TablaUsuarios.setGridColor(java.awt.SystemColor.controlHighlight);
        TablaUsuarios.setSelectionBackground(java.awt.SystemColor.controlHighlight);
        TablaUsuarios.setSelectionForeground(java.awt.SystemColor.controlHighlight);
        jScrollPane7.setViewportView(TablaUsuarios);
        if (TablaUsuarios.getColumnModel().getColumnCount() > 0) {
            TablaUsuarios.getColumnModel().getColumn(4).setResizable(false);
            TablaUsuarios.getColumnModel().getColumn(4).setPreferredWidth(0);
        }

        jPanelUsuarios.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 1110, 650));

        JTabbedPaneDetMenus.addTab("Usuarios", jPanelUsuarios);

        jPanelCaja.setBackground(new java.awt.Color(255, 102, 0));
        jPanelCaja.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nueva Caja", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnModificarCaja.setBackground(java.awt.SystemColor.controlHighlight);
        btnModificarCaja.setForeground(new java.awt.Color(0, 0, 0));
        btnModificarCaja.setText("Modificar");
        btnModificarCaja.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnModificarCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCajaActionPerformed(evt);
            }
        });
        jPanel21.add(btnModificarCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, 100, 30));

        btnNuevoCaja.setBackground(java.awt.SystemColor.controlHighlight);
        btnNuevoCaja.setForeground(new java.awt.Color(0, 0, 0));
        btnNuevoCaja.setText("Nuevo");
        btnNuevoCaja.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel21.add(btnNuevoCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 100, 30));

        btnRegistrarCaja.setBackground(java.awt.SystemColor.controlHighlight);
        btnRegistrarCaja.setForeground(new java.awt.Color(0, 0, 0));
        btnRegistrarCaja.setText("Registrar");
        btnRegistrarCaja.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel21.add(btnRegistrarCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, 100, 30));

        txtNombreCaja.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreCaja.setForeground(new java.awt.Color(0, 0, 0));
        jPanel21.add(txtNombreCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 220, -1));

        jLabel57.setBackground(new java.awt.Color(255, 255, 255));
        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Nombre");
        jPanel21.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        jLabel58.setBackground(new java.awt.Color(255, 255, 255));
        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Buscar");
        jPanel21.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        txtBuscarCaja.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarCaja.setForeground(new java.awt.Color(0, 0, 0));
        jPanel21.add(txtBuscarCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 220, -1));

        jLabel59.setBackground(new java.awt.Color(255, 255, 255));
        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Id");
        jPanel21.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        txtIdCaja.setBackground(new java.awt.Color(255, 255, 255));
        txtIdCaja.setForeground(new java.awt.Color(0, 0, 0));
        jPanel21.add(txtIdCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, 50, -1));

        InfoCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/informacion.png"))); // NOI18N
        InfoCaja.setToolTipText("");
        InfoCaja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel21.add(InfoCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 580, -1, -1));

        btnVerAC.setBackground(java.awt.SystemColor.controlHighlight);
        btnVerAC.setForeground(new java.awt.Color(0, 0, 0));
        btnVerAC.setText("Apertura");
        btnVerAC.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel21.add(btnVerAC, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 470, 100, 30));

        jPanelCaja.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 380, 650));

        TablaCajas.setBackground(java.awt.SystemColor.controlHighlight);
        TablaCajas.setForeground(new java.awt.Color(0, 0, 0));
        TablaCajas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Estado"
            }
        ));
        TablaCajas.setComponentPopupMenu(JPopupCajas);
        TablaCajas.setGridColor(java.awt.SystemColor.controlHighlight);
        TablaCajas.setSelectionBackground(java.awt.SystemColor.controlHighlight);
        TablaCajas.setSelectionForeground(java.awt.SystemColor.controlHighlight);
        jScrollPane14.setViewportView(TablaCajas);

        jPanelCaja.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 1110, 650));

        JTabbedPaneDetMenus.addTab("Caja", jPanelCaja);

        jPanelCaja1.setBackground(new java.awt.Color(255, 102, 102));
        jPanelCaja1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel22.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Apertura y Cierre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCerrarCajaAC.setBackground(java.awt.SystemColor.controlHighlight);
        btnCerrarCajaAC.setForeground(new java.awt.Color(0, 0, 0));
        btnCerrarCajaAC.setText("Cerrar");
        btnCerrarCajaAC.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCerrarCajaAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarCajaACActionPerformed(evt);
            }
        });
        jPanel22.add(btnCerrarCajaAC, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, 100, 30));

        btnNuevaCajaAC.setBackground(java.awt.SystemColor.controlHighlight);
        btnNuevaCajaAC.setForeground(new java.awt.Color(0, 0, 0));
        btnNuevaCajaAC.setText("Nuevo");
        btnNuevaCajaAC.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel22.add(btnNuevaCajaAC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 100, 30));

        btnAbrirCajaAC.setBackground(java.awt.SystemColor.controlHighlight);
        btnAbrirCajaAC.setForeground(new java.awt.Color(0, 0, 0));
        btnAbrirCajaAC.setText("Abrir");
        btnAbrirCajaAC.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel22.add(btnAbrirCajaAC, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, 100, 30));

        txtMontoInicialAC.setBackground(new java.awt.Color(255, 255, 255));
        txtMontoInicialAC.setForeground(new java.awt.Color(0, 0, 0));
        jPanel22.add(txtMontoInicialAC, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 220, -1));

        jLabel60.setBackground(new java.awt.Color(255, 255, 255));
        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Monto Inicial");
        jPanel22.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jLabel61.setBackground(new java.awt.Color(255, 255, 255));
        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Buscar");
        jPanel22.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        txtBuscarCajaAC.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarCajaAC.setForeground(new java.awt.Color(0, 0, 0));
        jPanel22.add(txtBuscarCajaAC, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 220, -1));

        InfoAC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/informacion.png"))); // NOI18N
        InfoAC.setToolTipText("");
        InfoAC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel22.add(InfoAC, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 580, -1, -1));

        jPanelCaja1.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 380, 650));

        TablaAperturaYCierre.setBackground(java.awt.SystemColor.controlHighlight);
        TablaAperturaYCierre.setForeground(new java.awt.Color(0, 0, 0));
        TablaAperturaYCierre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha Apertura", "Fecha Cierre", "Monto Inicial", "Monto Final", "Total Ventas", "Usuario"
            }
        ));
        TablaAperturaYCierre.setComponentPopupMenu(JPopupCajas);
        TablaAperturaYCierre.setGridColor(java.awt.SystemColor.controlHighlight);
        TablaAperturaYCierre.setSelectionBackground(java.awt.SystemColor.controlHighlight);
        TablaAperturaYCierre.setSelectionForeground(java.awt.SystemColor.controlHighlight);
        jScrollPane15.setViewportView(TablaAperturaYCierre);

        jPanelCaja1.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 1110, 650));

        JTabbedPaneDetMenus.addTab("Apertura y Cierre", jPanelCaja1);

        jPanelClientes.setBackground(new java.awt.Color(102, 255, 51));
        jPanelClientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Nombre");
        jPanel16.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Celular");
        jPanel16.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Direccion");
        jPanel16.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, -1));

        btnModificarCli.setBackground(java.awt.SystemColor.controlHighlight);
        btnModificarCli.setForeground(new java.awt.Color(0, 0, 0));
        btnModificarCli.setText("Modificar");
        btnModificarCli.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnModificarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCliActionPerformed(evt);
            }
        });
        jPanel16.add(btnModificarCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 100, 30));

        btnNuevoCli.setBackground(java.awt.SystemColor.controlHighlight);
        btnNuevoCli.setForeground(new java.awt.Color(0, 0, 0));
        btnNuevoCli.setText("Nuevo");
        btnNuevoCli.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel16.add(btnNuevoCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 100, 30));

        btnRegistrarCli.setBackground(java.awt.SystemColor.controlHighlight);
        btnRegistrarCli.setForeground(new java.awt.Color(0, 0, 0));
        btnRegistrarCli.setText("Registrar");
        btnRegistrarCli.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel16.add(btnRegistrarCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, 100, 30));

        txtNombreCli.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreCli.setForeground(new java.awt.Color(0, 0, 0));
        jPanel16.add(txtNombreCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 220, -1));

        txtCelularCli.setBackground(new java.awt.Color(255, 255, 255));
        txtCelularCli.setForeground(new java.awt.Color(0, 0, 0));
        jPanel16.add(txtCelularCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 220, -1));

        txtDireccionCli.setBackground(new java.awt.Color(255, 255, 255));
        txtDireccionCli.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane3.setViewportView(txtDireccionCli);

        jPanel16.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 220, 140));

        jLabel47.setBackground(new java.awt.Color(255, 255, 255));
        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Buscar");
        jPanel16.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        txtBuscarCli.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarCli.setForeground(new java.awt.Color(0, 0, 0));
        jPanel16.add(txtBuscarCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 220, -1));

        jLabel48.setBackground(new java.awt.Color(255, 255, 255));
        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Id");
        jPanel16.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        txtIdCli.setBackground(new java.awt.Color(255, 255, 255));
        txtIdCli.setForeground(new java.awt.Color(0, 0, 0));
        jPanel16.add(txtIdCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 50, -1));

        InfoCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/informacion.png"))); // NOI18N
        InfoCli.setToolTipText("");
        InfoCli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel16.add(InfoCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 590, -1, -1));

        jPanelClientes.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 380, 650));

        TablaClientes.setBackground(java.awt.SystemColor.controlHighlight);
        TablaClientes.setForeground(new java.awt.Color(0, 0, 0));
        TablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Celular", "Direccion", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaClientes.setComponentPopupMenu(JPopupClientes);
        TablaClientes.setGridColor(java.awt.SystemColor.controlHighlight);
        TablaClientes.setSelectionBackground(java.awt.SystemColor.controlHighlight);
        TablaClientes.setSelectionForeground(java.awt.SystemColor.controlHighlight);
        jScrollPane13.setViewportView(TablaClientes);

        jPanelClientes.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 1110, 650));

        JTabbedPaneDetMenus.addTab("Clientes", jPanelClientes);

        jPanelProveedores.setBackground(new java.awt.Color(51, 153, 255));
        jPanelProveedores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel17.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo Proveedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("CUIT");
        jPanel17.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Celular");
        jPanel17.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Direccion");
        jPanel17.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        btnModificarProv.setBackground(java.awt.SystemColor.controlHighlight);
        btnModificarProv.setForeground(new java.awt.Color(0, 0, 0));
        btnModificarProv.setText("Modificar");
        btnModificarProv.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnModificarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProvActionPerformed(evt);
            }
        });
        jPanel17.add(btnModificarProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 550, 100, 30));

        btnNuevoProv.setBackground(java.awt.SystemColor.controlHighlight);
        btnNuevoProv.setForeground(new java.awt.Color(0, 0, 0));
        btnNuevoProv.setText("Nuevo");
        btnNuevoProv.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel17.add(btnNuevoProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 100, 30));

        btnRegistrarProv.setBackground(java.awt.SystemColor.controlHighlight);
        btnRegistrarProv.setForeground(new java.awt.Color(0, 0, 0));
        btnRegistrarProv.setText("Registrar");
        btnRegistrarProv.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel17.add(btnRegistrarProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 550, 100, 30));

        txtCuitProv.setBackground(new java.awt.Color(255, 255, 255));
        txtCuitProv.setForeground(new java.awt.Color(0, 0, 0));
        jPanel17.add(txtCuitProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 220, -1));

        txtCelularProv.setBackground(new java.awt.Color(255, 255, 255));
        txtCelularProv.setForeground(new java.awt.Color(0, 0, 0));
        jPanel17.add(txtCelularProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 220, -1));

        txtDireccionProv.setBackground(new java.awt.Color(255, 255, 255));
        txtDireccionProv.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane4.setViewportView(txtDireccionProv);

        jPanel17.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 220, 140));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Nombre");
        jPanel17.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        txtNombreProv.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreProv.setForeground(new java.awt.Color(0, 0, 0));
        jPanel17.add(txtNombreProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 220, -1));

        txtBuscarProv.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarProv.setForeground(new java.awt.Color(0, 0, 0));
        jPanel17.add(txtBuscarProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 220, -1));

        txtIdProv.setBackground(new java.awt.Color(255, 255, 255));
        txtIdProv.setForeground(new java.awt.Color(0, 0, 0));
        jPanel17.add(txtIdProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 50, -1));

        jLabel49.setBackground(new java.awt.Color(255, 255, 255));
        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Id");
        jPanel17.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel50.setBackground(new java.awt.Color(255, 255, 255));
        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Buscar");
        jPanel17.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        InfoProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/informacion.png"))); // NOI18N
        InfoProv.setToolTipText("");
        InfoProv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel17.add(InfoProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 600, -1, -1));

        jPanelProveedores.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 380, 650));

        TablaProveedores.setBackground(java.awt.SystemColor.controlHighlight);
        TablaProveedores.setForeground(new java.awt.Color(0, 0, 0));
        TablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "CUIT", "Nombre", "Celular", "Direccion", "Estado"
            }
        ));
        TablaProveedores.setComponentPopupMenu(JPopupProveedores);
        TablaProveedores.setGridColor(java.awt.SystemColor.controlHighlight);
        TablaProveedores.setSelectionBackground(java.awt.SystemColor.controlHighlight);
        TablaProveedores.setSelectionForeground(java.awt.SystemColor.controlHighlight);
        jScrollPane5.setViewportView(TablaProveedores);

        jPanelProveedores.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 1110, 650));

        JTabbedPaneDetMenus.addTab("Proveedores", jPanelProveedores);

        jPanelCategorias.setBackground(new java.awt.Color(255, 102, 255));
        jPanelCategorias.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel19.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nueva Categoria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnModificarCat.setBackground(java.awt.SystemColor.controlHighlight);
        btnModificarCat.setForeground(new java.awt.Color(0, 0, 0));
        btnModificarCat.setText("Modificar");
        btnModificarCat.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnModificarCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCatActionPerformed(evt);
            }
        });
        jPanel19.add(btnModificarCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, 100, 30));

        btnNuevoCat.setBackground(java.awt.SystemColor.controlHighlight);
        btnNuevoCat.setForeground(new java.awt.Color(0, 0, 0));
        btnNuevoCat.setText("Nuevo");
        btnNuevoCat.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel19.add(btnNuevoCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 100, 30));

        btnRegistrarCat.setBackground(java.awt.SystemColor.controlHighlight);
        btnRegistrarCat.setForeground(new java.awt.Color(0, 0, 0));
        btnRegistrarCat.setText("Registrar");
        btnRegistrarCat.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel19.add(btnRegistrarCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, 100, 30));

        txtNombreCat.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreCat.setForeground(new java.awt.Color(0, 0, 0));
        jPanel19.add(txtNombreCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 220, -1));

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Nombre");
        jPanel19.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        jLabel53.setBackground(new java.awt.Color(255, 255, 255));
        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Buscar");
        jPanel19.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        txtBuscarCat.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarCat.setForeground(new java.awt.Color(0, 0, 0));
        jPanel19.add(txtBuscarCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 220, -1));

        jLabel54.setBackground(new java.awt.Color(255, 255, 255));
        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Id");
        jPanel19.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        txtIdCat.setBackground(new java.awt.Color(255, 255, 255));
        txtIdCat.setForeground(new java.awt.Color(0, 0, 0));
        jPanel19.add(txtIdCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, 50, -1));

        InfoCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/informacion.png"))); // NOI18N
        InfoCat.setToolTipText("");
        InfoCat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel19.add(InfoCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 580, -1, -1));

        jPanelCategorias.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 380, 650));

        TablaCategorias.setBackground(java.awt.SystemColor.controlHighlight);
        TablaCategorias.setForeground(new java.awt.Color(0, 0, 0));
        TablaCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Estado"
            }
        ));
        TablaCategorias.setComponentPopupMenu(JPopupCategorias);
        TablaCategorias.setGridColor(java.awt.SystemColor.controlHighlight);
        TablaCategorias.setSelectionBackground(java.awt.SystemColor.controlHighlight);
        TablaCategorias.setSelectionForeground(java.awt.SystemColor.controlHighlight);
        jScrollPane8.setViewportView(TablaCategorias);

        jPanelCategorias.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 1110, 650));

        JTabbedPaneDetMenus.addTab("Categorias", jPanelCategorias);

        jPanelProductos.setBackground(new java.awt.Color(255, 255, 102));
        jPanelProductos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Codigo");
        jPanel15.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nombre");
        jPanel15.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Precio Compra");
        jPanel15.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Precio Venta");
        jPanel15.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Proveedor");
        jPanel15.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, -1, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Categoria");
        jPanel15.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, -1, -1));

        txtPrecioVentaProd.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecioVentaProd.setForeground(new java.awt.Color(0, 0, 0));
        jPanel15.add(txtPrecioVentaProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 180, -1));

        cbxCategoriaProd.setBackground(new java.awt.Color(255, 255, 255));
        cbxCategoriaProd.setEditable(true);
        cbxCategoriaProd.setForeground(new java.awt.Color(0, 0, 0));
        jPanel15.add(cbxCategoriaProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 520, 180, -1));

        btnModificarProd.setBackground(java.awt.SystemColor.controlHighlight);
        btnModificarProd.setForeground(new java.awt.Color(0, 0, 0));
        btnModificarProd.setText("Modificar");
        btnModificarProd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnModificarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProdActionPerformed(evt);
            }
        });
        jPanel15.add(btnModificarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 570, 100, 30));

        btnNuevoProd.setBackground(java.awt.SystemColor.controlHighlight);
        btnNuevoProd.setForeground(new java.awt.Color(0, 0, 0));
        btnNuevoProd.setText("Nuevo");
        btnNuevoProd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel15.add(btnNuevoProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 100, 30));

        txtCodigoProd.setBackground(new java.awt.Color(255, 255, 255));
        txtCodigoProd.setForeground(new java.awt.Color(0, 0, 0));
        jPanel15.add(txtCodigoProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 180, -1));

        txtNombreProd.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreProd.setForeground(new java.awt.Color(0, 0, 0));
        jPanel15.add(txtNombreProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 180, -1));

        txtPrecioCompraProd.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecioCompraProd.setForeground(new java.awt.Color(0, 0, 0));
        jPanel15.add(txtPrecioCompraProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 180, -1));

        cbxProveedorProd.setBackground(new java.awt.Color(255, 255, 255));
        cbxProveedorProd.setEditable(true);
        cbxProveedorProd.setForeground(new java.awt.Color(0, 0, 0));
        jPanel15.add(cbxProveedorProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, 180, -1));

        jLabel55.setBackground(new java.awt.Color(255, 255, 255));
        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Buscar");
        jPanel15.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        txtBuscarProd.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarProd.setForeground(new java.awt.Color(0, 0, 0));
        jPanel15.add(txtBuscarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 220, -1));

        jLabel56.setBackground(new java.awt.Color(255, 255, 255));
        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Id");
        jPanel15.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        txtIdProd.setBackground(new java.awt.Color(255, 255, 255));
        txtIdProd.setForeground(new java.awt.Color(0, 0, 0));
        jPanel15.add(txtIdProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 50, -1));

        btnRegistrarProd.setBackground(java.awt.SystemColor.controlHighlight);
        btnRegistrarProd.setForeground(new java.awt.Color(0, 0, 0));
        btnRegistrarProd.setText("Registrar");
        btnRegistrarProd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel15.add(btnRegistrarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 570, 100, 30));

        InfoProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/informacion.png"))); // NOI18N
        InfoProd.setToolTipText("");
        InfoProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel15.add(InfoProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 610, -1, -1));

        jPanelProductos.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 380, 660));

        TablaProductos.setBackground(java.awt.SystemColor.controlHighlight);
        TablaProductos.setForeground(new java.awt.Color(0, 0, 0));
        TablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Codigo", "Nombre", "Precio Venta", "Stock", "Estado"
            }
        ));
        TablaProductos.setComponentPopupMenu(JPopupProductos);
        TablaProductos.setGridColor(java.awt.SystemColor.controlHighlight);
        TablaProductos.setSelectionBackground(java.awt.SystemColor.controlHighlight);
        TablaProductos.setSelectionForeground(java.awt.SystemColor.controlHighlight);
        jScrollPane1.setViewportView(TablaProductos);

        jPanelProductos.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 1110, 660));

        JTabbedPaneDetMenus.addTab("Productos", jPanelProductos);

        jPanelNC.setBackground(new java.awt.Color(224, 91, 91));
        jPanelNC.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setBackground(new java.awt.Color(255, 255, 255));
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Código");
        jPanelNC.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        txtCodigoNC.setBackground(new java.awt.Color(255, 255, 255));
        txtCodigoNC.setForeground(new java.awt.Color(0, 0, 0));
        jPanelNC.add(txtCodigoNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 180, 40));

        txtProductoNC.setBackground(new java.awt.Color(255, 255, 255));
        txtProductoNC.setForeground(new java.awt.Color(0, 0, 0));
        jPanelNC.add(txtProductoNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 180, 40));

        jLabel37.setBackground(new java.awt.Color(255, 255, 255));
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Producto");
        jPanelNC.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, -1));

        jLabel38.setBackground(new java.awt.Color(255, 255, 255));
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Cantidad");
        jPanelNC.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, -1, -1));

        txtCantidadNC.setBackground(new java.awt.Color(255, 255, 255));
        txtCantidadNC.setForeground(new java.awt.Color(0, 0, 0));
        jPanelNC.add(txtCantidadNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 110, 40));

        txtPrecioNC.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecioNC.setForeground(new java.awt.Color(0, 0, 0));
        jPanelNC.add(txtPrecioNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 110, 40));

        jLabel39.setBackground(new java.awt.Color(255, 255, 255));
        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Precio");
        jPanelNC.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, -1, -1));

        jLabel40.setBackground(new java.awt.Color(255, 255, 255));
        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Total");
        jPanelNC.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, -1, -1));

        txtIdNC.setBackground(new java.awt.Color(255, 255, 255));
        txtIdNC.setForeground(new java.awt.Color(0, 0, 0));
        txtIdNC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdNCActionPerformed(evt);
            }
        });
        jPanelNC.add(txtIdNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 40, 110, 40));

        btnComprar.setBackground(java.awt.SystemColor.controlHighlight);
        btnComprar.setForeground(new java.awt.Color(0, 0, 0));
        btnComprar.setText("Comprar");
        btnComprar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelNC.add(btnComprar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 40, 130, 30));

        TablaNuevaCompra.setBackground(java.awt.SystemColor.controlHighlight);
        TablaNuevaCompra.setForeground(new java.awt.Color(0, 0, 0));
        TablaNuevaCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Descripcion", "Cantidad", "Precio", "Total"
            }
        ));
        TablaNuevaCompra.setGridColor(java.awt.SystemColor.controlHighlight);
        TablaNuevaCompra.setSelectionBackground(java.awt.SystemColor.controlHighlight);
        TablaNuevaCompra.setSelectionForeground(java.awt.SystemColor.controlHighlight);
        jScrollPane10.setViewportView(TablaNuevaCompra);
        if (TablaNuevaCompra.getColumnModel().getColumnCount() > 0) {
            TablaNuevaCompra.getColumnModel().getColumn(3).setHeaderValue("Precio");
        }

        jPanelNC.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1500, 500));

        jLabel42.setBackground(new java.awt.Color(255, 255, 255));
        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Proveedor");
        jPanelNC.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 620, -1, -1));

        cbxProveedorNC.setBackground(new java.awt.Color(255, 255, 255));
        cbxProveedorNC.setForeground(new java.awt.Color(0, 0, 0));
        jPanelNC.add(cbxProveedorNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 610, 270, 40));

        jLabel43.setBackground(new java.awt.Color(255, 255, 255));
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Pagar con $");
        jPanelNC.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 620, -1, -1));

        txtPagarConNC.setBackground(new java.awt.Color(255, 255, 255));
        txtPagarConNC.setForeground(new java.awt.Color(0, 0, 0));
        jPanelNC.add(txtPagarConNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 610, 110, 40));

        jLabel44.setBackground(new java.awt.Color(255, 255, 255));
        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Vuelto $");
        jPanelNC.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 620, -1, -1));

        txtVueltoCompra.setBackground(new java.awt.Color(255, 255, 255));
        txtVueltoCompra.setForeground(new java.awt.Color(0, 0, 0));
        txtVueltoCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVueltoCompraActionPerformed(evt);
            }
        });
        jPanelNC.add(txtVueltoCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 610, 110, 40));

        jLabel45.setBackground(new java.awt.Color(255, 255, 255));
        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Total a pagar: $");
        jPanelNC.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 620, -1, -1));

        JLabelTotalAPagarCompra.setBackground(new java.awt.Color(255, 255, 255));
        JLabelTotalAPagarCompra.setForeground(new java.awt.Color(0, 0, 0));
        JLabelTotalAPagarCompra.setText("--------");
        jPanelNC.add(JLabelTotalAPagarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 620, -1, -1));

        txtTotalNC.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalNC.setForeground(new java.awt.Color(0, 0, 0));
        txtTotalNC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalNCActionPerformed(evt);
            }
        });
        jPanelNC.add(txtTotalNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 110, 40));

        btnVerCompras.setBackground(java.awt.SystemColor.controlHighlight);
        btnVerCompras.setForeground(new java.awt.Color(0, 0, 0));
        btnVerCompras.setText("Ver Compras");
        btnVerCompras.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnVerCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerComprasActionPerformed(evt);
            }
        });
        jPanelNC.add(btnVerCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 40, 110, 30));

        JTabbedPaneDetMenus.addTab("Nueva Compra", jPanelNC);

        jPanelCompras.setBackground(new java.awt.Color(255, 153, 153));
        jPanelCompras.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaCompras.setBackground(java.awt.SystemColor.controlHighlight);
        TablaCompras.setForeground(new java.awt.Color(0, 0, 0));
        TablaCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Proveedor", "Total", "Fecha"
            }
        ));
        TablaCompras.setGridColor(java.awt.SystemColor.controlHighlight);
        TablaCompras.setSelectionBackground(java.awt.SystemColor.controlHighlight);
        TablaCompras.setSelectionForeground(java.awt.SystemColor.controlHighlight);
        jScrollPane11.setViewportView(TablaCompras);

        jPanelCompras.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1500, 590));

        txtBuscarCompra.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarCompra.setForeground(new java.awt.Color(0, 0, 0));
        jPanelCompras.add(txtBuscarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 310, 40));

        btnHistorialCompras.setBackground(java.awt.SystemColor.controlHighlight);
        btnHistorialCompras.setForeground(new java.awt.Color(0, 0, 0));
        btnHistorialCompras.setText("Generar Factura");
        btnHistorialCompras.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelCompras.add(btnHistorialCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 180, 30));

        txtIdCompra.setBackground(new java.awt.Color(255, 255, 255));
        txtIdCompra.setForeground(new java.awt.Color(0, 0, 0));
        txtIdCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdCompraActionPerformed(evt);
            }
        });
        jPanelCompras.add(txtIdCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 110, 40));

        JTabbedPaneDetMenus.addTab("Compras", jPanelCompras);

        jPanelNV.setBackground(new java.awt.Color(0, 204, 204));
        jPanelNV.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaNuevaVenta.setBackground(java.awt.SystemColor.controlHighlight);
        TablaNuevaVenta.setForeground(new java.awt.Color(0, 0, 0));
        TablaNuevaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Producto", "Cantidad", "Precio", "Total"
            }
        ));
        TablaNuevaVenta.setGridColor(java.awt.SystemColor.controlHighlight);
        TablaNuevaVenta.setSelectionBackground(java.awt.SystemColor.controlHighlight);
        TablaNuevaVenta.setSelectionForeground(java.awt.SystemColor.controlHighlight);
        jScrollPane9.setViewportView(TablaNuevaVenta);

        jPanelNV.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1500, 500));

        txtVueltoVenta.setBackground(new java.awt.Color(255, 255, 255));
        txtVueltoVenta.setForeground(new java.awt.Color(0, 0, 0));
        txtVueltoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVueltoVentaActionPerformed(evt);
            }
        });
        jPanelNV.add(txtVueltoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 610, 110, 40));

        txtCodigoNV.setBackground(new java.awt.Color(255, 255, 255));
        txtCodigoNV.setForeground(new java.awt.Color(0, 0, 0));
        jPanelNV.add(txtCodigoNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 180, 40));

        txtProductoNV.setBackground(new java.awt.Color(255, 255, 255));
        txtProductoNV.setForeground(new java.awt.Color(0, 0, 0));
        jPanelNV.add(txtProductoNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 180, 40));

        txtCantidadNV.setBackground(new java.awt.Color(255, 255, 255));
        txtCantidadNV.setForeground(new java.awt.Color(0, 0, 0));
        jPanelNV.add(txtCantidadNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 110, 40));

        txtPrecioNV.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecioNV.setForeground(new java.awt.Color(0, 0, 0));
        jPanelNV.add(txtPrecioNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 110, 40));

        txtTotalNV.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalNV.setForeground(new java.awt.Color(0, 0, 0));
        txtTotalNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalNVActionPerformed(evt);
            }
        });
        jPanelNV.add(txtTotalNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 110, 40));

        btnVerVentas.setBackground(java.awt.SystemColor.controlHighlight);
        btnVerVentas.setForeground(new java.awt.Color(0, 0, 0));
        btnVerVentas.setText("Ver Ventas");
        btnVerVentas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnVerVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerVentasActionPerformed(evt);
            }
        });
        jPanelNV.add(btnVerVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 40, 110, 30));

        txtIdNV.setBackground(new java.awt.Color(255, 255, 255));
        txtIdNV.setForeground(new java.awt.Color(0, 0, 0));
        jPanelNV.add(txtIdNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 40, 110, 40));

        txtPagarConNV.setBackground(new java.awt.Color(255, 255, 255));
        txtPagarConNV.setForeground(new java.awt.Color(0, 0, 0));
        jPanelNV.add(txtPagarConNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 610, 110, 40));

        cbxClienteNV.setBackground(new java.awt.Color(255, 255, 255));
        cbxClienteNV.setForeground(new java.awt.Color(0, 0, 0));
        jPanelNV.add(cbxClienteNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 610, 270, 40));

        JLabelTotalAPagarVenta.setBackground(new java.awt.Color(255, 255, 255));
        JLabelTotalAPagarVenta.setForeground(new java.awt.Color(0, 0, 0));
        JLabelTotalAPagarVenta.setText("--------");
        jPanelNV.add(JLabelTotalAPagarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 620, -1, -1));

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Código");
        jPanelNV.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Producto");
        jPanelNV.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, -1));

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Cantidad");
        jPanelNV.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, -1, -1));

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Precio");
        jPanelNV.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, -1, -1));

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Total");
        jPanelNV.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, -1, -1));

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Stock");
        jPanelNV.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 10, -1, -1));

        jLabel32.setBackground(new java.awt.Color(255, 255, 255));
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Cliente");
        jPanelNV.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 620, -1, -1));

        jLabel33.setBackground(new java.awt.Color(255, 255, 255));
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Pagar con $");
        jPanelNV.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 620, -1, -1));

        jLabel34.setBackground(new java.awt.Color(255, 255, 255));
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Vuelto $");
        jPanelNV.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 620, -1, -1));

        jLabel35.setBackground(new java.awt.Color(255, 255, 255));
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Total a pagar: $");
        jPanelNV.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 620, -1, -1));

        txtStockNV.setBackground(new java.awt.Color(255, 255, 255));
        txtStockNV.setForeground(new java.awt.Color(0, 0, 0));
        txtStockNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockNVActionPerformed(evt);
            }
        });
        jPanelNV.add(txtStockNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 40, 110, 40));

        btnVender.setBackground(java.awt.SystemColor.controlHighlight);
        btnVender.setForeground(new java.awt.Color(0, 0, 0));
        btnVender.setText("Vender");
        btnVender.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderActionPerformed(evt);
            }
        });
        jPanelNV.add(btnVender, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 40, 110, 30));

        JTabbedPaneDetMenus.addTab("Nueva Venta", jPanelNV);

        jPanelVentas.setBackground(new java.awt.Color(102, 255, 204));
        jPanelVentas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaVentas.setBackground(java.awt.SystemColor.controlHighlight);
        TablaVentas.setForeground(new java.awt.Color(0, 0, 0));
        TablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Cliente", "Total", "Fecha"
            }
        ));
        TablaVentas.setGridColor(java.awt.SystemColor.controlHighlight);
        TablaVentas.setSelectionBackground(java.awt.SystemColor.controlHighlight);
        TablaVentas.setSelectionForeground(java.awt.SystemColor.controlHighlight);
        jScrollPane12.setViewportView(TablaVentas);

        jPanelVentas.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1500, 590));

        btnHistorialVentas.setBackground(java.awt.SystemColor.controlHighlight);
        btnHistorialVentas.setForeground(new java.awt.Color(0, 0, 0));
        btnHistorialVentas.setText("Generar Factura");
        btnHistorialVentas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelVentas.add(btnHistorialVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 180, 30));

        txtIdVenta.setBackground(new java.awt.Color(255, 255, 255));
        txtIdVenta.setForeground(new java.awt.Color(0, 0, 0));
        txtIdVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdVentaActionPerformed(evt);
            }
        });
        jPanelVentas.add(txtIdVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 110, 40));

        txtBuscarVenta.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarVenta.setForeground(new java.awt.Color(0, 0, 0));
        jPanelVentas.add(txtBuscarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 310, 40));

        JTabbedPaneDetMenus.addTab("Ventas", jPanelVentas);

        JPanelAdmin.add(JTabbedPaneDetMenus, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 1520, 720));

        JPanelEmpresa.setBackground(new java.awt.Color(102, 102, 255));
        JPanelEmpresa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMinimizarPanel.setBackground(new java.awt.Color(51, 51, 51));
        btnMinimizarPanel.setForeground(new java.awt.Color(255, 255, 255));
        btnMinimizarPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/minimizar2.png"))); // NOI18N
        btnMinimizarPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnMinimizarPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JPanelEmpresa.add(btnMinimizarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1650, 40, 50, 40));

        jLabel25.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Distribuidora MR");
        JPanelEmpresa.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, -1, -1));

        btnSalirDelPanel.setBackground(new java.awt.Color(51, 51, 51));
        btnSalirDelPanel.setForeground(new java.awt.Color(255, 255, 255));
        btnSalirDelPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/salir.png"))); // NOI18N
        btnSalirDelPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalirDelPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalirDelPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirDelPanelActionPerformed(evt);
            }
        });
        JPanelEmpresa.add(btnSalirDelPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1710, 40, 50, 40));

        btnActualizarPanel.setBackground(new java.awt.Color(51, 51, 51));
        btnActualizarPanel.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/actualizar.png"))); // NOI18N
        btnActualizarPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnActualizarPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JPanelEmpresa.add(btnActualizarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1590, 40, 50, 40));

        LabelAdministrador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/favoritos.png"))); // NOI18N
        JPanelEmpresa.add(LabelAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        LabelUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/anadir-contacto.png"))); // NOI18N
        JPanelEmpresa.add(LabelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        txtIdUsuario.setBackground(new java.awt.Color(102, 102, 255));
        txtIdUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtIdUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JPanelEmpresa.add(txtIdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 50, -1));

        txtNomUsuario.setBackground(new java.awt.Color(102, 102, 255));
        txtNomUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtNomUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JPanelEmpresa.add(txtNomUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 140, -1));

        txtRolUsuario.setBackground(new java.awt.Color(102, 102, 255));
        txtRolUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtRolUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JPanelEmpresa.add(txtRolUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 140, -1));

        JPanelAdmin.add(JPanelEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1790, 120));

        JPanelMenusVertical.setBackground(new java.awt.Color(102, 102, 102));
        JPanelMenusVertical.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPanelNuevaVenta.setBackground(new java.awt.Color(51, 51, 51));
        JPanelNuevaVenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLabelNuevaVenta.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        JLabelNuevaVenta.setForeground(new java.awt.Color(255, 255, 255));
        JLabelNuevaVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLabelNuevaVenta.setText("Nueva Venta");
        JLabelNuevaVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JPanelNuevaVenta.add(JLabelNuevaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 80));

        JPanelMenusVertical.add(JPanelNuevaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 80));

        JPanelNuevaCompra.setBackground(new java.awt.Color(51, 51, 51));
        JPanelNuevaCompra.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLabelNuevaCompra.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        JLabelNuevaCompra.setForeground(new java.awt.Color(255, 255, 255));
        JLabelNuevaCompra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLabelNuevaCompra.setText("Nueva Compra");
        JLabelNuevaCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JPanelNuevaCompra.add(JLabelNuevaCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 80));

        JPanelMenusVertical.add(JPanelNuevaCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 270, 80));

        JPanelProductos.setBackground(new java.awt.Color(51, 51, 51));
        JPanelProductos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLabelProductos.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        JLabelProductos.setForeground(new java.awt.Color(255, 255, 255));
        JLabelProductos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLabelProductos.setText("Productos");
        JLabelProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JPanelProductos.add(JLabelProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 80));

        JPanelMenusVertical.add(JPanelProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 270, 80));

        JPanelCategorias.setBackground(new java.awt.Color(51, 51, 51));
        JPanelCategorias.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLabelCategorias.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        JLabelCategorias.setForeground(new java.awt.Color(255, 255, 255));
        JLabelCategorias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLabelCategorias.setText("Categorias");
        JLabelCategorias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JPanelCategorias.add(JLabelCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 80));

        JPanelMenusVertical.add(JPanelCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 270, 80));

        JPanelProveedores.setBackground(new java.awt.Color(51, 51, 51));
        JPanelProveedores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLabelProveedores.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        JLabelProveedores.setForeground(new java.awt.Color(255, 255, 255));
        JLabelProveedores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLabelProveedores.setText("Proveedores");
        JLabelProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JPanelProveedores.add(JLabelProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 80));

        JPanelMenusVertical.add(JPanelProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 270, 80));

        JPanelClientes.setBackground(new java.awt.Color(51, 51, 51));
        JPanelClientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLabelClientes.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        JLabelClientes.setForeground(new java.awt.Color(255, 255, 255));
        JLabelClientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLabelClientes.setText("Clientes");
        JLabelClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JPanelClientes.add(JLabelClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 80));

        JPanelMenusVertical.add(JPanelClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 270, 80));

        JPanelCaja.setBackground(new java.awt.Color(51, 51, 51));
        JPanelCaja.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLabelCaja.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        JLabelCaja.setForeground(new java.awt.Color(255, 255, 255));
        JLabelCaja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLabelCaja.setText("Cajas");
        JLabelCaja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JPanelCaja.add(JLabelCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 80));

        JPanelMenusVertical.add(JPanelCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 270, 80));

        JPanelUsuarios.setBackground(new java.awt.Color(51, 51, 51));
        JPanelUsuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLabelUsuarios.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        JLabelUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        JLabelUsuarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLabelUsuarios.setText("Usuarios");
        JLabelUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JPanelUsuarios.add(JLabelUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 80));

        JPanelMenusVertical.add(JPanelUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 270, 80));

        JPanelConfiguracion.setBackground(new java.awt.Color(51, 51, 51));
        JPanelConfiguracion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLabelConfiguracion.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        JLabelConfiguracion.setForeground(new java.awt.Color(255, 255, 255));
        JLabelConfiguracion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLabelConfiguracion.setText("Configuracion");
        JLabelConfiguracion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JPanelConfiguracion.add(JLabelConfiguracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 80));

        JPanelMenusVertical.add(JPanelConfiguracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 640, 270, 80));

        JPanelAdmin.add(JPanelMenusVertical, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 270, 720));

        getContentPane().add(JPanelAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 840));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTotalNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalNVActionPerformed

    private void txtVueltoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVueltoVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVueltoVentaActionPerformed

    private void txtVueltoCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVueltoCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVueltoCompraActionPerformed

    private void txtIdNCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdNCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdNCActionPerformed

    private void btnModificarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarProdActionPerformed

    private void btnModificarCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarCatActionPerformed

    private void btnModificarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarProvActionPerformed

    private void btnModificarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarCliActionPerformed

    private void cbxCajaUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCajaUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCajaUserActionPerformed

    private void txtClaveUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveUserActionPerformed

    private void btnModificarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarUserActionPerformed

    private void btnModificarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarEmpresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarEmpresaActionPerformed

    private void JMenuEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuEliminarUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JMenuEliminarUsuarioActionPerformed

    private void btnSalirDelPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirDelPanelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirDelPanelActionPerformed

    private void txtTotalNCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalNCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalNCActionPerformed

    private void txtIdCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdCompraActionPerformed

    private void txtStockNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockNVActionPerformed

    private void btnVerVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerVentasActionPerformed

    private void txtIdVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdVentaActionPerformed

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVenderActionPerformed

    private void btnModificarCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarCajaActionPerformed

    private void btnCerrarCajaACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarCajaACActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarCajaACActionPerformed

    private void btnVerComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerComprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerComprasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PanelPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel InfoAC;
    public javax.swing.JLabel InfoCaja;
    public javax.swing.JLabel InfoCat;
    public javax.swing.JLabel InfoCli;
    public javax.swing.JLabel InfoConf;
    public javax.swing.JLabel InfoProd;
    public javax.swing.JLabel InfoProv;
    public javax.swing.JLabel JLabelCaja;
    public javax.swing.JLabel JLabelCategorias;
    public javax.swing.JLabel JLabelClientes;
    public javax.swing.JLabel JLabelConfiguracion;
    public javax.swing.JLabel JLabelNuevaCompra;
    public javax.swing.JLabel JLabelNuevaVenta;
    public javax.swing.JLabel JLabelProductos;
    public javax.swing.JLabel JLabelProveedores;
    public javax.swing.JLabel JLabelTotalAPagarCompra;
    public javax.swing.JLabel JLabelTotalAPagarVenta;
    public javax.swing.JLabel JLabelUsuarios;
    public javax.swing.JMenuItem JMenuEliminarCaja;
    public javax.swing.JMenuItem JMenuEliminarCategoria;
    public javax.swing.JMenuItem JMenuEliminarCliente;
    public javax.swing.JMenuItem JMenuEliminarProducto;
    public javax.swing.JMenuItem JMenuEliminarProveedor;
    public javax.swing.JMenuItem JMenuEliminarUsuario;
    public javax.swing.JMenuItem JMenuReingresarCaja;
    public javax.swing.JMenuItem JMenuReingresarCategoria;
    public javax.swing.JMenuItem JMenuReingresarCliente;
    public javax.swing.JMenuItem JMenuReingresarProducto;
    public javax.swing.JMenuItem JMenuReingresarProveedor;
    public javax.swing.JMenuItem JMenuReingresarUsuario;
    private javax.swing.JPanel JPanelAdmin;
    public javax.swing.JPanel JPanelCaja;
    public javax.swing.JPanel JPanelCategorias;
    public javax.swing.JPanel JPanelClientes;
    public javax.swing.JPanel JPanelConfiguracion;
    private javax.swing.JPanel JPanelEmpresa;
    private javax.swing.JPanel JPanelMenusVertical;
    public javax.swing.JPanel JPanelNuevaCompra;
    public javax.swing.JPanel JPanelNuevaVenta;
    public javax.swing.JPanel JPanelProductos;
    public javax.swing.JPanel JPanelProveedores;
    public javax.swing.JPanel JPanelUsuarios;
    private javax.swing.JPopupMenu JPopupCajas;
    private javax.swing.JPopupMenu JPopupCategorias;
    private javax.swing.JPopupMenu JPopupClientes;
    private javax.swing.JPopupMenu JPopupProductos;
    private javax.swing.JPopupMenu JPopupProveedores;
    private javax.swing.JPopupMenu JPopupUsuarios;
    public javax.swing.JTabbedPane JTabbedPaneDetMenus;
    public javax.swing.JLabel LabelAdministrador;
    public javax.swing.JLabel LabelUsuario;
    public javax.swing.JTable TablaAperturaYCierre;
    public javax.swing.JTable TablaCajas;
    public javax.swing.JTable TablaCategorias;
    public javax.swing.JTable TablaClientes;
    public javax.swing.JTable TablaCompras;
    public javax.swing.JTable TablaEmpresa;
    public javax.swing.JTable TablaNuevaCompra;
    public javax.swing.JTable TablaNuevaVenta;
    public javax.swing.JTable TablaProductos;
    public javax.swing.JTable TablaProveedores;
    public javax.swing.JTable TablaUsuarios;
    public javax.swing.JTable TablaVentas;
    public javax.swing.JButton btnAbrirCajaAC;
    public javax.swing.JButton btnActualizarPanel;
    public javax.swing.JButton btnCerrarCajaAC;
    public javax.swing.JButton btnComprar;
    public javax.swing.JButton btnHistorialCompras;
    public javax.swing.JButton btnHistorialVentas;
    public javax.swing.JButton btnMinimizarPanel;
    public javax.swing.JButton btnModificarCaja;
    public javax.swing.JButton btnModificarCat;
    public javax.swing.JButton btnModificarCli;
    public javax.swing.JButton btnModificarEmpresa;
    public javax.swing.JButton btnModificarProd;
    public javax.swing.JButton btnModificarProv;
    public javax.swing.JButton btnModificarUser;
    public javax.swing.JButton btnNuevaCajaAC;
    public javax.swing.JButton btnNuevoCaja;
    public javax.swing.JButton btnNuevoCat;
    public javax.swing.JButton btnNuevoCli;
    public javax.swing.JButton btnNuevoProd;
    public javax.swing.JButton btnNuevoProv;
    public javax.swing.JButton btnNuevoUser;
    public javax.swing.JButton btnRegistrarCaja;
    public javax.swing.JButton btnRegistrarCat;
    public javax.swing.JButton btnRegistrarCli;
    public javax.swing.JButton btnRegistrarProd;
    public javax.swing.JButton btnRegistrarProv;
    public javax.swing.JButton btnRegistrarUser;
    public javax.swing.JButton btnSalirDelPanel;
    public javax.swing.JButton btnVender;
    public javax.swing.JButton btnVerAC;
    public javax.swing.JButton btnVerCompras;
    public javax.swing.JButton btnVerVentas;
    public javax.swing.JComboBox<Object> cbxCajaUser;
    public javax.swing.JComboBox<Object> cbxCategoriaProd;
    public javax.swing.JComboBox<Object> cbxClienteNV;
    public javax.swing.JComboBox<Object> cbxProveedorNC;
    public javax.swing.JComboBox<Object> cbxProveedorProd;
    public javax.swing.JComboBox<String> cbxRolUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    public javax.swing.JPanel jPanel21;
    public javax.swing.JPanel jPanel22;
    public javax.swing.JPanel jPanelBienvenido;
    private javax.swing.JPanel jPanelCaja;
    private javax.swing.JPanel jPanelCaja1;
    private javax.swing.JPanel jPanelCategorias;
    private javax.swing.JPanel jPanelClientes;
    private javax.swing.JPanel jPanelCompras;
    private javax.swing.JPanel jPanelConfiguracion;
    private javax.swing.JPanel jPanelNC;
    private javax.swing.JPanel jPanelNV;
    private javax.swing.JPanel jPanelProductos;
    private javax.swing.JPanel jPanelProveedores;
    private javax.swing.JPanel jPanelUsuarios;
    private javax.swing.JPanel jPanelVentas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    public javax.swing.JTextField txtBuscarCaja;
    public javax.swing.JTextField txtBuscarCajaAC;
    public javax.swing.JTextField txtBuscarCat;
    public javax.swing.JTextField txtBuscarCli;
    public javax.swing.JTextField txtBuscarCompra;
    public javax.swing.JTextField txtBuscarProd;
    public javax.swing.JTextField txtBuscarProv;
    public javax.swing.JTextField txtBuscarUser;
    public javax.swing.JTextField txtBuscarVenta;
    public javax.swing.JTextField txtCantidadNC;
    public javax.swing.JTextField txtCantidadNV;
    public javax.swing.JTextField txtCelularCli;
    public javax.swing.JTextField txtCelularEmpresa;
    public javax.swing.JTextField txtCelularProv;
    public javax.swing.JPasswordField txtClaveUser;
    public javax.swing.JTextField txtCodigoNC;
    public javax.swing.JTextField txtCodigoNV;
    public javax.swing.JTextField txtCodigoProd;
    public javax.swing.JTextField txtCuitEmpresa;
    public javax.swing.JTextField txtCuitProv;
    public javax.swing.JTextPane txtDireccionCli;
    public javax.swing.JTextPane txtDireccionEmpresa;
    public javax.swing.JTextPane txtDireccionProv;
    public javax.swing.JTextField txtIdCaja;
    public javax.swing.JTextField txtIdCat;
    public javax.swing.JTextField txtIdCli;
    public javax.swing.JTextField txtIdCompra;
    public javax.swing.JTextField txtIdEmpresa;
    public javax.swing.JTextField txtIdNC;
    public javax.swing.JTextField txtIdNV;
    public javax.swing.JTextField txtIdProd;
    public javax.swing.JTextField txtIdProv;
    public javax.swing.JTextField txtIdUser;
    public javax.swing.JTextField txtIdUsuario;
    public javax.swing.JTextField txtIdVenta;
    public javax.swing.JTextField txtMontoInicialAC;
    public javax.swing.JTextField txtNomUsuario;
    public javax.swing.JTextField txtNombreCaja;
    public javax.swing.JTextField txtNombreCat;
    public javax.swing.JTextField txtNombreCli;
    public javax.swing.JTextField txtNombreEmpresa;
    public javax.swing.JTextField txtNombreProd;
    public javax.swing.JTextField txtNombreProv;
    public javax.swing.JTextField txtNombreUser;
    public javax.swing.JTextField txtPagarConNC;
    public javax.swing.JTextField txtPagarConNV;
    public javax.swing.JTextField txtPrecioCompraProd;
    public javax.swing.JTextField txtPrecioNC;
    public javax.swing.JTextField txtPrecioNV;
    public javax.swing.JTextField txtPrecioVentaProd;
    public javax.swing.JTextField txtProductoNC;
    public javax.swing.JTextField txtProductoNV;
    public javax.swing.JTextField txtRolUsuario;
    public javax.swing.JTextField txtStockNV;
    public javax.swing.JTextField txtTotalNC;
    public javax.swing.JTextField txtTotalNV;
    public javax.swing.JTextField txtUsuarioUser;
    public javax.swing.JTextField txtVueltoCompra;
    public javax.swing.JTextField txtVueltoVenta;
    // End of variables declaration//GEN-END:variables

}
