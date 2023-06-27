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

import com.demo.example.FavoriteDb.Alarm;
import com.demo.example.FavoriteDb.AlarmDao;
import com.demo.example.FavoriteDb.AppDatabase;
import com.demo.example.R;
import com.demo.example.models.AlbumModel;
import com.demo.example.models.AudioModel;

import java.util.List;

public class AlbumAdpter extends RecyclerView.Adapter<AlbumAdpter.ViewHolder> {

    Context context;
    List<AudioModel> albumList;
    onClickListner setOnClickListener;
//    public AlbumAdpter(Activity activity, onClickListner onClickListner, List<AudioModel> list) {
//        List<AlbumModel> albumList;
//        onClickListner setOnClickListener;
//
//    }
    public AlbumAdpter(Activity activity, onClickListner onClickListner, List<AudioModel> list) {
        this.context = activity;
        this.albumList = list;
        this.setOnClickListener = onClickListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txtAlbum.setText(albumList.get(position).Album);
            if (albumList.get(position).data != null) {
              Bitmap  bitmap = BitmapFactory.decodeByteArray(albumList.get(position).data, 0, albumList.get(position).data.length);
                holder.imageView.setImageBitmap(bitmap);
            } else {
                holder.imageView.setImageResource(R.drawable.ic_baseline_album_24);
            }

    }

        @Override
        public int getItemCount () {
            return albumList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                itemView.setOnClickListener(v -> {
                    setOnClickListener.onClick(getAdapterPosition(), albumList);
                });
            }

            ImageView imageView = itemView.findViewById(R.id.cardImage);
            TextView txtAlbum = itemView.findViewById(R.id.txtAlbum);
        }

         public interface onClickListner {
            void onClick(int position, List<AudioModel> alarmList);
        }

}
