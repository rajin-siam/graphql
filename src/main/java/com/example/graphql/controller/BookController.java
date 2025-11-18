package com.example.graphql.controller;

import com.example.graphql.model.Author;
import com.example.graphql.model.Book;
import com.example.graphql.model.Review;
import com.example.graphql.repository.AuthorRepository;
import com.example.graphql.repository.BookRepository;
import com.example.graphql.repository.ReviewRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ReviewRepository reviewRepository;

    // Constructor injection
    public BookController(BookRepository bookRepository,
                          AuthorRepository authorRepository,
                          ReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.reviewRepository = reviewRepository;
    }

    // Query: Get all books
    @QueryMapping
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    // Query: Get book by ID
    @QueryMapping
    public Book bookById(@Argument String id) {
        return bookRepository.findById(id).orElse(null);
    }

    // Query: Get books by author
    @QueryMapping
    public List<Book> booksByAuthor(@Argument String authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    // Schema Mapping: Resolve 'author' field in Book type
    // This is called automatically when a query requests the author of a book
    @SchemaMapping(typeName = "Book", field = "author")
    public Author author(Book book) {
        return authorRepository.findById(book.getAuthorId()).orElse(null);
    }

    // Schema Mapping: Resolve 'reviews' field in Book type
    @SchemaMapping(typeName = "Book", field = "reviews")
    public List<Review> reviews(Book book) {
        return reviewRepository.findByBookId(book.getId());
    }

    // Mutation: Create a new book
    @MutationMapping
    public Book createBook(@Argument Map<String, Object> input) {
        // Extract values from input map
        String title = (String) input.get("title");
        String isbn = (String) input.get("isbn");
        Integer publishedYear = (Integer) input.get("publishedYear");
        String authorId = (String) input.get("authorId");

        // Create and save the book
        Book book = new Book(null, title, isbn, publishedYear, authorId);
        return bookRepository.save(book);
    }

    // Mutation: Add a review
    @MutationMapping
    public Review addReview(@Argument Map<String, Object> input) {
        String bookId = (String) input.get("bookId");
        String reviewerName = (String) input.get("reviewerName");
        Integer rating = (Integer) input.get("rating");
        String comment = (String) input.get("comment");

        Review review = new Review(null, bookId, reviewerName, rating, comment);
        return reviewRepository.save(review);
    }
}
