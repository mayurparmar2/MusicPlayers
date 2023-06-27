package com.demo.example.fragment;
import static com.demo.example.activity.MainActivity.mp3Files;
import static com.demo.example.lib.Contants.scanDeviceForMp3Files;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.demo.example.FavoriteDb.Alarm;
import com.demo.example.FavoriteDb.AppDatabase;
import com.demo.example.R;
import com.demo.example.activity.MainActivity;
import com.demo.example.activity.PlayerActivity;
import com.demo.example.adapter.TracksAdpter;
import com.demo.example.models.AudioModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TracksFragment extends Fragment implements TracksAdpter.onClickListner {
    Activity activity;
    RecyclerView recyclerView;
    Context context;

    public TracksFragment() {
        activity = getActivity();
        this.context = getContext();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        FloatingActionButton actionButton = view.findViewById(R.id.floating_action_button);
        actionButton.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), PlayerActivity.class);
            getActivity().startActivity(i);
        });
//        ActionBar actionBar = activity.getActionBar();
//        actionBar.setIcon(R.drawable.arrow_back_24);
//        actionBar.setTitle("Alarm");
        setDate();

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tracks_fragment, container, false);
    }
    private void setDate() {
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
//        db = Room.databaseBuilder(getContext(), AppDatabase.class,  Alarm.class.toString()).allowMainThreadQueries().build();
//        alarmDao = db.alarmDao();
//        alarmList = alarmDao.getAll();

        recyclerView.setAdapter(new TracksAdpter(getActivity(),this,mp3Files));
    }

    @Override
    public void onClick(int position, List<AudioModel> alarmList) {
        Intent  intent  = new Intent(getActivity(),PlayerActivity.class);
        intent.putExtra("aArtist",alarmList.get(position).Artist);
        intent.putExtra("aName",alarmList.get(position).Name);
        intent.putExtra("aPath",alarmList.get(position).Path);
        intent.putExtra("aAlbum",alarmList.get(position).Album);
        intent.putExtra("duration",alarmList.get(position).duration);
      //  intent.putExtra("imgbytes",alarmList.get(position).data);
        intent.putExtra("position",position);
        getContext().startActivity(intent);

    }
}
