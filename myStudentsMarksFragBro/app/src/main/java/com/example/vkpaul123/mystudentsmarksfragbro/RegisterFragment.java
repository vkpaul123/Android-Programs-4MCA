package com.example.vkpaul123.mystudentsmarksfragbro;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.example.vkpaul123.mystudentsmarksfragbro.StudentContract.FeedEntry.col2;
import static com.example.vkpaul123.mystudentsmarksfragbro.StudentContract.FeedEntry.col3;
import static com.example.vkpaul123.mystudentsmarksfragbro.StudentContract.FeedEntry.col4;
import static com.example.vkpaul123.mystudentsmarksfragbro.StudentContract.FeedEntry.col5;
import static com.example.vkpaul123.mystudentsmarksfragbro.StudentContract.FeedEntry.col6;
import static com.example.vkpaul123.mystudentsmarksfragbro.StudentContract.FeedEntry.col7;
import static com.example.vkpaul123.mystudentsmarksfragbro.StudentContract.FeedEntry.col8;
import static com.example.vkpaul123.mystudentsmarksfragbro.StudentContract.FeedEntry.col9;
import static com.example.vkpaul123.mystudentsmarksfragbro.StudentContract.FeedEntry.tableName;

public class RegisterFragment extends Fragment {
    EditText studName;
    EditText studParent;
    EditText studMarks1;
    EditText studMarks2;
    EditText studMarks3;
    EditText studMarks4;

    TextView allotedID;

    Button btnCalc, btnDisplay;

    DBHelper dbHelper;

    String sName, sParentPhNo, sMarks1, sMarks2, sMarks3, sMarks4;

    private  static final int NOTIFICATION_ID = 1;
    private static final String NOTIFICATION_CHANNEL_ID = "my_notifications_channel";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        dbHelper = new DBHelper(getContext());

        studName = view.findViewById(R.id.editTextStudName);
        studParent = view.findViewById(R.id.editTextParentPhNo);

        studMarks1 = view.findViewById(R.id.editTextSub1);
        studMarks2 = view.findViewById(R.id.editTextSub2);
        studMarks3 = view.findViewById(R.id.editTextSub3);
        studMarks4 = view.findViewById(R.id.editTextSub4);

        btnCalc = view.findViewById(R.id.buttonCalculate);

        allotedID = view.findViewById(R.id.studAllotedID);

        btnDisplay = view.findViewById(R.id.buttonDisplay);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sName = studName.getText().toString();
                sParentPhNo = studParent.getText().toString();
                sMarks1 = studMarks1.getText().toString();
                sMarks2 = studMarks2.getText().toString();
                sMarks3 = studMarks3.getText().toString();
                sMarks4 = studMarks4.getText().toString();

                SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put(col2, sName);
                contentValues.put(col3, sParentPhNo);
                contentValues.put(col4, sMarks1);
                contentValues.put(col5, sMarks2);
                contentValues.put(col6, sMarks3);
                contentValues.put(col7, sMarks4);

                float tot = Float.parseFloat(sMarks1) +
                        Float.parseFloat(sMarks2) +
                        Float.parseFloat(sMarks3) +
                        Float.parseFloat(sMarks4);

                float perc = tot*0.25f;

                contentValues.put(col8, new Float(tot).toString());
                contentValues.put(col9, new Float(perc).toString());

                long result = sqLiteDatabase.insert(tableName, null, contentValues);

                if(result == -1)
                    Toast.makeText(getContext(), "Data Not Inserted", Toast.LENGTH_SHORT).show();
                else {
                    allotedID.setText(new Long(result).toString());
                    Toast.makeText(getContext(), "DATA  INSERTED " + result, Toast.LENGTH_LONG).show();
                }

                if (perc > 75f) {
                    NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);

                    Bitmap myImage = BitmapFactory.decodeResource(getResources(), R.drawable.good);

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);
                        notificationManager.createNotificationChannel(notificationChannel);
                    }

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity().getApplicationContext(), NOTIFICATION_CHANNEL_ID)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Student Report")
                            .setChannelId(NOTIFICATION_CHANNEL_ID)
                            .setContentText("Good Work, " + sName + ". Your Percentage is " + new Float(perc).toString() + "%.")
                            .setSmallIcon(R.drawable.ic_my_noti_icon)
                            .setStyle(new NotificationCompat.BigPictureStyle()
                                    .bigPicture(myImage));

                    Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                    builder.setContentIntent(pendingIntent);

                    Notification notification = builder.build();

                    notificationManager.notify(NOTIFICATION_ID, builder.build());
                } else if (perc < 50f) {
                    String phoneNo = sParentPhNo;
                    String message = "Dear Parent, Your Student, " + sName + ", has performed below satisfactory level, with " + new Float(perc).toString() + "%.";

                    Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                    intent.putExtra("studID1", allotedID.getText().toString());
                    PendingIntent pi = PendingIntent.getActivity(getActivity(), 0, intent,0);

                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(phoneNo, null, message, pi,null);

                    allotedID.setText(new Long(result).toString());

                    Toast.makeText(getActivity(), "Message Sent successfully!",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sID = allotedID.getText().toString();

                if(sID.equals("") == false) {
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    Bundle args = new Bundle();

                    args.putString("studID", sID);

                    MarksFragment blankFragment = new MarksFragment();
                    blankFragment.setArguments(args);

                    fragmentTransaction.replace(R.id.fragment_container, blankFragment);
                    fragmentTransaction.commit();
                }
                else
                    Toast.makeText(getActivity().getApplicationContext(), "Please Register Student First!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Create Student Record");
    }

    @Override
    public void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
