package sample.jogo;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import sample.Main;
import sample.classes.Musica;
import sample.classes.Objeto;
import sample.classes.Usuario;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.model.MusicaDAO;
import sample.model.UsuarioDAO;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Jogo{
    public Group grupo;
    public Scene scene;
    private int pontos = 0;
    private int barraRock = 100;
    private AnchorPane mainPane = new AnchorPane();
    private Pane braco = new Pane();
    private Pane pontosPane = new Pane();
    private Pane tempoPane = new Pane();
    private Pane infoMusicasPane = new Pane();
    private Label lbPontos = new Label("POINTS: "+pontos);
    private Label lbBarraRock = new Label("ROCK: "+barraRock);
    private Label lbStatus = new Label(" READY! 'SPACE'\n        TO START");
    private Label lbNome = new Label("NAME");
    private Label lbArtista = new Label("AUTOR, YEAR");
    private Label lbTempo = new Label("TIME ");
    private String bgIMG = new String();

    VBox vbox = new VBox();

    private Objeto nota1 = new Objeto();
    private Objeto nota2 = new Objeto();
    private Objeto nota3 = new Objeto();
    private Objeto nota4 = new Objeto();
    private Objeto nota5 = new Objeto();

    private Objeto palheta1 = new Objeto();
    private Objeto palheta2 = new Objeto();
    private Objeto palheta3 = new Objeto();
    private Objeto palheta4 = new Objeto();
    private Objeto palheta5 = new Objeto();

    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Musica musica = new Musica();
    private MusicaDAO musicaDAO = new MusicaDAO();
    public String musicaNotas = new String();
    public int contador;
    public String buffer;
    public contarTempo contarTempo = new contarTempo();
    public InputStream clap;
    public tocarMusica tocarMusica = new tocarMusica();
    public Player clip;
    //public boolean win = false;


    public void initialize() {


        lerArquivo();
        System.out.println(musicaNotas);
        contador = musicaNotas.length();

        palheta1.criarCirculo(300, 550, 20, 5, "#04B404", "#000000");
        palheta2.criarCirculo(350, 550, 20, 5, "#DF0101", "#000000");
        palheta3.criarCirculo(400, 550, 20, 5, "#FFFF00", "#000000");
        palheta4.criarCirculo(450, 550, 20, 5, "#01A9DB", "#000000");
        palheta5.criarCirculo(500, 550, 20, 5, "#FF8000", "#000000");

        nota1.criarCirculo(300, -20, 15, 4, "#000000", "#04B404");
        nota2.criarCirculo(350, -20, 15, 4, "#000000", "#DF0101");
        nota3.criarCirculo(400, -20, 15, 4, "#000000", "#FFFF00");
        nota4.criarCirculo(450, -20, 15, 4, "#000000", "#01A9DB");
        nota5.criarCirculo(500, -20, 15, 4, "#000000", "#FF8000");

        mainPane.setPrefSize(800, 600);
        mainPane.setStyle(" -fx-background-image: url(" + musica.getLink_imagem() + "); -fx-background-repeat: no-repeat;   -fx-background-size: 100% 100%;");
        pontosPane.setPrefSize(250, 100);
        pontosPane.setLayoutX(0);
        pontosPane.setLayoutY(500);
        pontosPane.getStyleClass().add("background-amplificador");
        pontosPane.getChildren().add(lbBarraRock);
        lbBarraRock.getStyleClass().add("label-header");
        lbBarraRock.setLayoutX(20);
        lbBarraRock.setLayoutY(55);
        pontosPane.getChildren().add(lbPontos);
        lbPontos.getStyleClass().add("label-header");
        lbPontos.setLayoutX(20);
        lbPontos.setLayoutY(6);

        /*tempoPane.setPrefSize(250, 50);
        tempoPane.setLayoutX(0);
        tempoPane.setLayoutY(550);
        tempoPane.getStyleClass().add("background-amplificador");
        tempoPane.getChildren().add(lbTempo);
        lbTempo.getStyleClass().add("label-header");
        lbTempo.setLayoutX(20);
        lbTempo.setLayoutY(6);*/

        infoMusicasPane.setPrefSize(250, 60);
        infoMusicasPane.setLayoutX(0);
        infoMusicasPane.setLayoutY(0);
        infoMusicasPane.getStyleClass().add("background-amplificador");
        infoMusicasPane.getChildren().add(lbNome);
        infoMusicasPane.getChildren().add(lbArtista);
        lbNome.getStyleClass().add("label-header");
        lbNome.setLayoutX(20);
        lbNome.setLayoutY(4);
        lbArtista.getStyleClass().add("label-elementos");
        lbArtista.setLayoutX(20);
        lbArtista.setLayoutY(30);

        vbox.setPrefSize(320, 175);
        vbox.setLayoutX(470);
        vbox.setLayoutY(0);

        braco.setPrefSize(300, 600);
        braco.setLayoutX(250);
        braco.setLayoutY(0);
        braco.getStyleClass().add("background-braco");
        braco.getChildren().add(lbStatus);
        lbStatus.getStyleClass().add("label-header");
        lbStatus.setLayoutX(40);
        lbStatus.setLayoutY(200);

        mainPane.getChildren().add(braco);
        mainPane.getChildren().add(vbox);
        mainPane.getChildren().add(pontosPane);
        mainPane.getChildren().add(infoMusicasPane);
        mainPane.getChildren().add(tempoPane);

        grupo = new Group(mainPane, nota1.getCirculo(), nota2.getCirculo(), nota3.getCirculo(), nota4.getCirculo(), nota5.getCirculo(), palheta1.getCirculo(), palheta2.getCirculo(), palheta3.getCirculo(), palheta4.getCirculo(), palheta5.getCirculo());
        scene = new Scene(grupo, 800, 600);
        scene.getStylesheets().add("sample/style/Style.css");
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Russo+One");
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=New+Rocker");
    }

    public void lerString(){
        contarTempo.start();
    }

    public class contarTempo extends Thread{
        public void run() {
            for (int i = 0; i < contador; i++){
                try {
                    Thread.sleep(340);
                    //if(nota1.getPosicaoY() > 800 && nota2.getPosicaoY() > 800 && nota3.getPosicaoY() > 800 && nota4.getPosicaoY() > 800 && nota5.getPosicaoY() > 800);
                } catch (Exception e) {
                }
                if(musicaNotas.substring(i, i+1).equals("-")){

                    buffer = musicaNotas.substring(i-5,i);
                    //System.out.println(buffer);
                    iniciarJogo(buffer);
                }
            }
        }
    }

    public void iniciaPartida (Stage stage, int idUsuario, int idMusica){
        //stage.setResizable(false);
        //stage.initStyle(StageStyle.UNDECORATED);

        carregarInfoMusica(idMusica);
        carregarInfoUsuario(idUsuario);

        initialize();
        gameLoop(scene, stage);

        stage.setScene(scene);
        //stage.show();
    }

    public void gameLoop(Scene scene, Stage stage){
            scene.setOnKeyPressed(e -> {
                Random gerador = new Random();
                //virfica jogo
                if (logicaJogo()) {
                    //stage.close();
                    //new contarTempo().stop();
                }

                if (e.getCode() == KeyCode.SPACE) {
                    //iniciarJogo();
                    lerString();
                    tocarMusica.start();
                }
                //palhetas
                if (e.getCode() == KeyCode.A) {
                    palheta1.setVerificaAnimacao(true);
                    palheta1.animacao();
                    examineColisao(nota1, palheta1);
                    ///nota1.setVerificaMovimento(gerador.nextInt(3));
                    //nota1.mover();

                } else if (e.getCode() == KeyCode.S) {
                    palheta2.setVerificaAnimacao(true);
                    palheta2.animacao();
                    examineColisao(nota2, palheta2);
                    //nota2.setVerificaMovimento(gerador.nextInt(3));
                    //nota2.mover();

                } else if (e.getCode() == KeyCode.J) {
                    palheta3.setVerificaAnimacao(true);
                    palheta3.animacao();
                    examineColisao(nota3, palheta3);
                    //nota3.setVerificaMovimento(gerador.nextInt(3));
                    //nota3.mover();

                } else if (e.getCode() == KeyCode.K) {
                    palheta4.setVerificaAnimacao(true);
                    palheta4.animacao();
                    examineColisao(nota4, palheta4);
                    //nota4.setVerificaMovimento(gerador.nextInt(3));
                    //nota4.mover();

                } else if (e.getCode() == KeyCode.L) {
                    palheta5.setVerificaAnimacao(true);
                    palheta5.animacao();
                    examineColisao(nota5, palheta5);
                    //nota5.setVerificaMovimento(gerador.nextInt(3));
                    //nota5.mover();

                }
            });
    }

    public void examineColisao (Objeto nota, Objeto palheta) {
        boolean colisao = false;
        if ( palheta.getCirculo()  != nota.getCirculo()) {
                Shape intersect = Shape.intersect ( nota.getCirculo(), palheta.getCirculo());
                if ( intersect.getBoundsInLocal ( ).getWidth ( ) != -1 ) {
                    colisao = true;
                    nota.setVerificaAnimacao(true);
                }
        }
        if(colisao) {
           pontos += 10;
           barraRock +=10;

           lbBarraRock.setText("ROCK: "+barraRock);
           lbPontos.setText("POINTS: "+pontos);
           lbStatus.setText("HIT THE NOTE! +10");
           lbStatus.setTextFill(Color.web("#2EFE2E"));
        }
        if ( !colisao ) {
            barraRock -= 30;
            lbBarraRock.setText("ROCK: "+barraRock);
            lbStatus.setText("MISSED NOTE! -10");
            lbStatus.setTextFill(Color.web("#FF0000"));
        }
    }

    public void carregarInfoMusica(int idMusica){
        musica = musicaDAO.selecionaMusica(idMusica);
        lbNome.setText(musica.getMusica());
        lbArtista.setText(""+musica.getAutor()+", "+musica.getAno());
    }

    public void carregarInfoUsuario(int idUsuario){
        usuario = usuarioDAO.selecionaUsuario(idUsuario);
    }

    public Boolean logicaJogo(){
        Boolean conf = false;
        if(barraRock <= 0){
            contarTempo.stop();
            tocarMusica.stop();
            clip.close();

            salvaDadosnoBanco();

            Main.trocaTela("score", usuario.getId(), musica.getId(), pontos, 0);


            conf = true;
        }
        else if(barraRock >= 100){
            barraRock = 90;
        }

        else if(clip.isComplete()){
            contarTempo.stop();
            tocarMusica.stop();
            clip.close();
            salvaDadosnoBanco();

            Main.trocaTela("score", usuario.getId(), musica.getId(), pontos, 1);

            conf = true;
        }

        return conf;
    }

    public void salvaDadosnoBanco(){
        usuarioDAO.salvarJogada(usuario.getId(), pontos, musica.getId());
    }

    public void iniciarJogo(String buffer){

        nota1.setVerificaMovimento(Integer.parseInt(buffer.substring(0,1)));
        nota1.mover();
        nota2.setVerificaMovimento(Integer.parseInt(buffer.substring(1,2)));
        nota2.mover();
        nota3.setVerificaMovimento(Integer.parseInt(buffer.substring(2,3)));
        nota3.mover();
        nota4.setVerificaMovimento(Integer.parseInt(buffer.substring(3,4)));
        nota4.mover();
        nota5.setVerificaMovimento(Integer.parseInt(buffer.substring(4,5)));
        nota5.mover();

        System.out.println(buffer.substring(0,1));
        System.out.println(buffer.substring(1,2));
        System.out.println(buffer.substring(2,3));
        System.out.println(buffer.substring(3,4));
        System.out.println(buffer.substring(4,5));
        System.out.println("-");

     }

     public void lerArquivo() {
        musicaNotas = musica.getNotas();
     }

    public class tocarMusica extends Thread{
        public void run() {
            tocarSom();
        }
    }

    public void tocarSom() {
        try {
            clap = getClass().getResourceAsStream("musics/"+musica.getLink_youtube() + ".mp3");
            clip = new Player(clap);
            clip.play();
            //Thread.sleep(clip.getMicrosecondLength());
            //win = true;

        } catch (JavaLayerException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
