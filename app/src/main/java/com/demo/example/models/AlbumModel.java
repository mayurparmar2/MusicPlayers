package com.demo.example.models;

public class AlbumModel {
    public String ALBUM;
    public String ALBUM_ARTIST;
    public byte[] DATA;

    public AlbumModel(String ALBUM, String ALBUM_ARTIST, byte[] DATA) {
        this.ALBUM = ALBUM;
        this.ALBUM_ARTIST = ALBUM_ARTIST;
        this.DATA = DATA;
    }
}