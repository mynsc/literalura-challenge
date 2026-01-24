package me.mynsc.literalura.models;

import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(unique = true)
    private String title;
    private Integer downloadCount;
    @Enumerated(EnumType.STRING)
    private Language language;
    @ManyToOne
    private Person author;

    public Book(DataBook dataBook) {
        this.title = dataBook.title();
        this.downloadCount = dataBook.downloadCount();
        this.language = Language.fromString(dataBook.language().get(0));
        this.author = dataBook.authors()
            .stream()
            .map(d -> new Person(d))
            .collect(Collectors.toList()).get(0);
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Language getLanguages() {
        return language;
    }

    public void setLanguages(Language language) {
        this.language = language;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return """
                $s
                por $s
                Descargado $d veces
                """.formatted(title, downloadCount, author);
    }
}
