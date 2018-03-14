package com.example.vkpaul123.fragmentsapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button frag1, frag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frag1 = (Button) findViewById(R.id.button1);
        frag2 = (Button) findViewById(R.id.button2);

        listeners();
    }

    public void listeners() {
        frag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                BlankFragment blankFragment = new BlankFragment();

                fragmentTransaction.replace(R.id.fragment_container, blankFragment);
                fragmentTransaction.commit();
                Toast.makeText(getApplicationContext(), "Frag 1", Toast.LENGTH_LONG).show();
            }
        });

        frag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                BlankFragment2 blankFragment2 = new BlankFragment2();

                fragmentTransaction.replace(R.id.fragment_container, blankFragment2);
                fragmentTransaction.commit();
                Toast.makeText(getApplicationContext(), "Frag 2", Toast.LENGTH_LONG).show();
            }
        });
    }
}
