package sample;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.jogo.Jogo;
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
    private static Scene rankGlobalScene;
    private static Scene creditosScene;
    private static Scene scoreScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("GuitarFX");
        primaryStage.setResizable(false);

        Parent mainFXML = FXMLLoader.load(getClass().getResource("view/usuario/MainFXML.fxml"));
        mainScene = new Scene(mainFXML, 800, 600);

        Parent principalFXML = FXMLLoader.load(getClass().getResource("view/usuario/PrincipalFXML.fxml"));
        principalScene = new Scene(principalFXML, 800, 600);

        Parent cadastroFXML = FXMLLoader.load(getClass().getResource("view/usuario/CadastroFXML.fxml"));
        cadastroScene = new Scene(cadastroFXML, 800, 600);

        Parent listaMusicasFXML = FXMLLoader.load(getClass().getResource("view/usuario/ListaMusicasFXML.fxml"));
        listaMusicasScene = new Scene(listaMusicasFXML, 800, 600);

        Parent informacoesMusicaFXML = FXMLLoader.load(getClass().getResource("view/usuario/InformacoesMusicaFXML.fxml"));
        informacoesMusicaScene = new Scene(informacoesMusicaFXML, 800, 600);

        Parent rankGlobalFXML = FXMLLoader.load(getClass().getResource("view/usuario/RankGlobalFXML.fxml"));
        rankGlobalScene = new Scene(rankGlobalFXML, 800, 600);

        Parent creditosFXML = FXMLLoader.load(getClass().getResource("view/usuario/CreditosFXML.fxml"));
        creditosScene = new Scene(creditosFXML, 800, 600);

        Parent scoreFXML = FXMLLoader.load(getClass().getResource("view/usuario/ScoreFXML.fxml"));
        scoreScene = new Scene(scoreFXML, 800, 600);


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
        rankGlobalScene.getStylesheets().add("https://fonts.googleapis.com/css?family=Russo+One");
        rankGlobalScene.getStylesheets().add("https://fonts.googleapis.com/css?family=New+Rocker");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
    public static void trocaTela(String scr, int idUsuario, int idMusica, int score, int status){
        switch (scr){
            case "main":
                stage.setScene(mainScene);
                notifyAllListeners("main", idUsuario, idMusica, score, status);
                break;
            case "principal":
                stage.setScene(principalScene);
                notifyAllListeners("principal", idUsuario, idMusica, score, status);
                break;
            case "cadastro":
                stage.setScene(cadastroScene);
                notifyAllListeners("cadastro", idUsuario, idMusica, score, status);
                break;
            case "listaMusicas":
                stage.setScene(listaMusicasScene);
                notifyAllListeners("listaMusicas", idUsuario, idMusica, score, status);
                break;
            case "informacoesMusica":
                stage.setScene(informacoesMusicaScene);
                notifyAllListeners("informacoesMusica", idUsuario, idMusica, score, status);
                break;
            case "rankGlobal":
                stage.setScene(rankGlobalScene);
                notifyAllListeners("rankGlobal", idUsuario, idMusica, score, status);
                break;
            case "creditos":
                stage.setScene(creditosScene);
                notifyAllListeners("creditos", idUsuario, idMusica, score, status);
                break;
            case "jogo":
                janelaJogo(idUsuario, idMusica);
                notifyAllListeners("jogo", idUsuario, idMusica, score, status);
                break;
            case "score":
                stage.setScene(scoreScene);
                notifyAllListeners("score", idUsuario, idMusica, score, status);
                break;
        }
    }

    public static void trocaTela(String scr){
        trocaTela(scr, 0, 0, 0, 5);
    }

    public static void trocaTela(String scr, int idUsuario, int idMusica){
        trocaTela(scr, idUsuario, idMusica, 0, 5);
    }

    public static void trocaTela(String scr, int idUsuario, int score, int status){
        trocaTela(scr, idUsuario, 0, score, status);
    }
    public static void janelaJogo(int idUsuario, int idMusica){
        Jogo jogo = new Jogo();

        try {
            jogo.iniciaPartida(stage, idUsuario, idMusica);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(Main.class, args);
    }

    //--------------------------------------------------------------------
    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    public static interface OnChangeScreen{
        void onScreenChanged(String newScreen, int idUsuario, int idMusica, int score, int status);
    }

    public static void addOnChangeScreenListener(OnChangeScreen newListener){
        listeners.add(newListener);
    }

    private static void notifyAllListeners(String newScreen, int idUsuario, int idMusica, int score, int status){
        for(OnChangeScreen l : listeners){
            l.onScreenChanged(newScreen, idUsuario, idMusica, score, status);
        }
    }

    public static void fechar(){
        stage.close();
    }
}
