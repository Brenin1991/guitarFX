package controller;

import classes.Musica;
import classes.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import main.Main;
import model.MusicaDAO;
import model.UsuarioDAO;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
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
            public void onScreenChanged(String newScreen, int usuario, int musica) {
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
        for (Musica m : musicaDAO.selectMusicaLista())
            cbListaMusicas.getItems().add(m);
    }

    public void selecionarMusica(){
        musica = cbListaMusicas.getSelectionModel().getSelectedItem();
        Main.trocaTela("informacoesMusica", usuario.getId(), musica.getId());
    }

    public void carregaInfoUsuario(int idUsuario){
        usuario = usuarioDAO.selectUsuario(idUsuario);
        lbUserNome.setText("Usuario: "+ usuario.getNome());
        lbUserPontos.setText("Pontos: "+ usuario.getTotalPontos());
    }
}
