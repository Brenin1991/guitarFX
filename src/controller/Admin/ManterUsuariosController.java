package controller.Admin;

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

public class ManterUsuariosController implements Initializable {
    @FXML
    private Button btVoltar = new Button();
    @FXML
    private TableView<RankUsuario> tvUsuarios;
    @FXML
    private TableColumn<RankUsuario, String> tcUsuarioId;
    @FXML
    private TableColumn<RankMusica, String> tcUsuarioUsuario;
    @FXML
    private TableColumn<RankMusica, String> tcUsuarioEmail;
    @FXML
    private TableColumn<RankUsuario, String> tcUsuarioSenha;
    @FXML
    private TableColumn<RankUsuario, String> tcUsuarioPontos;
    @FXML
    private TableColumn<RankUsuario, String> tcUsuarioNivel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btVoltar.setOnMouseClicked((MouseEvent e) -> {

        });
    }
}
