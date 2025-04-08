//Autor: MARIA ISABEL ROMANO
package Modelos;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
    //Crear variable con para poder establecer conexion
    Connection con;
    
    public Connection getConexion(){
        try{
            //Url de la base de datos
            String url = "jdbc:mysql://localhost:3306/distribuidora_mr?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            //Usuario y contraseña (de la base de datos con el que se inicia sesion en phpMyAdmin, no del registro creado en la tabla)
            con = DriverManager.getConnection(url, "root", "admin");
            return con;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return null;
    }
}
