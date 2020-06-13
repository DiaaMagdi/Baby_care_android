package com.example.babyinformation;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class TheVaccination extends Application {
    public static final String CHANNEL_1 = "channel 1";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotChannels();
    }

    private void createNotChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_1,
                    "channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("this is channel 1");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
