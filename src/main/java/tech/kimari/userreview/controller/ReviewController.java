package tech.kimari.userreview.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.kimari.userreview.entity.Review;
import tech.kimari.userreview.service.ReviewService;

@AllArgsConstructor
@RequestMapping("review")
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody Review review){
        this.reviewService.create(review);
    }
}
