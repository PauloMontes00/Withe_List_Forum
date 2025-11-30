package model;

public class Comunidade {
    private String nome;
    private boolean privada;

    public Comunidade(String nome, boolean privada) {
        this.nome = nome;
        this.privada = privada;
    }

    public String getNome() { return nome; }
    public boolean isPrivada() { return privada; }
}
