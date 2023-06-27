package com.demo.example.adapter;

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

import com.bumptech.glide.Glide;
import com.demo.example.FavoriteDb.Alarm;
import com.demo.example.FavoriteDb.AlarmDao;
import com.demo.example.FavoriteDb.AppDatabase;
import com.demo.example.R;
import com.demo.example.models.AudioModel;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class TracksAdpter extends RecyclerView.Adapter<TracksAdpter.ViewHolder> {
    private final AppDatabase db;
    private final AlarmDao alarmDao;
    Context context;
    List<AudioModel> audioModelList;
    onClickListner setOnClickListener;


    public TracksAdpter(Activity activity, onClickListner onClickListner, List<AudioModel> list) {
        this.context = activity;
        this.audioModelList = list;
        this.setOnClickListener = onClickListner;
        db = Room.databaseBuilder(activity.getApplicationContext(), AppDatabase.class, Alarm.class.toString()).allowMainThreadQueries().build();
        alarmDao = db.alarmDao();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_music, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtSongName.setText(audioModelList.get(position).Name);
        holder.txtArtistName.setText(audioModelList.get(position).Artist);
        if (audioModelList.get(position).data != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(audioModelList.get(position).data, 0, audioModelList.get(position).data.length);
            holder.imageView.setImageBitmap(bitmap);
        } else {
            holder.imageView.setImageResource(R.drawable.ic__music_24);
        }
    }

    @Override
    public int getItemCount() {
        return audioModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                setOnClickListener.onClick(getAdapterPosition(), audioModelList);
            });
        }

        TextView txtSongName = itemView.findViewById(R.id.txtMusicName);
        TextView txtArtistName = itemView.findViewById(R.id.txtTitleMusic);
        CircularImageView imageView = itemView.findViewById(R.id.imgCover);
    }

    public interface onClickListner {
        void onClick(int position, List<AudioModel> alarmList);
    }
}
