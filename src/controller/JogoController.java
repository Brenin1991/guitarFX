package controller;

import classes.Objeto;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;


public class JogoController implements Initializable {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Pane pane;

    private Objeto obj1 = new Objeto();
    private Objeto obj2 = new Objeto();
    private Objeto obj3 = new Objeto();
    private Objeto obj4 = new Objeto();
    private Objeto obj5 = new Objeto();

    private Objeto nota1 = new Objeto();
    private Objeto nota2 = new Objeto();
    private Objeto nota3 = new Objeto();
    private Objeto nota4 = new Objeto();
    private Objeto nota5 = new Objeto();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        obj1.criarCirculo(300, 550, 20, 4, "#00FF00", "#000000");
        obj2.criarCirculo(350, 550, 20, 4, "#FF0000", "#000000");
        obj3.criarCirculo(400, 550, 20, 4, "#FFFF00", "#000000");
        obj4.criarCirculo(450, 550, 20, 4, "#48D1CC", "#000000");
        obj5.criarCirculo(500, 550, 20, 4, "#FFA500", "#000000");

        nota1.criarCirculo(300, 30, 15, 4,"#000000", "#00FF00");
        nota2.criarCirculo(350, 30, 15, 4,"#000000", "#FF0000");
        nota3.criarCirculo(400, 30, 15, 4,"#000000", "#FFFF00");
        nota4.criarCirculo(450, 30, 15, 4,"#000000", "#48D1CC");
        nota5.criarCirculo(500, 30, 15, 4,"#000000", "#FFA500");

        mainPane.getChildren().addAll(obj1.getCirculo(), obj2.getCirculo(), obj3.getCirculo(), obj4.getCirculo(), obj5.getCirculo(), nota1.getCirculo(), nota2.getCirculo(), nota3.getCirculo(), nota4.getCirculo(), nota5.getCirculo());

        //gatilho();
        mainPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent event) {
                gatilho(event);

            }
        });
    }

    public void gatilho(KeyEvent event) {
        //NOTAS
        if (event.getCode() == KeyCode.DIGIT1) {
            nota1.setVerificaMovimento(true);
            nota1.mover();
            if (event.getCode() == KeyCode.DIGIT1) {
                nota1.setVerificaMovimento(false);
                nota1.mover();
            }
        }
        if (event.getCode() == KeyCode.DIGIT2) {
            nota2.setVerificaMovimento(true);
            nota2.mover();
            if (event.getCode() == KeyCode.DIGIT2) {
                nota2.setVerificaMovimento(false);
                nota2.mover();
            }
        }
        if (event.getCode() == KeyCode.DIGIT3) {
            nota3.setVerificaMovimento(true);
            nota3.mover();
            if (event.getCode() == KeyCode.DIGIT3) {
                nota3.setVerificaMovimento(false);
                nota3.mover();
            }
        }
        if (event.getCode() == KeyCode.DIGIT4) {
            nota4.setVerificaMovimento(true);
            nota4.mover();
            if (event.getCode() == KeyCode.DIGIT4) {
                nota4.setVerificaMovimento(false);
                nota4.mover();
            }
        }
        if (event.getCode() == KeyCode.DIGIT5) {
            nota5.setVerificaMovimento(true);
            nota5.mover();
            if (event.getCode() == KeyCode.DIGIT5) {
                nota5.setVerificaMovimento(false);
                nota5.mover();
            }
        }

        //PALHETA
        if (event.getCode() == KeyCode.A) {
            obj1.setVerificaAnimacao(true);
            obj1.animacao();
            if (event.getCode() == KeyCode.A) {
                obj1.setVerificaAnimacao(false);
                obj1.animacao();
            }
        }
        if (event.getCode() == KeyCode.S) {
            obj2.setVerificaAnimacao(true);
            obj2.animacao();
            if (event.getCode() == KeyCode.S) {
                obj2.setVerificaAnimacao(false);
                obj2.animacao();
            }
        }
        if (event.getCode() == KeyCode.D) {
            obj3.setVerificaAnimacao(true);
            obj3.animacao();
            if (event.getCode() == KeyCode.D) {
                obj3.setVerificaAnimacao(false);
                obj3.animacao();
            }
        }
        if (event.getCode() == KeyCode.J) {
            obj4.setVerificaAnimacao(true);
            obj4.animacao();
            if (event.getCode() == KeyCode.J) {
                obj4.setVerificaAnimacao(false);
                obj4.animacao();
            }
        }
        if (event.getCode() == KeyCode.K) {
            obj5.setVerificaAnimacao(true);
            obj5.animacao();
            if (event.getCode() == KeyCode.K) {
                obj5.setVerificaAnimacao(false);
                obj5.animacao();
            }
        }
    }

}
