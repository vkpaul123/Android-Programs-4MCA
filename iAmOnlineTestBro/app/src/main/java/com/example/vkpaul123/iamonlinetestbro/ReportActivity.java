package com.example.vkpaul123.iamonlinetestbro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReportActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        btn = (Button) findViewById(R.id.buttonNew);

        Intent i = getIntent();
        int totScore = i.getIntExtra("currScore", 0);
        int wrong = i.getIntExtra("wrong", 0);
        int noAtt = i.getIntExtra("noAtt", 0);

        TextView totalScore = (TextView) findViewById(R.id.totalScore);
        totalScore.setText(new Integer(totScore).toString() + " / 15");

        TextView attemptedQuestions = (TextView) findViewById(R.id.attemptedQuestions);
        attemptedQuestions.setText(new Integer(5 - noAtt).toString() + " / 5");

        TextView wrongQuestions = (TextView) findViewById(R.id.wrongQuestions);
        wrongQuestions.setText(new Integer(wrong).toString() + " / 5");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(i);
                finish();
            }
        });
    }
}
