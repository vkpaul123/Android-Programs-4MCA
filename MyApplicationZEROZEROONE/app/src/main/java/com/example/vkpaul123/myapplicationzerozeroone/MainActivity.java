package com.example.vkpaul123.myapplicationzerozeroone;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    boolean playing = false;
    MediaPlayer mp;
    MediaMetadataRetriever mmr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button play = (Button) findViewById(R.id.play);
        Button pause = (Button) findViewById(R.id.pause);
        Button stop = (Button) findViewById(R.id.stop);

        ImageView albumArt = (ImageView) findViewById(R.id.imageView);

        registerForContextMenu(albumArt);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress / 3, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mp = MediaPlayer.create(MainActivity.this, R.raw.song);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!playing) {
                    mp.start();
                    final Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            while (mp.isPlaying()) {
                                try {
                                    Thread.sleep(1000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                handler.post(new Runnable() {
                                    TextView timer = (TextView) findViewById(R.id.timer);
                                    TextView timer2 = (TextView) findViewById(R.id.timer2);

                                    @Override
                                    public void run() {
                                        int[] ints = splitToComponentTimes(new BigDecimal(mp.getCurrentPosition() / 1000));
                                        int[] ints2 = splitToComponentTimes(new BigDecimal((mp.getDuration() - mp.getCurrentPosition()) / 1000));

                                        timer.setText(new Integer(ints[0]).toString() + ":" + new Integer(ints[1]).toString() + ":" + new Integer(ints[2]).toString());
                                        timer2.setText(new Integer(ints2[0]).toString() + ":" + new Integer(ints2[1]).toString() + ":" + new Integer(ints2[2]).toString());

                                    }

                                    public int[] splitToComponentTimes(BigDecimal biggy) {
                                        long longVal = biggy.longValue();
                                        int hours = (int) longVal / 3600;
                                        int remainder = (int) longVal - hours * 3600;
                                        int mins = remainder / 60;
                                        remainder = remainder - mins * 60;
                                        int secs = remainder;

                                        int[] ints = {hours, mins, secs};
                                        return ints;
                                    }
                                });
                            }
                        }
                    };
                    new Thread(runnable).start();
                }
                Toast.makeText(MainActivity.this, "Play...", Toast.LENGTH_SHORT).show();
                playing = true;
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playing)
                    mp.pause();
                Toast.makeText(MainActivity.this, "Pause.", Toast.LENGTH_SHORT).show();
                playing = false;
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mp.stop();
                    mp = MediaPlayer.create(MainActivity.this, R.raw.song);
                } catch (Exception e) {
                    System.err.println(e);
                }
                Toast.makeText(MainActivity.this, "Stop!", Toast.LENGTH_LONG).show();
                playing = false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.simplemenu, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuPlay:
                if (!playing) {
                    mp.start();
                    final Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            while (mp.isPlaying()) {
                                try {
                                    Thread.sleep(1000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                handler.post(new Runnable() {
                                    TextView timer = (TextView) findViewById(R.id.timer);
                                    TextView timer2 = (TextView) findViewById(R.id.timer2);

                                    @Override
                                    public void run() {
                                        int[] ints = splitToComponentTimes(new BigDecimal(mp.getCurrentPosition() / 1000));
                                        int[] ints2 = splitToComponentTimes(new BigDecimal((mp.getDuration() - mp.getCurrentPosition()) / 1000));

                                        timer.setText(new Integer(ints[0]).toString() + ":" + new Integer(ints[1]).toString() + ":" + new Integer(ints[2]).toString());
                                        timer2.setText(new Integer(ints2[0]).toString() + ":" + new Integer(ints2[1]).toString() + ":" + new Integer(ints2[2]).toString());

                                    }

                                    public int[] splitToComponentTimes(BigDecimal biggy) {
                                        long longVal = biggy.longValue();
                                        int hours = (int) longVal / 3600;
                                        int remainder = (int) longVal - hours * 3600;
                                        int mins = remainder / 60;
                                        remainder = remainder - mins * 60;
                                        int secs = remainder;

                                        int[] ints = {hours, mins, secs};
                                        return ints;
                                    }
                                });
                            }
                        }
                    };
                    new Thread(runnable).start();
                }
                Toast.makeText(MainActivity.this, "Play...", Toast.LENGTH_SHORT).show();
                playing = true;
                return true;

            case R.id.menuPause:
                if (playing)
                    mp.pause();
                Toast.makeText(MainActivity.this, "Pause.", Toast.LENGTH_SHORT).show();
                playing = false;

                return true;

            case R.id.menuStop:
                try {
                    mp.stop();
                    mp = MediaPlayer.create(MainActivity.this, R.raw.song);
                } catch (Exception e) {
                    System.err.println(e);
                }
                Toast.makeText(MainActivity.this, "Stop!", Toast.LENGTH_LONG).show();
                playing = false;

                return true;

            case R.id.menuStop2:
                try {
                    mp.stop();
                    mp = MediaPlayer.create(MainActivity.this, R.raw.song);
                } catch (Exception e) {
                    System.err.println(e);
                }
                Toast.makeText(MainActivity.this, "Stop!", Toast.LENGTH_LONG).show();
                playing = false;

                return true;
        }
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contextmenu, menu);//Menu Resource, Menu
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cmenuPlay:
                if (!playing) {
                    mp.start();
                    final Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            while (mp.isPlaying()) {
                                try {
                                    Thread.sleep(1000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                handler.post(new Runnable() {
                                    TextView timer = (TextView) findViewById(R.id.timer);
                                    TextView timer2 = (TextView) findViewById(R.id.timer2);

                                    @Override
                                    public void run() {
                                        int[] ints = splitToComponentTimes(new BigDecimal(mp.getCurrentPosition() / 1000));
                                        int[] ints2 = splitToComponentTimes(new BigDecimal((mp.getDuration() - mp.getCurrentPosition()) / 1000));

                                        timer.setText(new Integer(ints[0]).toString() + ":" + new Integer(ints[1]).toString() + ":" + new Integer(ints[2]).toString());
                                        timer2.setText(new Integer(ints2[0]).toString() + ":" + new Integer(ints2[1]).toString() + ":" + new Integer(ints2[2]).toString());

                                    }

                                    public int[] splitToComponentTimes(BigDecimal biggy) {
                                        long longVal = biggy.longValue();
                                        int hours = (int) longVal / 3600;
                                        int remainder = (int) longVal - hours * 3600;
                                        int mins = remainder / 60;
                                        remainder = remainder - mins * 60;
                                        int secs = remainder;

                                        int[] ints = {hours, mins, secs};
                                        return ints;
                                    }
                                });
                            }
                        }
                    };
                    new Thread(runnable).start();
                }
                Toast.makeText(MainActivity.this, "Play...", Toast.LENGTH_SHORT).show();
                playing = true;
                return true;

            case R.id.cmenuPause:
                if (playing)
                    mp.pause();
                Toast.makeText(MainActivity.this, "Pause.", Toast.LENGTH_SHORT).show();
                playing = false;

                return true;

            case R.id.cmenuStop:
                try {
                    mp.stop();
                    mp = MediaPlayer.create(MainActivity.this, R.raw.song);
                } catch (Exception e) {
                    System.err.println(e);
                }
                Toast.makeText(MainActivity.this, "Stop!", Toast.LENGTH_LONG).show();
                playing = false;

                return true;
        }
        return false;
    }
}