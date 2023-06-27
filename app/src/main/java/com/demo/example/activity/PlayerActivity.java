package com.demo.example.activity;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.demo.example.lib.Contants.scanDeviceForMp3Files;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.demo.example.R;
import com.demo.example.databinding.ActivityPlayerBinding;
import com.demo.example.fragment.TracksFragment;
import com.demo.example.models.AudioModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PlayerActivity extends AppCompatActivity {
    ActivityPlayerBinding binding;
    private FragmentTransaction transaction;
    Boolean isPlaying = false;
    private MediaPlayer mediaPlayer;
    private Handler handler= new Handler();
    private static final int UPDATE_FREQUENCY = 500;
    String sArtist,songName,sPath,sAlbum;
    Long sDuration;
    byte[] sImgBytes;
    double sDurationInMin;
    List<AudioModel> songlist = new ArrayList<>();
    private int position = 0;
    private Uri uri;


    @SuppressLint("UseCompatLoadingForDrawables")
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        binding = ActivityPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        Toolbar toolbar =  findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setIcon(R.drawable.ic_music_);
//        getSupportActionBar().setTitle("Music");
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nowFrameLayout, new TracksFragment());
        transaction.addToBackStack(null);
        transaction.commit();
        getIntentMathod();
        binding.palyerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer!= null && fromUser){
                    mediaPlayer.seekTo(progress * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        PlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                 if (mediaPlayer!= null){
                     int currentPosition = mediaPlayer.getCurrentPosition()/1000;
                     binding.palyerSeekBar.setProgress(currentPosition);
                     binding.txtStarDuration.setText(formatDuration(currentPosition));
                 }
                handler.postDelayed(this,1000);
            }
        });



//        binding.txtSongName.setText(songName);
//        binding.txtArtist.setText(sArtist);
//        if (sImgBytes != null) {
//            Bitmap bitmap = BitmapFactory.decodeByteArray(sImgBytes, 0,sImgBytes.length);
//            binding.PalayImageId.setImageBitmap(bitmap);
//        }
//        binding.txtArtist.setText(sArtist);
//       // player = new MediaPlayer();
//
//        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), Uri.parse(sPath));
//        r.play();
//    //    binding.palyerSeekBar.setMax(player.getDuration());
//
//        isPlaying = true;
//        binding.btnplay.setImageResource(R.drawable.ic_pause_24);
//
//        binding.btnFavrite.setOnClickListener(v -> {
//                binding.btnFavrite.setImageResource(R.drawable.ic_baseline_border_24);
//        });
//        binding.btnNowPlayList.setOnClickListener(v -> {
//            if (binding.nowFrameLayout.getVisibility() == View.VISIBLE)
//            {
//                binding.txtSongName.setVisibility(View.VISIBLE);
//                binding.txtArtist.setVisibility(View.VISIBLE);
//                binding.PalayImageId.setVisibility(View.VISIBLE);
//                binding.nowFrameLayout.setVisibility(View.INVISIBLE);
//            }
//            else {
//                binding.txtSongName.setVisibility(View.INVISIBLE);
//                binding.txtArtist.setVisibility(View.INVISIBLE);
//                binding.PalayImageId.setVisibility(View.INVISIBLE);
//                binding.nowFrameLayout.setVisibility(View.VISIBLE);
//            }
//        });
//        binding.btnplay.setOnClickListener(v -> {
//
////            if (player != null) {
////                binding.palyerSeekBar.setProgress(player.getCurrentPosition());
////                binding.palyerSeekBar.setMax(player.getDuration());
////            }
////            String ppStr = tv_PlayPause.getText().toString();
////            if (ppStr.equals(getString(R.string.picture_play_audio))) {
////                tv_PlayPause.setText(getString(R.string.picture_pause_audio));
////                tv_musicStatus.setText(getString(R.string.picture_play_audio));
////                playOrPause();
////            }
////            else {
////                tv_PlayPause.setText(getString(R.string.picture_play_audio));
////                tv_musicStatus.setText(getString(R.string.picture_pause_audio));
////                playOrPause();
////            }
////            if (isPlaying == false) {
////                handler.post(runnable);
////                isPlaying = true;
////            }
//            if (isPlaying){
//                binding.btnplay.setImageResource(R.drawable.ic_play_arrow_24);
//                r.stop();
//                isPlaying = false;
//            }else {
//                binding.btnplay.setImageResource(R.drawable.ic_pause_24);
//                r.play();
//
//                binding.palyerSeekBar.setProgress(0);
//                isPlaying = true;
//            }
//        });
    }

    private String formatDuration(int currentPosition) {
        String totalout = "";
        String totalNew = "";
        String seconds = String.valueOf(currentPosition%60);
        String minutes = String.valueOf(currentPosition/60);

        totalout = minutes+":"+seconds;
        totalNew = minutes+":"+"0"+seconds;
        if (seconds.length() == 1)
        {
            return totalNew;
        }else {
            return totalout;
        }
    }

    private void getIntentMathod() {
        sArtist = getIntent().getStringExtra("aArtist");
        songName = getIntent().getStringExtra("aName");
        sPath =  getIntent().getStringExtra("aPath");
        sAlbum = getIntent().getStringExtra("aAlbum");
        sDuration = getIntent().getLongExtra("duration",0L);
        sImgBytes = getIntent().getByteArrayExtra("imgbytes");
        position = getIntent().getIntExtra("imgbytes",position);
        sDurationInMin = ( sDuration / 1000.0) / 60.0;
        songlist =  scanDeviceForMp3Files(getApplicationContext());
        if (songlist!=null){
            binding.btnplay.setImageResource(R.drawable.ic_pause_24);
            uri = Uri.parse(sPath);
        }
        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(getApplicationContext(),uri);
            mediaPlayer.start();
        }
        else {
            mediaPlayer = MediaPlayer.create(getApplicationContext(),uri);
            mediaPlayer.start();
        }
        binding.palyerSeekBar.setMax(mediaPlayer.getDuration()/1000);
        binding.txtEndDuration.setText(formatDuration( mediaPlayer.getDuration() / 1000));
    }


//    private void startPlay(String file) {
//        Log.i("Selected: ", file);
//
//     //   selectedFile.setText(file);
//        binding.palyerSeekBar.setProgress(0);
//        player.stop();
//        player.reset();
//
//        try {
//            player.setDataSource(file);
//            player.prepare();
//            player.start();
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        binding.palyerSeekBar.setMax(player.getDuration());
//        binding.btnplay.setImageResource(R.drawable.ic_pause_24);
//        updatePosition();
//        isPlaying = true;
//    }

//    private void updatePosition() {
////        handler.removeCallbacks(updatePositionRunnable);
////        binding.palyerSeekBar.setProgress(player.getCurrentPosition());
////        handler.postDelayed(updatePositionRunnable, UPDATE_FREQUENCY);
//    }
//    private void stopPlay() {
//        player.stop();
//        player.reset();
//        binding.btnplay.setImageResource(R.drawable.ic_play_arrow_24);
////        handler.removeCallbacks(updatePositionRunnable);
//        binding.palyerSeekBar.setProgress(0);
//        isPlaying = false;
//
//    }

}
