//Autor: MARIA ISABEL ROMANO
package Modelos;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class VentasDao {
    //Conectar a Conexion
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List ListaVentas(String valor) {
        List<Ventas> listaVent = new ArrayList();
        String sql = "SELECT v.*, c.nombre_cliente FROM ventas v INNER JOIN clientes c ON v.id_cliente = c.id ORDER BY v.id DESC";
        String buscar = "SELECT v.*, c.nombre_cliente FROM ventas v INNER JOIN clientes c ON v.id_cliente = c.id WHERE c.nombre_cliente LIKE '%" + valor + "%' OR v.fecha LIKE '%" + valor + "%'";
        try {
            con = cn.getConexion();
            if (valor.equalsIgnoreCase("")) {
                ps = con.prepareStatement(sql);
            } else {
                ps = con.prepareStatement(buscar);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Ventas vt = new Ventas();
                vt.setId(rs.getInt("id"));
                vt.setId_cliente(rs.getInt("id_cliente"));
                vt.setTotal(rs.getDouble("total"));
                vt.setFecha(rs.getString("fecha"));
                vt.setNombreCliente(rs.getString("nombre_cliente"));
                listaVent.add(vt);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaVent;
    }
    
    public String verificarCaja(int id_user) {
        String consulta = "SELECT * FROM detalle_caja WHERE estado = ? AND id_user = ?";
        con = cn.getConexion();
        try {
            ps = con.prepareStatement(consulta);
            ps.setInt(1, 1);
            ps.setInt(2, id_user);
            rs = ps.executeQuery();
            if (rs.next()) {
                return "caja_abierta";
            } else {
                return "caja_cerrada";
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentasDao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            return "error";
        }
    }
}
