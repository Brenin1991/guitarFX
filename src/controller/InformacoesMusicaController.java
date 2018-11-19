package controller;

import classes.InfoMusica;
import classes.Musica;
import classes.RankMusica;
import classes.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.Main;
import model.MusicaDAO;
import model.RankDAO;
import model.UsuarioDAO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InformacoesMusicaController implements Initializable {

    @FXML
    private Button btJogar = new Button();
    @FXML
    private Button btVoltarLista = new Button();
    @FXML
    private Button btVoltarPrincipal = new Button();
    @FXML
    private Label lbUserNome = new Label();
    @FXML
    private Label lbUserPontos = new Label();
    @FXML
    private Label lbNomeMusica = new Label();
    @FXML
    private Label lbNomeArtista = new Label();
    @FXML
    private Label lbAno = new Label();
    @FXML
    private Label lbGenero = new Label();
    @FXML
    private Label lbDescricao = new Label();
    @FXML
    private TableView<RankMusica> tvRankMusica;
    @FXML
    private TableColumn<RankMusica, String> tcUsuario;
    @FXML
    private TableColumn<RankMusica, String> tcPontos;

    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Musica musica = new Musica();
    private MusicaDAO musicaDAO = new MusicaDAO();
    private InfoMusica infoMusica;
    private final RankDAO rankDAO = new RankDAO();
    private ArrayList<RankMusica> listaRankMusica = new ArrayList<>();
    private ObservableList<RankMusica> obsListaRankMusica;

    public void initialize(URL url, ResourceBundle rb) {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, int usuario, int musica) {
                if(newScreen.equals("informacoesMusica")) {
                    System.out.println("\nTela: " + newScreen + "\nUsuario: " + usuario + "\nMusica: " + musica);

                    carregaInfoUsuario(usuario);
                    carregaInfoMusica(musica);
                    carregarRankingMusica();
                }
            }
        });

        btJogar.setOnMouseClicked((MouseEvent e) -> {
            //Main.trocaTela("jogo", null);
        });

        btVoltarLista.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("listaMusicas", usuario.getId(), 0);
        });

        btVoltarPrincipal.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("principal", usuario.getId(), 0);
        });
    }

    public void carregaInfoMusica(int idMusica){
        musica = musicaDAO.selectMusica(idMusica);
        infoMusica = musicaDAO.selectInfoMusica(musica.getId());
        lbNomeMusica.setText("Nome: "+infoMusica.getMusica());
        lbNomeArtista.setText("Autor: "+infoMusica.getAutor());
        lbAno.setText("Ano de lançamento: "+infoMusica.getAno());
        lbGenero.setText("Genero: "+infoMusica.getGenero());
        lbDescricao.setText("Descricao: "+infoMusica.getDescricao());
    }

    public void carregaInfoUsuario(int idUsuario){
        usuario = usuarioDAO.selectUsuario(idUsuario);
        lbUserNome.setText("Usuario: "+ usuario.getNome());
        lbUserPontos.setText("Pontos: "+usuario.getTotalPontos());
    }

    public void carregarRankingMusica(){
        tcUsuario.setCellValueFactory(new PropertyValueFactory<>("Usuario"));
        tcPontos.setCellValueFactory(new PropertyValueFactory<>("Pontos"));

        listaRankMusica = rankDAO.selectRankMusicaLista(musica.getId());

        obsListaRankMusica = FXCollections.observableArrayList(listaRankMusica);

        tvRankMusica.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tvRankMusica.setItems(obsListaRankMusica);
    }
}
