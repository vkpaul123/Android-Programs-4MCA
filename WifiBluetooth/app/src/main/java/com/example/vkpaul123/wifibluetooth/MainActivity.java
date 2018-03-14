package com.example.vkpaul123.wifibluetooth;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bluetoothBtn;
    Button wifiBtn;
    Button spinnBtn;
    Button horizBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothBtn = (Button) findViewById(R.id.bluetoothButton);
        wifiBtn = (Button) findViewById(R.id.wifiButton);

        spinnBtn = (Button) findViewById(R.id.spinButton);
        horizBtn = (Button) findViewById(R.id.horizButton);

        try {
            bluetoothToggle();
            wifiToggle();

            spinnerDialog();
            horizDialog();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void bluetoothToggle() {
        bluetoothBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if(!bluetoothAdapter.isEnabled()) {
                    bluetoothAdapter.enable();
                    Toast.makeText(getApplicationContext(), "Bluetooth ON", Toast.LENGTH_LONG).show();
                }
                else {
                    bluetoothAdapter.disable();
                    Toast.makeText(getApplicationContext(), "Bluetooth OFF", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void wifiToggle() {
        wifiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiManager wifiManager = (WifiManager) MainActivity.this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                if(wifiManager.isWifiEnabled()) {
                    wifiManager.setWifiEnabled(false);
                    Toast.makeText(getApplicationContext(), "Wifi OFF", Toast.LENGTH_LONG).show();
                }
                else {
                    wifiManager.setWifiEnabled(true);
                    Toast.makeText(getApplicationContext(), "Wifi ON", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void spinnerDialog() {
        spinnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage("This is an example of Spinner Dialog.");
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(true);
                progressDialog.show();

                progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface v) {
                        Toast.makeText(getApplicationContext(), "Spinner Dialog CANCELLED", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    public void horizDialog() {
        horizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setMessage("This is an example of Horizontal Dialog with Timer.");
                progressDialog.setCancelable(true);
                progressDialog.show();

                final Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        while(progressDialog.getProgress() < 100) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                System.err.println(e);
                            }
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.setProgress(progressDialog.getProgress() + 1);
                                }
                            });
                        }
                        progressDialog.cancel();
                    }
                };
                new Thread(runnable).start();

                progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Toast.makeText(getApplicationContext(), "Horizontal Dialog CANCLLED", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
