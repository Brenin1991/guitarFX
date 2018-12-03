package controller.admin;

import classes.Genero;
import classes.Musica;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import main.ADM;
import model.GeneroDAO;
import model.MusicaDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastrarMusicaController implements Initializable{
    @FXML
    private TextField tfAutor = new TextField();
    @FXML
    private TextField tfMusica = new TextField();
    @FXML
    private ComboBox<Genero> cbGenero = new ComboBox();
    @FXML
    private TextField tfAno = new TextField();
    @FXML
    private TextArea taDescricao = new TextArea();
    @FXML
    private TextField tfTempo = new TextField();
    @FXML
    private TextField tfLinkImagem = new TextField();
    @FXML
    private TextField tfLinkYoutube = new TextField();
    @FXML
    private Button btCadastrar = new Button();
    @FXML
    private Button btCancelar = new Button();


    private Musica musica = new Musica();
    private Genero genero = new Genero();
    private MusicaDAO musicaDAO = new MusicaDAO();
    private GeneroDAO generoDAO = new GeneroDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarListaGeneros();
        btCadastrar.setOnMouseClicked((MouseEvent e) -> {
            cadastrarMusica();
        });
        btCancelar.setOnMouseClicked((MouseEvent e) -> {
            ADM.trocaTela("manterMusicas");
        });
    }
    public void cadastrarMusica(){
        boolean conf = true;
        musica.setAutor(tfAutor.getText());
        musica.setMusica(tfMusica.getText());
        musica.setAno(Integer.parseInt(tfAno.getText()));
        genero = cbGenero.getSelectionModel().getSelectedItem();
        musica.setGenero(genero.getId());
        musica.setDescricao(taDescricao.getText());
        musica.setTempo(Integer.parseInt(tfTempo.getText()));
        musica.setLink_imagem(tfLinkImagem.getText());
        musica.setLink_youtube(tfLinkYoutube.getText());
        conf = musicaDAO.cadastrarMusica(musica);
        if(conf == true){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successo!");
            alert.setHeaderText("Musica cadastrada: ");
            alert.setContentText("Musica cadastrada com sucesso! ");
            alert.show();

            ADM.trocaTela("manterMusicas");
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erro!");
            alert.setHeaderText("Musica não foi cadastrada: ");
            alert.setContentText("Musica não cadastrada, tente novamente. ");
            alert.show();
        }
    }

    public void carregarListaGeneros(){
        for (Genero g : generoDAO.selecionaGeneroLista()){
            cbGenero.getItems().add(g);
        }
    }

}
