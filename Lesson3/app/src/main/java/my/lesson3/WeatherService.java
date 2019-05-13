package my.lesson3;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class WeatherService extends IntentService {


    private int messageId;

    public WeatherService() {
        super("WeatherService");
        speak("Constructor");
    }

    @Override
    public void onCreate() {
        speak("On Create");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        speak("On Start Command");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        speak("On Bind");
        return super.onBind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        speak("On Unbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        speak("On Destroy");
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        speak("On Handle");
    }

    private void speak(String message) {
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Service")
                .setContentText(message);

        Intent resultIntent = new Intent(this, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(messageId++, builder.build());
    }

}
