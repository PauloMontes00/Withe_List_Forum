package model;

public class Post {
    private String autor;
    private String conteudo;

    public Post(String autor, String conteudo) {
        this.autor = autor;
        this.conteudo = conteudo;
    }
    public String getAutor() { return autor; }
    public String getConteudo() { return conteudo; }
}