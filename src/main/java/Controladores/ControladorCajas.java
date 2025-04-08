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

public class ControladorCajas implements ActionListener, MouseListener, KeyListener {

    private Cajas cj;
    private CajasDao cjDao;
    private PanelPrincipal vistas;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorCajas(Cajas cj, CajasDao cjDao, PanelPrincipal vistas) {
        this.cj = cj;
        this.cjDao = cjDao;
        this.vistas = vistas;
        this.vistas.btnModificarCaja.addActionListener(this);
        this.vistas.btnRegistrarCaja.addActionListener(this);
        this.vistas.btnNuevoCaja.addActionListener(this);
        this.vistas.JMenuEliminarCaja.addActionListener(this);
        this.vistas.JMenuReingresarCaja.addActionListener(this);
        this.vistas.TablaCajas.addMouseListener(this);
        this.vistas.JLabelCaja.addMouseListener(this);
        this.vistas.txtBuscarCaja.addKeyListener(this);
        this.vistas.InfoCaja.addMouseListener(this);
        this.vistas.btnAbrirCajaAC.addActionListener(this);
        this.vistas.btnCerrarCajaAC.addActionListener(this);
        this.vistas.btnNuevaCajaAC.addActionListener(this);
        this.vistas.btnVerAC.addActionListener(this);
        llenarCajas();
        listarAperturas();
        //Metodo para autocompletar la caja al escribir en el combobox de caja
        AutoCompleteDecorator.decorate(vistas.cbxCajaUser);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistas.btnRegistrarCaja) {//Registrar caja a traves de la aplicacion
            if (vistas.txtNombreCaja.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "El nombre de la caja es obligatorio");
            } else {
                cj.setNombre(vistas.txtNombreCaja.getText());
                String verificar = cjDao.registrar(cj);
                switch (verificar) {
                    case "nombre_existe":
                        JOptionPane.showMessageDialog(null, "Ya hay una caja con este nombre");
                        break;
                    case "registrada":
                        limpiarTabla();
                        listarCajas();
                        limpiarPanel();
                        JOptionPane.showMessageDialog(null, "Caja registrada con exito");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error al registrar caja");
                        break;
                }
            }
        } else if (e.getSource() == vistas.btnModificarCaja) {//Modificar Caja a traves de la aplicacion
            if (vistas.txtIdCaja.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            } else {
                if (vistas.txtNombreCaja.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "El campo nombre es obligatorio");
                } else {
                    cj.setNombre(vistas.txtNombreCaja.getText());
                    cj.setId(Integer.parseInt(vistas.txtIdCaja.getText()));
                    String verificar = cjDao.modificar(cj);
                    switch (verificar) {
                        case "nombre_existe":
                            JOptionPane.showMessageDialog(null, "Ya hay una caja con este nombre");
                            break;
                        case "registrada":
                            limpiarTabla();
                            listarCajas();
                            limpiarPanel();
                            JOptionPane.showMessageDialog(null, "Caja modificada con exito");
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Error al modificar caja");
                            break;
                    }
                }
            }
        } else if (e.getSource() == vistas.JMenuEliminarCaja) {//Eliminar Caja a traves de la aplicacion
            if (vistas.txtIdCaja.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
            } else {
                int id = Integer.parseInt(vistas.txtIdCaja.getText());
                if (cjDao.accion("Inactivo", id)) {
                    limpiarTabla();
                    listarCajas();
                    limpiarPanel();
                    JOptionPane.showMessageDialog(null, "Caja eliminada con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar Caja");
                }
            }
        } else if (e.getSource() == vistas.JMenuReingresarCaja) {//Reingresar Caja a traves de la aplicacion
            if (vistas.txtIdCaja.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para reingresar");
            } else {
                int id = Integer.parseInt(vistas.txtIdCaja.getText());
                if (cjDao.accion("Activo", id)) {
                    limpiarTabla();
                    listarCajas();
                    limpiarPanel();
                    JOptionPane.showMessageDialog(null, "Caja reingresada con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al reingresar Caja");
                }
            }
        } else if (e.getSource() == vistas.btnAbrirCajaAC) {//Apertura y Cierre
            if (vistas.txtMontoInicialAC.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese el monto inicial");
            } else {
                double monto = Double.parseDouble(vistas.txtMontoInicialAC.getText());
                int id_user = Integer.parseInt(vistas.txtIdUsuario.getText());
                String resultado = cjDao.abrirCaja(monto, id_user);
                if ("existe".equalsIgnoreCase(resultado)) {
                    JOptionPane.showMessageDialog(null, "La caja ya esta abierta");
                } else if ("registrado".equalsIgnoreCase(resultado)) {
                    limpiarTabla();//verificar si estas dos lineas no son
                    listarAperturas();//necesarias
                    nuevaApertura();
                    JOptionPane.showMessageDialog(null, "Caja abierta");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al abrir la caja");
                }
            }
        } else if (e.getSource() == vistas.btnCerrarCajaAC) {//Apertura y Cierre
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de cerrar la caja?");
            if (pregunta == 0) {//Si, quiero cerrarla
                double montoFinal = cjDao.montoFinal(Integer.parseInt(vistas.txtIdUsuario.getText()));
                System.out.println(montoFinal);
                int totalVentas = cjDao.totalVentas(Integer.parseInt(vistas.txtIdUsuario.getText()));
                System.out.println(totalVentas);
                double montoInicial = cjDao.obtenerMontoInicial(Integer.parseInt(vistas.txtIdUsuario.getText()));
                AperturaCierre ac = new AperturaCierre();
                if (totalVentas > 0) {
                    //Sumar el monto inicial al monto final si es que hay ventas
                    ac.setMonto_final(montoFinal + montoInicial);
                } else {
                    //Si no hay ventas solo usar el monto inicial
                    ac.setMonto_final(montoInicial);
                }
                ac.setTotal_ventas(totalVentas);
                ac.setId_usuario(Integer.parseInt(vistas.txtIdUsuario.getText()));
                if (cjDao.cerrarCaja(ac)) {
                    JOptionPane.showMessageDialog(null, "Caja cerrada");
                    limpiarTabla();
                    listarAperturas();
                    nuevaApertura();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al cerrar la caja");
                }
            }
        } else if (e.getSource() == vistas.btnNuevaCajaAC) {//Apertura y Cierre
            nuevaApertura();
        } else if (e.getSource() == vistas.btnVerAC) {
            vistas.JTabbedPaneDetMenus.setSelectedIndex(4);
            listarAperturas();
        } else {
            limpiarPanel();
            vistas.btnRegistrarCaja.setEnabled(true);
        }
    }

    public void listarCajas() {
        Tabla color = new Tabla();//Llamar a la clase Tabla
        vistas.TablaCajas.setDefaultRenderer(vistas.TablaCajas.getColumnClass(0), color);//Para aplicar color a columna estado
        List<Cajas> lista = cjDao.ListaCajas(vistas.txtBuscarCaja.getText());
        modelo = (DefaultTableModel) vistas.TablaCajas.getModel();
        Object[] ob = new Object[3]; //3 columnas de la tabla
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        //Personalizar encabezado de la tabla Caja
        vistas.TablaCajas.setModel(modelo);
        JTableHeader header = vistas.TablaCajas.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.blue);
        header.setForeground(Color.white);
        //No permitir que el usuario pueda modificar el id
        vistas.txtIdCaja.setEditable(false);
    }

    public void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void limpiarPanel() {
        vistas.txtIdCaja.setText("");
        vistas.txtNombreCaja.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vistas.TablaCajas) {
            int fila = vistas.TablaCajas.rowAtPoint(e.getPoint());
            vistas.txtIdCaja.setText(vistas.TablaCajas.getValueAt(fila, 0).toString());
            vistas.txtNombreCaja.setText(vistas.TablaCajas.getValueAt(fila, 1).toString());
            String estado = vistas.TablaCajas.getValueAt(fila, 2).toString();
            if (estado.equals("Inactivo")) {
                vistas.JMenuEliminarCaja.setVisible(false);
                vistas.JMenuReingresarCaja.setVisible(true);
            } else {
                vistas.JMenuEliminarCaja.setVisible(true);
                vistas.JMenuReingresarCaja.setVisible(false);
            }
            vistas.btnRegistrarCaja.setEnabled(false);
        } else if (e.getSource() == vistas.JLabelCaja) {
            //Si se hace click sobre el menu Caja, se muestra el detalle del menu que se encuentra en el tab con indice 3
            vistas.JTabbedPaneDetMenus.setSelectedIndex(3);
            limpiarTabla();
            listarCajas();
        } else if (e.getSource() == vistas.InfoCaja) {
            JOptionPane.showMessageDialog(null, "Ver, buscar, registrar, modificar, eliminar y reingresar una Caja.", "FUNCION DEL MENU", JOptionPane.INFORMATION_MESSAGE);
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
        if (e.getSource() == vistas.txtBuscarCaja) {
            limpiarTabla();
            listarCajas();
        }
    }

    /*Metodo para llenar el combobox de caja en el menu usuarios, con las cajas 
    que hemos registrado en el menu cajas*/
    public void llenarCajas() {
        List<Cajas> lista = cjDao.ListaCajas(vistas.txtBuscarCaja.getText());
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getId();
            String nombre = lista.get(i).getNombre();
            vistas.cbxCajaUser.addItem(new Combo(id, nombre));
        }
    }

//Metodos para Apertura y Cierre de cajas
    private void nuevaApertura() {
        vistas.txtMontoInicialAC.setText("");
        vistas.txtBuscarCajaAC.setText("");
    }

    public void listarAperturas() {
        List<AperturaCierre> lista = cjDao.ListaAperturas(vistas.txtBuscarCajaAC.getText());
        modelo = (DefaultTableModel) vistas.TablaAperturaYCierre.getModel();
        Object[] ob = new Object[6]; //6 columnas de la tabla
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getFecha_apertura();
            ob[1] = lista.get(i).getFecha_cierre();
            ob[2] = lista.get(i).getMonto_inicial();
            ob[3] = lista.get(i).getMonto_final();
            ob[4] = lista.get(i).getTotal_ventas();
            ob[5] = lista.get(i).getNombre_usuario();
            modelo.addRow(ob);
        }
        //Personalizar encabezado de la tabla AC
        vistas.TablaAperturaYCierre.setModel(modelo);
        JTableHeader header = vistas.TablaAperturaYCierre.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.blue);
        header.setForeground(Color.white);
    }
}
