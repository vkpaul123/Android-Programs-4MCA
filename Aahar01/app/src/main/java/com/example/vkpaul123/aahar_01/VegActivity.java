package com.example.vkpaul123.aahar_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class VegActivity extends AppCompatActivity {

    Button btn1;
    CheckBox ch1, ch2, ch3;
    TextView t1, t2;
    Spinner sp1,sp2,sp3;

    String []arr = {"1", "2", "3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veg);

        btn1 = (Button) findViewById(R.id.buttonBillVeg);
        ch1 = (CheckBox) findViewById(R.id.checkBoxRice);
        ch2 = (CheckBox) findViewById(R.id.checkBoxRoti);
        ch3 = (CheckBox) findViewById(R.id.checkBoxPotato);
        t1 = (TextView) findViewById(R.id.subTotalVeg);
        t2 = (TextView) findViewById(R.id.currItemsVeg);

        sp1 = (Spinner) findViewById(R.id.spinnerRice);
        sp2 = (Spinner) findViewById(R.id.spinnerRoti);
        sp3 = (Spinner) findViewById(R.id.spinnerPotato);

        ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, arr);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp1.setAdapter(aa);
        sp2.setAdapter(aa);
        sp3.setAdapter(aa);

        Intent i = getIntent();
        String subTot = i.getStringExtra("amtInter");
        String currItems = i.getStringExtra("itemsArr");
        if(subTot == null)
            t1.setText("0");
        else
            t1.setText(subTot);

        if(currItems == null)
            t2.setText(t2.getText() + "");
        else
            t2.setText(t2.getText() + currItems);

        ch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    double amt = Double.parseDouble(t1.getText().toString());
                    String items = t2.getText().toString();

                    amt += 30;
                    items += "\nRice\t30";

                    t1.setText(new Double(amt).toString());
                    t2.setText(items);
                }
            }
        });

        ch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    double amt = Double.parseDouble(t1.getText().toString());
                    String items = t2.getText().toString();

                    amt += 5;
                    items += "\nRoti\t5";
                    t1.setText(new Double(amt).toString());
                    t2.setText(items);
                }
            }
        });

        ch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    double amt = Double.parseDouble(t1.getText().toString());
                    String items = t2.getText().toString();

                    amt += 50;
                    items += "\nPotato\t50";
                    t1.setText(new Double(amt).toString());
                    t2.setText(items);
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String items = t2.getText().toString();

                double amt = Double.parseDouble(t1.getText().toString());

                Toast.makeText(getApplicationContext(), "Order", Toast.LENGTH_SHORT).show();
                Intent i3 = new Intent(getApplicationContext(), OrderActivity.class);
                i3.putExtra("amtSend", amt);
                i3.putExtra("itemsArr", items);
                startActivity(i3);
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
                i.putExtra("amtInter", t1.getText());
                i.putExtra("itemsArr", t2.getText());
                startActivity(i);
                finish();

                return true;

            case R.id.menuVeg:
                Toast.makeText(getApplicationContext(), "Veg", Toast.LENGTH_SHORT).show();
                Intent i2 = new Intent(getApplicationContext(), VegActivity.class);
                i2.putExtra("amtInter", t1.getText());
                i2.putExtra("itemsArr", t2.getText());
                startActivity(i2);
                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
