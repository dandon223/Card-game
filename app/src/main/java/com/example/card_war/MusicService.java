package com.example.card_war;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    private MediaPlayer player;
    public MusicService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        player = MediaPlayer.create(this,R.raw.song);
        player.setLooping(true);
        player.setVolume(0.4f,0.4f);
        player.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String text = intent.getStringExtra(MainActivity.MUSIC_STOP);
        if(text!=null && text.equals("stop"))
            player.pause();
        else if(text!=null && text.equals("play"))
            player.start();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
    }


}
