package com.example.quick.broadcastreceiver;

/**
 * Created by vkpaul123 on 2/25/2018.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;



public class br1 extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Charger Connected !!!", Toast.LENGTH_SHORT).show();
    }
}

