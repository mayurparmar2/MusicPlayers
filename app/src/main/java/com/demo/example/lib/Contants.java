package com.demo.example.lib;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.MimeTypeMap;

import androidx.core.app.ActivityCompat;

import com.demo.example.models.AudioModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Contants {
    Context context;


    Contants(Context contant) {
        this.context = contant;
    }

    public static String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_SETTINGS,
    };

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
//
//    @SuppressLint("Range")
//    public static List<AudioModel> getAllAudioFromDevice(final Context context) {
//        final List<AudioModel> tempAudioList = new ArrayList<>();
//        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
//
//        if (cursor != null && cursor.moveToFirst()) {
//
//            int pathColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
//
//            do {
//                String thisPath = cursor.getString(pathColumn);
//
////                MediaMetadataRetriever mmr = new MediaMetadataRetriever();
////                mmr.setDataSource(thisPath);
////
////                byte[] data = mmr.getEmbeddedPicture();
//
////                if (data != null) {
////                    bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
////
////                } else {
////                    bitmap = null;
////                }
//
//                tempAudioList.add(new AudioModel(thisPath
//                        , cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
//                        , cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM))
//                        , cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)) ));
//
//            } while (cursor.moveToNext());
//
//
////        String[] STAR = {"*"};
////
////        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
////        String selection = MediaStore.Audio.Media.IS_MUSIC;
////        Cursor cursor = context.getContentResolver().query(uri, STAR, selection, null, null);
////
////        if (cursor != null) {
////            if (cursor.moveToFirst()) {
////                do {
////                    AudioModel audioModel = new AudioModel();
////                    @SuppressLint("Range")
////                    String songName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
////                    @SuppressLint("Range")
////                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
////                    @SuppressLint("Range")
////                    String albumName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
////                    @SuppressLint("Range")
////
////
////                    int ARTIST = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
////                    audioModel.setaName(songName);
////                    audioModel.setaAlbum(albumName);
////                    audioModel.setaArtist(ARTIST);
////                    audioModel.setaPath(path);
////                    tempAudioList.add(audioModel);
////
////                } while (cursor.moveToNext());
////            }
////        }
//        }
//        return tempAudioList;
//    }

    private String[] STAR = {"*"};

    public static List<AudioModel> scanDeviceForMp3Files(Context context) {
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION
        };
        final String sortOrder = MediaStore.Audio.AudioColumns.TITLE + " COLLATE LOCALIZED ASC";
        List<AudioModel> mp3Files = new ArrayList<>();

        Cursor cursor = null;
        try {
            Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            cursor = context.getContentResolver().query(uri, projection, selection, null, sortOrder);
            if (cursor != null) {
                cursor.moveToFirst();

                while (!cursor.isAfterLast()) {
                    String title = cursor.getString(0);
                    String artist = cursor.getString(1);
                    String path = cursor.getString(2);
                    String displayName = cursor.getString(3);
                    Long songDuration = Long.parseLong(cursor.getString(4));

                    MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                    mmr.setDataSource(path);

                    byte[] data = mmr.getEmbeddedPicture();


                    cursor.moveToNext();
                    if (path != null && path.endsWith(".mp3") || path.endsWith(".mp4"))
                    {
                        mp3Files.add(new AudioModel(path, title, artist, displayName, songDuration,data));
                    }
                }

            }

            // print to see list of mp3 files
//        for( String file : mp3Files) {
//            Log.i("TAG", file);
//        }

        } catch (Exception e) {
            Log.e("TAG", e.toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return mp3Files;
    }


    public static Bitmap getAlbumImage(String path) {
        android.media.MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(path);
        byte[] data = mmr.getEmbeddedPicture();
        if (data != null) return BitmapFactory.decodeByteArray(data, 0, data.length);
        return null;
    }

}
