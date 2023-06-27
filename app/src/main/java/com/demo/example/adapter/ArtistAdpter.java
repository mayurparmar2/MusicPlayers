package com.demo.example.adapter;

import static com.demo.example.lib.Contants.getArtistInfoById;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.demo.example.FavoriteDb.Alarm;
import com.demo.example.FavoriteDb.AlarmDao;
import com.demo.example.FavoriteDb.AppDatabase;
import com.demo.example.R;
import com.demo.example.models.ArtistModel;
import com.demo.example.models.AudioModel;
import com.demo.example.models.PlaylistModel;
import java.util.List;

public class ArtistAdpter extends RecyclerView.Adapter<ArtistAdpter.ViewHolder> {
    private final AppDatabase db;
    private final AlarmDao alarmDao;
    Context context;
    List<AudioModel> albumList;
    onClickListner setOnClickListener;
    public ArtistAdpter(Activity activity, onClickListner onClickListner, List<AudioModel> list) {
        this.context = activity;
        this.albumList = list;
        this.setOnClickListener = onClickListner;
        db = Room.databaseBuilder(activity.getApplicationContext(), AppDatabase.class, Alarm.class.toString()).allowMainThreadQueries().build();
        alarmDao = db.alarmDao();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_list, parent, false);
        return new ViewHolder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.txtArtistName.setText(albumList.get(position).Artist);
            holder.txtAlbums.setText("Albums "+albumList.get(position).Number_of_album);
            holder.txtTracks.setText("Tracks "+albumList.get(position).Number_of_tracks);
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
