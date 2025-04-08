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

public class ControladorCategorias implements ActionListener, MouseListener, KeyListener {
    private Categorias ca;
    private CategoriasDao caDao;
    private PanelPrincipal vistas;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorCategorias(Categorias ca, CategoriasDao caDao, PanelPrincipal vistas) {
        this.ca = ca;
        this.caDao = caDao;
        this.vistas = vistas;
        this.vistas.btnModificarCat.addActionListener(this);
        this.vistas.btnRegistrarCat.addActionListener(this);
        this.vistas.btnNuevoCat.addActionListener(this);
        this.vistas.JMenuEliminarCategoria.addActionListener(this);
        this.vistas.JMenuReingresarCategoria.addActionListener(this);
        this.vistas.TablaCategorias.addMouseListener(this);
        this.vistas.JLabelCategorias.addMouseListener(this);
        this.vistas.txtBuscarCat.addKeyListener(this);
        this.vistas.InfoCat.addMouseListener(this);
        listarCategorias();
        llenarCategorias();
        //Metodo para autocompletar el proveedor al escribir en el combobox de proveedor
        AutoCompleteDecorator.decorate(vistas.cbxCategoriaProd);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistas.btnRegistrarCat) {//Registrar categoria a traves de la aplicacion
            if (vistas.txtNombreCat.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "El nombre de la categoria es obligatorio");
            } else {
                ca.setNombre_categoria(vistas.txtNombreCat.getText());
                String verificar = caDao.registrar(ca);
                switch (verificar) {
                    case "nombre_existe":
                        JOptionPane.showMessageDialog(null, "Ya hay una categoria con este nombre");
                        break;
                    case "registrada":
                        limpiarTabla();
                        listarCategorias();
                        limpiarPanel();
                        JOptionPane.showMessageDialog(null, "Categoria registrada con exito");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error al registrar categoria");
                        break;
                }
            }
        } else if (e.getSource() == vistas.btnModificarCat) {//Modificar Categoria a traves de la aplicacion
            if (vistas.txtIdCat.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            } else {
                if (vistas.txtNombreCat.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                } else {
                    ca.setNombre_categoria(vistas.txtNombreCat.getText());
                    ca.setId(Integer.parseInt(vistas.txtIdCat.getText()));
                    String verificar = caDao.modificar(ca);
                switch (verificar) {
                    case "nombre_existe":
                        JOptionPane.showMessageDialog(null, "Ya hay una categoria con este nombre");
                        break;
                    case "modificada":
                        limpiarTabla();
                        listarCategorias();
                        limpiarPanel();
                        JOptionPane.showMessageDialog(null, "Categoria modificada con exito");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error al modificar categoria");
                        break;
                }
                }
            }
        } else if (e.getSource() == vistas.JMenuEliminarCategoria) {//Eliminar Categoria a traves de la aplicacion
            if (vistas.txtIdCat.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
            } else {
                int id = Integer.parseInt(vistas.txtIdCat.getText());
                if (caDao.accion("Inactivo", id)) {
                    limpiarTabla();
                    listarCategorias();
                    limpiarPanel();
                    JOptionPane.showMessageDialog(null, "Categoria eliminada con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar Categoria");
                }
            }
        } else if (e.getSource() == vistas.JMenuReingresarCategoria) {//Reingresar Categoria a traves de la aplicacion
            if (vistas.txtIdCat.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para reingresar");
            } else {
                int id = Integer.parseInt(vistas.txtIdCat.getText());
                if (caDao.accion("Activo", id)) {
                    limpiarTabla();
                    listarCategorias();
                    limpiarPanel();
                    JOptionPane.showMessageDialog(null, "Categoria reingresada con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al reingresar Categoria");
                }
            }
        } else {
            limpiarPanel();
            vistas.btnRegistrarCat.setEnabled(true);
        }
    }

    public void listarCategorias() {
        Tabla color = new Tabla();
        vistas.TablaCategorias.setDefaultRenderer(vistas.TablaCategorias.getColumnClass(0), color);
        List<Categorias> lista = caDao.ListaCategorias(vistas.txtBuscarCat.getText());
        modelo = (DefaultTableModel) vistas.TablaCategorias.getModel();
        Object[] ob = new Object[3]; //3 columnas de la tabla
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre_categoria();
            ob[2] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        //Personalizar encabezado de la tabla Categoria
        vistas.TablaCategorias.setModel(modelo);
        JTableHeader header = vistas.TablaCategorias.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.blue);
        header.setForeground(Color.white);
        //No permitir que el usuario pueda modificar el id
        vistas.txtIdCat.setEditable(false);
    }

    public void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void limpiarPanel() {
        vistas.txtIdCat.setText("");
        vistas.txtNombreCat.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vistas.TablaCategorias) {
            int fila = vistas.TablaCategorias.rowAtPoint(e.getPoint());
            vistas.txtIdCat.setText(vistas.TablaCategorias.getValueAt(fila, 0).toString());
            vistas.txtNombreCat.setText(vistas.TablaCategorias.getValueAt(fila, 1).toString());
            String estado = vistas.TablaCategorias.getValueAt(fila, 2).toString();
            if(estado.equals("Inactivo")){
                vistas.JMenuEliminarCategoria.setVisible(false);
                vistas.JMenuReingresarCategoria.setVisible(true);
            }else{
                vistas.JMenuEliminarCategoria.setVisible(true);
                vistas.JMenuReingresarCategoria.setVisible(false);
            }
            vistas.btnRegistrarCat.setEnabled(false);
        } else if (e.getSource() == vistas.JLabelCategorias) {
            //Si se hace click sobre el menu Categoria, se muestra el detalle del menu que se encuentra en el tab con indice 6
            vistas.JTabbedPaneDetMenus.setSelectedIndex(7);
            limpiarTabla();
            listarCategorias();
        } else if (e.getSource() == vistas.InfoCat){
            JOptionPane.showMessageDialog(null, "Ver, buscar, registrar, modificar, eliminar y reingresar una Categoria.", "FUNCION DEL MENU", JOptionPane.INFORMATION_MESSAGE); 
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
        if (e.getSource() == vistas.txtBuscarCat) {
            limpiarTabla();
            listarCategorias();
        }
    }
    
    /*Metodo para llenar el combobox de categoria en el menu productos, con los proveedores 
    que hemos registrado en el menu categorias*/
    public void llenarCategorias() {
        List<Categorias> lista = caDao.ListaCategorias(vistas.txtBuscarCat.getText());
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getId();
            String nombre = lista.get(i).getNombre_categoria();
            vistas.cbxCategoriaProd.addItem(new Combo(id, nombre));
        }
    }
}
