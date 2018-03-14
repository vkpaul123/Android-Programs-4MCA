package com.example.vkpaul123.firstproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private CheckBox check1, check2, check3, check4;
    private Button button1;
    private double total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        addListenerOnButton();
    }

    public void addListenerOnButton() {
        check1 = (CheckBox) findViewById(R.id.checkBoxApple);
        check2 = (CheckBox) findViewById(R.id.checkBoxBanana);
        check3 = (CheckBox) findViewById(R.id.checkBoxCarrot);
        check4 = (CheckBox) findViewById(R.id.checkBoxMango);

        button1 = (Button) findViewById(R.id.buttonCalc);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check1.isChecked())
                    total += 10;

                if (check2.isChecked())
                    total += 20;

                if(check3.isChecked())
                    total += 30;

                if(check4.isChecked())
                    total += 40;

                TextView textViewTotal = (TextView) findViewById(R.id.textViewTotalShow);
                textViewTotal.setText("Rs. " + new Double(total).toString() + "0/-");
                total = 0;
            }
        });
    }

}
