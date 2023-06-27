package com.demo.example.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.demo.example.FavoriteDb.Alarm;
import com.demo.example.FavoriteDb.AlarmDao;
import com.demo.example.FavoriteDb.AppDatabase;
import com.demo.example.R;
import com.demo.example.fragment.FavoritesFragment;
import com.demo.example.models.AudioModel;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {
    private final AppDatabase db;
    private final AlarmDao alarmDao;
    Context context;
    List<AudioModel> albumList;
    onClickListner setOnClickListener;
    public FavoritesAdapter(Activity activity, FavoritesAdapter.onClickListner onClickListner, List<AudioModel> list) {
        this.context = activity;
        this.albumList = list;
        this.setOnClickListener = onClickListner;
        db = Room.databaseBuilder(activity.getApplicationContext(), AppDatabase.class, Alarm.class.toString()).allowMainThreadQueries().build();
        alarmDao = db.alarmDao();
    }
    @NonNull
    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_list, parent, false);
        return new FavoritesAdapter.ViewHolder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtArtistName.setText(albumList.get(position).Name);
        holder.txtAlbums.setText("Albums " + albumList.get(position).Number_of_album);
        holder.txtTracks.setText("Tracks " + albumList.get(position).Number_of_tracks);
//     if (getArtistInfoById(context, Long.valueOf(albumList.get(position)._ID)) !=null){
//         holder.txtPlaylistItemsCount.setText(getArtistInfoById(context, Long.valueOf(albumList.get(position)._ID)).size()+"");
//     }

    }
    @Override
    public int getItemCount() {
        return albumList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtArtistName;
        TextView txtAlbums;
        TextView txtTracks;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                setOnClickListener.onClick(getAdapterPosition(), albumList);
            });
            txtArtistName = itemView.findViewById(R.id.txtArtistNm);
            txtAlbums = itemView.findViewById(R.id.txtArtistAlbum);
            txtTracks = itemView.findViewById(R.id.txtArtistTracks);
        }
    }
    public interface onClickListner {
        void onClick(int position, List<AudioModel> alarmList);
    }
}
