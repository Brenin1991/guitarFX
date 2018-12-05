package fabricaConexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
    2018 - BR3N0 C4MP05 R1B3IR0
*/
public class FabricaConexao {

    //private static Connection con;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "USER";
    private static final String PASSWORD = "PASS";
    private static final String URL = "URL DATA BASE";

    public static Connection getConnection(){
        try{
            Class.forName(DRIVER);

            System.out.println("Success: Connected!");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch(ClassNotFoundException | SQLException error){
            throw new RuntimeException("Error: Not connected!", error);
        }
    }

    public static void closeConnection(Connection con){
        try {
            if(con!=null){
                con.close();
            }
        }catch (SQLException ex) {
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt){

        closeConnection(con);

        try {
            if(stmt!=null){
                stmt.close();
            }
        }catch (SQLException ex) {
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){

        closeConnection(con, stmt);

        try {
            if(rs!=null){
                rs.close();
            }
        }catch (SQLException ex) {
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

