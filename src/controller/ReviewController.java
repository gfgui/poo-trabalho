package controller;

import model.Review;
import java.util.ArrayList;
import java.util.List;

public class ReviewController {
    private List<Review> reviews = new ArrayList<>();

    public void createReview(Review review) {
        reviews.add(review);
    }

    public void deleteReview(int albumId) {
        reviews.removeIf(review -> review.getAlbumId() == albumId);
    }

    public void updateReview(int id, Review updatedReview) {
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getAlbumId() == id) {
                reviews.set(i, updatedReview);
                return;
            }
        }
    }

    public List<Review> getAllReviews() {
        return reviews;
    }
}
