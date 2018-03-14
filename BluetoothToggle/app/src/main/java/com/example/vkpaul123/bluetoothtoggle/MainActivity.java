package com.example.vkpaul123.bluetoothtoggle;

import android.bluetooth.BluetoothAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(!bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.enable();
            Toast.makeText(getApplicationContext(), "Bluetooth ON", Toast.LENGTH_LONG).show();
        }
        else {
            bluetoothAdapter.disable();
            Toast.makeText(getApplicationContext(), "Bluetooth OFF", Toast.LENGTH_LONG).show();
        }

        finish();
    }
}
