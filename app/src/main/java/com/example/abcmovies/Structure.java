package com.example.abcmovies;

public class Structure {
    String film,genre;
    int year, budget, gross;

    public Structure() {
    }

    public Structure(String film, String genre, int year, int budget, int gross) {
        this.film = film;
        this.genre = genre;
        this.year = year;
        this.budget = budget;
        this.gross = gross;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getGross() {
        return gross;
    }

    public void setGross(int gross) {
        this.gross = gross;
    }
}
