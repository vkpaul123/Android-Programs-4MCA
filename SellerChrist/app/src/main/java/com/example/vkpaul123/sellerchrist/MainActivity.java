package com.example.vkpaul123.sellerchrist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1;

    EditText sregno, sname, semail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        continueToNext();
    }

    public void continueToNext() {
        b1 = (Button) findViewById(R.id.b1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sregno = (EditText) findViewById(R.id.sregno);
                sname = (EditText) findViewById(R.id.sname);
                semail = (EditText) findViewById(R.id.semail);

                if(sregno.getText().length() != 7)
                    sregno.setError("Please Enter Regno");
                else if(sname.getText().length() < 3)
                    sname.setError("Please Enter proper Name");
                else if(semail.getText().length() < 5)
                    semail.setError("Please Enter Email");
                else {
                    Intent i = new Intent(getApplicationContext(), ChooseProduct.class);
                    startActivity(i);
                    finish();
                }

            }
        });
    }
}
