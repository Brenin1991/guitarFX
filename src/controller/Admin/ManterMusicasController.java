package controller.Admin;

import classes.RankMusica;
import classes.RankUsuario;
import classes.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.Main;
import model.RankDAO;
import model.UsuarioDAO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManterMusicasController implements Initializable {
    @FXML
    private Button btVoltar = new Button();
    @FXML
    private TableView<RankUsuario> tvMusicas;
    @FXML
    private TableColumn<RankUsuario, String> tcMusicaId;
    @FXML
    private TableColumn<RankMusica, String> tcMusicaMusica;
    @FXML
    private TableColumn<RankMusica, String> tcMusicaGenero;
    @FXML
    private TableColumn<RankMusica, String> tcMusicaAno;
    @FXML
    private TableColumn<RankMusica, String> tcMusicaDescricao;
    @FXML
    private TableColumn<RankMusica, String> tcMusicaTempo;
    @FXML
    private TableColumn<RankMusica, String> tcMusicaLink_Imagem;
    @FXML
    private TableColumn<RankMusica, String> tcMusicaLink_Youtube;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btVoltar.setOnMouseClicked((MouseEvent e) -> {

        });
    }


}
