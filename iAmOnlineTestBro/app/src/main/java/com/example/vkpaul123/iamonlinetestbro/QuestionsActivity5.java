package com.example.vkpaul123.iamonlinetestbro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class QuestionsActivity5 extends AppCompatActivity {

    Button btn;
    RadioGroup radGrp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions5);

        btn = (Button) findViewById(R.id.button5);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radGrp = (RadioGroup) findViewById(R.id.radioGroup5);
                int selectedId = radGrp.getCheckedRadioButtonId();
                Intent i2 = getIntent();

                int score = i2.getIntExtra("currScore", 0);
                int wrong = i2.getIntExtra("wrong", 0);
                int noAtt = i2.getIntExtra("noAtt", 0);

                if(selectedId == R.id.radioButtonMeat)
                    score+=3;
                else if(selectedId == R.id.radioButtonmili || selectedId == R.id.radioButton300 || selectedId==R.id.radioButton657)
                    wrong++;
                else
                    noAtt++;

                Intent i = new Intent(getApplicationContext(), ReportActivity.class);
                i.putExtra("currScore", score);
                i.putExtra("wrong", wrong);
                i.putExtra("noAtt", noAtt);
                startActivity(i);
                finish();
            }
        });
    }
}
