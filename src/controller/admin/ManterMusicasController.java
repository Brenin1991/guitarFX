package controller.admin;

import classes.Musica;
import classes.RankMusica;
import classes.RankUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.ADM;
import model.MusicaDAO;

import java.net.URL;
import java.util.*;

public class ManterMusicasController implements Initializable {
    @FXML
    private Button btVoltar = new Button();
    @FXML
    private Button btCadastrar = new Button();
    @FXML
    private Button btRemover = new Button();
    @FXML
    private Button btAtualizar = new Button();
    @FXML
    private TableView<Musica> tvMusicas;
    @FXML
    private TableColumn<RankUsuario, String> tcMusicaId;
    @FXML
    private TableColumn<RankMusica, String> tcMusicaMusica;
    @FXML
    private TableColumn<RankMusica, String> tcMusicaAutor;
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

    private final MusicaDAO musicaDAO = new MusicaDAO();
    private ArrayList<Musica> listaMusicas = new ArrayList<>();
    private ObservableList<Musica> obsListaMusicas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaListaMusicas();
        btVoltar.setOnMouseClicked((MouseEvent e) -> {
            voltar();
        });
        btCadastrar.setOnMouseClicked((MouseEvent e) ->{
            ADM.trocaTela("cadastrarMusica");
        });
        btRemover.setOnMouseClicked((MouseEvent e) ->{

        });
        btAtualizar.setOnMouseClicked((MouseEvent e) ->{
            carregaListaMusicas();
        });
    }

    public void carregaListaMusicas(){
        tcMusicaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcMusicaMusica.setCellValueFactory(new PropertyValueFactory<>("musica"));
        tcMusicaAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        tcMusicaGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        tcMusicaAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        tcMusicaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tcMusicaTempo.setCellValueFactory(new PropertyValueFactory<>("tempo"));
        tcMusicaLink_Imagem.setCellValueFactory(new PropertyValueFactory<>("link_imagem"));
        tcMusicaLink_Youtube.setCellValueFactory(new PropertyValueFactory<>("youtube_link"));

        listaMusicas = musicaDAO.selectMusicaLista();
        obsListaMusicas = FXCollections.observableArrayList(listaMusicas);

        tvMusicas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tvMusicas.setItems(obsListaMusicas);
    }

    public void voltar(){
        ADM.trocaTela("admMain");
    }

    public void removerMusica(){
        
    }
}
