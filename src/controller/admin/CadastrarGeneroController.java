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

public class CadastrarGeneroController implements Initializable{
    @FXML
    private TextField tfGenero = new TextField();
    @FXML
    private Button btCadastrar = new Button();
    @FXML
    private Button btCancelar = new Button();



    private Genero genero = new Genero();
    private GeneroDAO generoDAO = new GeneroDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btCadastrar.setOnMouseClicked((MouseEvent e) -> {
            cadastrarGenero();
        });
        btCancelar.setOnMouseClicked((MouseEvent e) -> {
            ADM.trocaTela("manterGeneros");
        });
    }
    public void cadastrarGenero(){
        boolean conf = true;
        genero.setNome(tfGenero.getText());
        conf = generoDAO.createGenero(genero);
        if(conf == true){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successo!");
            alert.setHeaderText("Genero cadastrado: ");
            alert.setContentText("Genero cadastrado com sucesso! ");
            alert.show();

            ADM.trocaTela("manterGeneros");
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erro!");
            alert.setHeaderText("Genero não foi cadastrado: ");
            alert.setContentText("Genero não cadastrado, tente novamente. ");
            alert.show();
        }
    }
}
