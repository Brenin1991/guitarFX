package controller.Usuario;

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

public class RankGlobalController implements Initializable {

    @FXML
    private Button btVoltar = new Button();
    @FXML
    private Label lbUserNome = new Label();
    @FXML
    private Label lbUserPontos = new Label();
    @FXML
    private TableView<RankUsuario> tvRankGlobal;
    @FXML
    private TableColumn<RankUsuario, String> tcUsuario;
    @FXML
    private TableColumn<RankMusica, String> tcNivel;
    @FXML
    private TableColumn<RankMusica, String> tcPontos;

    private final RankDAO rankDAO = new RankDAO();
    private ArrayList<RankUsuario> listaRankGlobal = new ArrayList<>();
    private ObservableList<RankUsuario> obsListaRankGlobal;

    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, int usuario, int musica) {
                if(newScreen.equals("principal")) {
                    System.out.println("\nTela: " + newScreen + "\nUsuario: " + usuario + "\nMusica: " + musica);

                    carregaInfoUsuario(usuario);
                    carregarRankGlobal();
                }
            }
        });

        btVoltar.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("principal", usuario.getId(), 0);
        });
    }

    public void carregaInfoUsuario(int idUsuario){
        usuario = usuarioDAO.selecionaUsuario(idUsuario);
        lbUserNome.setText("Usuario: "+ usuario.getNome());
        lbUserPontos.setText("Pontos: "+usuario.getTotalPontos());
    }

    public void carregarRankGlobal(){
        tcUsuario.setCellValueFactory(new PropertyValueFactory<>("Usuario"));
        tcNivel.setCellValueFactory(new PropertyValueFactory<>("Nivel"));
        tcPontos.setCellValueFactory(new PropertyValueFactory<>("Pontos"));

        listaRankGlobal = rankDAO.selecionaRankGlobalLista();
        obsListaRankGlobal = FXCollections.observableArrayList(listaRankGlobal);

        tvRankGlobal.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tvRankGlobal.setItems(obsListaRankGlobal);
    }
}
