package dk.easj.anbo.unboundserviceexample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

/**
 * Android Notes for Professionals, chapter 18
 */
public class RecordingService extends Service {
    private int NOTIFICATION = 1; // Unique identifier for our notification
    public static boolean isRunning = false;
    public static RecordingService instance = null;
    private NotificationManager notificationManager = null;

    public RecordingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onCreate() {
        instance = this;
        isRunning = true;
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service started", Toast.LENGTH_LONG).show();
        // The PendingIntent to launch our activity if the user selects this notification
        Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://anbo-easj.dk/"));
        // Intend intent1 new Intent(this, MainActivity.class);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent1, 0);
        // Set the info for the views that show in the notification panel.
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher) // the status icon
                .setTicker("Service running...") // the status text
                .setWhen(System.currentTimeMillis()) // the time stamp
                .setContentTitle("My App") // the label of the entry
                .setContentText("Service running...") // the content of the entry
                .setContentIntent(contentIntent) // the intent to send when the entry is clicked
                .setOngoing(true) // make persistent (disable swipe-away)
                .build();
        // Start service in foreground mode
        startForeground(NOTIFICATION, notification);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        isRunning = false;
        instance = null;
        notificationManager.cancel(NOTIFICATION); // Remove notification
        super.onDestroy();
    }

    public void doSomething(CharSequence message) {
        Toast.makeText(getApplicationContext(), "Doing stuff from service..." + message,
                Toast.LENGTH_SHORT).show();
    }
}
