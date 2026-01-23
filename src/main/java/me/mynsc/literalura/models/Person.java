package me.mynsc.literalura.models;

public class Person {
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    public Person(DataPerson dataPerson) {
        this.name = dataPerson.name();
        this.birthYear = dataPerson.birthYear();
        this.deathYear = dataPerson.deathYear();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    @Override
    public String toString() {
        return "$s ($d - $d)".formatted(name, birthYear, deathYear);
    }
}
