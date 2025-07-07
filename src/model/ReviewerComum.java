package model;

public class ReviewerComum extends AbstractReviewer {
    private String plataformaDeStreamFavorita;

    public ReviewerComum(int id, String nome, String generoFavorito,
                         String plataformaDeStreamFavorita) {
        super(id, nome, generoFavorito);
        this.plataformaDeStreamFavorita = plataformaDeStreamFavorita;
    }

    public String getPlataformaDeStreamFavorita() { return plataformaDeStreamFavorita; }
}
