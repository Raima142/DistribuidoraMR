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

public class ControladorClientes implements ActionListener, MouseListener, KeyListener {

    private Clientes cl;
    private ClientesDao clDao;
    private PanelPrincipal vistas;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorClientes(Clientes cl, ClientesDao clDao, PanelPrincipal vistas) {
        this.cl = cl;
        this.clDao = clDao;
        this.vistas = vistas;
        this.vistas.btnModificarCli.addActionListener(this);
        this.vistas.btnRegistrarCli.addActionListener(this);
        this.vistas.btnNuevoCli.addActionListener(this);
        this.vistas.JMenuEliminarCliente.addActionListener(this);
        this.vistas.JMenuReingresarCliente.addActionListener(this);
        this.vistas.TablaClientes.addMouseListener(this);
        this.vistas.JLabelClientes.addMouseListener(this);
        this.vistas.txtBuscarCli.addKeyListener(this);
        this.vistas.InfoCli.addMouseListener(this);
        listarClientes();
        llenarClientes();
        AutoCompleteDecorator.decorate(vistas.cbxClienteNV);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistas.btnRegistrarCli) {
            if (vistas.txtNombreCli.getText().equals("") || vistas.txtCelularCli.getText().equals("") || vistas.txtDireccionCli.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Los campos Nombre, Celular y Direccion son obligatorios");
            } else {
                cl.setNombre_cliente(vistas.txtNombreCli.getText());
                cl.setCelular(vistas.txtCelularCli.getText());
                cl.setDireccion(vistas.txtDireccionCli.getText());
                String verificar = clDao.registrar(cl);
                switch (verificar) {
                    case "nombre_existe":
                        JOptionPane.showMessageDialog(null, "Ya hay un cliente con este nombre");
                        break;
                    case "celular_existe":
                        JOptionPane.showMessageDialog(null, "Ya hay un cliente con este celular");
                        break;
                    case "direccion_existe":
                        JOptionPane.showMessageDialog(null, "Ya hay un cliente con esta direccion");
                        break;
                    case "registrado":
                        limpiarTabla();
                        listarClientes();
                        limpiarPanel();
                        JOptionPane.showMessageDialog(null, "Cliente registrado con exito");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error al registrar cliente");
                        break;
                }
            }
        } else if (e.getSource() == vistas.btnModificarCli) {//Modificar cliente a traves de la aplicacion
            if (vistas.txtIdCli.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            } else {
                if (vistas.txtNombreCli.getText().equals("") || vistas.txtCelularCli.getText().equals("") || vistas.txtDireccionCli.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                } else {
                    cl.setNombre_cliente(vistas.txtNombreCli.getText());
                    cl.setCelular(vistas.txtCelularCli.getText());
                    cl.setDireccion(vistas.txtDireccionCli.getText());
                    cl.setId(Integer.parseInt(vistas.txtIdCli.getText()));
                    String verificar = clDao.modificar(cl);
                    switch (verificar) {
                        case "nombre_existe":
                            JOptionPane.showMessageDialog(null, "Ya hay un cliente con este nombre");
                            break;
                        case "celular_existe":
                            JOptionPane.showMessageDialog(null, "Ya hay un cliente con este celular");
                            break;
                        case "direccion_existe":
                            JOptionPane.showMessageDialog(null, "Ya hay un cliente con esta direccion");
                            break;
                        case "modificado":
                            limpiarTabla();
                            listarClientes();
                            limpiarPanel();
                            JOptionPane.showMessageDialog(null, "Cliente modificado con exito");
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Error al modificar cliente");
                            break;
                    }
                }
            }
        } else if (e.getSource() == vistas.JMenuEliminarCliente) {//Eliminar cliente a traves de la aplicacion
            if (vistas.txtIdCli.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
            } else {
                int id = Integer.parseInt(vistas.txtIdCli.getText());
                if (clDao.accion("Inactivo", id)) {
                    limpiarTabla();
                    listarClientes();
                    limpiarPanel();
                    JOptionPane.showMessageDialog(null, "Cliente eliminado con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar cliente");
                }
            }
        } else if (e.getSource() == vistas.JMenuReingresarCliente) {//Reingresar cliente a traves de la aplicacion
            if (vistas.txtIdCli.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para reingresar");
            } else {
                int id = Integer.parseInt(vistas.txtIdCli.getText());
                if (clDao.accion("Activo", id)) {
                    limpiarTabla();
                    listarClientes();
                    limpiarPanel();
                    JOptionPane.showMessageDialog(null, "Cliente reingresado con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al reingresar cliente");
                }
            }
        } else {
            limpiarPanel();
            vistas.btnRegistrarCli.setEnabled(true);
        }
    }

    public void listarClientes() {
        Tabla color = new Tabla();
        vistas.TablaClientes.setDefaultRenderer(vistas.TablaClientes.getColumnClass(0), color);
        List<Clientes> lista = clDao.ListaClientes(vistas.txtBuscarCli.getText());
        modelo = (DefaultTableModel) vistas.TablaClientes.getModel();
        Object[] ob = new Object[5]; //5 columnas de la tabla
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre_cliente();
            ob[2] = lista.get(i).getCelular();
            ob[3] = lista.get(i).getDireccion();
            ob[4] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        //Personalizar encabezado de la tabla clientes
        vistas.TablaClientes.setModel(modelo);
        JTableHeader header = vistas.TablaClientes.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.blue);
        header.setForeground(Color.white);
        vistas.txtIdCli.setEditable(false);
    }

    public void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void limpiarPanel() {
        vistas.txtIdCli.setText("");
        vistas.txtNombreCli.setText("");
        vistas.txtCelularCli.setText("");
        vistas.txtDireccionCli.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vistas.TablaClientes) {
            int fila = vistas.TablaClientes.rowAtPoint(e.getPoint());
            vistas.txtIdCli.setText(vistas.TablaClientes.getValueAt(fila, 0).toString());
            vistas.txtNombreCli.setText(vistas.TablaClientes.getValueAt(fila, 1).toString());
            vistas.txtCelularCli.setText(vistas.TablaClientes.getValueAt(fila, 2).toString());
            vistas.txtDireccionCli.setText(vistas.TablaClientes.getValueAt(fila, 3).toString());
            String estado = vistas.TablaClientes.getValueAt(fila, 4).toString();
            if (estado.equals("Inactivo")) {
                vistas.JMenuEliminarCliente.setVisible(false);
                vistas.JMenuReingresarCliente.setVisible(true);
            } else {
                vistas.JMenuEliminarCliente.setVisible(true);
                vistas.JMenuReingresarCliente.setVisible(false);
            }
            vistas.btnRegistrarCli.setEnabled(false);
        } else if (e.getSource() == vistas.JLabelClientes) {
            //Si se hace click sobre el menu clientes, se muestra el detalle del menu que se encuentra en el tab con indice 4
            vistas.JTabbedPaneDetMenus.setSelectedIndex(5);
            limpiarTabla();
            listarClientes();
        } else if (e.getSource() == vistas.InfoCli) {
            JOptionPane.showMessageDialog(null, "Ver, buscar, registrar, modificar, eliminar y reingresar un Cliente.", "FUNCION DEL MENU", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void llenarClientes() {
        List<Clientes> lista = clDao.ListaClientes(vistas.txtBuscarCli.getText());
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getId();
            String nombre = lista.get(i).getNombre_cliente();
            //Llenar combobox de cliente en el menu nueva venta
            vistas.cbxClienteNV.addItem(new Combo(id, nombre));
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
        if (e.getSource() == vistas.txtBuscarCli) {
            limpiarTabla();
            listarClientes();
        }
    }
}
