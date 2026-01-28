package me.mynsc.literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.mynsc.literalura.models.Book;
import me.mynsc.literalura.models.Language;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
    List<Book> findByLanguage(Language language);
}
