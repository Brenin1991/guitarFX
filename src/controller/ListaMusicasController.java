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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

public class ListaMusicasController implements Initializable {

    @FXML
    private Button btSelecionarMusica = new Button();
    @FXML
    private ComboBox<Musica> cbListaMusicas = new ComboBox<>();
    @FXML
    private Label lbUserNome = new Label();
    @FXML
    private Label lbUserPontos = new Label();

    private MusicaDAO musicaDAO = new MusicaDAO();
    private Usuario OBJusuario = new Usuario();
    private Musica musica = new Musica();

    public void initialize(URL url, ResourceBundle rb) {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Usuario usuario, Musica musica) {
                if(newScreen.equals("listaMusicas")){
                    System.out.println("\nTela: "+newScreen+"\nUsuario: "+ usuario.getNome() +"\nMusica: ");

                    OBJusuario = usuario;
                    carregaInfoUsuario();
                }
            }
        });
        carregarListaMusicas();

        btSelecionarMusica.setOnMouseClicked((MouseEvent e) -> {
            System.out.println(cbListaMusicas.getValue());
            selecionarMusica();
        });
    }

    public void carregarListaMusicas(){
        for (Musica m : musicaDAO.selectMusicaLista())
            cbListaMusicas.getItems().add(m);
    }

    public void selecionarMusica(){
        musica = cbListaMusicas.getSelectionModel().getSelectedItem();
        Main.trocaTela("informacoesMusica", OBJusuario, musica);
    }

    public void carregaInfoUsuario(){
        lbUserNome.setText("Usuario: "+ OBJusuario.getNome());
        lbUserPontos.setText("Pontos: "+OBJusuario.getTotalPontos());
    }
}
