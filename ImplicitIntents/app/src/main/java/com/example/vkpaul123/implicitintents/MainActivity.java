package com.example.vkpaul123.implicitintents;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3, b4;
    EditText to, subject, text, phno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnB1();
        addListenerOnB2();
        addListenerOnB3();
        addListenerOnB4();
    }

    //  Google Maps
    public void addListenerOnB1() {
        b1 = (Button) findViewById(R.id.button1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("geo: 12.972442, 77.580643"));

                Intent chooser = Intent.createChooser(i, "Launch Maps");
                startActivity(chooser);
            }
        });
    }

    //  Call
    public void addListenerOnB2() {
        b2 = (Button) findViewById(R.id.button2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);

                phno = (EditText) findViewById(R.id.editPHno);

                String phNoText = phno.getText().toString();

                if(phNoText.trim().isEmpty())
                    i.setData(Uri.parse("tel:8296765476"));
                else
                    i.setData(Uri.parse("tel:" + phNoText));

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(getApplicationContext(),"Please grant the permission to call", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    startActivity(i);
                }
            }

        });
    }

    //  Send Email
    public void addListenerOnB3() {
        b3 = (Button) findViewById(R.id.button3);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to = (EditText) findViewById(R.id.emailTo);
                subject = (EditText) findViewById(R.id.editSubject);
                text = (EditText) findViewById(R.id.editEmailText);

                String []sendTo = {to.getText().toString()};
                String subjectText = subject.getText().toString();
                String messageText = text.getText().toString();

                Intent i = new Intent(Intent.ACTION_SEND);

                i.putExtra(Intent.EXTRA_EMAIL, sendTo);
                i.putExtra(Intent.EXTRA_SUBJECT, subjectText);
                i.putExtra(Intent.EXTRA_TEXT, messageText);
                i.setType("message/rfc822");

                Intent chooser = Intent.createChooser(i, "Send EMail");
                startActivity(chooser);
            }
        });
    }

    //  Browser
    public void addListenerOnB4() {
        b4 = (Button) findViewById(R.id.button4);

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://medium.com/";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));

                startActivity(i);
            }
        });
    }
}
