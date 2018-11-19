package model;

import classes.RankMusica;
import classes.RankUsuario;
import fabricaConexao.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RankDAO {

    public ArrayList<RankMusica> selectRankMusicaLista(int idMusica){
        ArrayList<RankMusica> listaRankMusicas = new ArrayList<>();
        RankMusica rankMusica;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = FabricaConexao.getConnection();
            stmt = con.prepareStatement("call ranking_musica(?)");

            stmt.setInt(1, idMusica);
            rs = stmt.executeQuery();

            while(rs.next()){
                rankMusica = new RankMusica();

                rankMusica.setUsuario(rs.getString("usuario"));
                rankMusica.setMusica(rs.getString("musica"));
                rankMusica.setPontos(rs.getInt("pontos"));


                listaRankMusicas.add(rankMusica);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RankDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            FabricaConexao.closeConnection(con, stmt, rs);
        }

        return listaRankMusicas;
    }

    public ArrayList<RankUsuario> selectRankGlobalLista(){
        ArrayList<RankUsuario> listaRankGlobal = new ArrayList<>();
        RankUsuario rankUsuario;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = FabricaConexao.getConnection();
            stmt = con.prepareStatement("call lista_ranking_global();");

            rs = stmt.executeQuery();

            while(rs.next()){
                rankUsuario = new RankUsuario();

                rankUsuario.setUsuario(rs.getString("usuario"));
                rankUsuario.setPontos(rs.getInt("pontos"));
                rankUsuario.setNivel(rs.getInt("nivel"));

                listaRankGlobal.add(rankUsuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RankDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            FabricaConexao.closeConnection(con, stmt, rs);
        }

        return listaRankGlobal;
    }
}
