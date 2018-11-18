package controller;

import classes.Musica;
import classes.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import main.Main;

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

    private Usuario OBJusuario = new Usuario();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Usuario usuario, Musica musica) {
                if(newScreen.equals("principal")) {
                    System.out.println("\nTela: " + newScreen + "\nUsuario: " + usuario.getNome() + "\nMusica: " + musica);

                    OBJusuario = usuario;
                    carregaInfoUsuario();
                }
            }
        });

        btPlay.setOnMouseClicked((MouseEvent e) -> {
           Main.trocaTela("listaMusicas", OBJusuario, null);
        });

        btQuit.setOnMouseClicked((MouseEvent e) -> {
            Main.fechar();
        });
    }

    public void carregaInfoUsuario(){
        lbUserNome.setText("Usuario: "+ OBJusuario.getNome());
        lbUserPontos.setText("Pontos: "+OBJusuario.getTotalPontos());
    }
}
