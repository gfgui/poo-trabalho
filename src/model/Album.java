package model;

public class Album {
    private int id;
    private String nome;
    private String anoDeLancamento;
    private String[] compositores;
    private int duracao; // minutos
    private int numeroDeFaixas;

    public Album(int id, String nome, String anoDeLancamento, String[] compositores, int duracao, int numeroDeFaixas) {
        this.id = id;
        this.nome = nome;
        this.anoDeLancamento = anoDeLancamento;
        this.compositores = compositores;
        this.duracao = duracao;
        this.numeroDeFaixas = numeroDeFaixas;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getAnoDeLancamento() {
        return this.anoDeLancamento;
    }

    public String getDuracao() {
        return this.duracao+"";
    }

    public int getNumeroDeFaixas() {
        return this.numeroDeFaixas;
    }

    public String[] getCompositores() {
        return compositores;
    }

    // Getters e Setters
}
