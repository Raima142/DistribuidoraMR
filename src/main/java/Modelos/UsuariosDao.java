//Autor: MARIA ISABEL ROMANO
package Modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuariosDao {

    //Conectar a Conexion
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Usuarios login(String usuario, String clave) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND clave = ?";
        Usuarios us = new Usuarios();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            //Usuario y clave del registro creado en la tabla de la base de datos (es el que se ingresa en el panel de login)
            ps.setString(1, usuario);//1 es el indice en el que se ejecuta la instruccion sql en usuario = ?
            ps.setString(2, clave);//2 es el indice en el que se ejecuta la instruccion sql en clave = ?
            //Los indices se cuentan en cada ? o = ? de la instruccion indicada al inicio del metodo
            rs = ps.executeQuery();
            if (rs.next()) {
                us.setId(rs.getInt("id"));
                us.setUsuario(rs.getString("usuario"));
                us.setNombre(rs.getString("nombre"));
                us.setRol(rs.getString("rol"));
                us.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return us;
    }

    //Registrar Usuarios    
    public String registrar(Usuarios us) {
        String verificarUsuario = "SELECT * FROM usuarios WHERE usuario = ?";
        String verificarNombre = "SELECT * FROM usuarios WHERE nombre = ?";
        String registrar = "INSERT INTO usuarios (usuario, clave, nombre, id_caja, rol) VALUES (?,?,?,?,?)";

        try (Connection con = cn.getConexion(); 
            PreparedStatement psu = con.prepareStatement(verificarUsuario);
            PreparedStatement psn = con.prepareStatement(verificarNombre)) {
            psu.setString(1, us.getUsuario());
            try (ResultSet rs = psu.executeQuery()) {
                if (rs.next()) {
                    return "usuario_existe";
                }
            }
            psn.setString(1, us.getNombre());
            try (ResultSet rs = psn.executeQuery()) {
                if (rs.next()) {
                    return "nombre_existe";
                }
            }
            //Registrar
            try (PreparedStatement psr = con.prepareStatement(registrar)) {
                psr.setString(1, us.getUsuario());
                psr.setString(2, us.getClave());
                psr.setString(3, us.getNombre());
                psr.setInt(4, us.getId_caja());
                psr.setString(5, us.getRol());
                psr.executeUpdate();
            }
            return "registrado";
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return "error";
        }
    }
    
    //Listar Usuarios
    public List ListaUsuarios(String valor) {
        List<Usuarios> listaUsers = new ArrayList();
        String sql = "SELECT u.*, c.nombre AS caja FROM usuarios u INNER JOIN cajas c ON u.id_caja = c.id ORDER BY u.estado ASC";
        String buscar = "SELECT u.*, c.nombre AS caja FROM usuarios u INNER JOIN cajas c ON u.id_caja = c.id WHERE u.usuario LIKE '%" + valor + "%' OR u.nombre LIKE '%" + valor + "%' OR c.caja LIKE '%" + valor + "%'";
        try {
            con = cn.getConexion();
            if (valor.equalsIgnoreCase("")) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            } else {
                ps = con.prepareStatement(buscar);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                Usuarios us = new Usuarios();
                us.setId(rs.getInt("id"));
                us.setUsuario(rs.getString("usuario"));
                us.setNombre(rs.getString("nombre"));
                us.setId_caja(rs.getInt("id_caja"));
                us.setTipo_caja(rs.getString("caja"));
                us.setRol(rs.getString("rol"));
                us.setEstado(rs.getString("estado"));
                listaUsers.add(us);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaUsers;
    }
    
    //Modificar Usuarios    
    public String modificar(Usuarios us) {
        String verificarUsuario = "SELECT * FROM usuarios WHERE usuario = ? AND id != ?";
        String verificarNombre = "SELECT * FROM usuarios WHERE nombre = ? AND id != ?";
        String modificar = "UPDATE usuarios SET usuario = ?, clave = ?, nombre = ?, id_caja = ?, rol = ? WHERE id = ?";

        try (Connection con = cn.getConexion(); 
            PreparedStatement psu = con.prepareStatement(verificarUsuario);
            PreparedStatement psn = con.prepareStatement(verificarNombre)) {
            psu.setString(1, us.getUsuario());
            psu.setInt(2, us.getId());
            try (ResultSet rs = psu.executeQuery()) {
                if (rs.next()) {
                    return "usuario_existe";
                }
            }
            psn.setString(1, us.getNombre());
            psn.setInt(2, us.getId());
            try (ResultSet rs = psn.executeQuery()) {
                if (rs.next()) {
                    return "nombre_existe";
                }
            }
            //Modificar
            try (PreparedStatement psr = con.prepareStatement(modificar)) {
                psr.setString(1, us.getUsuario());
                psr.setString(2, us.getClave());
                psr.setString(3, us.getNombre());
                psr.setInt(4, us.getId_caja());
                psr.setString(5, us.getRol());
                psr.setInt(6, us.getId());
                psr.executeUpdate();
            }
            return "modificado";
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return "error";
        }
    }
    
    //Actualizar estado de Usuarios
    public boolean accion(String estado, int id){
        String sql = "UPDATE usuarios SET estado = ? WHERE id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            ps.setInt(2, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
}
    
