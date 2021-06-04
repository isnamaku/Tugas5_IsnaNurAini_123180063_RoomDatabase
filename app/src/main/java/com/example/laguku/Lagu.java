package com.example.laguku;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tLagu")
public class Lagu implements Serializable {


    //kolom id sebagai primary key
    @NonNull
    @ColumnInfo(name = "id_lagu")
    @PrimaryKey(autoGenerate = true)
    private   int id=0;

    //kolom judul lagu
    @ColumnInfo(name = "judul_lagu")
    private String judul;

    @ColumnInfo(name = "penyanyi_lagu", defaultValue = "Anonim")
    private String penyanyi;

    @ColumnInfo(name = "genre_lagu", defaultValue = "null")
    private  String genre;

    @ColumnInfo(name = "tahun_rilis", defaultValue = "null")
    private String tahun_rilis;


    @ColumnInfo(name = "lirik_lagu", defaultValue = "null")
    private  String lirik;


    @NonNull
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenyanyi() {
        return penyanyi;
    }

    public void setPenyanyi(String penyanyi) {
        this.penyanyi = penyanyi;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTahun_rilis() {
        return tahun_rilis;
    }

    public void setTahun_rilis(String tahun_rilis) {
        this.tahun_rilis = tahun_rilis;
    }

    public String getLirik() {
        return lirik;
    }

    public void setLirik(String lirik) {
        this.lirik = lirik;
    }
}
