//Autor: MARIA ISABEL ROMANO
package Modelos;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class ComprasDao {
    //Conectar a Conexion
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List ListaCompras(String valor) {
        List<Compras> listaComp = new ArrayList();
        String sql = "SELECT c.*, p.nombre_proveedor FROM compras c INNER JOIN proveedores p ON c.id_proveedor = p.id ORDER BY c.id DESC";
        String buscar = "SELECT c.*, p.nombre_proveedor FROM compras c INNER JOIN proveedores p ON c.id_proveedor = p.id WHERE p.nombre_proveedor LIKE '%" + valor + "%' OR c.fecha LIKE '%" + valor + "%'";
        try {
            con = cn.getConexion();
            if (valor.equalsIgnoreCase("")) {
                ps = con.prepareStatement(sql);
            } else {
                ps = con.prepareStatement(buscar);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Compras cp = new Compras();
                cp.setId(rs.getInt("id"));
                cp.setId_proveedor(rs.getInt("id_proveedor"));
                cp.setTotal(rs.getDouble("total"));
                cp.setFecha(rs.getString("fecha"));
                cp.setNombreProveedor(rs.getString("nombre_proveedor"));
                listaComp.add(cp);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaComp;
    }
}
