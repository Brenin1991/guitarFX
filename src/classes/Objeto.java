package classes;

import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Objeto {
    private Circle circulo;
    private double posicaoX;
    private double posicaoY;
    private double tamanho;
    private String corFundo;
    private String corBorda;
    private double velocidade;
    private int verificaMovimento = 0;
    private boolean verificaAnimacao = false;

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public double getPosicaoX() {
        return posicaoX;
    }

    public void setPosicaoX(double posicaoX) {
        this.posicaoX = posicaoX;
    }

    public double getPosicaoY() {
        return posicaoY;
    }

    public void setPosicaoY(double posicaoY) {
        this.posicaoY = posicaoY;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public Circle getCirculo() {
        return circulo;
    }

    public String getCorFundo() {
        return corFundo;
    }

    public void setCorFundo(String corFundo) {
        this.corFundo = corFundo;
    }

    public String getCorBorda() {
        return corBorda;
    }

    public void setCorBorda(String corBorda) {
        this.corBorda = corBorda;
    }

    public void setCirculo(Circle circulo) {
        this.circulo = circulo;
    }

    public int getVerificaMovimento() {
        return verificaMovimento;
    }

    public void setVerificaMovimento(int verificaMovimento) {
        this.verificaMovimento = verificaMovimento;
    }

    public boolean getVerificaAnimacao() {
        return verificaAnimacao;
    }

    public void setVerificaAnimacao(Boolean verificaAnimacao) {
        this.verificaAnimacao = verificaAnimacao;
    }

    public void criarCirculo(double posx, double posy, double tam, double tamBorda, String corBorda, String corFundo){
        circulo = new Circle();
        setPosicaoX(posx);
        setPosicaoY(posy);
        setTamanho(tam);
        setCorBorda(corBorda);
        setCorFundo(corFundo);
        //setCirculo(circulo);

        getCirculo().setFill(Color.web(getCorFundo()));
        getCirculo().setCenterX(getPosicaoX());
        getCirculo().setCenterY(getPosicaoY());
        getCirculo().setRadius(tam);
        getCirculo().setOpacity(0.7);
        getCirculo().setStrokeWidth(tamBorda);
        getCirculo().setStroke(Color.web(getCorBorda()));
    }

    public void mover(){
        PathTransition transicao = new PathTransition();
        Line linhaMovimento = new Line(circulo.getCenterX(), circulo.getCenterY(), circulo.getCenterX(), circulo.getLayoutY()+800);
        transicao.setNode(circulo);
        transicao.setDuration(Duration.seconds(4));
        transicao.setPath(linhaMovimento);
        transicao.setCycleCount(PathTransition.INDEFINITE);
        if(getVerificaMovimento() == 1) {
            transicao.play();
        }else{
            transicao.stop();
        }
    }

    public void animacao(){
        ScaleTransition escala = new ScaleTransition();
        escala.setNode(circulo);
        escala.setByX(0.5f);
        escala.setByY(0.5f);
        escala.setFromX(0.5f);
        escala.setFromY(0.5f);
        escala.setDuration(Duration.seconds(0.1f));
        escala.setCycleCount(1);
        if(getVerificaAnimacao() == true) {
            escala.play();
        }else{
            escala.stop();
        }
    }
}
