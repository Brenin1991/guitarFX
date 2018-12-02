package controller.admin;

import classes.Genero;
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
import model.GeneroDAO;
import model.MusicaDAO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManterGenerosController implements Initializable {
    @FXML
    private Button btVoltar = new Button();
    @FXML
    private Button btCadastrar = new Button();
    @FXML
    private Button btRemover = new Button();
    @FXML
    private Button btAtualizar = new Button();
    @FXML
    private TableView<Genero> tvGeneros;
    @FXML
    private TableColumn<RankUsuario, String> tcGeneroId;
    @FXML
    private TableColumn<RankMusica, String> tcGenero;


    private final GeneroDAO generoDAO = new GeneroDAO();
    private ArrayList<Genero> listaGeneros = new ArrayList<>();
    private ObservableList<Genero> obsListaGeneros;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaListaGeneros();
        btVoltar.setOnMouseClicked((MouseEvent e) -> {
            voltar();
        });
        btCadastrar.setOnMouseClicked((MouseEvent e) ->{
            ADM.trocaTela("cadastrarGenero");
        });
        btRemover.setOnMouseClicked((MouseEvent e) ->{
            removerGenero();
        });
        btAtualizar.setOnMouseClicked((MouseEvent e) ->{
            carregaListaGeneros();
        });
    }

    public void carregaListaGeneros(){
        tcGeneroId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcGenero.setCellValueFactory(new PropertyValueFactory<>("nome"));


        listaGeneros = generoDAO.selecionaGeneroLista();
        obsListaGeneros = FXCollections.observableArrayList(listaGeneros);

        tvGeneros.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tvGeneros.setItems(obsListaGeneros);
    }

    public void voltar(){
        ADM.trocaTela("admMain");
    }

    public void removerGenero(){
        if(generoDAO.removerGenero(tvGeneros.getSelectionModel().getSelectedItem().getId())){
            carregaListaGeneros();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erro!");
            alert.setHeaderText("Genero não foi removido: ");
            alert.setContentText("Genero não removido, tente novamente. ");
            alert.show();
        }
    }
}
