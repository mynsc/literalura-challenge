package me.mynsc.literalura.models;

import java.util.List;

public class Book {
    private String title; 
    private Integer downloadCount; 
    private Language language;
    private List<DataPerson> authors;

    public Book(DataBook dataBook) {
        this.title = dataBook.title();
        this.downloadCount = dataBook.downloadCount();
        this.language = Language.fromString(dataBook.language().get(0));
        this.authors = dataBook.authors();
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

    public List<DataPerson> getAuthors() {
        return authors;
    }

    public void setAuthors(List<DataPerson> authors) {
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
