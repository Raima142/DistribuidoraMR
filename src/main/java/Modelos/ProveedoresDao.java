//Autor: MARIA ISABEL ROMANO
package Modelos;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class ProveedoresDao {
    //Conectar a Conexion
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //Registrar Proveedores
    public String registrar(Proveedores pr) {
        String verificarCuit = "SELECT * FROM proveedores WHERE cuit = ?";
        String verificarNombre = "SELECT * FROM proveedores WHERE nombre_proveedor = ?";
        String verificarCelular = "SELECT * FROM proveedores WHERE celular = ?";
        String verificarDireccion = "SELECT * FROM proveedores WHERE direccion = ?";
        String registrar = "INSERT INTO proveedores (cuit, nombre_proveedor, celular, direccion) VALUES (?,?,?,?)";
        
        try (Connection con = cn.getConexion();
             PreparedStatement psct = con.prepareStatement(verificarCuit);   
             PreparedStatement psn = con.prepareStatement(verificarNombre);
             PreparedStatement psc = con.prepareStatement(verificarCelular);
             PreparedStatement psd = con.prepareStatement(verificarDireccion)) {
            psct.setString(1, pr.getCuit());
            try (ResultSet rs = psct.executeQuery()) {
                if (rs.next()) {
                    return "cuit_existe";
                }
            }
            psn.setString(1, pr.getNombre_proveedor());
            try (ResultSet rs = psn.executeQuery()) {
                if (rs.next()) {
                    return "nombre_existe";
                }
            }
            psc.setString(1, pr.getCelular());
            try (ResultSet rs = psc.executeQuery()) {
                if (rs.next()) {
                    return "celular_existe";
                }
            }
            psd.setString(1, pr.getDireccion());
            try (ResultSet rs = psd.executeQuery()) {
                if (rs.next()) {
                    return "direccion_existe";
                }
            }
            //Registrar
            try (PreparedStatement psr = con.prepareStatement(registrar)) {
                psr.setString(1, pr.getCuit());
                psr.setString(2, pr.getNombre_proveedor());
                psr.setString(3, pr.getCelular());
                psr.setString(4, pr.getDireccion());
                psr.executeUpdate();
            }
            return "registrado";
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return "error";
        }
    }
    
    //Listar Proveedores
    public List ListaProveedores(String valor) {
        List<Proveedores> listaProv = new ArrayList();
        String sql = "SELECT * FROM proveedores ORDER BY estado ASC";
        String buscar = "SELECT * FROM proveedores WHERE cuit LIKE '%" + valor + "%' OR nombre_proveedor LIKE '%" + valor + "%' OR celular LIKE '%" + valor + "%'";
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
                Proveedores pr = new Proveedores();
                pr.setId(rs.getInt("id"));
                pr.setCuit(rs.getString("cuit"));
                pr.setNombre_proveedor(rs.getString("nombre_proveedor"));
                pr.setCelular(rs.getString("celular"));
                pr.setDireccion(rs.getString("direccion"));
                pr.setEstado(rs.getString("estado"));
                listaProv.add(pr);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaProv;
    }
    
    //Modificar Proveedores
    public String modificar(Proveedores pr) {
        String verificarCuit = "SELECT * FROM proveedores WHERE cuit = ? AND id != ?";
        String verificarNombre = "SELECT * FROM proveedores WHERE nombre_proveedor = ? AND id != ?";
        String verificarCelular = "SELECT * FROM proveedores WHERE celular = ? AND id != ?";
        String verificarDireccion = "SELECT * FROM proveedores WHERE direccion = ? AND id != ?";
        String modificar = "UPDATE proveedores SET cuit = ?, nombre_proveedor = ?, celular = ?, direccion = ? WHERE id = ?";
        
        try (Connection con = cn.getConexion();
             PreparedStatement psct = con.prepareStatement(verificarCuit);   
             PreparedStatement psn = con.prepareStatement(verificarNombre);
             PreparedStatement psc = con.prepareStatement(verificarCelular);
             PreparedStatement psd = con.prepareStatement(verificarDireccion)) {
            psct.setString(1, pr.getCuit());
            psct.setInt(2, pr.getId());
            try (ResultSet rs = psct.executeQuery()) {
                if (rs.next()) {
                    return "cuit_existe";
                }
            }
            psn.setString(1, pr.getNombre_proveedor());
            psn.setInt(2, pr.getId());
            try (ResultSet rs = psn.executeQuery()) {
                if (rs.next()) {
                    return "nombre_existe";
                }
            }
            psc.setString(1, pr.getCelular());
            psc.setInt(2, pr.getId());
            try (ResultSet rs = psc.executeQuery()) {
                if (rs.next()) {
                    return "celular_existe";
                }
            }
            psd.setString(1, pr.getDireccion());
            psd.setInt(2, pr.getId());
            try (ResultSet rs = psd.executeQuery()) {
                if (rs.next()) {
                    return "direccion_existe";
                }
            }
            //Modificar
            try (PreparedStatement psr = con.prepareStatement(modificar)) {
                psr.setString(1, pr.getCuit());
                psr.setString(2, pr.getNombre_proveedor());
                psr.setString(3, pr.getCelular());
                psr.setString(4, pr.getDireccion());
                psr.setInt(5, pr.getId());
                psr.executeUpdate();
            }
            return "modificado";
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return "error";
        }
    }
    
    //Actualizar estado de Proveedores
    public boolean accion(String estado, int id){
        String sql = "UPDATE proveedores SET estado = ? WHERE id = ?";
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
