package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa a maior parte do comportamento comum a qualquer reviewer.
 * Classes concretas só precisam acrescentar seus atributos específicos.
 */
public abstract class AbstractReviewer implements IReviewer {
    protected int id;
    protected String nome;
    protected String generoFavorito;
    protected List<Review> reviews = new ArrayList<>();

    protected AbstractReviewer(int id, String nome, String generoFavorito) {
        this.id = id;
        this.nome = nome;
        this.generoFavorito = generoFavorito;
    }

    // --------- IReviewer ---------
    @Override
    public void createNewReview(Review review) {
        reviews.add(review);
    }

    @Override
    public double getNotaMedia() {
        if (reviews.isEmpty()) return 0.0;
        int soma = reviews.stream().mapToInt(Review::getNota).sum();
        return (double) soma / reviews.size();
    }

    @Override
    public int getNumeroDeReviews() {
        return reviews.size();
    }

    @Override public int getId()               { return id; }
    @Override public String getNome()          { return nome; }
    @Override public String getGeneroFavorito(){ return generoFavorito; }

    @Override public String getDescricao() {
        return "Reviewer :" + nome;
    }
}