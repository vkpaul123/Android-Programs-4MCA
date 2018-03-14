package com.example.immanualtrinity.trinitysms;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    Button sendBtn,B2;
    EditText txtphoneNo,txtMessage;
    TextView mCounter;
    String message,phoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        B2 = (Button) findViewById(R.id.button);
        sendBtn = (Button) findViewById(R.id.button2);
        txtphoneNo = (EditText) findViewById(R.id.editText2);
        txtMessage = (EditText) findViewById(R.id.editText3);




        B2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage();
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMS2();
            }
        });
    }


    public void sendSMSMessage() {
        phoneNo = txtphoneNo.getText().toString();
        message = txtMessage.getText().toString();

        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage(phoneNo, null, message, pi,null);

        Toast.makeText(getApplicationContext(), "Message Sent successfully!",Toast.LENGTH_LONG).show();


    }


    public void sendSMS2() {

        String message = txtMessage.getText().toString();
        String phoneNo = txtphoneNo.getText().toString();
        if(!TextUtils.isEmpty(message) && !TextUtils.isEmpty(phoneNo)) {
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNo));
            smsIntent.putExtra("sms_body", message);
            startActivity(smsIntent);
        }
    }


}


