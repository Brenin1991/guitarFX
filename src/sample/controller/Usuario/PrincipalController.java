package sample.controller.Usuario;

import sample.classes.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import sample.Main;
import sample.model.UsuarioDAO;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {
    @FXML
    private Button btPlay = new Button();
    @FXML
    private Button btRank = new Button();
    @FXML
    private Button btQuit = new Button();
    @FXML
    private Label lbUserNome = new Label();
    @FXML
    private Label lbUserPontos = new Label();
    @FXML
    private Button btCreditos = new Button();

    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, int usuario, int musica, int score, int status) {
                if(newScreen.equals("principal")) {
                    System.out.println("\nTela: " + newScreen + "\nUsuario: " + usuario + "\nMusica: " + musica);

                    carregaInfoUsuario(usuario);
                }
            }
        });

        btPlay.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("listaMusicas", usuario.getId(), 0);
        });

        btRank.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("rankGlobal", usuario.getId(), 0);
        });

        btCreditos.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("creditos", usuario.getId(), 0);
        });

        btQuit.setOnMouseClicked((MouseEvent e) -> {
            Main.fechar();
        });
    }

    public void carregaInfoUsuario(int idUsuario){
        usuario = usuarioDAO.selecionaUsuario(idUsuario);
        lbUserNome.setText("Logged in with "+ usuario.getNome());
        lbUserPontos.setText("Points: "+usuario.getPontos());
    }
}
