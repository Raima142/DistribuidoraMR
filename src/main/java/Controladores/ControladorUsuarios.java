//Autor: MARIA ISABEL ROMANO
package Controladores;

import Modelos.Combo;
import Modelos.UsuariosDao;
import Modelos.Usuarios;
import Modelos.Tabla;
import Vistas.PanelPrincipal;
import java.awt.Color;
import java.awt.event.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.*;

public class ControladorUsuarios implements ActionListener, MouseListener, KeyListener {

    private Usuarios us;
    private UsuariosDao usDao;
    private PanelPrincipal vistas;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorUsuarios(Usuarios us, UsuariosDao usDao, PanelPrincipal vistas) {
        this.us = us;
        this.usDao = usDao;
        this.vistas = vistas;
        this.vistas.btnModificarUser.addActionListener(this);
        this.vistas.btnRegistrarUser.addActionListener(this);
        this.vistas.btnNuevoUser.addActionListener(this);
        this.vistas.JMenuEliminarUsuario.addActionListener(this);
        this.vistas.JMenuReingresarUsuario.addActionListener(this);
        this.vistas.TablaUsuarios.addMouseListener(this);
        this.vistas.JLabelUsuarios.addMouseListener(this);
        this.vistas.txtBuscarUser.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistas.btnRegistrarUser) {//Registrar usuario a traves de la aplicacion
            if (vistas.txtUsuarioUser.getText().equals("") || String.valueOf(vistas.txtClaveUser.getPassword()).equals("") || vistas.txtNombreUser.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Los campos Usuario, Clave y Nombre son obligatorios");
            } else {
                us.setUsuario(vistas.txtUsuarioUser.getText());
                us.setClave(String.valueOf(vistas.txtClaveUser.getPassword()));
                us.setNombre(vistas.txtNombreUser.getText());
                //Llamar a la clase Combo para obtener las cajas
                Combo itemCaja = (Combo) vistas.cbxCajaUser.getSelectedItem();
                //Luego obtenemos su id
                us.setId_caja(itemCaja.getId());
                us.setRol(vistas.cbxRolUser.getSelectedItem().toString());
                String verificar = usDao.registrar(us);
                switch (verificar) {
                    case "usuario_existe":
                        JOptionPane.showMessageDialog(null, "Ya hay un usuario con este usuario");
                        break;
                    case "nombre_existe":
                        JOptionPane.showMessageDialog(null, "Ya hay un usuario con este nombre");
                        break;
                    case "registrado":
                        limpiarTabla();
                        listarUsuarios();
                        limpiarPanel();
                        JOptionPane.showMessageDialog(null, "Usuario registrado con exito");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error al registrar usuario");
                        break;
                }
            }
        } else if (e.getSource() == vistas.btnModificarUser) {//Modificar usuario a traves de la aplicacion
            if (vistas.txtUsuarioUser.getText().equals("") || String.valueOf(vistas.txtClaveUser.getPassword()).equals("") || vistas.txtNombreUser.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Los campos Usuario, Clave y Nombre son obligatorios");
            } else {
                us.setUsuario(vistas.txtUsuarioUser.getText());
                us.setClave(String.valueOf(vistas.txtClaveUser.getPassword()));
                us.setNombre(vistas.txtNombreUser.getText());
                //Llamar a la clase Combo para obtener las cajas
                Combo itemCaja = (Combo) vistas.cbxCajaUser.getSelectedItem();
                //Luego obtenemos su id
                us.setId_caja(itemCaja.getId());
                us.setRol(vistas.cbxRolUser.getSelectedItem().toString());
                us.setId(Integer.parseInt(vistas.txtIdUser.getText()));
                String verificar = usDao.modificar(us);
                switch (verificar) {
                    case "usuario_existe":
                        JOptionPane.showMessageDialog(null, "Ya hay un usuario con este usuario");
                        break;
                    case "nombre_existe":
                        JOptionPane.showMessageDialog(null, "Ya hay un usuario con este nombre");
                        break;
                    case "modificado":
                        limpiarTabla();
                        listarUsuarios();
                        limpiarPanel();
                        JOptionPane.showMessageDialog(null, "Usuario modificado con exito");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error al modificar usuario");
                        break;
                }
            }
        } else if (e.getSource() == vistas.JMenuEliminarUsuario) {//Eliminar usuario a traves de la aplicacion
            if (vistas.txtIdUser.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
            } else {
                int id = Integer.parseInt(vistas.txtIdUser.getText());
                if (usDao.accion("Inactivo", id)) {
                    limpiarTabla();
                    listarUsuarios();
                    limpiarPanel();
                    JOptionPane.showMessageDialog(null, "Usuario eliminado con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar usuario");
                }
            }
        } else if (e.getSource() == vistas.JMenuReingresarUsuario) {//Reingresar usuario a traves de la aplicacion
            if (vistas.txtIdUser.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para reingresar");
            } else {
                int id = Integer.parseInt(vistas.txtIdUser.getText());
                if (usDao.accion("Activo", id)) {
                    limpiarTabla();
                    listarUsuarios();
                    limpiarPanel();
                    JOptionPane.showMessageDialog(null, "Usuario reingresado con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al reingresar usuario");
                }
            }
        } else {
            limpiarPanel();
            vistas.btnRegistrarUser.setEnabled(true);
        }
    }

    public void listarUsuarios() {
        Tabla color = new Tabla();
        vistas.TablaUsuarios.setDefaultRenderer(vistas.TablaUsuarios.getColumnClass(0), color);
        List<Usuarios> lista = usDao.ListaUsuarios(vistas.txtBuscarUser.getText());
        modelo = (DefaultTableModel) vistas.TablaUsuarios.getModel();
        Object[] ob = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getUsuario();
            ob[2] = lista.get(i).getNombre();
            ob[3] = lista.get(i).getId_caja();
            ob[4] = lista.get(i).getTipo_caja();
            ob[5] = lista.get(i).getRol();
            ob[6] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        //Personalizar encabezado de la tabla usuarios
        vistas.TablaUsuarios.setModel(modelo);
        JTableHeader header = vistas.TablaUsuarios.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.blue);
        header.setForeground(Color.white);
        vistas.txtIdUser.setEditable(false);
    }

    public void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void limpiarPanel() {
        vistas.txtIdUser.setText("");
        vistas.txtUsuarioUser.setText("");
        vistas.txtNombreUser.setText("");
        vistas.txtClaveUser.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vistas.TablaUsuarios) {
            int fila = vistas.TablaUsuarios.rowAtPoint(e.getPoint());
            vistas.txtIdUser.setText(vistas.TablaUsuarios.getValueAt(fila, 0).toString());
            vistas.txtUsuarioUser.setText(vistas.TablaUsuarios.getValueAt(fila, 1).toString());
            vistas.txtNombreUser.setText(vistas.TablaUsuarios.getValueAt(fila, 2).toString());
            int id_caja = Integer.parseInt(vistas.TablaUsuarios.getValueAt(fila, 3).toString());
            String caja = vistas.TablaUsuarios.getValueAt(fila, 4).toString();
            vistas.cbxCajaUser.setSelectedItem(new Combo(id_caja, caja));
            vistas.cbxRolUser.setSelectedItem(vistas.TablaUsuarios.getValueAt(fila, 5).toString());
            String estado = vistas.TablaUsuarios.getValueAt(fila, 6).toString();
            if (estado.equals("Inactivo")) {
                vistas.JMenuEliminarUsuario.setVisible(false);
                vistas.JMenuReingresarUsuario.setVisible(true);
            } else {
                vistas.JMenuEliminarUsuario.setVisible(true);
                vistas.JMenuReingresarUsuario.setVisible(false);
            }
            vistas.btnRegistrarUser.setEnabled(false);
        } else if (e.getSource() == vistas.JLabelUsuarios) {
            //Si se hace click sobre el menu usuarios, se muestra el detalle del menu que se encuentra en el tab con indice 1
            vistas.JTabbedPaneDetMenus.setSelectedIndex(2);
            limpiarTabla();
            listarUsuarios();
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
        if (e.getSource() == vistas.txtBuscarUser) {
            limpiarTabla();
            listarUsuarios();
        }
    }
}
