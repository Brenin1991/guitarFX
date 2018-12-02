package model;

import classes.Usuario;
import fabricaConexao.FabricaConexao;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BackupUsuarioDAO {

    private ArrayList<Usuario> suporte;
    private static BackupUsuarioDAO instance;
    private static String NOME_ARQ_TXT="backup/Usuario.txt";
    private FileWriter fw;
    private FileReader fr;
    BufferedReader br;

    private BackupUsuarioDAO(){
        try {
            suporte = new ArrayList<>();
            fw = new FileWriter(NOME_ARQ_TXT);
            fr = new FileReader(NOME_ARQ_TXT);
            br = new BufferedReader(fr);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static BackupUsuarioDAO getInstance(){
        if(instance==null){
            instance = new BackupUsuarioDAO();
        }
        return instance;
    }

    public void addSuporte(Usuario star){
        if(star != null) {
            suporte.add(star);
        }
    }

    public void salvarTXT() throws IOException {
        suporte.clear();
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from usuario");
            while (rs.next()){
                Usuario user = montaUsuario(rs);
                suporte.add(user);
            }
            rs.close();
            stm.close();
            c.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            FileWriter fw = new FileWriter(NOME_ARQ_TXT);
            BufferedWriter bw = new BufferedWriter(fw);

            for(Usuario u: suporte){

                    bw.write(u.getNome() + ";" + u.getEmail() + ";" + u.getSenha() + ";" + u.getPontos() + ";" + u.getNivel() + "\n");

            }

            bw.close();
            fw.close();

        }catch (IOException e){
            System.out.println("Problema com o arquivo!!!"+e.getMessage());
        }



    }

    private Usuario montaUsuario(ResultSet rs)throws SQLException{
        Usuario u=null;
        try {

            String usuario = rs.getString("usuario");
            String email = rs.getString("email");
            String senha = rs.getString("senha");
            int pontos = rs.getInt("pontos");
            int nivel = rs.getInt("nivel");

            u = new Usuario();
            u.setNome(usuario);
            u.setEmail(email);
            u.setSenha(senha);
            u.setPontos(pontos);
            u.setNivel(nivel);
            return u;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return u;
    }

    public void lerTXT() throws IOException{
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try{
            Connection c = FabricaConexao.getConnection();
            Statement stmt = c.createStatement();
            String s = "delete from usuario";
            String a = "ALTER TABLE usuario AUTO_INCREMENT = 1";
            stmt.execute(s);
            stmt.execute(a);
            stmt.close();
            c.close();


            suporte.clear();
            String linha = null;

            while((linha=br.readLine())!=null){
                String[] tks = linha.split(";");


                String usuario = tks[0];
                String email = tks[1];
                String senha = tks[2];
                int pontos = Integer.parseInt(tks[3]);
                int nivel = Integer.parseInt(tks[4]);

                Usuario user = new Usuario();
                user.setNome(usuario);
                user.setEmail(email);
                user.setSenha(senha);
                user.setPontos(pontos);
                user.setNivel(nivel);

                usuarioDAO.cadastraUsuario(user);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
