package com.example.vkpaul123.colorful;

import android.graphics.Color;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ConstraintLayout constraintLayout;

    Handler handler;

    int colorSwitch=0;

    private GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView1);
        constraintLayout = (ConstraintLayout) findViewById(R.id.layout1);

        SwipeGestureDetector simpleGestureListener = new SwipeGestureDetector();
        simpleGestureListener.setListener(new SwipeGestureDetector.Listener() {
            @Override
            public void onScrollHorizontal(float dx) {
                Log.e("Horizontal","horizontal = " +dx);
            }

            @Override
            public void onScrollVertical(float dy) {
                Log.e("Vertical","horizontal = " +dy);
                if(dy > 0) {
                    textView.setText("Swipe Up");
                    constraintLayout.setBackgroundColor(Color.GRAY);
                } else {
                    textView.setText("Swipe Down");
                    constraintLayout.setBackgroundColor(Color.DKGRAY);
                }
            }
        });
        mDetector = new GestureDetector(this, simpleGestureListener);

        handler = new Handler();
        handler.postDelayed(runnable, 5000);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            colorSwitch++;
            if(colorSwitch == 5)
                colorSwitch = 0;

            sendToast(colorSwitch);

            handler.postDelayed(this, 5000);
        }
    };

    public void sendToast(int colorSwitch) {
        Toast toast = Toast.makeText(getApplicationContext(), "I am a Helicopter!", Toast.LENGTH_SHORT);

        View toastView = toast.getView();

        TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
        toastMessage.setTextSize(25);
        toastMessage.setTextColor(Color.WHITE);

        toastMessage.setGravity(Gravity.CENTER);

        switch (colorSwitch) {
            case 1:
                toastView.setBackgroundColor(Color.RED);
                break;
            case 2:
                toastView.setBackgroundColor(Color.YELLOW);
                break;
            case 3:
                toastView.setBackgroundColor(Color.GREEN);
                break;
            case 4:
                toastView.setBackgroundColor(Color.BLUE);
                break;
            case 0:
                toastView.setBackgroundColor(Color.MAGENTA);
                break;
        }
        toast.show();
    }
}
