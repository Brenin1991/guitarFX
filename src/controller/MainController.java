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

    private Usuario usuario;
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
        usuario = new Usuario();
        usuarioDAO = new UsuarioDAO();

        usuario = usuarioDAO.checkLogin(tfEmail.getText(), pfSenha.getText());
        if(usuario != null){
            Main.trocaTela("principal", usuario, null);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Login successfully: ");
            alert.setContentText("Welcome "+usuario.getNome()+". ");
            alert.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Email and/or password does not match: ");
            alert.setContentText("Please try again. ");
            alert.show();
        }
    }
}
