package com.demo.example.fragment;

import static com.demo.example.lib.Contants.getAlbumFileList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.example.R;
import com.demo.example.activity.ArtistActivity;
import com.demo.example.activity.MainActivity;
import com.demo.example.adapter.ArtistAdpter;
import com.demo.example.models.AudioModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ArtistsFragment extends Fragment implements ArtistAdpter.onClickListner {
    Activity activity;
    RecyclerView recyclerView;
    Context context;
    private String TAG="TAG";

    public ArtistsFragment() {
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
        List<AudioModel> mp3Files = new ArrayList<>();
        String[] proj = {
                MediaStore.Audio.Artists._ID,
                MediaStore.Audio.Artists.ARTIST,
                MediaStore.Audio.Artists.NUMBER_OF_ALBUMS,
                MediaStore.Audio.Artists.NUMBER_OF_TRACKS,
                MediaStore.Audio.Artists.ARTIST_KEY,
          };
        final Cursor cursor = getActivity().managedQuery(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, proj, null, null, MediaStore.Audio.Artists.ARTIST + " ASC");
        AudioModel audioModel = new AudioModel(path, title, artist, displayName, songDuration, data);
        if (cursor.moveToFirst()) {
            do {
                mp3Files.add(new AudioModel(
                        audioModel._ID=cursor.getString(0),
                        audioModel.Artist=cursor.getString(1),
                        audioModel.Number_of_album=cursor.getString(2),
                        audioModel.Number_of_tracks=cursor.getString(3)
                ));
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        if (mp3Files != null) {
            recyclerView.setAdapter(new ArtistAdpter(getActivity(), this, mp3Files));
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        }
    }


    @Override
    public void onClick(int position, List<AudioModel> alarmList) {
        Intent intent = new Intent(getActivity(), ArtistActivity.class);
        intent.putExtra("_ID", alarmList.get(position)._ID);
        startActivity(intent);
    }
}
