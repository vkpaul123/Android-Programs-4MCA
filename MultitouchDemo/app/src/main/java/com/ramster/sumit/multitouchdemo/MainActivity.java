package com.ramster.sumit.multitouchdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button one,two,three,four;long lastDown;
    long lastDuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one = (Button)findViewById(R.id.button);
        two = (Button)findViewById(R.id.button2);
        three = (Button)findViewById(R.id.button3);
        four = (Button)findViewById(R.id.button4);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);


        LinearLayout ll;
        ll=(LinearLayout)findViewById(R.id.linear);
        /*ll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int index = motionEvent.getActionIndex();
                int pointerId = motionEvent.getPointerId(index);
                  view.onTouchEvent(motionEvent);

                Toast.makeText(getApplicationContext(),index+""+pointerId,Toast.LENGTH_SHORT).show();
                return false;
            }
        });*/
        one.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    lastDown = System.currentTimeMillis();
                    one.setText(lastDown+"");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    lastDuration = System.currentTimeMillis() - lastDown;
                    one.setText(lastDown+"");
                }

                return true;
            }
        });

        two.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    lastDown = System.currentTimeMillis();
                    two.setText(lastDown+"");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    lastDuration = System.currentTimeMillis() - lastDown;
                    two.setText(lastDown+"");
                }

                return true;
            }
        });
        three.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    lastDown = System.currentTimeMillis();
                    three.setText(lastDown+"");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    lastDuration = System.currentTimeMillis() - lastDown;
                    three.setText(lastDown+"");
                }

                return true;
            }
        });
        four.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    lastDown = System.currentTimeMillis();
                    four.setText(lastDown+"");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    lastDuration = System.currentTimeMillis() - lastDown;
                    four.setText(lastDown+"");
                }

                return true;
            }
        });

    }

    @Override
    public void onClick(View view) {
    if(view.getId()==R.id.button){
        one.setText("touch");
    }

        if(view.getId()==R.id.button2){
            two.setText("touch");
        }

        if(view.getId()==R.id.button3){
            three.setText("touch");
        }

        if(view.getId()==R.id.button4){
            four.setText("touch");
        }

    }
}
