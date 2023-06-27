package com.demo.example.fragment;

import static com.demo.example.activity.MainActivity.mp3Files;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import androidx.room.Room;

import com.demo.example.FavoriteDb.Alarm;
import com.demo.example.FavoriteDb.AppDatabase;
import com.demo.example.R;
import com.demo.example.activity.MainActivity;
import com.demo.example.adapter.ArtistAdpter;
import com.demo.example.adapter.FavoritesAdapter;
import com.demo.example.adapter.TracksAdpter;
import com.demo.example.models.AudioModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment implements FavoritesAdapter.onClickListner {
    Activity activity;
    RecyclerView recyclerView;
    Context context;
    private String TAG = "TAG";
    public FavoritesFragment() {
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
//        List<AudioModel> mp3Files = new ArrayList<>();
//        String[] proj = {
//                MediaStore.Audio.Media.ARTIST,
//                MediaStore.Audio.Media.DATA,
//                MediaStore.Audio.Media.DISPLAY_NAME,
//                MediaStore.Audio.Media.DURATION,
//                MediaStore.Audio.Media.ALBUM
//
//        };
//        final Cursor cursor = getActivity().managedQuery(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, proj, MediaStore.Audio.Media.IS_MUSIC+"!= 0", null, null);
//        AudioModel audioModel = new AudioModel();
//        if (cursor.moveToFirst()) {
//            do {
////                mp3Files.add(new AudioModel(
////                        audioModel._ID=cursor.getString(0),
////                        audioModel.Artist=cursor.getString(1),
////                        audioModel.Number_of_album=cursor.getString(2),
////                        audioModel.Number_of_tracks=cursor.getString(3)
////                ));
//                Log.e(TAG, "setDate:1 " + cursor.getString(0));
//                Log.e(TAG, "setDate:2 " + cursor.getString(1));
//                Log.e(TAG, "setDate:2 " + cursor.getString(2));
//                Log.e(TAG, "setDate:2 " + cursor.getString(3));
//                Log.e(TAG, "setDate:2 " + cursor.getString(4));
//            } while (cursor.moveToNext());
//        }
//        if (cursor != null) {
//            cursor.close();
//        }
//        if (cursor == null) {
//            Log.e(TAG, "is Null " + cursor.getString(5));
//
//        }

        if (mp3Files != null) {
            recyclerView.setAdapter(new FavoritesAdapter(getActivity(), this, mp3Files));
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        }
    }

    @Override
    public void onClick(int position, List<AudioModel> alarmList) {

    }
}
