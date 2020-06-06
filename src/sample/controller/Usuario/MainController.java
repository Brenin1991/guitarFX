package sample.controller.Usuario;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import sample.Main;
import sample.model.UsuarioDAO;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField pfSenha;
    @FXML
    private Button btRegister;
    @FXML
    private Button btLogin;
    @FXML
    private WebView web;
    public WebEngine engine;
    private UsuarioDAO usuarioDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        engine = web.getEngine();
        engine.load("https://hardrockstudio.github.io/home/pages/guitarFX/server%20news/index.html");
        btLogin.setOnMouseClicked((MouseEvent e) -> {
            login();
        });

        btRegister.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("cadastro");
        });

    }
    public void login(){
        int usuarioId = 0;
        usuarioDAO = new UsuarioDAO();

        usuarioId = usuarioDAO.checkLogin(tfEmail.getText(), pfSenha.getText());
        if(usuarioId != 0){
            Main.trocaTela("principal", usuarioId, 0);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS!");
            alert.setHeaderText("LOGGED IN: ");
            alert.setContentText("WELCOME!");
            alert.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText("INVALID LOGIN: ");
            alert.setContentText("TRY AGAIN. ");
            alert.show();
        }
    }
}
