package controller;

import classes.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.Main;
import model.UsuarioDAO;

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

    private Usuario usuario;
    private UsuarioDAO usuarioDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btLogin.setOnMouseClicked((MouseEvent e) -> {
                Main.trocaTela("principal");
        });
    }


}
