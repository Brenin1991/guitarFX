package controller.admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import main.ADM;

import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalADMController implements Initializable {
    @FXML
    private Button btManterMusica = new Button();
    @FXML
    private Button btManterUsuarios = new Button();
    @FXML
    private Button btManterGenero = new Button();
    @FXML
    private Button btHistoricoJogadas = new Button();
    @FXML
    private Button btSair = new Button();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btManterMusica.setOnMouseClicked((MouseEvent e) -> {
            ADM.trocaTela("manterMusicas");
        });

        btManterUsuarios.setOnMouseClicked((MouseEvent e) -> {
            //ADM.trocaTela("manterUsuarios");
        });

        btManterGenero.setOnMouseClicked((MouseEvent e) -> {
            ADM.trocaTela("manterGeneros");
        });


        btHistoricoJogadas.setOnMouseClicked((MouseEvent e) -> {

        });

        btSair.setOnMouseClicked((MouseEvent e) -> {
            ADM.fechar();
        });



    }



}
