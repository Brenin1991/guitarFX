package controller.admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import main.ADM;
import model.JogadaDAO;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

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
