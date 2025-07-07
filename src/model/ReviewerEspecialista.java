package model;

public class ReviewerEspecialista extends AbstractReviewer {
    private int nivelDeCredibilidade; // por exemplo, escala 1–5
    private String empresa;

    public ReviewerEspecialista(int id, String nome, String generoFavorito,
                                int nivelDeCredibilidade, String empresa) {
        super(id, nome, generoFavorito);
        this.nivelDeCredibilidade = nivelDeCredibilidade;
        this.empresa = empresa;
    }

    public int getNivelDeCredibilidade() { return nivelDeCredibilidade; }

    public String getEmpresa() { return empresa; }
}