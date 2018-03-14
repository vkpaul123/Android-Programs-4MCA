package com.example.quick.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by vkpaul123 on 2/25/2018.
 */

public class mybr extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Battery Low Received !!!", Toast.LENGTH_SHORT).show();
    }
}

