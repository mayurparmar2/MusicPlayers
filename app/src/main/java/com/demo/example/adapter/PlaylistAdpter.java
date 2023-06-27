package com.demo.example.adapter;

import static com.demo.example.lib.Contants.getPlaylistAudio;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.demo.example.FavoriteDb.Alarm;
import com.demo.example.FavoriteDb.AlarmDao;
import com.demo.example.FavoriteDb.AppDatabase;
import com.demo.example.R;
import com.demo.example.models.AlbumModel;
import com.demo.example.models.AudioModel;
import com.demo.example.models.PlaylistModel;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class PlaylistAdpter extends RecyclerView.Adapter<PlaylistAdpter.ViewHolder> {
    private final AppDatabase db;
    private final AlarmDao alarmDao;
    Context context;
    List<AudioModel> albumList;
    onClickListner setOnClickListener;
    public PlaylistAdpter(Activity activity, onClickListner onClickListner, List<AudioModel> list) {
        this.context = activity;
        this.albumList = list;
        this.setOnClickListener = onClickListner;
        db = Room.databaseBuilder(activity.getApplicationContext(), AppDatabase.class, Alarm.class.toString()).allowMainThreadQueries().build();
        alarmDao = db.alarmDao();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtPlayName.setText(albumList.get(position).playlists_Name);
      //  holder.txtPlaylistItemsCount.setText(albumList.get(position).Number_of_playlist);
        holder.txtPlaylistItemsCount.setText(getPlaylistAudio(context,Long.parseLong(albumList.get(position)._ID)).size()+"");
        if (albumList.get(position).data != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(albumList.get(position).data, 0, albumList.get(position).data.length);
            holder.CircularImageView.setImageBitmap(bitmap);
        }
        else {
            holder.CircularImageView.setImageResource(R.drawable.ic_baseline_album_24);
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtPlayName;
        TextView txtPlaylistItemsCount;
        CircularImageView CircularImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                setOnClickListener.onClick(getAdapterPosition(), albumList);
            });
             txtPlayName = itemView.findViewById(R.id.txtPlayName);
             txtPlaylistItemsCount = itemView.findViewById(R.id.txtPlaylistItemsCount);
            CircularImageView = itemView.findViewById(R.id.imgCover);
        }
    }

    public interface onClickListner {
        void onClick(int position, List<AudioModel> alarmList);
    }
}
