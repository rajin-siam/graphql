package com.example.graphql.model;

import java.util.List;

public class Author {
    private String id;
    private String name;
    private String email;
    private List<String> bookIds; // References to books

    // Constructor
    public Author(String id, String name, String email, List<String> bookIds) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.bookIds = bookIds;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<String> getBookIds() { return bookIds; }
    public void setBookIds(List<String> bookIds) { this.bookIds = bookIds; }
}
