package com.example.vkpaul123.sellerchrist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BillActivity extends AppCompatActivity {

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        TextView amt = (TextView) findViewById(R.id.amt);
        Intent i = getIntent();

        Double amtShow = i.getDoubleExtra("amtSend", 0);
        String amtShowStr = amtShow.toString();
        amt.setText(amtShowStr);

        gotoHome();
    }

    public void gotoHome() {
        b1 = (Button) findViewById(R.id.b1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
