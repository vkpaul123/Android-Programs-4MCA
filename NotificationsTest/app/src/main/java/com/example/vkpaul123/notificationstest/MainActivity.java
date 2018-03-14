package com.example.vkpaul123.notificationstest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

@RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3, b4;

    private  static final int NOTIFICATION_ID = 1;
    private static final String NOTIFICATION_CHANNEL_ID = "my_notifications_channel";
    private static final String KEY_TEXT_REPLY = "key_text_reply";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.simpleButton);
        b2 = (Button) findViewById(R.id.withIconButton);
        b3 = (Button) findViewById(R.id.withImageButton);
        b4 = (Button) findViewById(R.id.withReplyButton);

        clearExistingNotifications();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationsSimple();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationWithIcon();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationWithImage();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                notificationWithReply();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        processInlineReply(intent);

    }

    private void clearExistingNotifications() {
        int notificationId = getIntent().getIntExtra("notificationId", 0);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(notificationId);
    }

    private void processInlineReply(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);

        if (remoteInput != null) {
            String reply = remoteInput.getCharSequence(
                    KEY_TEXT_REPLY).toString();

            //Set the inline reply text in the TextView
            Toast.makeText(getApplicationContext(), reply, Toast.LENGTH_SHORT).show();


            //Update the notification to show that the reply was received.
            Notification.Builder repliedNotification =
                    new Notification.Builder(this)
                            .setSmallIcon(
                                    android.R.drawable.stat_notify_chat)
                            .setContentText("Inline Reply received");

            NotificationManager notificationManager =
                    (NotificationManager)
                            getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(NOTIFICATION_ID,
                    repliedNotification.build());

        }
    }

    public void notificationsSimple() {
        Toast.makeText(getApplicationContext(), "Simple Notification", Toast.LENGTH_SHORT).show();


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Content Title")
                .setChannelId(NOTIFICATION_CHANNEL_ID)
                .setContentText("Hi");

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public void notificationWithIcon() {
        Toast.makeText(getApplicationContext(), "Icon Notification", Toast.LENGTH_SHORT).show();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Content Title - With Icon")
                .setChannelId(NOTIFICATION_CHANNEL_ID)
                .setContentText("Hi. There is an Icon.")
                .setSmallIcon(R.drawable.ic_my_noti_icon);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public void notificationWithImage() {
        Toast.makeText(getApplicationContext(), "Picture Notification", Toast.LENGTH_SHORT).show();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Bitmap myImage = BitmapFactory.decodeResource(getResources(), R.drawable.gripen);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Content Title - With Picture")
                .setChannelId(NOTIFICATION_CHANNEL_ID)
                .setContentText("Hi. There is a Picture.")
                .setSmallIcon(R.drawable.ic_my_noti_icon)
                .setStyle(new NotificationCompat.BigPictureStyle()
                    .bigPicture(myImage));

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void notificationWithReply() {
        Toast.makeText(getApplicationContext(), "With Reply Notification", Toast.LENGTH_SHORT).show();

        //Create notification builder
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setContentTitle("Inline Reply Notification");

        String replyLabel = "Enter your reply here";

        //Initialise RemoteInput
        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel(replyLabel)
                .build();


        int randomRequestCode = new Random().nextInt(54325);

        //PendingIntent that restarts the current activity instance.
        Intent resultIntent = new Intent(this, MainActivity.class);
        //Set a unique request code for this pending intent
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, randomRequestCode, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        //Notification Action with RemoteInput instance added.
        Notification.Action replyAction = new Notification.Action.Builder(
                android.R.drawable.sym_action_chat, "REPLY", resultPendingIntent)
                .addRemoteInput(remoteInput)
                .setAllowGeneratedReplies(true)
                .build();

        //Notification.Action instance added to Notification Builder.
        builder.addAction(replyAction);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("notificationId", NOTIFICATION_ID);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent dismissIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);


        builder.addAction(android.R.drawable.ic_menu_close_clear_cancel, "DISMISS", dismissIntent);

        //Create Notification.
        NotificationManager notificationManager =
                (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(NOTIFICATION_ID,
                builder.build());
    }
}
