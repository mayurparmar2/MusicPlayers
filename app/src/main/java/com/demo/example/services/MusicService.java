package com.demo.example.services;

import static com.demo.example.activity.MainActivity.mp3Files;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;

import com.demo.example.R;
import com.demo.example.interfaces.ActionPlaying;
import com.demo.example.models.AudioModel;
import java.util.ArrayList;
import java.util.List;

public class MusicService extends Service implements MediaPlayer.OnCompletionListener {
    IBinder mBinder = new Mybinder();
    MediaPlayer mediaPlayer;
    List<AudioModel> audioList = new ArrayList<>();
    public static String  TAG = "TAG";
    private Uri uri;
    private int position;
    ActionPlaying actionPlaying;

    @Override
    public void onCreate() {
        super.onCreate();
        audioList =mp3Files;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
            Log.e("TAG", "onBind: " );
        return mBinder;
    }



    public class Mybinder extends Binder{
        public MusicService getService(){
            return MusicService.this;
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        int MyPosition = intent.getIntExtra("ServicePosition",-1);
        if (MyPosition!=-1){
            PlayMedia(position);
        }
        return START_STICKY;
    }

    private void PlayMedia(int startPosition) {
        audioList =mp3Files;
        position = startPosition;
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            if (audioList!=null){
                createMediaPlayer(position);
                mediaPlayer.start();
            }
        }else {
            createMediaPlayer(position);
            mediaPlayer.start();
        }
    }

    public void  start() {
        mediaPlayer.start();
    }
    public Boolean isPlaying(){
        return mediaPlayer.isPlaying();
    }
    public void stop() {
        mediaPlayer.stop();
    }
    public void pause() {
        mediaPlayer.pause();
    }
    public void release(){
        mediaPlayer.release();
    }
    public int getDuration(){
        return mediaPlayer.getDuration();
    }
    public void seekTo(int position){
        mediaPlayer.seekTo(position);
    }
    public int getCurrentPosition(){
        return mediaPlayer.getCurrentPosition();
    }
    public void createMediaPlayer(int position){
        uri = Uri.parse(audioList.get(position).getPath());
        mediaPlayer = MediaPlayer.create(getBaseContext(),uri);
    }
    public void OnCompleted(){
        mediaPlayer.setOnCompletionListener(this);
    }
    @Override
    public void onCompletion(MediaPlayer mp) {
        if (actionPlaying!= null){
            actionPlaying.NextBtnClicked();
        }
            createMediaPlayer(position);
            start();
            OnCompleted();

    }
}
