package controller.Admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalADMController implements Initializable {
    @FXML
    private Button btManterMusica = new Button();
    @FXML
    private Button btManterUsuarios = new Button();
    @FXML
    private Button btManterGeneros = new Button();
    @FXML
    private Button btHistoricoJogadas = new Button();
    @FXML
    private Button btSair = new Button();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
