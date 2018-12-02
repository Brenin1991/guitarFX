package controller.admin;

import classes.Genero;
import classes.RankMusica;
import classes.RankUsuario;
import classes.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.ADM;
import model.GeneroDAO;
import model.UsuarioDAO;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ManterUsuariosController implements Initializable {
    @FXML
    private Button btVoltar = new Button();
    @FXML
    private Button btCadastrar = new Button();
    @FXML
    private Button btRemover = new Button();
    @FXML
    private Button btAtualizar = new Button();
    @FXML
    private Button btRelatorio = new Button();
    @FXML
    private TableView<Usuario> tvUsuarios;
    @FXML
    private TableColumn<Usuario, String> tcId;
    @FXML
    private TableColumn<Usuario, String> tcUsuario;
    @FXML
    private TableColumn<Usuario, String> tcEmail;
    @FXML
    private TableColumn<Usuario, String> tcSenha;
    @FXML
    private TableColumn<Usuario, String> tcPontos;
    @FXML
    private TableColumn<Usuario, String> tcNivel;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private ObservableList<Usuario> obsListaUsuarios;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       carregaListaUsuarios();
        btVoltar.setOnMouseClicked((MouseEvent e) -> {
            voltar();
        });
        btCadastrar.setOnMouseClicked((MouseEvent e) ->{
            ADM.trocaTela("cadastrarGenero");
        });
        btRemover.setOnMouseClicked((MouseEvent e) ->{
            removerGenero();
        });
        btAtualizar.setOnMouseClicked((MouseEvent e) ->{
            carregaListaUsuarios();
        });
        btRelatorio.setOnMouseClicked((MouseEvent e) ->{
            relatorio();
        });
    }

    public void carregaListaUsuarios(){
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcUsuario.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        tcPontos.setCellValueFactory(new PropertyValueFactory<>("pontos"));
        tcNivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));


        listaUsuarios = usuarioDAO.selecionaUsuarioLista();
        obsListaUsuarios = FXCollections.observableArrayList(listaUsuarios);

        tvUsuarios.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tvUsuarios.setItems(obsListaUsuarios);
    }

    public void voltar(){
        ADM.trocaTela("admMain");
    }

    public void removerGenero(){
        if(usuarioDAO.removerUsuario(tvUsuarios.getSelectionModel().getSelectedItem().getId())){
            carregaListaUsuarios();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erro!");
            alert.setHeaderText("Genero não foi removido: ");
            alert.setContentText("Genero não removido, tente novamente. ");
            alert.show();
        }
    }

    @FXML
    private void relatorio(){
        try{
            JRResultSetDataSource relatResul = new JRResultSetDataSource(usuarioDAO.relatorioUsuarios());
            JasperPrint jpPrint = JasperFillManager.fillReport("relatorios/UsuariosRelatorio.jasper", new HashMap(),relatResul);
            JasperViewer jv = new JasperViewer(jpPrint,false);
            jv.setVisible(true);
            jv.toFront();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
