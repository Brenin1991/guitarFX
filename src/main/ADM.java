package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;

public class ADM extends Application {
    private static Stage stage;
    private static Scene principalADMScene;
    private static Scene manterMusicasScene;
    private static Scene manterUsuariosScene;
    private static Scene manterGenerosScene;
    private static Scene editarMusicaScene;
    private static Scene editarUsuarioScene;
    private static Scene editarGeneroScene;
    private static Scene cadastrarMusicaScene;
    private static Scene cadastrarUsuarioScene;
    private static Scene cadastrarGeneroScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("GuitarFX");
        primaryStage.setResizable(false);

        Parent principalADMFXML = FXMLLoader.load(getClass().getResource("../view/admin/PrincipalADMFXML.fxml"));
        principalADMScene = new Scene( principalADMFXML, 800, 600);

        Parent manterMusicasFXML = FXMLLoader.load(getClass().getResource("../view/admin/ManterMusicasFXML.fxml"));
        manterMusicasScene = new Scene(manterMusicasFXML, 800, 600);

        Parent manterGenerosFXML = FXMLLoader.load(getClass().getResource("../view/admin/ManterGenerosFXML.fxml"));
        manterGenerosScene = new Scene(manterGenerosFXML, 800, 600);

        Parent manterUsuarios = FXMLLoader.load(getClass().getResource("../view/admin/ManterUsuariosFXML.fxml"));
        manterUsuariosScene = new Scene(manterUsuarios, 800, 600);

        Parent cadastrarMusica = FXMLLoader.load(getClass().getResource("../view/admin/CadastrarMusicaFXML.fxml"));
        cadastrarMusicaScene = new Scene(cadastrarMusica, 800, 600);

        Parent cadastrarGenero = FXMLLoader.load(getClass().getResource("../view/CadastrarGeneroFXML.fxml"));
        cadastrarGeneroScene = new Scene(cadastrarGenero, 800, 600);

        Parent cadastrarUsuario = FXMLLoader.load(getClass().getResource("../view/CadastrarUsuarioFXML.fxml"));
        cadastrarUsuarioScene = new Scene(cadastrarUsuario, 800, 600);

        Parent editarMusica = FXMLLoader.load(getClass().getResource("../view/EditarMusicaFXML.fxml"));
        editarMusicaScene = new Scene(editarMusica, 800, 600);

        Parent editarGenero = FXMLLoader.load(getClass().getResource("../view/EditarGeneroFXML.fxml"));
        editarGeneroScene = new Scene(editarGenero, 800, 600);

        Parent editarUsuario = FXMLLoader.load(getClass().getResource("../view/EditarUsuarioFXML.fxml"));
        editarUsuarioScene = new Scene(editarUsuario, 800, 600);

        principalADMScene.getStylesheets().add("https://fonts.googleapis.com/css?family=Russo+One");
        principalADMScene.getStylesheets().add("https://fonts.googleapis.com/css?family=New+Rocker");

        primaryStage.setScene(principalADMScene);
        primaryStage.show();
    }
    public static void trocaTela(String scr, int idMusica, int idGenero, int idUsuario){
        switch (scr){
            case "admMain":
                stage.setScene(principalADMScene);
                notifyAllListeners("admMain", idMusica, idGenero, idUsuario);
                break;
            case "manterMusicas":
                stage.setScene(manterMusicasScene);
                notifyAllListeners("manterMusicas", idMusica, idGenero, idUsuario);
                break;
            case "manterGeneros":
                stage.setScene(manterGenerosScene);
                notifyAllListeners("manterGeneros", idMusica, idGenero, idUsuario);
                break;
            case "manterUsuarios":
                stage.setScene(manterUsuariosScene);
                notifyAllListeners("manterUsuarios", idMusica, idGenero, idUsuario);
                break;
            case "editarMusica":
                stage.setScene(editarMusicaScene);
                notifyAllListeners("editarMusica", idMusica, idGenero, idUsuario);
                break;
            case "editarGenero":
                stage.setScene(editarGeneroScene);
                notifyAllListeners("editarGeneros", idMusica, idGenero, idUsuario);
                break;
            case "editarUsuario":
                stage.setScene(editarUsuarioScene);
                notifyAllListeners("editarUsuario", idMusica, idGenero, idUsuario);
                break;
            case "cadastrarMusica":
                stage.setScene(cadastrarMusicaScene);
                notifyAllListeners("cadastrarMusica", idMusica, idGenero, idUsuario);
                break;
            case "cadastrarGenero":
                stage.setScene(cadastrarGeneroScene);
                notifyAllListeners("cadastrarGenero", idMusica, idGenero, idUsuario);
                break;
            case "cadastrarUsuario":
                stage.setScene(cadastrarUsuarioScene);
                notifyAllListeners("cadastrarUsuario", idMusica, idGenero, idUsuario);
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    //--------------------------------------------------------------------
    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    public static interface OnChangeScreen{
        void onScreenChanged(String newScreen, int idMusica, int idGeneros, int idUsuario);
    }

    public static void addOnChangeScreenListener(OnChangeScreen newListener){
        listeners.add(newListener);
    }

    private static void notifyAllListeners(String newScreen, int idMusica, int idGenero, int idUsuario){
        for(OnChangeScreen l : listeners){
            l.onScreenChanged(newScreen, idMusica, idGenero, idUsuario);
        }
    }

    public static void fechar(){
        stage.close();
    }
}
