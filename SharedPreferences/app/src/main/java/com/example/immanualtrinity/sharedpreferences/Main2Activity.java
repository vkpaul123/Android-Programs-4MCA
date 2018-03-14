package com.example.immanualtrinity.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class Main2Activity extends AppCompatActivity {

    TextView t;
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        t=(TextView)findViewById(R.id.textView);

      /*  if(savedInstanceState!=null)
        {
        */
            sharedpreferences = getSharedPreferences("mydata", Context.MODE_PRIVATE);

            t.setText("Hi "+sharedpreferences.getString("name","")+"\n\nYour Mobile Number: "+sharedpreferences.getString("mob","")+"\n\nHome Town: "+sharedpreferences.getString("home",""));

    //    }
    }
}
