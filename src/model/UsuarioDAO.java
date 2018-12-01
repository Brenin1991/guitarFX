package model;

import classes.Usuario;
import fabricaConexao.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {
    public boolean cadastraUsuario(Usuario usuario){
        Connection con = FabricaConexao.getConnection();
        PreparedStatement stmt = null;
        int registros = 0;
        boolean conf;
        conf = existsLogin(usuario.getEmail());

        if(conf != true){
            try {
                stmt = con.prepareStatement("INSERT INTO usuario (usuario, email, senha,"+
                        " pontos, nivel) VALUES (?,?,?,?,?)");

                stmt.setString(1, usuario.getNome());
                stmt.setString(2,usuario.getEmail());
                stmt.setString(3,usuario.getSenha());
                stmt.setInt(4, 0);
                stmt.setInt(5, 1);

                registros = stmt.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                FabricaConexao.closeConnection(con, stmt);
            }
        }
        if(registros == 1){
            return true;
        }
        else {
            return false;
        }
    }

    public Usuario selecionaUsuario(int id){
        Usuario usuario = null;
        Connection con = FabricaConexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;

        try {
            stmt = con.prepareStatement("call seleciona_usuario(?)");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next()){
                usuario = new Usuario();

                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("usuario"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTotalPontos(rs.getInt("pontos"));
                usuario.setNivel(rs.getInt("nivel"));
                check = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            FabricaConexao.closeConnection(con, stmt, rs);
        }

        return usuario;
    }


    public boolean removerUsuario(Usuario usuario){
        Connection con = FabricaConexao.getConnection();
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            stmt = con.prepareStatement("UPDATE usuario SET"+
                    " total_pontos = ? WHERE id = ?");


            stmt.setInt(1, usuario.getTotalPontos());
            stmt.setInt(2, usuario.getId());


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

    public int checkLogin(String email, String senha){
        int idPlayer = 0;
        Connection con = FabricaConexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;

        try {
            stmt = con.prepareStatement("select login(?, ?);");
            stmt.setString(1, email);
            stmt.setString(2, senha);

            rs = stmt.executeQuery();

            if(rs.next()){
               idPlayer = rs.getInt("login('"+email+"', '"+senha+"')");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            FabricaConexao.closeConnection(con, stmt, rs);
        }

        return idPlayer;
    }

    public boolean existsLogin(String email){
        Connection con = FabricaConexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        int conf = 0;

        try {
            stmt = con.prepareStatement("select verifica_cadastro(?)");
            stmt.setString(1, email);

            rs = stmt.executeQuery();

            if(rs.next()){
                conf = rs.getInt("verifica_cadastro('"+email+"')");
                if(conf != 0) {
                    check = true;
                }
                else{
                    check = false;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            FabricaConexao.closeConnection(con, stmt, rs);
        }

        return check;
    }

    public ArrayList<Usuario> selecionaUsuarioLista(){
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        Usuario usuario;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = FabricaConexao.getConnection();
            stmt = con.prepareStatement("call lista_usuarios()");

            rs = stmt.executeQuery();

            while(rs.next()){
                usuario = new Usuario();

                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTotalPontos(rs.getInt("total_pontos"));
                usuario.setNivel(rs.getInt("nivel"));

                listaUsuarios.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            FabricaConexao.closeConnection(con, stmt, rs);
        }

        return listaUsuarios;
    }

    public void salvarJogada(int usuarioId, int usuarioPontos, int musicaId){
        Connection con = FabricaConexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("call salva_dados_jogada(?, ? , ?);");


            stmt.setInt(1, usuarioId);
            stmt.setInt(2, usuarioPontos);
            stmt.setInt(3, musicaId);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            FabricaConexao.closeConnection(con, stmt);
        }
    }

}
