package com.example.babyinformation.Remainder.reminder;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import com.example.babyinformation.R;
import com.example.babyinformation.Remainder.AddReminderActivity;
import com.example.babyinformation.Remainder.ReminderMain;
import com.example.babyinformation.Remainder.data.AlarmReminderContract;

import java.util.Random;


public class ReminderAlarmService extends IntentService {
    private static final String TAG = ReminderAlarmService.class.getSimpleName();

    private final static String NOTIFICATION_CHANNEL_ID = "1000002";

    Cursor cursor;

    public static PendingIntent getReminderPendingIntent(Context context, Uri uri) {
        Intent action = new Intent(context, ReminderAlarmService.class);
        action.setData(uri);
        return PendingIntent.getService(context, 0, action, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public ReminderAlarmService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Uri uri = intent.getData();

        Intent action = new Intent(this, AddReminderActivity.class);
        action.setData(uri);
        PendingIntent operation = TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(action)
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        if (uri != null) {
            cursor = getContentResolver().query(uri, null, null, null, null);
        }

        String description = "ReminderAlarm!";
        try {
            if (cursor != null && cursor.moveToFirst()) {
                description = AlarmReminderContract.getColumnString(cursor, AlarmReminderContract.AlarmReminderEntry.KEY_TITLE);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        NotificationCompat.Builder notificationBuilder;
        NotificationManager notificationManager = (NotificationManager) this.getApplicationContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notificationBuilder = new NotificationCompat.Builder(this.getApplicationContext())
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle(getString(R.string.reminder_title))
                    .setContentText(description)
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .setAutoCancel(true)
                    .setContentIntent(operation);
        } else {
            notificationBuilder = new NotificationCompat.Builder(this.getApplicationContext())
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle(getString(R.string.reminder_title))
                    .setContentText(description)
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .setAutoCancel(true)
                    .setContentIntent(operation);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel;
            notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert notificationManager != null;
            notificationBuilder.setContentTitle(getString(R.string.reminder_title));
            notificationBuilder.setContentText(description);
            notificationBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        Random random = new Random();
        int notificationID = random.nextInt(9999 - 1000) + 1000;
        notificationManager.notify(notificationID, notificationBuilder.build());

    }
}





