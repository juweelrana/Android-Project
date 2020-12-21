package com.example.abcmovies;

public class FavStructure {
    private String uId,cTime,filmId,serial;

    public FavStructure() {
    }

    public FavStructure(String uId, String cTime, String filmId, String serial) {
        this.uId = uId;
        this.cTime = cTime;
        this.filmId = filmId;
        this.serial = serial;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }
}
