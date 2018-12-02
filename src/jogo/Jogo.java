package jogo;

import classes.Musica;
import classes.Objeto;
import classes.Usuario;
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
import model.MusicaDAO;
import model.UsuarioDAO;

import java.util.Random;

public class Jogo{
    public Scene scene;
    private int pontos = 0;
    private int barraRock = 100;
    private AnchorPane mainPane = new AnchorPane();
    private Pane braco = new Pane();
    private Pane pontosPane = new Pane();
    private Pane tempoPane = new Pane();
    private Pane infoMusicasPane = new Pane();
    private Label lbPontos = new Label("Pontos: "+pontos);
    private Label lbBarraRock = new Label("Rock: "+barraRock);
    private Label lbStatus = new Label("PREPARADO! \n    'ESPAÇO'\nPARA INICIAR");
    private Label lbNome = new Label("Nome");
    private Label lbArtista = new Label("Artista, ano");
    private Label lbTempo = new Label("Tempo: ");
    private String bgIMG = new String();
    WebView browser = new WebView();
    WebEngine webEngine = browser.getEngine();
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

    public void initialize() {
        nota1.criarCirculo(300, -20, 15, 4, "#000000", "#04B404");
        nota2.criarCirculo(350, -20, 15, 4, "#000000", "#DF0101");
        nota3.criarCirculo(400, -20, 15, 4, "#000000", "#FFFF00");
        nota4.criarCirculo(450, -20, 15, 4, "#000000", "#01A9DB");
        nota5.criarCirculo(500, -20, 15, 4, "#000000", "#FF8000");

        palheta1.criarCirculo(300, 550, 20, 5, "#04B404", "#000000");
        palheta2.criarCirculo(350, 550, 20, 5, "#DF0101", "#000000");
        palheta3.criarCirculo(400, 550, 20, 5, "#FFFF00", "#000000");
        palheta4.criarCirculo(450, 550, 20, 5, "#01A9DB", "#000000");
        palheta5.criarCirculo(500, 550, 20, 5, "#FF8000", "#000000");

        mainPane.setPrefSize(800, 600);
        mainPane.setStyle(" -fx-background-image: url(" + musica.getLink_imagem() + "); -fx-background-repeat: no-repeat;   -fx-background-size: 100% 100%;");
        pontosPane.setPrefSize(250, 100);
        pontosPane.setLayoutX(0);
        pontosPane.setLayoutY(450);
        pontosPane.getStyleClass().add("background-amplificador");
        pontosPane.getChildren().add(lbBarraRock);
        lbBarraRock.getStyleClass().add("label-header");
        lbBarraRock.setLayoutX(20);
        lbBarraRock.setLayoutY(55);
        pontosPane.getChildren().add(lbPontos);
        lbPontos.getStyleClass().add("label-header");
        lbPontos.setLayoutX(20);
        lbPontos.setLayoutY(6);

        tempoPane.setPrefSize(250, 50);
        tempoPane.setLayoutX(0);
        tempoPane.setLayoutY(550);
        tempoPane.getStyleClass().add("background-amplificador");
        tempoPane.getChildren().add(lbTempo);
        lbTempo.getStyleClass().add("label-header");
        lbTempo.setLayoutX(20);
        lbTempo.setLayoutY(6);

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

        webEngine.loadContent("<iframe width=\"300\" height=\"150\" src=\"https://www.youtube-nocookie.com/embed/"+musica.getLink_youtube()+"?autoplay=1&controls=0&disablekb=1&start=5&rel=0\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");

        braco.setPrefSize(300, 600);
        braco.setLayoutX(250);
        braco.setLayoutY(0);
        braco.getStyleClass().add("background-braco");
        braco.getChildren().add(lbStatus);
        lbStatus.getStyleClass().add("label-header");
        lbStatus.setLayoutX(75);
        lbStatus.setLayoutY(200);

        vbox.getChildren().add(browser);

        mainPane.getChildren().add(braco);
        mainPane.getChildren().add(vbox);
        mainPane.getChildren().add(pontosPane);
        mainPane.getChildren().add(infoMusicasPane);
        mainPane.getChildren().add(tempoPane);

        final Group grupo = new Group(mainPane, nota1.getCirculo(), nota2.getCirculo(), nota3.getCirculo(), nota4.getCirculo(), nota5.getCirculo(), palheta1.getCirculo(), palheta2.getCirculo(), palheta3.getCirculo(), palheta4.getCirculo(), palheta5.getCirculo());
        scene = new Scene(grupo, 800, 600);
        scene.getStylesheets().add("style/Style.css");
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Russo+One");
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=New+Rocker");
    }

