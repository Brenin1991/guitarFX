package controller.admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import main.ADM;
import model.BackupGeneroDAO;
import model.BackupMusicaDAO;
import model.BackupUsuarioDAO;
import model.JogadaDAO;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PrincipalADMController implements Initializable {
    @FXML
    private Button btManterMusica = new Button();
    @FXML
    private Button btManterUsuario = new Button();
    @FXML
    private Button btManterGenero = new Button();
    @FXML
    private Button btHistoricoJogada = new Button();
    @FXML
    private Button btFazerBackup = new Button();
    @FXML
    private Button btLerBackup = new Button();
    @FXML
    private Button btSair = new Button();
    private JogadaDAO jogadaDAO = new JogadaDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btManterMusica.setOnMouseClicked((MouseEvent e) -> {
            ADM.trocaTela("manterMusicas");
        });

        btManterUsuario.setOnMouseClicked((MouseEvent e) -> {
            ADM.trocaTela("manterUsuarios");
        });

        btManterGenero.setOnMouseClicked((MouseEvent e) -> {
            ADM.trocaTela("manterGeneros");
        });

        btHistoricoJogada.setOnMouseClicked((MouseEvent e) -> {
            relatorio();
        });

        btFazerBackup.setOnMouseClicked((MouseEvent e) -> {
            try {
                BackupGeneroDAO.getInstance().salvarTXT();
                BackupMusicaDAO.getInstance().salvarTXT();
                BackupUsuarioDAO.getInstance().salvarTXT();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ok!");
                alert.setHeaderText("Backup realizado com sucesso: ");
                alert.setContentText("Backup feito. ");
                alert.show();
            } catch (IOException e1) {
                e1.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erro!");
                alert.setHeaderText("Backup não realizado com sucesso: ");
                alert.setContentText("Tente novamente. ");
            }
        });

        btLerBackup.setOnMouseClicked((MouseEvent e) -> {
            try {
                BackupGeneroDAO.getInstance().lerTXT();
                BackupMusicaDAO.getInstance().lerTXT();
                BackupUsuarioDAO.getInstance().lerTXT();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ok!");
                alert.setHeaderText("Backup realizado com sucesso: ");
                alert.setContentText("Backup feito. ");
                alert.show();
            } catch (IOException e1) {
                e1.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erro!");
                alert.setHeaderText("Backup não realizado com sucesso: ");
                alert.setContentText("Tente novamente. ");
            }
        });

        btSair.setOnMouseClicked((MouseEvent e) -> {
            ADM.fechar();
        });



    }

    @FXML
    private void relatorio(){
        try{
            JRResultSetDataSource relatResul = new JRResultSetDataSource(jogadaDAO.relatorioJogadas());
            JasperPrint jpPrint = JasperFillManager.fillReport("relatorios/JogadasRelatorio.jasper", new HashMap(),relatResul);
            JasperViewer jv = new JasperViewer(jpPrint,false);
            jv.setVisible(true);
            jv.toFront();
        }catch(Exception e){
            e.printStackTrace();
        }
    }



}
