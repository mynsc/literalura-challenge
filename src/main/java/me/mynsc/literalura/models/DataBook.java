package me.mynsc.literalura.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBook(
    @JsonAlias("title")
    String title, 
    @JsonAlias("download_count")
    Integer downloadCount, 
    @JsonAlias("languages")
    List<String> language, 
    @JsonAlias("authors")
    List<DataPerson> authors
) {}
