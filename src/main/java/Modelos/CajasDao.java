//Autor: MARIA ISABEL ROMANO
package Modelos;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class CajasDao {

    //Conectar a Conexion
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //Registrar Cajas    
    public String registrar(Cajas cj) {
        String verificarNombre = "SELECT * FROM cajas WHERE nombre = ?";
        String registrar = "INSERT INTO cajas (nombre) VALUES (?)";
        
        try (Connection con = cn.getConexion();
             PreparedStatement psn = con.prepareStatement(verificarNombre)) {
            psn.setString(1, cj.getNombre());
            try (ResultSet rs = psn.executeQuery()) {
                if (rs.next()) {
                    return "nombre_existe";
                }
            }
            //Registrar
            try (PreparedStatement psr = con.prepareStatement(registrar)) {
                psr.setString(1, cj.getNombre());
                psr.executeUpdate();
            }
            return "registrada";
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return "error";
        }
    }

    //Listar Cajas
    public List ListaCajas(String valor) {
        List<Cajas> listaCaj = new ArrayList();
        String sql = "SELECT * FROM cajas ORDER BY estado ASC";
        String buscar = "SELECT * FROM cajas WHERE nombre LIKE '%" + valor + "%' OR estado LIKE '%" + valor + "%'";
        try {
            con = cn.getConexion();
            if (valor.equalsIgnoreCase("")) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            } else {
                ps = con.prepareStatement(buscar);//(sql)???
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                Cajas cj = new Cajas();
                cj.setId(rs.getInt("id"));
                cj.setNombre(rs.getString("nombre"));
                cj.setEstado(rs.getString("estado"));
                listaCaj.add(cj);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaCaj;
    }

    //Modificar Cajas   
    public String modificar(Cajas cj) {
        String verificarNombre = "SELECT * FROM cajas WHERE nombre = ? AND id != ?";
        String modificar = "UPDATE cajas SET nombre = ? WHERE id = ?";
        
        try (Connection con = cn.getConexion();
             PreparedStatement psn = con.prepareStatement(verificarNombre)) {
            psn.setString(1, cj.getNombre());
            psn.setInt(2, cj.getId());
            try (ResultSet rs = psn.executeQuery()) {
                if (rs.next()) {
                    return "nombre_existe";
                }
            }
            //Modificar
            try (PreparedStatement psm = con.prepareStatement(modificar)) {
                psm.setString(1, cj.getNombre());
                psm.setInt(2, cj.getId());
                psm.executeUpdate();
            }
            return "modificada";
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return "error";
        }
    }

    //Actualizar estado de Cajas
    public boolean accion(String estado, int id) {
        String sql = "UPDATE cajas SET estado = ? WHERE id = ?";
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

    //Crear las relaciones restrictas de usuarios.id_caja --> cajas.id
    //Crear las relaciones restrictas de detalle_caja.id_user --> usuarios.id
    //Crear las relaciones restrictas de detalle_venta.id_producto --> productos.id
    //Crear las relaciones restrictas de detalle_venta.id_venta --> ventas.id
    //Crear las relaciones restrictas de detalle_compra.id_producto --> productos.id
    //Crear las relaciones restrictas de ventas.id_cliente --> clientes.id
    //Esto se puede hacer desde Estructura, Vista de relaciones, sin indicar previamente la llave indice
    //Metodos para Apertura y Cierre
    public String abrirCaja(double monto, int id_user) {
        String truncateSql = "TRUNCATE TABLE ventas_reg_tmp";
        String consulta = "SELECT d.*, u.nombre FROM detalle_caja d INNER JOIN usuarios u ON d.id_user = u.id WHERE d.estado = 1";
        String sql = "INSERT INTO detalle_caja (monto_inicial, id_user) VALUES (?,?)";
        try {
            con = cn.getConexion();

            ps = con.prepareStatement(truncateSql);
            ps.executeUpdate();

            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            if (rs.next()) {
                return "existe";
            } else {
                ps = con.prepareStatement(sql);
                ps.setDouble(1, monto);
                ps.setInt(2, id_user);
                ps.execute();
                return "registrado";
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return "error";
    }

    public double obtenerMontoInicial(int id_user) {
        double montoInicial = 0.00;
        String sql = "SELECT monto_inicial FROM detalle_caja WHERE id_user = ? AND estado = 1";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_user);
            rs = ps.executeQuery();
            if (rs.next()) {
                montoInicial = rs.getDouble("monto_inicial");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return montoInicial;
    }

    public List ListaAperturas(String valor) {
        List<AperturaCierre> listaApe = new ArrayList();
        try {
            con = cn.getConexion();
            if ("".equalsIgnoreCase(valor)) {
                String sql = "SELECT d.*, u.nombre FROM detalle_caja d INNER JOIN usuarios u ON d.id_user = u.id ORDER BY d.fecha_apertura DESC";
                ps = con.prepareStatement(sql);
            } else {
                String sql = "SELECT d.*, u.nombre FROM detalle_caja d INNER JOIN usuarios u ON d.id_user = u.id WHERE u.nombre LIKE '%" + valor + "%' OR d.fecha_apertura LIKE '%" + valor + "%'";
                ps = con.prepareStatement(sql);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                AperturaCierre ac = new AperturaCierre();
                ac.setFecha_apertura(rs.getString("fecha_apertura"));
                ac.setFecha_cierre(rs.getString("fecha_cierre"));
                ac.setMonto_inicial(rs.getDouble("monto_inicial"));
                ac.setMonto_final(rs.getDouble("monto_final"));
                ac.setTotal_ventas(rs.getInt("total_ventas"));
                ac.setNombre_usuario(rs.getString("nombre"));
                listaApe.add(ac);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());

        }
        return listaApe;
    }

    public double montoFinal(int id_user) {
        double monto = 0.00;
        //String sql = "SELECT total AS monto_total FROM ventas WHERE id_user = ? ORDER BY fecha DESC LIMIT 1";
        String sql = "SELECT SUM(total) AS monto_total FROM ventas_reg_tmp WHERE id_user = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_user);
            rs = ps.executeQuery();
            if (rs.next()) {
                monto = rs.getDouble("monto_total");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return monto;
    }

    public int totalVentas(int id_user) {
        int total = 0;
        String sql = "SELECT COUNT(*) AS total FROM ventas_reg_tmp WHERE id_user = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_user);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return total;
    }

    public boolean cerrarCaja(AperturaCierre ac) {
        String sql = "UPDATE detalle_caja SET fecha_cierre = CURRENT_TIMESTAMP, monto_final = ?, total_ventas = ?, estado = ? WHERE id_user = ? AND estado = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, ac.getMonto_final());
            ps.setInt(2, ac.getTotal_ventas());
            ps.setInt(3, 0);
            ps.setInt(4, ac.getId_usuario());
            ps.setInt(5, 1);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
}
