package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ADM extends Application {
    private static Stage stage;
    private static Scene principalADMScene;
    private static Scene manterMusicasScene;
    private static Scene manterUsuariosScene;
    private static Scene manterGenerosScene;
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

        //Parent manterUsuarios = FXMLLoader.load(getClass().getResource("../view/admin/ManterUsuariosFXML.fxml"));
        //manterUsuariosScene = new Scene(manterUsuarios, 800, 600);

        Parent cadastrarMusica = FXMLLoader.load(getClass().getResource("../view/admin/CadastrarMusicaFXML.fxml"));
        cadastrarMusicaScene = new Scene(cadastrarMusica, 800, 600);

        Parent cadastrarGenero = FXMLLoader.load(getClass().getResource("../view/admin/CadastrarGeneroFXML.fxml"));
        cadastrarGeneroScene = new Scene(cadastrarGenero, 800, 600);

       // Parent cadastrarUsuario = FXMLLoader.load(getClass().getResource("../view/admin/CadastrarUsuarioFXML.fxml"));
       // cadastrarUsuarioScene = new Scene(cadastrarUsuario, 800, 600);

        principalADMScene.getStylesheets().add("https://fonts.googleapis.com/css?family=Russo+One");
        principalADMScene.getStylesheets().add("https://fonts.googleapis.com/css?family=New+Rocker");

        primaryStage.setScene(principalADMScene);
        primaryStage.show();
    }
    public static void trocaTela(String scr){
        switch (scr){
            case "admMain":
                stage.setScene(principalADMScene);
                break;
            case "manterMusicas":
                stage.setScene(manterMusicasScene);
                break;
            case "manterGeneros":
                stage.setScene(manterGenerosScene);
                break;
           // case "manterUsuarios":
             //   stage.setScene(manterUsuariosScene);
             //   break;
            case "cadastrarMusica":
                stage.setScene(cadastrarMusicaScene);
                break;
            case "cadastrarGenero":
                stage.setScene(cadastrarGeneroScene);
                break;
            //case "cadastrarUsuario":
               // stage.setScene(cadastrarUsuarioScene);
               // break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }



    public static void fechar(){
        stage.close();
    }
}
