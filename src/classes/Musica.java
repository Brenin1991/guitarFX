package classes;

public class Musica {
    private int id;
    private String autor;
    private String musica;
    private int genero;
    private int ano;
    private String descricao;
    private float tempo;
    private String link_imagem;
    private String link_youtube;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getTempo() {
        return tempo;
    }

    public void setTempo(float tempo) {
        this.tempo = tempo;
    }

    public String getLink_imagem() {
        return link_imagem;
    }

    public void setLink_imagem(String linkImagem) {
        this.link_imagem = linkImagem;
    }

    public String getLink_youtube() {
        return link_youtube;
    }

    public void setLink_youtube(String linkYoutube) {
        this.link_youtube = linkYoutube;
    }

    @Override
    public String toString() {
        return ""+musica+" - "+autor;
    }
}
