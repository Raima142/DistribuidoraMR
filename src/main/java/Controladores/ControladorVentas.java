//Autor: MARIA ISABEL ROMANO
package Controladores;

import Modelos.*;
import Vistas.PanelPrincipal;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;

public class ControladorVentas implements ActionListener, MouseListener, KeyListener {

    private Ventas vt;
    private VentasDao vtDao;
    private PanelPrincipal vistas;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorVentas(Ventas vt, VentasDao vtDao, PanelPrincipal vistas) {
        this.vt = vt;
        this.vtDao = vtDao;
        this.vistas = vistas;
        this.vistas.TablaVentas.addMouseListener(this);
        this.vistas.txtBuscarVenta.addKeyListener(this);
        this.vistas.btnHistorialVentas.addActionListener(this);
        listarVentas();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistas.btnHistorialVentas) {
            if (vistas.txtIdVenta.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            } else {
                int id_venta = Integer.parseInt(vistas.txtIdVenta.getText());
                String nomVendedor = vistas.txtNomUsuario.getText();
                ProductosDao vtDao = new ProductosDao();
                vtDao.generarFacturaVenta(id_venta, nomVendedor);
            }
        } 
    }

    public void listarVentas() {
        List<Ventas> lista = vtDao.ListaVentas(vistas.txtBuscarVenta.getText());
        modelo = (DefaultTableModel) vistas.TablaVentas.getModel();
        Object[] ob = new Object[4]; //4 columnas de la tabla
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombreCliente();
            ob[2] = lista.get(i).getTotal();
            ob[3] = lista.get(i).getFecha();
            modelo.addRow(ob);
        }
        //Personalizar encabezado de la tabla Ventas
        vistas.TablaVentas.setModel(modelo);
        JTableHeader header = vistas.TablaVentas.getTableHeader();
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
        if (e.getSource() == vistas.TablaVentas) {//Elementos de la tabla
            int fila = vistas.TablaVentas.rowAtPoint(e.getPoint());
            vistas.txtIdVenta.setText(vistas.TablaVentas.getValueAt(fila, 0).toString());
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
        if (e.getSource() == vistas.txtBuscarVenta) {
            limpiarTabla();
            listarVentas();
        } 
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}