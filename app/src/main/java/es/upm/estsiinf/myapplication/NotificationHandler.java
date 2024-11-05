package es.upm.estsiinf.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

public class NotificationHandler extends ContextWrapper {
    private NotificationManager notifManager;
    private final String GROUP_NAME = "GRUPO";
    public final int GROUP_ID = 111;

    public NotificationHandler(Context base) {
        super(base);

        // Crea el canal de notificacion
        NotificationChannel channel = new NotificationChannel("0","INICIO-CHANNEL",NotificationManager.IMPORTANCE_HIGH);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

        getManager();
        notifManager.createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (notifManager == null) {
            notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return notifManager;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder crearNotificationBuilder(String titulo, String msg) {
        Bitmap bitmapLogo = BitmapFactory.decodeResource(getResources(), R.id.fav_logo);

        return new Notification.Builder(getApplicationContext(), "0")
                .setContentTitle(titulo)
                .setContentText(msg)
                .setSmallIcon(R.drawable.ic_notif)
                .setGroup(GROUP_NAME)
                .setAutoCancel(true)
                .setLargeIcon(bitmapLogo)
                .setStyle(new Notification.BigTextStyle().bigText(msg))
                .setStyle(new Notification.BigPictureStyle().bigPicture(bitmapLogo).bigLargeIcon((Bitmap) null));
    }

    public void publishGroup() {
        Notification groupNotification = new Notification.Builder(getApplicationContext(), "0")
                .setGroup(GROUP_NAME)
                .setGroupSummary(true)
                .setSmallIcon(R.drawable.ic_notif).build();

        getManager().notify(GROUP_ID, groupNotification);
    }

}
