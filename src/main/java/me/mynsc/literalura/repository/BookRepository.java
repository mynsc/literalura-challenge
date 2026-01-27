package me.mynsc.literalura.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.mynsc.literalura.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
}
