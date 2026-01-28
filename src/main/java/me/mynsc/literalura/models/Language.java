package me.mynsc.literalura.models;

public enum Language {
    EN("en", "inglés"),
    ES("es", "español"),
    FR("fr", "francés"),
    UNKNOWN("-", "desconocido");

    private String languageGutendex;
    private String languageFullName;

    Language(String languageGutendex, String languageFullName) {
        this.languageGutendex = languageGutendex;
        this.languageFullName = languageFullName;
    }

    public static Language fromString(String text) {
        for (Language language : Language.values()) {
            if (language.languageGutendex.equalsIgnoreCase(text)) {
                return language;
            }
        }
        return UNKNOWN;
    }

    public static Language fromLanguageFullName(String text) {
        for (Language language : Language.values()) {
            if (language.languageFullName.equalsIgnoreCase(text)) {
                return language;
            }
        }
        return UNKNOWN;
    }
}
