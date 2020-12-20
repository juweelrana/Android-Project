package com.example.abcmovies;

public class Structure {
    private String Title,Url,Genre;
    private int Year;
    private double Rating;

    public Structure() {
    }

    public Structure(String title, String url, String genre, int year, double rating) {
        Title = title;
        Url = url;
        Genre = genre;
        Year = year;
        Rating = rating;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double rating) {
        Rating = rating;
    }
}
