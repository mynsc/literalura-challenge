package me.mynsc.literalura.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DataBook(
    @JsonAlias("title")
    String title, 
    @JsonAlias("download_count")
    Integer downloadCount, 
    @JsonAlias("languages")
    List<String> languages, 
    @JsonAlias("authors")
    List<DataPerson> authors
) {}
