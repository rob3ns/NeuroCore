package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rob3ns
 */
public class Database {

    private String url;
    private String user;
    private String pssw;
    private Connection conexion;

    public Database(String url, String user, String pwd) {
        super();
        this.url = url;
        this.user = user;
        this.pssw = pwd;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPssw() {
        return pssw;
    }

    public void setPssw(String pssw) {
        this.pssw = pssw;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexio) {
        this.conexion = conexio;
    }

    public Connection activaConexion() {
        try {
            conexion = DriverManager.getConnection(url, user, pssw);
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos.");
        }
        return conexion;
    }

    public ResultSet ejecutarQuery(String query) {
        ResultSet res = null;
        try {
            Statement sentencia = conexion.createStatement();
            res = sentencia.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar un query.");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
}
