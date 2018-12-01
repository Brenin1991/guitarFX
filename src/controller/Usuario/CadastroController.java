package controller.Usuario;

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

public class CadastroController  implements Initializable {
    @FXML
    private TextField tfNickname = new TextField();
    @FXML
    private TextField tfEmail = new TextField();
    @FXML
    private PasswordField pfPassword = new PasswordField();
    @FXML
    private Button btBack = new Button();
    @FXML
    private Button btRegister = new Button();

    private Usuario usuario;
    private UsuarioDAO usuarioDAO;

    public void initialize(URL url, ResourceBundle rb) {
        btBack.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("main");
        });

        btRegister.setOnMouseClicked((MouseEvent e) -> {
            register();
        });
    }

    //Register a player
    public void register(){
        usuario = new Usuario();
        usuarioDAO = new UsuarioDAO();
        usuario.setNome(tfNickname.getText());
        usuario.setEmail(tfEmail.getText());
        usuario.setSenha(pfPassword.getText());
        boolean confirmation = false;
        confirmation = usuarioDAO.cadastraUsuario(usuario);
        if(confirmation == true){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successo!");
            alert.setHeaderText("Registered player: ");
            alert.setContentText("login to continue. ");
            alert.show();
            Main.trocaTela("main");
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("There was an error registering the player: ");
            alert.setContentText("Please try again. ");
            alert.show();
        }
    }
}
