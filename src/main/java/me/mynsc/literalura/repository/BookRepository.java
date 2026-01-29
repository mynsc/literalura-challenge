package me.mynsc.literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import me.mynsc.literalura.models.Book;
import me.mynsc.literalura.models.Language;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
    List<Book> findByLanguage(Language language);
    
    @Query (value = "SELECT * FROM books ORDER BY download_count ASC LIMIT 1", nativeQuery = true)
    Optional<Book> findLeastDownloadedBook();

    @Query (value = "SELECT * FROM books ORDER BY download_count DESC LIMIT 1", nativeQuery = true)
    Optional<Book> findMostDownloadedBook();
}
