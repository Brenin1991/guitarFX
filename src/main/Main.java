package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage stage;

    private static Scene mainScene;
    private static Scene principalScene;
    private static Scene cadastroScene;
    private static Scene jogoScene;


    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;

        primaryStage.setTitle("GuitarFX - Login");
        primaryStage.setResizable(false);

        Parent mainFXML = FXMLLoader.load(getClass().getResource("../view/MainFXML.fxml"));
        mainScene = new Scene(mainFXML, 800, 600);

        Parent principalFXML = FXMLLoader.load(getClass().getResource("../view/PrincipalFXML.fxml"));
        principalScene = new Scene(principalFXML, 800, 600);

        mainScene.getStylesheets().add("https://fonts.googleapis.com/css?family=Russo+One");
        mainScene.getStylesheets().add("https://fonts.googleapis.com/css?family=New+Rocker");
        principalScene.getStylesheets().add("https://fonts.googleapis.com/css?family=Russo+One");
        principalScene.getStylesheets().add("https://fonts.googleapis.com/css?family=New+Rocker");


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

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
