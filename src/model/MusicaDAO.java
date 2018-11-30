package model;

import classes.InfoMusica;
import classes.Musica;
import fabricaConexao.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MusicaDAO {
    public boolean createMusica(Musica musica){
        Connection con = FabricaConexao.getConnection();
        PreparedStatement stmt = null;
        boolean conf = false;
        int registros = 0;

            try {
                stmt = con.prepareStatement("INSERT INTO musica (autor, musica, genero,"+
                        " ano, descricao, tempo, link_imagem, youtube_link) VALUES (?,?,?,?,?,?,?,?)");

                stmt.setString(1, musica.getAutor());
                stmt.setString(2,musica.getMusica());
                stmt.setInt(3, musica.getGenero());
                stmt.setInt(4, musica.getAno());
                stmt.setString(5, musica.getDescricao());
                stmt.setFloat(6, musica.getTempo());
                stmt.setString(7, musica.getLink_imagem());
                stmt.setString(8, musica.getLink_youtube());

                registros = stmt.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(MusicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                FabricaConexao.closeConnection(con, stmt);
            }
        if(registros == 1){
            return true;
        }
        else {
            return false;
        }
    }

    public Musica selectMusica(int id){
        Musica musica = null;
        Connection con = FabricaConexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;

        try {
            stmt = con.prepareStatement("SELECT * FROM musica WHERE id = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next()){
                musica = new Musica();

                musica.setId(rs.getInt("id"));
                musica.setAutor(rs.getString("autor"));
                musica.setMusica(rs.getString("musica"));
                musica.setGenero(rs.getInt("genero"));
                musica.setAno(rs.getInt("ano"));
                musica.setDescricao(rs.getString("descricao"));
                musica.setTempo(rs.getFloat("tempo"));
                musica.setLink_imagem(rs.getString("link_imagem"));
                musica.setLink_youtube(rs.getString("youtube_link"));
                check = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MusicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            FabricaConexao.closeConnection(con, stmt, rs);
        }

        return musica;
    }

    public boolean editarMusica(Musica musica){
        Connection con = FabricaConexao.getConnection();
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            stmt = con.prepareStatement("UPDATE musica SET"+
                    "autor = ?, musica = ?, genero = ?,\"+\n" +
                    "                        \" ano = ?, descricao = ?, tempo = ?, link_imagem = ?, youtube_link = ? WHERE id = ?");

            stmt.setString(1, musica.getAutor());
            stmt.setString(2,musica.getMusica());
            stmt.setInt(3, musica.getGenero());
            stmt.setInt(4, musica.getAno());
            stmt.setString(5, musica.getDescricao());
            stmt.setFloat(6, musica.getTempo());
            stmt.setString(7, musica.getLink_imagem());
            stmt.setString(8, musica.getLink_youtube());

            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            FabricaConexao.closeConnection(con, stmt);
        }
        if(registros == 1){
            return true;
        }
        else {
            return false;
        }

    }

    public ArrayList<Musica> selectMusicaLista(){
        ArrayList<Musica> listaMusicas = new ArrayList<>();
        Musica musica;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = FabricaConexao.getConnection();
            stmt = con.prepareStatement("SELECT * FROM musica");

            rs = stmt.executeQuery();

            while(rs.next()){
                musica = new Musica();

                musica.setId(rs.getInt("id"));
                musica.setAutor(rs.getString("autor"));
                musica.setMusica(rs.getString("musica"));
                musica.setGenero(rs.getInt("genero"));
                musica.setAno(rs.getInt("ano"));
                musica.setDescricao(rs.getString("descricao"));
                musica.setTempo(rs.getFloat("tempo"));
                musica.setLink_imagem(rs.getString("link_imagem"));
                musica.setLink_youtube(rs.getString("youtube_link"));

                listaMusicas.add(musica);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MusicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            FabricaConexao.closeConnection(con, stmt, rs);
        }
        return listaMusicas;
    }

    public InfoMusica selectInfoMusica(int id){
        InfoMusica musica = null;
        Connection con = FabricaConexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;

        try {
            stmt = con.prepareStatement("call info_musica(?)");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next()){
                musica = new InfoMusica();

                musica.setMusica(rs.getString("musica"));
                musica.setAutor(rs.getString("autor"));
                musica.setGenero(rs.getString("genero"));
                musica.setAno(rs.getInt("ano"));
                musica.setDescricao(rs.getString("descricao"));
                check = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MusicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            FabricaConexao.closeConnection(con, stmt, rs);
        }

        return musica;
    }

}
