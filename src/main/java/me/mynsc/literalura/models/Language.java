package me.mynsc.literalura.models;

public enum Language {
    EN("en"),
    ES("es"),
    FR("fr");

    private String languageGutendex;

    Language(String languageGutendex) {
        this.languageGutendex = languageGutendex;
    }

    public static Language fromString(String text) {
        for (Language language : Language.values()) {
            if (language.languageGutendex.equalsIgnoreCase(text)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Ningún lenguaje \"" + text + "\" encontrado");
    }
}
