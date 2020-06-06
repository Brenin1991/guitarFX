package sample.controller.Usuario;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.Main;
import sample.classes.Usuario;
import sample.model.UsuarioDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class ScoreController implements Initializable {

    @FXML
    private Label lbUserNome = new Label();
    @FXML
    private Label lbUserPontos = new Label();

    @FXML
    private Label lbStatus = new Label();
    @FXML
    private Label lbBestScore = new Label();
    @FXML
    private Label lbScore = new Label();
    @FXML
    private Button btVoltar = new Button();

    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public int m;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, int usuario, int musica, int score, int status) {
                if(newScreen.equals("score")) {
                    System.out.println("\nTela: " + newScreen + "\nUsuario: " + usuario + "\nMusica: " + musica);

                    carregaInfoUsuario(usuario);
                    if (status == 1) {
                        lbStatus.setText("YOU WIN!");
                    } else {
                        lbStatus.setText("YOU LOSE!");
                    }

                    lbScore.setText("YOUR SCORE: "+score);
                    m = musica;
                }
            }
        });

        btVoltar.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("informacoesMusica", usuario.getId(), m);
        });
    }

    public void carregaInfoUsuario(int idUsuario){
        usuario = usuarioDAO.selecionaUsuario(idUsuario);
        lbUserNome.setText("Logged in with "+ usuario.getNome());
        lbUserPontos.setText("Points: "+usuario.getPontos());
    }

}
