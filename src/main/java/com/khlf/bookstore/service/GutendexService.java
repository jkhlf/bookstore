package com.khlf.bookstore.service;

import com.khlf.bookstore.dto.BookDTO;
import com.khlf.bookstore.model.Book;
import com.khlf.bookstore.repository.BookRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GutendexService {

    private static final String API_URL = "https://gutendex.com/books/";
    private final RestTemplate restTemplate;
    private final BookRepository bookRepository;

    public GutendexService(RestTemplate restTemplate, BookRepository bookRepository) {
        this.restTemplate = restTemplate;
        this.bookRepository = bookRepository;
    }

    public List<Book> fetchBooksFromApi() {
        GutendexResponse response = restTemplate.getForObject(API_URL, GutendexResponse.class);

        return Optional.ofNullable(response)
                .map(GutendexResponse::getResults)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::convertToBook)
                .collect(Collectors.toList());
    }

    private Book convertToBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.title());
        book.setAuthor(extractAuthorName(bookDTO));
        book.setAuthorLife(extractAuthorLife(bookDTO));
        book.setSubjects(bookDTO.subjects());
        book.setCoverImage(extractCoverImage(bookDTO));
        book.setDownloadUrl(extractDownloadUrl(bookDTO));
        return book;
    }

    private String extractAuthorName(BookDTO bookDTO) {
        return bookDTO.authors().isEmpty()
                ? "Unknown Author"
                : bookDTO.authors().get(0).name();
    }

    private String extractAuthorLife(BookDTO bookDTO) {
        return bookDTO.authors().isEmpty()
                ? ""
                : String.format("%d - %d",
                bookDTO.authors().get(0).birth_year() != null ? bookDTO.authors().get(0).birth_year() : 0,
                bookDTO.authors().get(0).death_year() != null ? bookDTO.authors().get(0).death_year() : 0);
    }

    private String extractCoverImage(BookDTO bookDTO) {
        return bookDTO.formats().get("image/jpeg");
    }

    private String extractDownloadUrl(BookDTO bookDTO) {
        return bookDTO.formats().get("application/epub+zip");
    }

    public List<Book> syncBooks() {
        List<Book> books = fetchBooksFromApi();
        return bookRepository.saveAll(books);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}