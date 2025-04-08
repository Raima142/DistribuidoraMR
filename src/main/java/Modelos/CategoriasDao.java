//Autor: MARIA ISABEL ROMANO
package Modelos;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class CategoriasDao {
    //Conectar a Conexion
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //Registrar Categorias   
    public String registrar(Categorias ca) {
        String verificarNombre = "SELECT * FROM categorias WHERE nombre_categoria = ?";
        String registrar = "INSERT INTO categorias (nombre_categoria) VALUES (?)";
        
        try (Connection con = cn.getConexion();
             PreparedStatement psn = con.prepareStatement(verificarNombre)) {
            psn.setString(1, ca.getNombre_categoria());
            try (ResultSet rs = psn.executeQuery()) {
                if (rs.next()) {
                    return "nombre_existe";
                }
            }
            //Registrar
            try (PreparedStatement psr = con.prepareStatement(registrar)) {
                psr.setString(1, ca.getNombre_categoria());
                psr.executeUpdate();
            }
            return "registrada";
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return "error";
        }
    }
    
    //Listar Categorias
    public List ListaCategorias(String valor) {
        List<Categorias> listaCat = new ArrayList();
        String sql = "SELECT * FROM categorias ORDER BY estado ASC";
        String buscar = "SELECT * FROM categorias WHERE nombre_categoria LIKE '%" + valor + "%'";
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
                Categorias ca = new Categorias();
                ca.setId(rs.getInt("id"));
                ca.setNombre_categoria(rs.getString("nombre_categoria"));
                ca.setEstado(rs.getString("estado"));
                listaCat.add(ca);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaCat;
    }
    
    //Modificar Categorias
    public String modificar(Categorias ca) {
        String verificarNombre = "SELECT * FROM categorias WHERE nombre_categoria = ? AND id != ?";
        String modificar = "UPDATE categorias SET nombre_categoria = ? WHERE id = ?";
        
        try (Connection con = cn.getConexion();
             PreparedStatement psn = con.prepareStatement(verificarNombre)) {
            psn.setString(1, ca.getNombre_categoria());
            psn.setInt(2, ca.getId());
            try (ResultSet rs = psn.executeQuery()) {
                if (rs.next()) {
                    return "nombre_existe";
                }
            }
            //Modificar
            try (PreparedStatement psm = con.prepareStatement(modificar)) {
                psm.setString(1, ca.getNombre_categoria());
                psm.setInt(2, ca.getId());
                psm.executeUpdate();
            }
            return "modificada";
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return "error";
        }
    }
    
    //Actualizar estado de Categorias
    public boolean accion(String estado, int id){
        String sql = "UPDATE categorias SET estado = ? WHERE id = ?";
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
