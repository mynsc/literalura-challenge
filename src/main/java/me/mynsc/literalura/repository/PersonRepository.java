package me.mynsc.literalura.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.mynsc.literalura.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByName(String name);
}
