package com.example.vkpaul123.vikramcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class CalcMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_main);
    }

    public void addButtonClicked(View view) {
        EditText t1 = (EditText) findViewById(R.id.editText3);
        EditText t2 = (EditText) findViewById(R.id.editText4);

        TextView tvAdd = (TextView) findViewById(R.id.textView10);

        try {
            double num1 = Double.parseDouble(t1.getText().toString());
            double num2 = Double.parseDouble(t2.getText().toString());

            double sum = num1 + num2;

            tvAdd.setText(Double.toString(sum));
        }catch (Exception e) {
            Toast.makeText(CalcMain.this, "Exception Occoured! " + e, Toast.LENGTH_LONG).show();
        }finally {
            Toast.makeText(CalcMain.this, "Answer Calculated.", Toast.LENGTH_LONG).show();
        }
    }

    public void subButtonClicked(View view) {
        EditText t1 = (EditText) findViewById(R.id.editText3);
        EditText t2 = (EditText) findViewById(R.id.editText4);

        TextView tvSub = (TextView) findViewById(R.id.textView11);

        try {
            double num1 = Double.parseDouble(t1.getText().toString());
            double num2 = Double.parseDouble(t2.getText().toString());

            double sum = num1 - num2;

            tvSub.setText(Double.toString(sum));
        }catch (Exception e) {
            Toast.makeText(CalcMain.this, "Exception Occoured! " + e, Toast.LENGTH_LONG).show();
        }finally {
            Toast.makeText(CalcMain.this, "Answer Calculated.", Toast.LENGTH_LONG).show();
        }
    }

    public void multButtonClicked(View view) {
        EditText t1 = (EditText) findViewById(R.id.editText3);
        EditText t2 = (EditText) findViewById(R.id.editText4);

        TextView tvMult = (TextView) findViewById(R.id.textView12);


        try {
            double num1 = Double.parseDouble(t1.getText().toString());
            double num2 = Double.parseDouble(t2.getText().toString());

            double sum = num1 * num2;

            tvMult.setText(Double.toString(sum));
        }catch (Exception e) {
            Toast.makeText(CalcMain.this, "Exception Occoured! " + e, Toast.LENGTH_LONG).show();
        }finally {
            Toast.makeText(CalcMain.this, "Answer Calculated.", Toast.LENGTH_LONG).show();
        }
    }

    public void divButtonClicked(View view) {
        EditText t1 = (EditText) findViewById(R.id.editText3);
        EditText t2 = (EditText) findViewById(R.id.editText4);

        TextView tvDiv = (TextView) findViewById(R.id.textView13);

        try {
            double num1 = Double.parseDouble(t1.getText().toString());
            double num2 = Double.parseDouble(t2.getText().toString());

            double sum = num1 / num2;

            tvDiv.setText(Double.toString(sum));
        }catch (Exception e) {
            Toast.makeText(CalcMain.this, "Exception Occoured! " + e, Toast.LENGTH_LONG).show();
        }finally {
            Toast.makeText(CalcMain.this, "Answer Calculated.", Toast.LENGTH_LONG).show();
        }
    }
}
