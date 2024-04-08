package com.example.screensharingdeceting;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

public class ForegroundService extends Service {

    private static final int FOREGROUND_SERVICE_ID = 101;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(FOREGROUND_SERVICE_ID, createNotification());
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Notification createNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new Notification.Builder(this, "channel_id");
        } else {
            builder = new Notification.Builder(this);
        }

        return builder.setContentTitle("Screen Capture")
                .setContentText("Screen recording is active")
                .setContentIntent(pendingIntent)
                .build();
    }
}
