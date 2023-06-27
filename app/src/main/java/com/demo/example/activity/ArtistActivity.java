package com.demo.example.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.demo.example.adapter.TracksAdpter;
import com.demo.example.databinding.ActivityAlbumBinding;
import com.demo.example.models.AudioModel;

import java.util.ArrayList;
import java.util.List;


public class ArtistActivity extends AppCompatActivity implements TracksAdpter.onClickListner {
    ActivityAlbumBinding binding;
    private String position;
    private List<AudioModel> AlbumSongList = new ArrayList<>();
    private long _ID;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        binding = ActivityAlbumBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//            Toolbar toolbar =  findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setIcon(R.drawable.ic_music_);
//        getSupportActionBar().setTitle("Music");
//        mToolbar =toolbar;
      //  _ID = Long.parseLong(getIntent().getStringExtra("_ID"));
       // AlbumSongList = getArtistlist(getApplicationContext(),_ID);
      //  binding.recycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
//        db = Room.databaseBuilder(getContext(), AppDatabase.class,  Alarm.class.toString()).allowMainThreadQueries().build();
//        alarmDao = db.alarmDao();
//        alarmList = alarmDao.getAll();
     //   binding.recycleView.setAdapter(new TracksAdpter(this, this, AlbumSongList));
    }

    @Override
    public void onClick(int position, List<AudioModel> alarmList) {
        Intent intent  = new Intent(this,PlayerActivity.class);
        intent.putExtra("aArtist",alarmList.get(position).Artist);
        intent.putExtra("aName",alarmList.get(position).Name);
        intent.putExtra("aPath",alarmList.get(position).Path);
        intent.putExtra("aAlbum",alarmList.get(position).Album);
        intent.putExtra("duration",alarmList.get(position).duration);
        //  intent.putExtra("imgbytes",alarmList.get(position).data);
        intent.putExtra("position",position);
        startActivity(intent);
    }
}
