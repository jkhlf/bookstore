package com.khlf.bookstore.repository;

import com.khlf.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Buscar por título (case insensitive)
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Listar por idioma (usando subjects para representar idiomas)
    @Query("SELECT b FROM Book b JOIN b.subjects s WHERE s = :language")
    List<Book> findByLanguage(@Param("language") String language);
}
