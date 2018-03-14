package com.example.immanualtrinity.trinitybroadcastreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Immanual Trinity on 21-02-2018.
 */

public class chargeConnected extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Charger Connected..!", Toast.LENGTH_SHORT).show();
    }
}
