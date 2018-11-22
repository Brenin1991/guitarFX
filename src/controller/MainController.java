package controller;

import classes.Musica;
import classes.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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

    private UsuarioDAO usuarioDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
            Main.trocaTela("principal", usuarioId);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successo!");
            alert.setHeaderText("Login realizado: ");
            alert.setContentText("Bem-vindo(a)!");
            alert.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Email e/ou senha não existem: ");
            alert.setContentText("Tente novamente. ");
            alert.show();
        }
    }
}
