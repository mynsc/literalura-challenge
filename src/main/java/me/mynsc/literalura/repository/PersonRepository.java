package me.mynsc.literalura.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import me.mynsc.literalura.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByName(String name);

    // this method is for practicing JPQL queries
    @Query ("SELECT a FROM Person a WHERE a.name ILIKE %:name%")
    Optional<Person> findAuthorByName(String name);
}
