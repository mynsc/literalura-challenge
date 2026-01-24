package me.mynsc.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.mynsc.literalura.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}
