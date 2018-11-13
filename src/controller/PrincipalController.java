package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import main.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    @FXML
    private Button btPlay = new Button();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btPlay.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("jogo");
        });
    }

}
