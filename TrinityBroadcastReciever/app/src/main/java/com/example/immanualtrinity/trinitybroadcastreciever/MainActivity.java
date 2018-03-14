package com.example.immanualtrinity.trinitybroadcastreciever;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        if(amIConnected())
        {
            Log.i("TRUE","USER is Connected");
            Toast.makeText(this, "USER is Connected", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "USER is Not Connected", Toast.LENGTH_SHORT).show();
            Log.i("FALSE","USER is not Connected");
        }

        */
    }

    /*
    public boolean amIConnected()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo =connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
*/
}
