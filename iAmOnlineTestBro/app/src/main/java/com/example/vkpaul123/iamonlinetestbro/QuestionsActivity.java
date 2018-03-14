package com.example.vkpaul123.iamonlinetestbro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuestionsActivity extends AppCompatActivity {

    Button btn;
    RadioGroup radGrp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        btn = (Button) findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radGrp = (RadioGroup) findViewById(R.id.radioGroup1);
                int selectedId = radGrp.getCheckedRadioButtonId();
                int score=0;
                int wrong=0;
                int noAtt=0;

                if(selectedId == R.id.radioButtonBlue)
                    score+=3;
                else if(selectedId == R.id.radioButtonGreen || selectedId == R.id.radioButtonRed || selectedId == R.id.radioButtonYellow)
                    wrong++;
                else
                    noAtt++;

                Intent i = new Intent(getApplicationContext(), QuestionsActivity2.class);
                i.putExtra("currScore", score);
                i.putExtra("wrong", wrong);
                i.putExtra("noAtt", noAtt);

                startActivity(i);
                finish();
            }
        });
    }
}
