package model;

public interface IReviewer {
    int getId();
    String getNome();
    String getGeneroFavorito();

    void createNewReview(Review review);
    double getNotaMedia();
    int getNumeroDeReviews();
    String getDescricao();
}
