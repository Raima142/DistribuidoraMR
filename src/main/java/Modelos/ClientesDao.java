//Autor: MARIA ISABEL ROMANO
package Modelos;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class ClientesDao {

    //Conectar a Conexion
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //Registrar Clientes
    public String registrar(Clientes cl) {
        String verificarNombre = "SELECT * FROM clientes WHERE nombre_cliente = ?";
        String verificarCelular = "SELECT * FROM clientes WHERE celular = ?";
        String verificarDireccion = "SELECT * FROM clientes WHERE direccion = ?";
        String registrar = "INSERT INTO clientes (nombre_cliente, celular, direccion) VALUES (?, ?, ?)";
        
        try (Connection con = cn.getConexion();
             PreparedStatement psn = con.prepareStatement(verificarNombre);
             PreparedStatement psc = con.prepareStatement(verificarCelular);
             PreparedStatement psd = con.prepareStatement(verificarDireccion)) {
            //Verificar si ya hay un cliente con el mismo nombre
            psn.setString(1, cl.getNombre_cliente());
            try (ResultSet rs = psn.executeQuery()) {
                if (rs.next()) {
                    return "nombre_existe";
                }
            }
            //Verificar si ya hay un cliente con el mismo celular
            psc.setString(1, cl.getCelular());
            try (ResultSet rs = psc.executeQuery()) {
                if (rs.next()) {
                    return "celular_existe";
                }
            }
            //Verificar si ya hay un cliente con la misma direccion
            psd.setString(1, cl.getDireccion());
            try (ResultSet rs = psd.executeQuery()) {
                if (rs.next()) {
                    return "direccion_existe";
                }
            }
            //Registrar
            try (PreparedStatement psr = con.prepareStatement(registrar)) {
                psr.setString(1, cl.getNombre_cliente());
                psr.setString(2, cl.getCelular());
                psr.setString(3, cl.getDireccion());
                psr.executeUpdate();
            }
            return "registrado";
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return "error";
        }
    }

    //Listar Clientes
    public List ListaClientes(String valor) {
        List<Clientes> listaCli = new ArrayList();
        String sql = "SELECT * FROM clientes ORDER BY estado ASC";
        String buscar = "SELECT * FROM clientes WHERE nombre_cliente LIKE '%" + valor + "%' OR celular LIKE '%" + valor + "%'";
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
                Clientes cl = new Clientes();
                cl.setId(rs.getInt("id"));
                cl.setNombre_cliente(rs.getString("nombre_cliente"));
                cl.setCelular(rs.getString("celular"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setEstado(rs.getString("estado"));
                listaCli.add(cl);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaCli;
    }
    
    //Modificar Clientes
    public String modificar(Clientes cl) {
        String verificarNombre = "SELECT * FROM clientes WHERE nombre_cliente = ? AND id != ?";
        String verificarCelular = "SELECT * FROM clientes WHERE celular = ? AND id != ?";
        String verificarDireccion = "SELECT * FROM clientes WHERE direccion = ? AND id != ?";
        String modificar = "UPDATE clientes SET nombre_cliente = ?, celular = ?, direccion = ? WHERE id = ?";
        
        try (Connection con = cn.getConexion();
             PreparedStatement psn = con.prepareStatement(verificarNombre);
             PreparedStatement psc = con.prepareStatement(verificarCelular);
             PreparedStatement psd = con.prepareStatement(verificarDireccion)) {
            //Verificar si ya hay un cliente con el mismo nombre
            psn.setString(1, cl.getNombre_cliente());
            psn.setInt(2, cl.getId());
            try (ResultSet rs = psn.executeQuery()) {
                if (rs.next()) {
                    return "nombre_existe";
                }
            }
            //Verificar si ya hay un cliente con el mismo celular
            psc.setString(1, cl.getCelular());
            psc.setInt(2, cl.getId());
            try (ResultSet rs = psc.executeQuery()) {
                if (rs.next()) {
                    return "celular_existe";
                }
            }
            //Verificar si ya hay un cliente con la misma direccion
            psd.setString(1, cl.getDireccion());
            psd.setInt(2, cl.getId());
            try (ResultSet rs = psd.executeQuery()) {
                if (rs.next()) {
                    return "direccion_existe";
                }
            }
            //Modificar
            try (PreparedStatement psm = con.prepareStatement(modificar)) {
                psm.setString(1, cl.getNombre_cliente());
                psm.setString(2, cl.getCelular());
                psm.setString(3, cl.getDireccion());
                psm.setInt(4, cl.getId());
                psm.executeUpdate();
            }
            return "modificado";
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return "error";
        }
    }

    //Actualizar estado de Clientes
    public boolean accion(String estado, int id) {
        String sql = "UPDATE clientes SET estado = ? WHERE id = ?";
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
