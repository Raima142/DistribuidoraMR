//Autor: MARIA ISABEL ROMANO
package Controladores;

import Modelos.*;
import Vistas.*;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class ControladorLogin implements ActionListener, KeyListener, MouseListener {

    private Usuarios us;
    private UsuariosDao usDao;
    private PanelLogin vistas;

    public ControladorLogin(Usuarios us, UsuariosDao usDao, PanelLogin vistas) {
        this.us = us;
        this.usDao = usDao;
        this.vistas = vistas;
        //Colocamos el PanelLogin en el centro
        this.vistas.setLocationRelativeTo(null);
        //Ponemos los botones del PanelLogin a la escucha de un click
        this.vistas.btnLogin.addActionListener(this);
        this.vistas.btnCancelar.addActionListener(this);
        //Ponemos los campos y los botones a la escucha de determinadas teclas del teclado
        this.vistas.txtUsuario.addKeyListener(this);
        this.vistas.txtClave.addKeyListener(this);
        this.vistas.btnLogin.addKeyListener(this);
        this.vistas.btnCancelar.addKeyListener(this);
        this.vistas.JLabelNoVerClave.addMouseListener(this);
        this.vistas.JLabelVerClave.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistas.btnLogin) {
            //Si se logea con los campos vacios
            if (vistas.txtUsuario.getText().equals("") || String.valueOf(vistas.txtClave.getPassword()).equals("")) {
                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            } else {//Si el usuario y contraseña son correctos
                String usuario = vistas.txtUsuario.getText();
                String clave = String.valueOf(vistas.txtClave.getPassword());
                us = usDao.login(usuario, clave);
                if (us.getUsuario() != null) {
                    PanelPrincipal pp = new PanelPrincipal(us.getId(), us.getNombre(), us.getRol());
                    pp.setVisible(true);
                    //LIMITAR PRIVILEGIOS SI NO ES ADMINISTRADOR
                    if ("Usuario".equals(us.getRol())) {
                        pp.LabelAdministrador.setVisible(false);
                        pp.LabelUsuario.setVisible(true);
                        //CONFIGURACION
                        pp.btnModificarEmpresa.setEnabled(false);
                        //USUARIOS
                        pp.btnModificarUser.setEnabled(false);
                        //CAJA
                        pp.btnModificarCaja.setEnabled(false);
                        //CLIENTES
                        pp.btnModificarCli.setEnabled(false);
                        //PROVEEDORES
                        pp.btnModificarProv.setEnabled(false);
                        //CATEGORIAS
                        pp.btnModificarCat.setEnabled(false);
                        //PRODUCTOS
                        pp.btnModificarProd.setEnabled(false);
                    } else if ("Administrador".equals(us.getRol())) {
                        pp.LabelAdministrador.setVisible(true);
                        pp.LabelUsuario.setVisible(false);
                    }
                    pp.JTabbedPaneDetMenus.setEnabled(false);
                    this.vistas.dispose();//Cuando ingresamos al panel principal se cierra el panel login
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectos");
                }
            }
        } else {//Si se desea salir
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea salir?", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (pregunta == 0) {
                System.exit(0);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                if (vistas.txtUsuario.isFocusOwner()) {
                    vistas.txtClave.requestFocus();
                }
                break;
            case KeyEvent.VK_UP:
                if (vistas.btnCancelar.isFocusOwner()) {
                    vistas.txtClave.requestFocus();
                } else if (vistas.txtClave.isFocusOwner()) {
                    vistas.txtUsuario.requestFocus();
                }
                break;
            case KeyEvent.VK_ENTER:
                if (vistas.txtUsuario.isFocusOwner()) {
                    vistas.txtClave.requestFocus();
                } else if (vistas.txtClave.isFocusOwner()) {
                    vistas.btnLogin.doClick();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                vistas.btnCancelar.doClick();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Comprobar si Bloq Mayus está activado
        boolean bloqMayusIsOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        vistas.JLabelBloqMayusAviso.setVisible(bloqMayusIsOn);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vistas.JLabelNoVerClave) {//Ver clave
            vistas.txtClave.setEchoChar((char) 0);
            vistas.JLabelNoVerClave.setVisible(false);
            vistas.JLabelVerClave.setVisible(true);
        } else if (e.getSource() == vistas.JLabelVerClave) {//Ocultar clave
            vistas.txtClave.setEchoChar('•');
            vistas.JLabelVerClave.setVisible(false);
            vistas.JLabelNoVerClave.setVisible(true);
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
}
