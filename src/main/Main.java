package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jogo.Jogo;

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
    public static void trocaTela(String scr){
        switch (scr){
            case "main":
                stage.setScene(mainScene);
                break;
            case "principal":
                stage.setScene(principalScene);
                break;
            case "cadastro":
                stage.setScene(cadastroScene);
                break;
            case "listaMusicas":
                stage.setScene(listaMusicasScene);
                break;
            case "informacoesMusica":
                stage.setScene(informacoesMusicaScene);
                break;
            case "jogo":
                janelaJogo();
                break;
        }
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
}
