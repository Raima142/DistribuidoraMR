//Autor: MARIA ISABEL ROMANO
package Controladores;

import Vistas.PanelPrincipal;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class ControladorPanelPrincipal implements MouseListener {
    private PanelPrincipal vistas;

    public ControladorPanelPrincipal(PanelPrincipal vistas) {
        this.vistas = vistas;
        //Colocamos el PanelPrincipal en el centro
        this.vistas.setLocationRelativeTo(null);
        //Poner las etiquetas a la escucha de lo que haga el mouse
        this.vistas.JLabelConfiguracion.addMouseListener(this);
        this.vistas.JLabelUsuarios.addMouseListener(this);
        this.vistas.JLabelCaja.addMouseListener(this);
        this.vistas.JLabelClientes.addMouseListener(this);
        this.vistas.JLabelProveedores.addMouseListener(this);
        this.vistas.JLabelCategorias.addMouseListener(this);
        this.vistas.JLabelProductos.addMouseListener(this);
        this.vistas.JLabelNuevaCompra.addMouseListener(this);
        this.vistas.JLabelNuevaVenta.addMouseListener(this);
        this.vistas.btnSalirDelPanel.addMouseListener(this);
        this.vistas.btnMinimizarPanel.addMouseListener(this);
        this.vistas.btnActualizarPanel.addMouseListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vistas.btnSalirDelPanel) {
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea salir?", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(pregunta == 0){
                System.exit(0);
            }
        }else if (e.getSource() == vistas.btnMinimizarPanel){
            //Minimizar panel
            vistas.setState(PanelPrincipal.ICONIFIED);
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
        //Si el mouse esta sobre el elemento indicado, pintar del color indicado
        if(e.getSource() == vistas.JLabelConfiguracion){
            vistas.JPanelConfiguracion.setBackground(new Color(0,102,205));
        }else if(e.getSource() == vistas.JLabelUsuarios){
            vistas.JPanelUsuarios.setBackground(new Color(0,102,205));
        }else if(e.getSource() == vistas.JLabelCaja){
            vistas.JPanelCaja.setBackground(new Color(0,102,205));
        }else if(e.getSource() == vistas.JLabelClientes){
            vistas.JPanelClientes.setBackground(new Color(0,102,205));
        }else if(e.getSource() == vistas.JLabelProveedores){
            vistas.JPanelProveedores.setBackground(new Color(0,102,205));
        }else if(e.getSource() == vistas.JLabelCategorias){
            vistas.JPanelCategorias.setBackground(new Color(0,102,205));
        }else if(e.getSource() == vistas.JLabelProductos){
            vistas.JPanelProductos.setBackground(new Color(0,102,205));
        }else if(e.getSource() == vistas.JLabelNuevaCompra){
            vistas.JPanelNuevaCompra.setBackground(new Color(0,102,205));
        }else if(e.getSource() == vistas.JLabelNuevaVenta){
            vistas.JPanelNuevaVenta.setBackground(new Color(0,102,205));
        }else if(e.getSource() == vistas.btnSalirDelPanel){
            vistas.btnSalirDelPanel.setBackground(new Color(102,102,102));
        }else if(e.getSource() == vistas.btnMinimizarPanel){
            vistas.btnMinimizarPanel.setBackground(new Color(102,102,102));
        }else if(e.getSource() == vistas.btnActualizarPanel){
            vistas.btnActualizarPanel.setBackground(new Color(102,102,102));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //Si el mouse ya no esta sobre el elemento indicado, regresar al color original
        if(e.getSource() == vistas.JLabelConfiguracion){
            vistas.JPanelConfiguracion.setBackground(new Color(51,51,51));
        }else if(e.getSource() == vistas.JLabelUsuarios){
            vistas.JPanelUsuarios.setBackground(new Color(51,51,51));
        }else if(e.getSource() == vistas.JLabelCaja){
            vistas.JPanelCaja.setBackground(new Color(51,51,51));
        }else if(e.getSource() == vistas.JLabelClientes){
            vistas.JPanelClientes.setBackground(new Color(51,51,51));
        }else if(e.getSource() == vistas.JLabelProveedores){
            vistas.JPanelProveedores.setBackground(new Color(51,51,51));
        }else if(e.getSource() == vistas.JLabelCategorias){
            vistas.JPanelCategorias.setBackground(new Color(51,51,51));
        }else if(e.getSource() == vistas.JLabelProductos){
            vistas.JPanelProductos.setBackground(new Color(51,51,51));
        }else if(e.getSource() == vistas.JLabelNuevaCompra){
            vistas.JPanelNuevaCompra.setBackground(new Color(51,51,51));
        }else if(e.getSource() == vistas.JLabelNuevaVenta){
            vistas.JPanelNuevaVenta.setBackground(new Color(51,51,51));
        }else if(e.getSource() == vistas.btnSalirDelPanel){
            vistas.btnSalirDelPanel.setBackground(new Color(51,51,51));
        }else if(e.getSource() == vistas.btnMinimizarPanel){
            vistas.btnMinimizarPanel.setBackground(new Color(51,51,51));
        }else if(e.getSource() == vistas.btnActualizarPanel){
            vistas.btnActualizarPanel.setBackground(new Color(51,51,51));
        }
    }
}
