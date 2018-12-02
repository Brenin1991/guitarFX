package model;

import classes.Genero;
import classes.Musica;
import fabricaConexao.FabricaConexao;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BackupGeneroDAO {

    private ArrayList<Genero> suporte;
    private static BackupGeneroDAO instance;
    private static String NOME_ARQ_TXT="backup/Genero.txt";
    private FileWriter fw;
    private FileReader fr;
    BufferedReader br;

    private BackupGeneroDAO(){
        try {
            suporte = new ArrayList<>();
            fw = new FileWriter(NOME_ARQ_TXT);
            fr = new FileReader(NOME_ARQ_TXT);
            br = new BufferedReader(fr);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static BackupGeneroDAO getInstance(){
        if(instance==null){
            instance = new BackupGeneroDAO();
        }
        return instance;
    }

    public void addSuporte(Genero star){
        if(star != null) {
            suporte.add(star);
        }
    }

    public void salvarTXT() throws IOException {
        suporte.clear();
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from genero");
            while (rs.next()){
                Genero genero = monta(rs);
                suporte.add(genero);
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

            for(Genero g: suporte){

                bw.write(g.getNome() + "\n");

            }

            bw.close();
            fw.close();

        }catch (IOException e){
            System.out.println("Problema com o arquivo!!!"+e.getMessage());
        }



    }

    private Genero monta(ResultSet rs)throws SQLException{
        Genero g = null;
        try {
            String genero = rs.getString("genero");


            g = new Genero();
            g.setNome(genero);
            return g;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return g;
    }

    public void lerTXT() throws IOException{
        GeneroDAO generoDAO = new GeneroDAO();
        try{
            Connection c = FabricaConexao.getConnection();
            Statement stmt = c.createStatement();
            String s = "delete from genero";
            String a = "ALTER TABLE genero AUTO_INCREMENT = 1";
            stmt.execute(s);
            stmt.execute(a);
            stmt.close();
            c.close();

            suporte.clear();
            String linha = null;

            while((linha=br.readLine())!=null){
                String[] tks = linha.split(";");


                String autor = tks[0];

                Genero g = new Genero();

                g.setNome(autor);

                generoDAO.cadastrarGenero(g);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
