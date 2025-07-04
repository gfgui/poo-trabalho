package model;

import java.util.Date;

public class Review {
    private int nota;
    private String conteudo;
    private Date dataDePublicacao;
    private int reviewerId;
    private int albumId;

    public Review(int nota, String conteudo, Date dataDePublicacao, int reviewerId, int albumId) {
        this.nota = nota;
        this.conteudo = conteudo;
        this.dataDePublicacao = dataDePublicacao;
        this.reviewerId = reviewerId;
        this.albumId = albumId;
    }

    public int getNota() {
        return this.nota;
    }

    public int getAlbumId() {
        return this.albumId;
    }

    public int getReviewerId() {
        return  this.reviewerId;
    }

    public String getConteudo() {
        return this.conteudo;
    }

    public Date getDataDePublicacao() {
        return  this.dataDePublicacao;
    }

    // Getters e Setters
}
