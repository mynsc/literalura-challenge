package me.mynsc.literalura.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
    @ManyToOne(fetch = FetchType.EAGER)
    private Person author;

    public Book() {}

    public Book(DataBook dataBook) {
        this.title = dataBook.title();
        this.downloadCount = dataBook.downloadCount();
        this.language = Language.fromString(dataBook.language().get(0));
        
        if (dataBook.authors() != null && !dataBook.authors().isEmpty()) {
            this.author = new Person(dataBook.authors().get(0));
        } else {
            this.author = new Person("Desconocido", null, null);
        }
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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
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
                %s
                por %s
                Descargado %d veces
                """.formatted(title, author != null ? author.getName() : "Desconocido", downloadCount);
    }
}
