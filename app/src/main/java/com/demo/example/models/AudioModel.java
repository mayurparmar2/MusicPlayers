package com.demo.example.models;

public class AudioModel {
    public String playlists_Name;
    public String Number_of_album;
    public String Number_of_tracks;
    public String Number_of_playlist;
    public String Playlists_Name;
    public String _ID;
    public String Path;
    public String displayName;
    public String Name;
    public String Album;
    public String Artist;
    public byte[] data = null;
    public long duration = 0L;


    public AudioModel(String path, String title, String artist, String displayName, Long songDuration, byte[] data, String album) {
        this.Path = path;
        this.Name = title;
        this.Album = album;
        this.Artist = artist;
        this.duration = songDuration;
        this.data = data;
        this.displayName = displayName;
    }

    public AudioModel(String a_ID, String path, String title, String artist, String displayName, Long songDuration, byte[] data, String album) {
        this._ID = a_ID;
        this.Path = path;
        this.Name = title;
        this.Album = album;
        this.Artist = artist;
        this.duration = songDuration;
        this.data = data;
        this.displayName = displayName;
    }

    public AudioModel(String path) {
        this.Path = path;
    }

    public AudioModel(String _ID, String artist, String Number_of_album, String Number_of_tracks) {
        this._ID = _ID;
        this.Artist = artist;
        this.Number_of_album = Number_of_album;
        this.Number_of_tracks = Number_of_tracks;
    }

    public AudioModel(String _ID, String playlists_Name) {
        this._ID = _ID;
        this.playlists_Name = playlists_Name;
    }

    public AudioModel(String path, String title, String artist, String displayName, Long songDuration, byte[] data) {
    }

    public AudioModel(String s, String s1, String s2, String s3, String s4, byte[] s5) {
        this.Name = s;
        this.Artist = s1;
        this.Path = s2;
        this.displayName = s3;
        this.Album = s4;
        this.data = s5;
    }


    public String get_ID() {
        return _ID;
    }

    public String getNumber_of_album() {
        return Number_of_album;
    }

    public void setNumber_of_album(String number_of_album) {
        Number_of_album = number_of_album;
    }

    public String getNumber_of_tracks() {
        return Number_of_tracks;
    }

    public String getNumber_of_playlist() {
        return Number_of_playlist;
    }

    public void setNumber_of_playlist(String number_of_playlist) {
        Number_of_playlist = number_of_playlist;
    }

    public void setNumber_of_tracks(String number_of_tracks) {
        Number_of_tracks = number_of_tracks;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getPlaylists_Name() {
        return Playlists_Name;
    }

    public void setPlaylists_Name(String playlists_Name) {
        Playlists_Name = playlists_Name;
    }

}