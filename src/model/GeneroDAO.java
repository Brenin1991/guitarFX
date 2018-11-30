package model;

import classes.Genero;
import fabricaConexao.FabricaConexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneroDAO {

    public ArrayList<Genero> selectGeneroLista(){
        ArrayList<Genero> listaGenero = new ArrayList<>();
        Genero genero;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = FabricaConexao.getConnection();
            stmt = con.prepareStatement("SELECT * FROM genero");

            rs = stmt.executeQuery();

            while(rs.next()){
                genero = new Genero();

                genero.setId(rs.getInt("id"));
                genero.setNome(rs.getString("genero"));


                listaGenero.add(genero);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GeneroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            FabricaConexao.closeConnection(con, stmt, rs);
        }
        return listaGenero;
    }
}
