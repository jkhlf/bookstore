package com.khlf.bookstore.controller;

import com.khlf.bookstore.model.Book;
import com.khlf.bookstore.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Listar todos os livros
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Buscar livros por título
    @GetMapping("/search")
    public List<Book> searchBooksByTitle(@RequestParam String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

}
