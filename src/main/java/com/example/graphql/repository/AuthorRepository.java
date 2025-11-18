package com.example.graphql.repository;

import com.example.graphql.model.Author;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AuthorRepository {

    private final Map<String, Author> authors = new HashMap<>();

    public AuthorRepository() {
        authors.put("1", new Author("1", "Robert C. Martin", "uncle.bob@example.com",
                Arrays.asList("1", "4")));
        authors.put("2", new Author("2", "David Thomas", "david@example.com",
                Arrays.asList("2")));
        authors.put("3", new Author("3", "Erich Gamma", "erich@example.com",
                Arrays.asList("3")));
    }

    public List<Author> findAll() {
        return new ArrayList<>(authors.values());
    }

    public Optional<Author> findById(String id) {
        return Optional.ofNullable(authors.get(id));
    }

    public Author save(Author author) {
        if (author.getId() == null) {
            author.setId(UUID.randomUUID().toString());
        }
        authors.put(author.getId(), author);
        return author;
    }
}
