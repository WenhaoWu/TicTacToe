package com.excercise.wenhaowu.tictactoe_1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;

/**
 * Created by wenhaowu on 16/07/15.
 */
public class Bgm_Service extends Service {

    MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.sound);
        player.setLooping(true);
        player.setVolume(100, 100);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return 1;
    }

    public void onStop(){
        player.stop();
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }


}
