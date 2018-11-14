package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import main.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class ListaMusicasController implements Initializable {
    @FXML
    private Button btSelecionarMusica = new Button();

    public void initialize(URL url, ResourceBundle rb) {
        btSelecionarMusica.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("informacoesMusica");
        });
    }
}
