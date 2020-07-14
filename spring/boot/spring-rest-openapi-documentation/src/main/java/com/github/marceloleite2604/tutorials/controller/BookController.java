package com.github.marceloleite2604.tutorials.controller;

import com.github.marceloleite2604.tutorials.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@CrossOrigin(origins = "https://portal-thoughtworks38b348.kong-cloud.com", maxAge = 3600)
@RequestMapping("/api/book")
public class BookController {

    private Map<Integer, Book> booksById = Map.of(1, new Book(1, "Clean Coding", "Robert C. Martin"),
            2, new Book(2, "The Colour of Magic", "Terry Pratchett"),
            3, new Book(3, "The Light Fantastic", "Terry Pratchett"));

    @GetMapping("/{id}")
    public Book findById(@PathVariable int id) {
        return booksById.getOrDefault(id, null);
    }

    @GetMapping("/")
    public Collection<Book> findBooks() {
        return booksById.values();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBook(
            @PathVariable("id") final int id, @RequestBody final Book book) {
        booksById.put(id, book);
        return book;
    }
}
