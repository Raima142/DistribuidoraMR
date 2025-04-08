//Autor: MARIA ISABEL ROMANO
package Controladores;

import Modelos.*;
import Vistas.PanelPrincipal;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;

public class ControladorCompras implements ActionListener, MouseListener, KeyListener {

    private Compras cp;
    private ComprasDao cpDao;
    private PanelPrincipal vistas;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorCompras(Compras cp, ComprasDao cpDao, PanelPrincipal vistas) {
        this.cp = cp;
        this.cpDao = cpDao;
        this.vistas = vistas;
        this.vistas.TablaCompras.addMouseListener(this);
        this.vistas.txtBuscarCompra.addKeyListener(this);
        this.vistas.btnHistorialCompras.addActionListener(this);
        listarCompras();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistas.btnHistorialCompras) {//Registrar producto a traves de la aplicacion
            if (vistas.txtIdCompra.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            } else {
                int id_compra = Integer.parseInt(vistas.txtIdCompra.getText());
                String nomComprador = vistas.txtNomUsuario.getText();
                ProductosDao pdDao = new ProductosDao();
                pdDao.generarFacturaCompra(id_compra, nomComprador);
            }
        } 
    }

    public void listarCompras() {
        List<Compras> lista = cpDao.ListaCompras(vistas.txtBuscarCompra.getText());
        modelo = (DefaultTableModel) vistas.TablaCompras.getModel();
        Object[] ob = new Object[4]; //4 columnas de la tabla
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombreProveedor();
            ob[2] = lista.get(i).getTotal();
            ob[3] = lista.get(i).getFecha();
            modelo.addRow(ob);
        }
        //Personalizar encabezado de la tabla Compras
        vistas.TablaCompras.setModel(modelo);
        JTableHeader header = vistas.TablaCompras.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.blue);
        header.setForeground(Color.white);
    }

    public void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vistas.TablaCompras) {//Elementos de la tabla
            int fila = vistas.TablaCompras.rowAtPoint(e.getPoint());
            vistas.txtIdCompra.setText(vistas.TablaCompras.getValueAt(fila, 0).toString());
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
        if (e.getSource() == vistas.txtBuscarCompra) {
            limpiarTabla();
            listarCompras();
        } 
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
