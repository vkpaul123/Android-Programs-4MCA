package com.example.vkpaul123.sellerchrist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseProduct extends AppCompatActivity {

    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_product);

        gotoLaptops();
        gotoBooks();
        gotoElectronics();
    }

    public void gotoLaptops() {
        b1 = (Button) findViewById(R.id.b1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ChooseLaptops.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void gotoElectronics() {
        b2 = (Button) findViewById(R.id.b2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ChooseElectronics.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void gotoBooks() {
        b3 = (Button) findViewById(R.id.b3);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ChooseBooks.class);
                startActivity(i);
                finish();
            }
        });
    }
}
