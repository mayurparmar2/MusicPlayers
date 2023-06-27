package com.demo.example.fragment;

import static com.demo.example.activity.MainActivity.mp3Files;
import static com.demo.example.lib.Contants.getAlbumFileList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.example.R;
import com.demo.example.activity.MainActivity;
import com.demo.example.adapter.ArtistAdpter;
import com.demo.example.adapter.FolderAdpter;
import com.demo.example.models.AudioModel;
import com.demo.example.models.PlaylistModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FolderFragmet extends Fragment implements  FolderAdpter.onClickListner {
    Activity activity;
    RecyclerView recyclerView;
    Context context;
    public FolderFragmet() {
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
            Intent i = new Intent(getContext(), MainActivity.class);
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
        recyclerView.setHasFixedSize(true);
//        String[] projection = {
//                MediaStore.Audio.Artists._ID,
//                MediaStore.Audio.Artists.ARTIST
//        };
//        Uri uri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
//        List<AudioModel> mp3Files = new ArrayList<>();
//        Cursor cursor = getAlbumFileList(getActivity(), uri, projection);
//        AudioModel audioModel = new AudioModel();
//        if (cursor != null) {
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
//                audioModel.set_ID(cursor.getString(0));
//                audioModel.setArtist(cursor.getString(1));
//                cursor.moveToNext();
//              //  if (cursor.getString(1) != null) {
//                    mp3Files.add(audioModel);
//               // }
//            }
//        }

        if (mp3Files != null) {
            recyclerView.setAdapter(new FolderAdpter(getActivity(), this,mp3Files));
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        }
    }


    @Override
    public void onClick(int position, List<AudioModel> alarmList) {

    }
}