    public void iniciaPartida (Stage stage, int idUsuario, int idMusica){
        stage.setTitle("GuitarFX - Game");
        stage.setResizable(false);

        carregarInfoMusica(idMusica);
        carregarInfoUsuario(idUsuario);

        initialize();
        gameLoop(scene, stage);

        stage.setScene(scene);
        stage.show();
    }

    public void gameLoop(Scene scene, Stage stage){
            scene.setOnKeyPressed(e -> {
                Random gerador = new Random();
                //virfica jogo
                if (logicaJogo()) {
                    stage.close();
                }

                if (e.getCode() == KeyCode.SPACE) {
                    iniciarJogo();
                }
                //palhetas
                if (e.getCode() == KeyCode.A) {
                    palheta1.setVerificaAnimacao(true);
                    palheta1.animacao();
                    examineColisao(nota1.getCirculo(), palheta1.getCirculo());
                    nota1.setVerificaMovimento(gerador.nextInt(3));
                    nota1.mover();

                } else if (e.getCode() == KeyCode.S) {
                    palheta2.setVerificaAnimacao(true);
                    palheta2.animacao();
                    examineColisao(nota2.getCirculo(), palheta2.getCirculo());
                    nota2.setVerificaMovimento(gerador.nextInt(3));
                    nota2.mover();

                } else if (e.getCode() == KeyCode.J) {
                    palheta3.setVerificaAnimacao(true);
                    palheta3.animacao();
                    examineColisao(nota3.getCirculo(), palheta3.getCirculo());
                    nota3.setVerificaMovimento(gerador.nextInt(3));
                    nota3.mover();

                } else if (e.getCode() == KeyCode.K) {
                    palheta4.setVerificaAnimacao(true);
                    palheta4.animacao();
                    examineColisao(nota4.getCirculo(), palheta4.getCirculo());
                    nota4.setVerificaMovimento(gerador.nextInt(3));
                    nota4.mover();

                } else if (e.getCode() == KeyCode.L) {
                    palheta5.setVerificaAnimacao(true);
                    palheta5.animacao();
                    examineColisao(nota5.getCirculo(), palheta5.getCirculo());
                    nota5.setVerificaMovimento(gerador.nextInt(3));
                    nota5.mover();

                }
            });
    }

    public void examineColisao (Shape nota, Shape palheta) {
        boolean colisao = false;
        if ( palheta  != nota) {
                Shape intersect = Shape.intersect ( nota, palheta);
                if ( intersect.getBoundsInLocal ( ).getWidth ( ) != -1 ) {
                    colisao = true;
                }
            }
        if(colisao) {
           pontos += 10;
           barraRock +=10;

           lbBarraRock.setText("Rock: "+barraRock);
           lbPontos.setText("Pontos: "+pontos);
           lbStatus.setText("ACERTOU! +10");
           lbStatus.setTextFill(Color.web("#2EFE2E"));
        }
        if ( !colisao ) {
            barraRock -= 30;
            lbBarraRock.setText("Rock: "+barraRock);
            lbStatus.setText("ERROOU! -10");
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
            salvaDadosnoBanco();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Perdeu Playboy!");
            alert.setHeaderText("Você precisa treinar!");
            alert.setContentText("Sua pontuação na partida: "+pontos);
            alert.show();

            browser.getEngine().load(null);

            conf = true;
        }
        else if(barraRock >= 100){
            barraRock = 90;
        }

        return conf;
    }

    public void salvaDadosnoBanco(){
        usuarioDAO.salvarJogada(usuario.getId(), pontos, musica.getId());
    }

    public void iniciarJogo(){
            nota1.setVerificaMovimento(1);
            nota1.mover();
            nota2.setVerificaMovimento(1);
            nota2.mover();
            nota3.setVerificaMovimento(1);
            nota3.mover();
            nota4.setVerificaMovimento(1);
            nota4.mover();
            nota5.setVerificaMovimento(1);
            nota5.mover();
     }
}
