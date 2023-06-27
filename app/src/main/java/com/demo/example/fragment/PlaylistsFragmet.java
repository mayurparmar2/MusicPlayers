package com.demo.example.fragment;

import static com.demo.example.lib.Contants.getAlbumFileList;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.example.R;
import com.demo.example.activity.MainActivity;
import com.demo.example.activity.PlaylistActivity;
import com.demo.example.adapter.PlaylistAdpter;
import com.demo.example.models.AudioModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PlaylistsFragmet extends Fragment implements PlaylistAdpter.onClickListner {
    Activity activity;
    RecyclerView recyclerView;
    Context context;
    String TAG ="TAG";
    public PlaylistsFragmet() {
        activity = getActivity();
        this.context = getContext();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tracks_fragment, container, false);
        recyclerView = view.findViewById(R.id.recycleView);
        FloatingActionButton actionButton = view.findViewById(R.id.floating_action_button);
        actionButton.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), MainActivity.class);
            getActivity().startActivity(i);
        });
        setDate();
        return view;
    }

    private void setDate() {
        recyclerView.setHasFixedSize(true);
        List<AudioModel> mp3Files = new ArrayList<>();
        String[] proj = {
                MediaStore.Audio.Playlists._ID,
                MediaStore.Audio.Playlists.NAME,
        };
        ContentResolver resolver = getActivity().getContentResolver();
        final Cursor cursor = resolver.query(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, proj, null, null, null);
        AudioModel audioModel = new AudioModel(path, title, artist, displayName, songDuration, data);
        if (cursor.moveToFirst()) {
            do {
                mp3Files.add(new AudioModel(
                        audioModel._ID=cursor.getString(0),
                        audioModel.Playlists_Name=cursor.getString(1)
                ));
//                Log.e(TAG, "setDate:1 "+cursor.getString(0) );
//                Log.e(TAG, "setDate2: "+cursor.getString(1) );

            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        if (mp3Files != null){
            recyclerView.setAdapter(new PlaylistAdpter(getActivity(),this,mp3Files));
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        }
    }
    @Override
    public void onClick(int position, List<AudioModel> alarmList) {
        Intent  intent  = new Intent(getActivity(), PlaylistActivity.class);
        intent.putExtra("_ID",alarmList.get(position)._ID);
        startActivity(intent);
    }
}