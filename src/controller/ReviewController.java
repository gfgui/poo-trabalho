package controller;

import model.Review;
import java.util.ArrayList;
import java.util.List;

public class ReviewController {
    private List<Review> reviews = new ArrayList<>();

    public void createReview(Review review) {
        reviews.add(review);
    }
    public List<Review> getAllReviews() {
        return reviews;
    }

    public int generateNewId() {
        int maxId = 0;
        for (Review review : reviews) {
            if (review.getId() > maxId) {
                maxId = review.getId();
            }
        }
        return maxId + 1;
    }
}
