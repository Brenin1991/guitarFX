package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import main.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class InformacoesMusicaController implements Initializable {
    @FXML
    private Button btJogar = new Button();

    public void initialize(URL url, ResourceBundle rb) {
        btJogar.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("jogo");
        });
    }
}
