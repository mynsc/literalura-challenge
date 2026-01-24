package me.mynsc.literalura.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

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
    @Transient
    private DataPerson authors;

    public Book(DataBook dataBook) {
        this.title = dataBook.title();
        this.downloadCount = dataBook.downloadCount();
        this.language = Language.fromString(dataBook.language().get(0));
        this.authors = dataBook.authors().get(0);
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

    public DataPerson getAuthors() {
        return authors;
    }

    public void setAuthors(DataPerson authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return """
                $s
                por $s
                Descargado $d veces
                """.formatted(title, downloadCount, authors);
    }
}
