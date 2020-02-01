package com.example.card_war;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    static final String MUSIC_STOP = "com.example.MUSIC_STOP";
    private boolean isMusic;

    @Override
    protected void onDestroy() {
        Intent musicService=  new Intent(this, MusicService.class);
        if(isMyServiceRunning(MusicService.class))
            stopService(musicService);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Intent musicService=  new Intent(this, MusicService.class);

        if(!isMyServiceRunning(MusicService.class)){
            isMusic = true;
            startService(musicService);
        }
    }
    public void newGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    public void changingMusic(View view){
        ImageButton musicButton =(ImageButton) findViewById(R.id.musicButton);
        Intent intent = new Intent(this, MusicService.class);
        if(isMusic){
            musicButton.setImageResource(android.R.drawable.ic_media_play);
            intent.putExtra(MUSIC_STOP , "stop");
            isMusic = false;
        }
        else{
            isMusic = true;
            musicButton.setImageResource(android.R.drawable.ic_media_pause);
            intent.putExtra(MUSIC_STOP , "play");
        }
        startService(intent);
    }


}
