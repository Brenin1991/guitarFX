package sample.controller.Usuario;

import javafx.scene.image.ImageView;
import sample.classes.InfoMusica;
import sample.classes.Musica;
import sample.classes.RankMusica;
import sample.classes.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.Main;
import sample.model.MusicaDAO;
import sample.model.RankDAO;
import sample.model.UsuarioDAO;
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
            public void onScreenChanged(String newScreen, int usuario, int musica, int score, int status) {
                if(newScreen.equals("informacoesMusica")) {
                    System.out.println("\nTela: " + newScreen + "\nUsuario: " + usuario + "\nMusica: " + musica);

                    carregaInfoUsuario(usuario);
                    carregaInfoMusica(musica);
                    carregarRankingMusica();
                }
            }
        });

        btJogar.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("jogo", usuario.getId(), musica.getId());
        });

        btVoltarLista.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("listaMusicas", usuario.getId(), 0);
        });

        btVoltarPrincipal.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("principal", usuario.getId(), 0);
        });
    }

    public void carregaInfoMusica(int idMusica){
        musica = musicaDAO.selecionaMusica(idMusica);
        infoMusica = musicaDAO.selecionaInfoMusica(idMusica);

        lbNomeMusica.setText("Name: "+infoMusica.getMusica());
        lbNomeArtista.setText("Autor: "+infoMusica.getAutor());
        lbAno.setText("Year: "+infoMusica.getAno());
        lbGenero.setText("Genre: "+infoMusica.getGenero());
        lbDescricao.setText("Description: "+infoMusica.getDescricao());
    }

    public void carregaInfoUsuario(int idUsuario){
        usuario = usuarioDAO.selecionaUsuario(idUsuario);
        lbUserNome.setText("Logged in with "+ usuario.getNome());
        lbUserPontos.setText("Points: "+usuario.getPontos());
    }

    public void carregarRankingMusica(){
        tcUsuario.setCellValueFactory(new PropertyValueFactory<>("Usuario"));
        tcPontos.setCellValueFactory(new PropertyValueFactory<>("Pontos"));

        listaRankMusica = rankDAO.selecionaRankMusicaLista(musica.getId());
        obsListaRankMusica = FXCollections.observableArrayList(listaRankMusica);

        tvRankMusica.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tvRankMusica.setItems(obsListaRankMusica);
    }
}
