package model;

import java.util.Date;

public class Review {

    public enum TipoReviewer { ESPECIALISTA, COMUM }

    private final int id;
    private final int nota;
    private final String conteudo;
    private final Date dataDePublicacao;
    private final int reviewerId;
    private final int albumId;
    private final TipoReviewer tipo;   // << NOVO

    public Review(int id, int nota, String conteudo,
                  Date dataDePublicacao,
                  int reviewerId, int albumId,
                  TipoReviewer tipo) {          // << NOVO
        this.id = id;
        this.nota = nota;
        this.conteudo = conteudo;
        this.dataDePublicacao = dataDePublicacao;
        this.reviewerId = reviewerId;
        this.albumId = albumId;
        this.tipo = tipo;                       // << NOVO
    }

    // getters
    public int getId() {
        return id;
    }
    public int getNota()              { return nota; }
    public int getAlbumId()           { return albumId; }
    public int getReviewerId()        { return reviewerId; }
    public String getConteudo()       { return conteudo; }
    public Date getDataDePublicacao() { return dataDePublicacao; }
    public TipoReviewer getTipo()     { return tipo; }      // << NOVO
}