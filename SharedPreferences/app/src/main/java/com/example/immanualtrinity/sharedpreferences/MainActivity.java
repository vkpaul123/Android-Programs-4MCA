package com.example.immanualtrinity.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


public class MainActivity extends AppCompatActivity {

    EditText e,e1,e2;
    String name,mob,home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e=(EditText)findViewById(R.id.editText);
        e1=(EditText)findViewById(R.id.editText2);
        e2=(EditText)findViewById(R.id.editText3);


    }

    public void toSave(View v)
    {
        String x=e.getText().toString();
        String y=e1.getText().toString();
        String z=e2.getText().toString();

        SharedPreferences sharedpreferences = getSharedPreferences("mydata",Context.MODE_PRIVATE);
        sharedpreferences.getString("name","");
        sharedpreferences.getString("mob","");
        sharedpreferences.getString("home","");

        SharedPreferences.Editor ed = sharedpreferences.edit();


        ed.putString("name",x);
        ed.putString("mob",y);
        ed.putString("home",z);

            ed.apply();
            // or
      //  ed.commit();


        Intent i=new Intent(MainActivity.this,Main2Activity.class);
        startActivity(i);

    }
}
