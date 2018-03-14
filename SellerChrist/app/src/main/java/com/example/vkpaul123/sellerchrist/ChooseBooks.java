package com.example.vkpaul123.sellerchrist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class ChooseBooks extends AppCompatActivity {

    Button b1;
    CheckBox c2, c3, c4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_books);

        gotoBill();
    }

    public void gotoBill() {
        b1 = (Button) findViewById(R.id.b1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c2 = (CheckBox) findViewById(R.id.checkBox2);
                c3 = (CheckBox) findViewById(R.id.checkBox3);
                c4 = (CheckBox) findViewById(R.id.checkBox4);

                double amt = 0;

                if(c2.isChecked())
                    amt += 100;

                if(c3.isChecked())
                    amt += 200;

                if(c4.isChecked())
                    amt += 150;

                Intent i = new Intent(getApplicationContext(), BillActivity.class);
                i.putExtra("amtSend", amt);
                startActivity(i);
                finish();
            }
        });
    }
}
