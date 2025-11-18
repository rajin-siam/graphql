package com.example.graphql.repository;

import com.example.graphql.model.Review;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ReviewRepository {

    private final Map<String, Review> reviews = new HashMap<>();

    public ReviewRepository() {
        reviews.put("1", new Review("1", "1", "Alice", 5, "Excellent book on clean coding!"));
        reviews.put("2", new Review("2", "1", "Bob", 4, "Very helpful for developers"));
        reviews.put("3", new Review("3", "2", "Charlie", 5, "Must-read for programmers"));
    }

    public List<Review> findAll() {
        return new ArrayList<>(reviews.values());
    }

    public List<Review> findByBookId(String bookId) {
        return reviews.values().stream()
                .filter(review -> review.getBookId().equals(bookId))
                .collect(Collectors.toList());
    }

    public Review save(Review review) {
        if (review.getId() == null) {
            review.setId(UUID.randomUUID().toString());
        }
        reviews.put(review.getId(), review);
        return review;
    }
}
