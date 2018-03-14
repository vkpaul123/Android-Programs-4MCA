package com.example.vkpaul123.myconference;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView t = (TextView) findViewById(R.id.textView5);
        String []recieveArray = getIntent().getStringArrayExtra("sendArray");
        String showText = "";
        for(int i=0; i<recieveArray.length; i++)
            showText += (recieveArray[i] + " ");

        t.setText(showText);
    }

    public void gotoRegister(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
