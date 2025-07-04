package model;

import java.util.ArrayList;
import java.util.List;

public class Reviewer {
    private int id;
    private String nome;
    private String generoFavorito;
    private List<Review> reviews = new ArrayList<>();

    public Reviewer(int id, String nome, String generoFavorito) {
        this.id = id;
        this.nome = nome;
        this.generoFavorito = generoFavorito;
    }

    public void createNewReview(Review review) {
        reviews.add(review);
    }

    public double getNotaMedia() {
        if (reviews.isEmpty()) return 0;
        int total = 0;
        for (Review r : reviews) total += r.getNota();
        return (double) total / reviews.size();
    }

    public int getNumeroDeReviews() {
        return reviews.size();
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return  this.nome;
    }

    public String getGeneroFavorito() {
        return this.generoFavorito;
    }

    // Getters e Setters
}
