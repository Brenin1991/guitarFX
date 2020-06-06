package sample.controller.Usuario;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import sample.Main;
import sample.classes.Usuario;
import sample.model.UsuarioDAO;
import java.net.URL;
import java.util.ResourceBundle;


public class CreditosController implements Initializable {

    @FXML
    private Button btVoltar;
    @FXML
    private Label lbUserNome = new Label();
    @FXML
    private Label lbUserPontos = new Label();

    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();


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
        btVoltar.setOnMouseClicked((MouseEvent e) -> {
            Main.trocaTela("principal", usuario.getId(), 0);
        });
    }

    public void carregaInfoUsuario(int idUsuario){
        usuario = usuarioDAO.selecionaUsuario(idUsuario);
        lbUserNome.setText("Logged in with "+ usuario.getNome());
        lbUserPontos.setText("Points: "+usuario.getPontos());
    }
}
