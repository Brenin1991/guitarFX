package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.Main;

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


    public void initialize(URL url, ResourceBundle rb) {
        btBack.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("main");
        });

        btRegister.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("main");
        });
    }
}
