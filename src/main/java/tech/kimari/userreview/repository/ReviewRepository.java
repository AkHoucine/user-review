package tech.kimari.userreview.repository;

import org.springframework.data.repository.CrudRepository;
import tech.kimari.userreview.entity.Review;

public interface ReviewRepository extends CrudRepository<Review, Integer> {
}
