package com.example.vkpaul123.aahar_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    Button b1;

    TextView t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        b1 = (Button) findViewById(R.id.confirm);

        t1 = (TextView) findViewById(R.id.itemsAll);
        t2 = (TextView) findViewById(R.id.totalPrice);

        Intent i = getIntent();

        Double amtShow = i.getDoubleExtra("amtSend", 0);
        String amtShowStr = amtShow.toString();
        t2.setText(amtShowStr);

        String itemsAllOfThem = i.getStringExtra("itemsArr");
        t1.setText(itemsAllOfThem);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Confirm", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
