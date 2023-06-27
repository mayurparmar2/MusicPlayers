package com.demo.example.adapter;

import static com.demo.example.activity.MainActivity.mp3Files;

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
import com.demo.example.models.AudioModel;
import com.demo.example.models.PlaylistModel;

import java.util.List;

public class FolderAdpter extends RecyclerView.Adapter<FolderAdpter.ViewHolder> {
    private final AppDatabase db;
    private final AlarmDao alarmDao;
    Context context;
    List<AudioModel> albumList;
    onClickListner setOnClickListener;
    public FolderAdpter(Activity activity, onClickListner onClickListner, List<AudioModel> list) {
        this.context = activity;
        this.albumList = list;
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
//        holder.txtMusicName.setText(albumList.get(position).ALBUM);
//        if (albumList.get(position).data != null) {
//            Bitmap bitmap = BitmapFactory.decodeByteArray(albumList.get(position).data, 0, albumList.get(position).data.length);
//            holder.imageView.setImageBitmap(bitmap);
//        } else {
            String[] directories = mp3Files.get(position).Path.split("/");
            holder.folderName.setText(directories[directories.length-2]);
            holder.folderPath.setText(directories[0]+"/../"+directories[directories.length-2]);
//        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                setOnClickListener.onClick(getAdapterPosition(), albumList);
            });
        }

     //   ImageView imageView = itemView.findViewById(R.id.cardImage);
        TextView folderName = itemView.findViewById(R.id.txtMusicName);
        TextView folderPath = itemView.findViewById(R.id.txtTitleMusic);
       // ImageView imgCover = itemView.findViewById(R.id.imgCover);
    }

    public interface onClickListner {
        void onClick(int position, List<AudioModel> alarmList);
    }




}
