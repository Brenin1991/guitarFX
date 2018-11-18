package main;

import classes.Musica;
import classes.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jogo.Jogo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    private static Stage stage;

    private static Scene mainScene;
    private static Scene principalScene;
    private static Scene cadastroScene;
    private static Scene listaMusicasScene;
    private static Scene informacoesMusicaScene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;

        primaryStage.setTitle("GuitarFX - Login");
        primaryStage.setResizable(false);

        Parent mainFXML = FXMLLoader.load(getClass().getResource("../view/MainFXML.fxml"));
        mainScene = new Scene(mainFXML, 800, 600);

        Parent principalFXML = FXMLLoader.load(getClass().getResource("../view/PrincipalFXML.fxml"));
        principalScene = new Scene(principalFXML, 800, 600);

        Parent cadastroFXML = FXMLLoader.load(getClass().getResource("../view/CadastroFXML.fxml"));
        cadastroScene = new Scene(cadastroFXML, 800, 600);

        Parent listaMusicasFXML = FXMLLoader.load(getClass().getResource("../view/ListaMusicasFXML.fxml"));
        listaMusicasScene = new Scene(listaMusicasFXML, 800, 600);

        Parent informacoesMusicaFXML = FXMLLoader.load(getClass().getResource("../view/InformacoesMusicaFXML.fxml"));
        informacoesMusicaScene = new Scene(informacoesMusicaFXML, 800, 600);

        mainScene.getStylesheets().add("https://fonts.googleapis.com/css?family=Russo+One");
        mainScene.getStylesheets().add("https://fonts.googleapis.com/css?family=New+Rocker");
        principalScene.getStylesheets().add("https://fonts.googleapis.com/css?family=Russo+One");
        principalScene.getStylesheets().add("https://fonts.googleapis.com/css?family=New+Rocker");
        cadastroScene.getStylesheets().add("https://fonts.googleapis.com/css?family=Russo+One");
        cadastroScene.getStylesheets().add("https://fonts.googleapis.com/css?family=New+Rocker");
        listaMusicasScene.getStylesheets().add("https://fonts.googleapis.com/css?family=Russo+One");
        listaMusicasScene.getStylesheets().add("https://fonts.googleapis.com/css?family=New+Rocker");
        informacoesMusicaScene.getStylesheets().add("https://fonts.googleapis.com/css?family=Russo+One");
        informacoesMusicaScene.getStylesheets().add("https://fonts.googleapis.com/css?family=New+Rocker");


        primaryStage.setScene(mainScene);
        primaryStage.show();

    }
    public static void trocaTela(String scr, Usuario usuario, Musica musica){
        switch (scr){
            case "main":
                stage.setScene(mainScene);
                notifyAllListeners("main", usuario, musica);
                break;
            case "principal":
                stage.setScene(principalScene);
                notifyAllListeners("principal", usuario, musica);
                break;
            case "cadastro":
                stage.setScene(cadastroScene);
                notifyAllListeners("cadastro", usuario, musica);
                break;
            case "listaMusicas":
                stage.setScene(listaMusicasScene);
                notifyAllListeners("listaMusicas", usuario, musica);
                break;
            case "informacoesMusica":
                stage.setScene(informacoesMusicaScene);
                notifyAllListeners("informacoesMusica", usuario, musica);
                break;
            case "jogo":
                janelaJogo();
                break;
        }
    }

    public static void trocaTela(String scr){
        trocaTela(scr, null, null);
    }

    public static void trocaTela(String scr, Usuario usuario){
        trocaTela(scr, usuario, null);
    }

    public static void janelaJogo(){
        Jogo jogo = new Jogo();

        try {
            jogo.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    //--------------------------------------------------------------------
    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    public static interface OnChangeScreen{
        void onScreenChanged(String newScreen, Usuario usuario, Musica musica);
    }

    public static void addOnChangeScreenListener(OnChangeScreen newListener){
        listeners.add(newListener);
    }

    private static void notifyAllListeners(String newScreen, Usuario usuario, Musica musica){
        for(OnChangeScreen l : listeners){
            l.onScreenChanged(newScreen, usuario, musica);
        }
    }

    public static void fechar(){
        stage.close();
    }
}
