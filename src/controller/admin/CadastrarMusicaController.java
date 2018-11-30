package controller.admin;

import classes.Musica;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.ADM;
import model.MusicaDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastrarMusicaController implements Initializable{
    @FXML
    private TextField tfAutor = new TextField();
    @FXML
    private TextField tfMusica = new TextField();
    @FXML
    private ComboBox cbGenero = new ComboBox();
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

    private Musica musica = new Musica();
    private MusicaDAO musicaDAO = new MusicaDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btCadastrar.setOnMouseClicked((MouseEvent e) -> {
            cadastrarMusica();
        });
    }
    public void cadastrarMusica(){
        boolean conf = true;
        musica.setAutor(tfAutor.getText());
        musica.setMusica(tfMusica.getText());
        musica.setAno(Integer.parseInt(tfAno.getText()));
        musica.setGenero(1);
        musica.setDescricao(taDescricao.getText());
        musica.setTempo(Integer.parseInt(tfTempo.getText()));
        musica.setLink_imagem(tfLinkImagem.getText());
        musica.setLink_youtube(tfLinkYoutube.getText());
        conf = musicaDAO.createMusica(musica);
        if(conf == true){
            System.out.println("Cadastrou! \n"+ musica.getMusica()+"\n"+musica.getAutor()+"\n"+musica.getGenero()+"\n"+musica.getAno()+"\n"+
                    musica.getDescricao()+"\n"+musica.getTempo()+"\n"+musica.getLink_imagem()+"\n"+musica.getLink_youtube());
        }
        else{
            System.out.println("Não cadastrou!");
        }
    }
}
