//Autor: MARIA ISABEL ROMANO
package Modelos;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class ConfiguracionDao {
    //Conectar a Conexion
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List ListaConfiguracion(String valor) {
        List<Configuracion> listaConf = new ArrayList();
        String sql = "SELECT * FROM configuracion ORDER BY id ASC";
        String buscar = "SELECT * FROM configuracion WHERE cuit LIKE '%" + valor + "%' OR nombre_empresa LIKE '%" + valor + "%' OR celular LIKE '%" + valor + "%'";
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
                Configuracion conf = new Configuracion();
                conf.setId(rs.getInt("id"));
                conf.setCuit(rs.getString("cuit"));
                conf.setNombre_empresa(rs.getString("nombre_empresa"));
                conf.setCelular(rs.getString("celular"));
                conf.setDireccion(rs.getString("direccion"));
                listaConf.add(conf);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaConf;
    }
    
    //Modificar Empresa
    public boolean modificar(Configuracion conf){
        String sql = "UPDATE configuracion SET cuit = ?, nombre_empresa = ?, celular = ?, direccion = ? WHERE id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, conf.getCuit());
            ps.setString(2, conf.getNombre_empresa());
            ps.setString(3, conf.getCelular());
            ps.setString(4, conf.getDireccion());
            ps.setInt(5, conf.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
    
    //Actualizar estado de la empresa
    public boolean accion(String cuit, String nombre_empresa, String celular, String direccion, int id){
        String sql = "UPDATE configuracion SET cuit = ?, nombre_empresa = ?, celular = ?, direccion = ? WHERE id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cuit);
            ps.setString(2, nombre_empresa);
            ps.setString(3, celular);
            ps.setString(4, direccion);
            ps.setInt(5, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
}
