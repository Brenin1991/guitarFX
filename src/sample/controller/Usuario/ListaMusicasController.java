package sample.controller.Usuario;

import sample.classes.Musica;
import sample.classes.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import sample.Main;
import sample.model.MusicaDAO;
import sample.model.UsuarioDAO;
import java.net.URL;
import java.util.ResourceBundle;

public class ListaMusicasController implements Initializable {
    @FXML
    private Button btSelecionarMusica = new Button();
    @FXML
    private Button btVoltar = new Button();
    @FXML
    private ComboBox<Musica> cbListaMusicas = new ComboBox<>();
    @FXML
    private Label lbUserNome = new Label();
    @FXML
    private Label lbUserPontos = new Label();

    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Musica musica = new Musica();
    private MusicaDAO musicaDAO = new MusicaDAO();

    public void initialize(URL url, ResourceBundle rb) {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, int usuario, int musica, int score, int status) {
                if(newScreen.equals("listaMusicas")){
                    System.out.println("\nTela: "+newScreen+"\nUsuario: "+ usuario +"\nMusica: ");

                    carregaInfoUsuario(usuario);
                }
            }
        });
        carregarListaMusicas();

        btSelecionarMusica.setOnMouseClicked((MouseEvent e) -> {
            System.out.println(cbListaMusicas.getValue());
            selecionarMusica();
        });

        btVoltar.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("principal", usuario.getId(), 0);
        });
    }

    public void carregarListaMusicas(){
        for (Musica m : musicaDAO.selecionaMusicaLista())
            cbListaMusicas.getItems().add(m);
    }

    public void selecionarMusica(){
        musica = cbListaMusicas.getSelectionModel().getSelectedItem();
        Main.trocaTela("informacoesMusica", usuario.getId(), musica.getId());
    }

    public void carregaInfoUsuario(int idUsuario){
        usuario = usuarioDAO.selecionaUsuario(idUsuario);
        lbUserNome.setText("Logged in with "+ usuario.getNome());
        lbUserPontos.setText("Points: "+usuario.getPontos());
    }
}
