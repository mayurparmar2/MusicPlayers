package com.demo.example.activity;
import static com.demo.example.lib.Contants.PERMISSIONS;
import static com.demo.example.lib.Contants.hasPermissions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentTransaction;

import com.demo.example.R;
import com.demo.example.adapter.TracksAdpter;
import com.demo.example.adapter.ViewPagerAdapter;
import com.demo.example.databinding.ActivityMainBinding;
import com.demo.example.fragment.AlbumsFragmet;
import com.demo.example.fragment.ArtistsFragment;
import com.demo.example.fragment.FavoritesFragment;
import com.demo.example.fragment.FolderFragmet;
import com.demo.example.fragment.PlaylistsFragmet;
import com.demo.example.fragment.TracksFragment;
import com.demo.example.models.AudioModel;
import com.google.android.material.tabs.TabLayout;

import java.util.List;


public class MainActivity extends AppCompatActivity  {
    ActivityMainBinding binding;
    private int PERMISSION_ALL = 1;
    private MenuItem mSearchItem;
    private View mToolbar;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//            Toolbar toolbar =  findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setIcon(R.drawable.ic_music_);
//        getSupportActionBar().setTitle("Music");
//        mToolbar =toolbar;
        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

            viewPagerAdapter.add(new PlaylistsFragmet(), "Playlists");
            viewPagerAdapter.add(new PlaylistsFragmet(), "Playlists");
            viewPagerAdapter.add(new TracksFragment(), "Tracks");
            viewPagerAdapter.add(new AlbumsFragmet(), "Albums");
            viewPagerAdapter.add(new ArtistsFragment(), "Artists");
            viewPagerAdapter.add(new FolderFragmet(), "Folder");

            binding.ViewPager.setAdapter(viewPagerAdapter);
            binding.animTab.setupWithViewPager(binding.ViewPager);
    }

}
