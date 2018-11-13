package jogo;

import classes.Objeto;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;


public class Jogo extends Application {

    public static Scene scene;
    private int pontos = 100;
    private AnchorPane mainPane = new AnchorPane();
    private Pane braco = new Pane();
    private Pane pontosPane = new Pane();
    private Pane tempoPane = new Pane();
    private Pane infoMusicasPane = new Pane();
    private Label lbPontos = new Label("Pontos: "+pontos);
    private Label lbStatus = new Label("PREPARADO!");
    private Label lbNome = new Label("Nome");
    private Label lbArtista = new Label("Artista, ano");
    private Label lbTempo = new Label("Tempo: ");

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

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("GuitarFX - Game");
        stage.setResizable(false);
        //-------------------------------------------------------------------------------------------------
        nota1.criarCirculo(300, -20,15, 4, "#000000", "#04B404");
        nota2.criarCirculo(350, -20,15, 4, "#000000", "#DF0101");
        nota3.criarCirculo(400, -20,15, 4, "#000000", "#FFFF00");
        nota4.criarCirculo(450, -20,15, 4, "#000000", "#01A9DB");
        nota5.criarCirculo(500, -20,15, 4, "#000000", "#FF8000");

        palheta1.criarCirculo(300, 550,20, 5, "#04B404", "#000000");
        palheta2.criarCirculo(350, 550,20, 5, "#DF0101", "#000000");
        palheta3.criarCirculo(400, 550,20, 5, "#FFFF00", "#000000");
        palheta4.criarCirculo(450, 550,20, 5, "#01A9DB", "#000000");
        palheta5.criarCirculo(500, 550,20, 5, "#FF8000", "#000000");

        mainPane.setPrefSize(800, 600);
        mainPane.setStyle(" -fx-background-image: url(\"https://abrilvejasp.files.wordpress.com/2016/12/metallica1.jpg\"); -fx-background-repeat: no-repeat;   -fx-background-size: 100% 100%;");
        pontosPane.setPrefSize(250,50);
        pontosPane.setLayoutX(0);
        pontosPane.setLayoutY(500);
        pontosPane.getStyleClass().add("background-amplificador");
        pontosPane.getChildren().add(lbPontos);
        lbPontos.getStyleClass().add("label-header");
        lbPontos.setLayoutX(20);
        lbPontos.setLayoutY(6);

        tempoPane.setPrefSize(250,50);
        tempoPane.setLayoutX(0);
        tempoPane.setLayoutY(550);
        tempoPane.getStyleClass().add("background-amplificador");
        tempoPane.getChildren().add(lbTempo);
        lbTempo.getStyleClass().add("label-header");
        lbTempo.setLayoutX(20);
        lbTempo.setLayoutY(6);

        infoMusicasPane.setPrefSize(250,60);
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

        braco.setPrefSize(300,600);
        braco.setLayoutX(250);
        braco.setLayoutY(0);
        braco.getStyleClass().add("background-braco");
        braco.getChildren().add(lbStatus);
        lbStatus.getStyleClass().add("label-header");
        lbStatus.setLayoutX(75);
        lbStatus.setLayoutY(30);

        mainPane.getChildren().add(braco);
        mainPane.getChildren().add(pontosPane);
        mainPane.getChildren().add(infoMusicasPane);
        mainPane.getChildren().add(tempoPane);
        //--------------------------------------------------------------------------------------------------
        final Group grupo = new Group(mainPane, nota1.getCirculo(), nota2.getCirculo(), nota3.getCirculo(), nota4.getCirculo(), nota5.getCirculo(),palheta1.getCirculo(), palheta2.getCirculo(), palheta3.getCirculo(), palheta4.getCirculo(), palheta5.getCirculo());
        scene = new Scene(grupo, 800,600);
        scene.getStylesheets().add("style/Style.css");
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Russo+One");
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=New+Rocker");

        gatilho(scene);

        stage.setScene(scene);
        stage.show();
    }

    public void gatilho(Scene scene){
        scene.setOnKeyPressed(e ->{
            //notas
            if(e.getCode() == KeyCode.DIGIT1){
                nota1.setVerificaMovimento(true);
                nota1.mover();
            }
            else if(e.getCode() == KeyCode.DIGIT2){
                nota2.setVerificaMovimento(true);
                nota2.mover();
            }
            else if(e.getCode() == KeyCode.DIGIT3){
                nota3.setVerificaMovimento(true);
                nota3.mover();
            }
            else if(e.getCode() == KeyCode.DIGIT4){
                nota4.setVerificaMovimento(true);
                nota4.mover();
            }
            else if(e.getCode() == KeyCode.DIGIT5){
                nota5.setVerificaMovimento(true);
                nota5.mover();
            }
            //palhetas
            if(e.getCode() == KeyCode.A){
                palheta1.setVerificaAnimacao(true);
                palheta1.animacao();
                examineColisao(nota1.getCirculo(), palheta1.getCirculo());
            }
            else if(e.getCode() == KeyCode.S){
                palheta2.setVerificaAnimacao(true);
                palheta2.animacao();
                examineColisao(nota2.getCirculo(), palheta2.getCirculo());
            }
            else if(e.getCode() == KeyCode.J){
                palheta3.setVerificaAnimacao(true);
                palheta3.animacao();
                examineColisao(nota3.getCirculo(), palheta3.getCirculo());
            }
            else if(e.getCode() == KeyCode.K){
                palheta4.setVerificaAnimacao(true);
                palheta4.animacao();
                examineColisao(nota4.getCirculo(), palheta4.getCirculo());
            }
            else if(e.getCode() == KeyCode.L){
                palheta5.setVerificaAnimacao(true);
                palheta5.animacao();
                examineColisao(nota5.getCirculo(), palheta5.getCirculo());
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
           lbPontos.setText("Pontos: "+pontos);
           lbStatus.setText("ACERTOU! +10");
           lbStatus.setTextFill(Color.web("#2EFE2E"));
        }
        if ( !colisao ) {
            pontos -= 10;
            lbPontos.setText("Pontos: "+pontos);
            lbStatus.setText("ERROOU! -10");
            lbStatus.setTextFill(Color.web("#FF0000"));
        }


    }

    public static void main(String[] args) { launch(args); }

    public static Stage GetStage(){
        return stage;
    }

    public static void SetStage(Stage stage){
        Jogo.stage = stage;
    }

}