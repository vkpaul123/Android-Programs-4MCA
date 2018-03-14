package com.example.vkpaul123.firstproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FirstActivity extends AppCompatActivity {

    //  onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Log.i("activityOnCreateInfo", "onCreate() Executed");
    }

    //  onStart()
    @Override
    protected void onStart() {
        super.onStart();

        Log.i("activityOnStartInfo", "onStart() Executed");
    }

    //  onRestart()
    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i("activityOnRestartInfo", "onRestart() Executed");
    }

    //  onResume
    @Override
    protected void onResume() {
        super.onResume();

        Log.i("activityOnResumeInfo", "onResume() Executed");
    }

    //  onPause
    @Override
    protected void onPause() {
        super.onPause();

        Log.i("activityOnPauseInfo", "onPause() Executed");
    }

    //  onStop
    @Override
    protected void onStop() {
        super.onStop();

        Log.i("activityOnStopInfo", "onStop() Executed");
    }

    //  onDestroy
    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("activityOnDestroyInfo", "onDestroy() Executed");
    }

    //  Button Click
    public void buttonClickLog(View view) {
        Log.i("buttonClickInfo", "Button was clicked.");
    }
}
