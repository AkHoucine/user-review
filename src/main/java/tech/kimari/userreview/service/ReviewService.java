package tech.kimari.userreview.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.kimari.userreview.entity.Review;
import tech.kimari.userreview.repository.ReviewRepository;

@AllArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void create(Review review) {
        this.reviewRepository.save(review);

    }

}
