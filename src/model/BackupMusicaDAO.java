package model;

import classes.Musica;
import fabricaConexao.FabricaConexao;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BackupMusicaDAO {

    private ArrayList<Musica> suporte;
    private static BackupMusicaDAO instance;
    private static String NOME_ARQ_TXT="backup/Musica.txt";
    private FileWriter fw;
    private FileReader fr;
    BufferedReader br;

    private BackupMusicaDAO(){
        try {
            suporte = new ArrayList<>();
            fw = new FileWriter(NOME_ARQ_TXT);
            fr = new FileReader(NOME_ARQ_TXT);
            br = new BufferedReader(fr);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static BackupMusicaDAO getInstance(){
        if(instance==null){
            instance = new BackupMusicaDAO();
        }
        return instance;
    }

    public void addSuporte(Musica star){
        if(star != null) {
            suporte.add(star);
        }
    }

    public void salvarTXT() throws IOException {
        suporte.clear();
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from musica");
            while (rs.next()){
                Musica musica = monta(rs);
                suporte.add(musica);
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

            for(Musica m: suporte){

                bw.write(m.getAutor() + ";" + m.getMusica() + ";" + m.getGenero() + ";" + m.getAno() + ";" + m.getDescricao() + ";" +m.getTempo() + ";"+ m.getLink_imagem() + ";"+ m.getLink_youtube() + "\n");

            }

            bw.close();
            fw.close();

        }catch (IOException e){
            System.out.println("Problema com o arquivo!!!"+e.getMessage());
        }



    }

    private Musica monta(ResultSet rs)throws SQLException{
        Musica m = null;
        try {

            String autor = rs.getString("autor");
            String musica = rs.getString("musica");
            int genero = rs.getInt("genero");
            int ano = rs.getInt("ano");
            String descricao = rs.getString("descricao");
            float tempo = rs.getFloat("tempo");
            String link_imagem = rs.getString("link_imagem");
            String link_youtube = rs.getString("youtube_link");

            m = new Musica();
            m.setAutor(autor);
            m.setMusica(musica);
            m.setGenero(genero);
            m.setAno(ano);
            m.setDescricao(descricao);
            m.setTempo(tempo);
            m.setLink_imagem(link_imagem);
            m.setLink_youtube(link_youtube);
            return m;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return m;
    }

    public void lerTXT() throws IOException{
        MusicaDAO musicaDAO = new MusicaDAO();
        try{
            Connection c = FabricaConexao.getConnection();
            Statement stmt = c.createStatement();
            String s = "delete from musica";
            String a = "ALTER TABLE musica AUTO_INCREMENT = 1";
            stmt.execute(s);
            stmt.execute(a);
            stmt.close();
            c.close();

            suporte.clear();
            String linha = null;

            while((linha=br.readLine())!=null){
                String[] tks = linha.split(";");


                String autor = tks[0];
                String musica = tks[1];
                int genero = Integer.parseInt(tks[2]);
                int ano = Integer.parseInt(tks[3]);
                String descricao = tks[4];
                String tempo = tks[5];
                String link_imagem = tks[6];
                String link_youtube = tks[7];

                Musica m = new Musica();

                m.setAutor(autor);
                m.setMusica(musica);
                m.setGenero(genero);
                m.setAno(ano);
                m.setDescricao(descricao);
                m.setTempo(180);
                m.setLink_imagem(link_imagem);
                m.setLink_youtube(link_youtube);

                musicaDAO.cadastrarMusica(m);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
