//Autor: MARIA ISABEL ROMANO
package Controladores;

import Modelos.*;
import Vistas.PanelPrincipal;
import java.awt.Color;
import java.awt.event.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class ControladorProveedores implements ActionListener, MouseListener, KeyListener {

    private Proveedores pr;
    private ProveedoresDao prDao;
    private PanelPrincipal vistas;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorProveedores(Proveedores pr, ProveedoresDao prDao, PanelPrincipal vistas) {
        this.pr = pr;
        this.prDao = prDao;
        this.vistas = vistas;
        this.vistas.btnModificarProv.addActionListener(this);
        this.vistas.btnRegistrarProv.addActionListener(this);
        this.vistas.btnNuevoProv.addActionListener(this);
        this.vistas.JMenuEliminarProveedor.addActionListener(this);
        this.vistas.JMenuReingresarProveedor.addActionListener(this);
        this.vistas.TablaProveedores.addMouseListener(this);
        this.vistas.JLabelProveedores.addMouseListener(this);
        this.vistas.txtBuscarProv.addKeyListener(this);
        this.vistas.InfoProv.addMouseListener(this);
        listarProveedores();
        llenarProveedores();

        //Metodo para autocompletar el proveedor al escribir en el combobox indicado
        AutoCompleteDecorator.decorate(vistas.cbxProveedorProd);
        AutoCompleteDecorator.decorate(vistas.cbxProveedorNC);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistas.btnRegistrarProv) {//Registrar proveedor a traves de la aplicacion
            if (vistas.txtCuitProv.getText().equals("") || vistas.txtNombreProv.getText().equals("")
                    || vistas.txtCelularProv.getText().equals("") || vistas.txtDireccionProv.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Los campos Cuit, Nombre, Celular y Direccion son obligatorios");
            } else {
                pr.setCuit(vistas.txtCuitProv.getText());
                pr.setNombre_proveedor(vistas.txtNombreProv.getText());
                pr.setCelular(vistas.txtCelularProv.getText());
                pr.setDireccion(vistas.txtDireccionProv.getText());
                String verificar = prDao.registrar(pr);
                switch (verificar) {
                    case "cuit_existe":
                        JOptionPane.showMessageDialog(null, "Ya hay un proveedor con este cuit");
                        break;
                    case "nombre_existe":
                        JOptionPane.showMessageDialog(null, "Ya hay un proveedor con este nombre");
                        break;
                    case "celular_existe":
                        JOptionPane.showMessageDialog(null, "Ya hay un proveedor con este celular");
                        break;
                    case "direccion_existe":
                        JOptionPane.showMessageDialog(null, "Ya hay un proveedor con esta direccion");
                        break;
                    case "registrado":
                        limpiarTabla();
                        listarProveedores();
                        limpiarPanel();
                        JOptionPane.showMessageDialog(null, "Proveedor registrado con exito");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error al registrar proveedor");
                        break;
                }
            }
        } else if (e.getSource() == vistas.btnModificarProv) {//Modificar Proveedor a traves de la aplicacion
            if (vistas.txtIdProv.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            } else {
                if (vistas.txtCuitProv.getText().equals("") || vistas.txtNombreProv.getText().equals("")
                        || vistas.txtCelularProv.getText().equals("") || vistas.txtDireccionProv.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Los campos Cuit, Nombre, Celular y Direccion son obligatorios");
                } else {
                    pr.setCuit(vistas.txtCuitProv.getText());
                    pr.setNombre_proveedor(vistas.txtNombreProv.getText());
                    pr.setCelular(vistas.txtCelularProv.getText());
                    pr.setDireccion(vistas.txtDireccionProv.getText());
                    pr.setId(Integer.parseInt(vistas.txtIdProv.getText()));
                    String verificar = prDao.modificar(pr);
                    switch (verificar) {
                        case "cuit_existe":
                            JOptionPane.showMessageDialog(null, "Ya hay un proveedor con este cuit");
                            break;
                        case "nombre_existe":
                            JOptionPane.showMessageDialog(null, "Ya hay un proveedor con este nombre");
                            break;
                        case "celular_existe":
                            JOptionPane.showMessageDialog(null, "Ya hay un proveedor con este celular");
                            break;
                        case "direccion_existe":
                            JOptionPane.showMessageDialog(null, "Ya hay un proveedor con esta direccion");
                            break;
                        case "modificado":
                            limpiarTabla();
                            listarProveedores();
                            limpiarPanel();
                            JOptionPane.showMessageDialog(null, "Proveedor modificado con exito");
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Error al modificar proveedor");
                            break;
                    }
                }
            }
        } else if (e.getSource() == vistas.JMenuEliminarProveedor) {//Eliminar Proveedor a traves de la aplicacion
            if (vistas.txtIdProv.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
            } else {
                int id = Integer.parseInt(vistas.txtIdProv.getText());
                if (prDao.accion("Inactivo", id)) {
                    limpiarTabla();
                    listarProveedores();
                    limpiarPanel();
                    JOptionPane.showMessageDialog(null, "Proveedor eliminado con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar Proveedor");
                }
            }
        } else if (e.getSource() == vistas.JMenuReingresarProveedor) {//Reingresar Proveedor a traves de la aplicacion
            if (vistas.txtIdProv.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para reingresar");
            } else {
                int id = Integer.parseInt(vistas.txtIdProv.getText());
                if (prDao.accion("Activo", id)) {
                    limpiarTabla();
                    listarProveedores();
                    limpiarPanel();
                    JOptionPane.showMessageDialog(null, "Proveedor reingresado con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al reingresar Proveedor");
                }
            }
        } else {
            limpiarPanel();
            vistas.btnRegistrarProv.setEnabled(true);
        }
    }

    public void listarProveedores() {
        Tabla color = new Tabla();
        vistas.TablaProveedores.setDefaultRenderer(vistas.TablaProveedores.getColumnClass(0), color);
        List<Proveedores> lista = prDao.ListaProveedores(vistas.txtBuscarProv.getText());
        modelo = (DefaultTableModel) vistas.TablaProveedores.getModel();
        Object[] ob = new Object[6]; //6 columnas de la tabla
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getCuit();
            ob[2] = lista.get(i).getNombre_proveedor();
            ob[3] = lista.get(i).getCelular();
            ob[4] = lista.get(i).getDireccion();
            ob[5] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        //Personalizar encabezado de la tabla Proveedores
        vistas.TablaProveedores.setModel(modelo);
        JTableHeader header = vistas.TablaProveedores.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.blue);
        header.setForeground(Color.white);
        vistas.txtIdProv.setEditable(false);
    }

    public void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void limpiarPanel() {
        vistas.txtIdProv.setText("");
        vistas.txtCuitProv.setText("");
        vistas.txtNombreProv.setText("");
        vistas.txtCelularProv.setText("");
        vistas.txtDireccionProv.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vistas.TablaProveedores) {
            int fila = vistas.TablaProveedores.rowAtPoint(e.getPoint());
            vistas.txtIdProv.setText(vistas.TablaProveedores.getValueAt(fila, 0).toString());
            vistas.txtCuitProv.setText(vistas.TablaProveedores.getValueAt(fila, 1).toString());
            vistas.txtNombreProv.setText(vistas.TablaProveedores.getValueAt(fila, 2).toString());
            vistas.txtCelularProv.setText(vistas.TablaProveedores.getValueAt(fila, 3).toString());
            vistas.txtDireccionProv.setText(vistas.TablaProveedores.getValueAt(fila, 4).toString());
            String estado = vistas.TablaProveedores.getValueAt(fila, 5).toString();
            if (estado.equals("Inactivo")) {
                vistas.JMenuEliminarProveedor.setVisible(false);
                vistas.JMenuReingresarProveedor.setVisible(true);
            } else {
                vistas.JMenuEliminarProveedor.setVisible(true);
                vistas.JMenuReingresarProveedor.setVisible(false);
            }
            vistas.btnRegistrarProv.setEnabled(false);
        } else if (e.getSource() == vistas.JLabelProveedores) {
            //Si se hace click sobre el menu proveedores, se muestra el detalle del menu que se encuentra en el tab con indice 5
            vistas.JTabbedPaneDetMenus.setSelectedIndex(6);
            limpiarTabla();
            listarProveedores();
            vistas.txtCuitProv.setEditable(true);
        } else if (e.getSource() == vistas.InfoProv) {
            JOptionPane.showMessageDialog(null, "Ver, buscar, registrar, modificar, eliminar y reingresar un Proveedor.", "FUNCION DEL MENU", JOptionPane.INFORMATION_MESSAGE);
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
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vistas.txtBuscarProv) {
            limpiarTabla();
            listarProveedores();
        }
    }

    /*Metodo para llenar los comboboxs de otros menus con los proveedores registrados en este menu*/
    public void llenarProveedores() {
        List<Proveedores> lista = prDao.ListaProveedores(vistas.txtBuscarProv.getText());
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getId();
            String nombre = lista.get(i).getNombre_proveedor();
            //Llenar combobox de proveedor en el menu productos
            vistas.cbxProveedorProd.addItem(new Combo(id, nombre));
            //Llenar combobox de proveedor en el menu nueva compra
            vistas.cbxProveedorNC.addItem(new Combo(id, nombre));
        }
    }
}
