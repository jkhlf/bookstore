package com.khlf.bookstore.controller;

import com.khlf.bookstore.model.Book;
import com.khlf.bookstore.service.GutendexService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final GutendexService gutendexService;

    public BookController(GutendexService gutendexService) {
        this.gutendexService = gutendexService;
    }

    @GetMapping
    public List<Book> getBooks() {
        return gutendexService.getBooks();
    }

    @GetMapping("/sync")
    public List<Book> syncBooks() {
        return gutendexService.syncBooks();
    }
}