package com.khlf.bookstore.service;

import com.khlf.bookstore.model.Book;
import com.khlf.bookstore.repository.BookRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GutendexService {

    private final RestTemplate restTemplate;
    private final BookRepository bookRepository;

    public GutendexService(RestTemplate restTemplate, BookRepository bookRepository) {
        this.restTemplate = restTemplate;
        this.bookRepository = bookRepository;
    }

    public List<Book> fetchBooksFromApi() {
        String apiUrl = "https://gutendex.com/books/";
        JsonNode response = restTemplate.getForObject(apiUrl, JsonNode.class);

        List<Book> books = new ArrayList<>();
        if (response != null && response.has("results")) {
            for (JsonNode result : response.get("results")) {
                Long id = result.get("id").asLong();
                String title = result.get("title").asText();

                // Autor principal
                String author = "";
                String authorLife = "";
                if (result.has("authors") && result.get("authors").isArray() && result.get("authors").size() > 0) {
                    JsonNode firstAuthor = result.get("authors").get(0);
                    author = firstAuthor.get("name").asText();
                    authorLife = String.format(
                            "%d - %d",
                            firstAuthor.get("birth_year").asInt(0),
                            firstAuthor.get("death_year").asInt(0)
                    );
                }

                // Assuntos
                List<String> subjects = new ArrayList<>();
                if (result.has("subjects") && result.get("subjects").isArray()) {
                    for (JsonNode subject : result.get("subjects")) {
                        subjects.add(subject.asText());
                    }
                }

                // Imagem da capa
                String coverImage = result.has("formats") && result.get("formats").has("image/jpeg")
                        ? result.get("formats").get("image/jpeg").asText()
                        : null;

                // URL para download
                String downloadUrl = result.has("formats") && result.get("formats").has("application/epub+zip")
                        ? result.get("formats").get("application/epub+zip").asText()
                        : null;

                // Contagem de downloads
                int downloadCount = result.get("download_count").asInt();

                Book book = new Book(id, title, author, authorLife, subjects, coverImage, downloadUrl, downloadCount);
                books.add(book);
            }
        }

        return books;
    }

    public List<Book> syncBooks() {
        List<Book> books = fetchBooksFromApi();
        bookRepository.saveAll(books);
        return books;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}