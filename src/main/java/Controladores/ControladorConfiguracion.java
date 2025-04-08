//Autor: MARIA ISABEL ROMANO
package Controladores;

import Modelos.*;
import Vistas.PanelPrincipal;
import java.awt.Color;
import java.awt.event.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.*;

public class ControladorConfiguracion implements ActionListener, MouseListener, KeyListener {

    private Configuracion conf;
    private ConfiguracionDao confDao;
    private PanelPrincipal vistas;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorConfiguracion(Configuracion conf, ConfiguracionDao confDao, PanelPrincipal vistas) {
        this.conf = conf;
        this.confDao = confDao;
        this.vistas = vistas;
        this.vistas.btnModificarEmpresa.addActionListener(this);
        this.vistas.TablaEmpresa.addMouseListener(this);
        this.vistas.JLabelConfiguracion.addMouseListener(this);
        this.vistas.InfoConf.addMouseListener(this);
        listarEmpresa();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistas.btnModificarEmpresa) {//Modificar empresa a traves de la aplicacion
            if (vistas.txtIdEmpresa.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            } else {
                if (vistas.txtCuitEmpresa.getText().equals("") ||vistas.txtNombreEmpresa.getText().equals("") ||
                    vistas.txtCelularEmpresa.getText().equals("") || vistas.txtDireccionEmpresa.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Ningun campo puede estar vacio");
                } else {
                    conf.setCuit(vistas.txtCuitEmpresa.getText());
                    conf.setNombre_empresa(vistas.txtNombreEmpresa.getText());
                    conf.setCelular(vistas.txtCelularEmpresa.getText());
                    conf.setDireccion(vistas.txtDireccionEmpresa.getText());
                    conf.setId(Integer.parseInt(vistas.txtIdEmpresa.getText()));
                    if (confDao.modificar(conf)) {
                        limpiarTabla();
                        listarEmpresa();
                        limpiarPanel();
                        JOptionPane.showMessageDialog(null, "Empresa modificada con exito");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al modificar empresa");
                    }
                }
            }
        } else {
            limpiarPanel();
        }
    }

    public void listarEmpresa() {
        List<Configuracion> lista = confDao.ListaConfiguracion(vistas.txtIdEmpresa.getText());
        modelo = (DefaultTableModel) vistas.TablaEmpresa.getModel();
        Object[] ob = new Object[5]; //5 columnas de la tabla
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getCuit();
            ob[2] = lista.get(i).getNombre_empresa();
            ob[3] = lista.get(i).getCelular();
            ob[4] = lista.get(i).getDireccion();
            modelo.addRow(ob);
        }
        //Personalizar encabezado de la tabla empresa
        vistas.TablaEmpresa.setModel(modelo);
        JTableHeader header = vistas.TablaEmpresa.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.blue);
        header.setForeground(Color.white);
        //No permitir que el usuario pueda modificar el id de la empresa
        vistas.txtIdEmpresa.setEditable(false);
    }

    public void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void limpiarPanel() {
        vistas.txtIdEmpresa.setText("");
        vistas.txtCuitEmpresa.setText("");
        vistas.txtNombreEmpresa.setText("");
        vistas.txtCelularEmpresa.setText("");
        vistas.txtDireccionEmpresa.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vistas.TablaEmpresa) {
            int fila = vistas.TablaEmpresa.rowAtPoint(e.getPoint());
            vistas.txtIdEmpresa.setText(vistas.TablaEmpresa.getValueAt(fila, 0).toString());
            vistas.txtCuitEmpresa.setText(vistas.TablaEmpresa.getValueAt(fila, 1).toString());
            vistas.txtNombreEmpresa.setText(vistas.TablaEmpresa.getValueAt(fila, 2).toString());
            vistas.txtCelularEmpresa.setText(vistas.TablaEmpresa.getValueAt(fila, 3).toString());
            vistas.txtDireccionEmpresa.setText(vistas.TablaEmpresa.getValueAt(fila, 4).toString());
        } else if (e.getSource() == vistas.JLabelConfiguracion) {
            //Si se hace click sobre el menu configuracion, se muestra el detalle del menu que se encuentra en el tab con indice 0
            vistas.JTabbedPaneDetMenus.setSelectedIndex(1);
            limpiarTabla();
            listarEmpresa();
        } else if (e.getSource() == vistas.InfoConf){
            JOptionPane.showMessageDialog(null, "   Ver y modificar datos de la empresa.   ", "FUNCION DEL MENU", JOptionPane.INFORMATION_MESSAGE); 
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
    }
}
