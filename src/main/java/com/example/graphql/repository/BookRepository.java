package com.example.graphql.repository;

import com.example.graphql.model.Book;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookRepository {

    // In-memory storage using a HashMap
    private final Map<String, Book> books = new HashMap<>();

    // Initialize with sample data in constructor
    public BookRepository() {
        books.put("1", new Book("1", "Clean Code", "978-0132350884", 2008, "1"));
        books.put("2", new Book("2", "The Pragmatic Programmer", "978-0135957059", 2019, "2"));
        books.put("3", new Book("3", "Design Patterns", "978-0201633610", 1994, "3"));
        books.put("4", new Book("4", "Refactoring", "978-0134757599", 2018, "1"));
    }

    // Find all books
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    // Find book by ID
    public Optional<Book> findById(String id) {
        return Optional.ofNullable(books.get(id));
    }

    // Find books by author ID
    public List<Book> findByAuthorId(String authorId) {
        return books.values().stream()
                .filter(book -> book.getAuthorId().equals(authorId))
                .collect(Collectors.toList());
    }

    // Save a new book
    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(UUID.randomUUID().toString());
        }
        books.put(book.getId(), book);
        return book;
    }
}
