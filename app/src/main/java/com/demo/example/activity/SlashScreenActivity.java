package com.demo.example.activity;
import static com.demo.example.lib.Contants.PERMISSIONS;
import static com.demo.example.lib.Contants.getAlbumFileList;
import static com.demo.example.lib.Contants.hasPermissions;
import static com.demo.example.lib.Contants.scanDeviceForMp3Files;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.androidpoet.metaphor.MetaphorAnimation;
import com.androidpoet.metaphor.MetaphorFragment;
import com.bumptech.glide.Glide;
import com.demo.example.R;
import com.demo.example.adapter.ViewPagerAdapter;
import com.demo.example.databinding.ActivityMainBinding;
import com.demo.example.databinding.ActivitySlashScreenBinding;
import com.demo.example.fragment.AlbumsFragmet;
import com.demo.example.fragment.ArtistsFragment;
import com.demo.example.fragment.FolderFragmet;
import com.demo.example.fragment.PlaylistsFragmet;
import com.demo.example.fragment.TracksFragment;
import com.demo.example.models.AlbumModel;
import com.demo.example.models.AudioModel;

import java.util.List;

public class SlashScreenActivity extends AppCompatActivity  {
    ActivitySlashScreenBinding binding;
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        binding = ActivitySlashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
//        Glide.with(this).load(R.drawable.ic__music_24).into(binding.logo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SlashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);
    }
}
