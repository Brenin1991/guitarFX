package controller.admin;

import classes.RankMusica;
import classes.RankUsuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ManterGenerosController implements Initializable {
    @FXML
    private Button btVoltar = new Button();
    @FXML
    private TableView<RankUsuario> tvMusicas;
    @FXML
    private TableColumn<RankUsuario, String> tcGeneroId;
    @FXML
    private TableColumn<RankMusica, String> tcGeneroGenero;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btVoltar.setOnMouseClicked((MouseEvent e) -> {

        });
    }
}
