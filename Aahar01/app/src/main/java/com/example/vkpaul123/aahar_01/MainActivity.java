package com.example.vkpaul123.aahar_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b2 = (Button) findViewById(R.id.button2);
        b1 = (Button) findViewById(R.id.button1);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Non Veg", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), NonVegActivity.class);
                startActivity(i);
                finish();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Veg", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), VegActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuNonVeg:
                Toast.makeText(getApplicationContext(), "Non Veg", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), NonVegActivity.class);
                startActivity(i);
                finish();

                return true;

            case R.id.menuVeg:
                Toast.makeText(getApplicationContext(), "Veg", Toast.LENGTH_SHORT).show();
                Intent i2 = new Intent(getApplicationContext(), VegActivity.class);
                startActivity(i2);
                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
