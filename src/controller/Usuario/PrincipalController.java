package controller.Usuario;

import classes.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import main.Main;
import model.UsuarioDAO;
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
                }
            }
        });

        btPlay.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("listaMusicas", usuario.getId());
        });

        btRank.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("rankGlobal", usuario.getId());
        });

        btQuit.setOnMouseClicked((MouseEvent e) -> {
            Main.fechar();
        });
    }

    public void carregaInfoUsuario(int idUsuario){
        usuario = usuarioDAO.selecionaUsuario(idUsuario);
        lbUserNome.setText("Usuario: "+ usuario.getNome());
        lbUserPontos.setText("Pontos: "+usuario.getTotalPontos());
    }
}
