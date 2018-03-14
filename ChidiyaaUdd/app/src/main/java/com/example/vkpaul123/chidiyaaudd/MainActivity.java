package com.example.vkpaul123.chidiyaaudd;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int playerPressedFlags[] = new int[2];
    int randFly, score1, score2;

    double prevFlyObj_randToken, currFlyObj_randToken;

    boolean gameOver = false, roundGoing = false;

    RelativeLayout playerArea1, playerArea2;

    TextView textView, textView2;

    TextView textView3, textView4, tvScore1, tvScore2;

    TextView textViewNoOfRounds;

    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    long MillisecondTime2, StartTime2, TimeBuff2, UpdateTime2 = 0L ;

    long TotalUpdateTime, TotalUpdateTime2;

    int noOfRounds = 0, player1Lives=3, player2Lives=3;
    TextView textViewPlayer1Lives, textViewPlayer2Lives;

    long MY_DELAY = 3000;

    Handler handler;
    Handler handler2;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enableImmersiveMode(getWindow().getDecorView());

        playerArea1 = (RelativeLayout) findViewById(R.id.player1Area);
        playerArea2 = (RelativeLayout) findViewById(R.id.player2Area);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        currFlyObj_randToken = prevFlyObj_randToken = 0;

        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView6);

        tvScore1 = (TextView) findViewById(R.id.score1);
        tvScore2 = (TextView) findViewById(R.id.score2);

        textViewPlayer1Lives = (TextView) findViewById(R.id.textViewPlayer1Lives);
        textViewPlayer2Lives = (TextView) findViewById(R.id.textViewPlayer2Lives);
        textViewNoOfRounds = (TextView) findViewById(R.id.textView7);

        handler = new Handler();
        handler2 = new Handler();

        playerArea1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        v.setPressed(true);
                        // Start action ...
                        playerPressedFlags[0] = 1;
                        playerArea1.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
//                        Toast.makeText(getApplicationContext(), "Player 1: "+playerPressedFlags[0], Toast.LENGTH_SHORT).show();
                        changeFlyingObject();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_OUTSIDE:
                    case MotionEvent.ACTION_CANCEL:
                        v.setPressed(false);
                        // Stop action ...
                        playerPressedFlags[0] = 0;
                        playerArea1.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));

//                        Toast.makeText(getApplicationContext(), "roundGoing: "+ roundGoing, Toast.LENGTH_SHORT).show();
                        TimeBuff += MillisecondTime;
                        handler.removeCallbacks(runnable);
                        checkWinner();

                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                }
                return true;
            }
        });

        playerArea2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        v.setPressed(true);
                        // Start action ...
                        playerPressedFlags[1] = 1;
                        playerArea2.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
//                        Toast.makeText(getApplicationContext(), "Player 2: "+ playerPressedFlags[1], Toast.LENGTH_SHORT).show();
                        changeFlyingObject();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_OUTSIDE:
                    case MotionEvent.ACTION_CANCEL:
                        v.setPressed(false);
                        // Stop action ...
                        playerPressedFlags[1] = 0;
                        playerArea2.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
//                        Toast.makeText(getApplicationContext(), "roundGoing: "+ roundGoing, Toast.LENGTH_SHORT).show();
                        TimeBuff2 += MillisecondTime2;
                        handler2.removeCallbacks(runnable2);
                        checkWinner();

                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                }
                return true;
            }
        });
    }

    public static void enableImmersiveMode(final View decorView) {
        decorView.setSystemUiVisibility(setSystemUiVisibility());
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(setSystemUiVisibility());
                }
            }
        });
    }
    public static int setSystemUiVisibility() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
    }

    public Runnable runnable = new Runnable() {
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            handler.postDelayed(this, 0);
            textView3.setText(new Long(UpdateTime).toString());

            if(UpdateTime > MY_DELAY) {
                TimeBuff += MillisecondTime;
                handler.removeCallbacks(runnable);
                playerArea1.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
                checkWinner();
            }
        }
    };

    public Runnable runnable2 = new Runnable() {
        public void run() {
            MillisecondTime2 = SystemClock.uptimeMillis() - StartTime2;
            UpdateTime2 = TimeBuff2 + MillisecondTime2;
            handler2.postDelayed(this, 0);
            textView4.setText(new Long(UpdateTime2).toString());

            if(UpdateTime2 > MY_DELAY) {
                TimeBuff2 += MillisecondTime2;
                handler2.removeCallbacks(runnable2);
                playerArea2.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
                checkWinner();
            }
        }
    };

    public void checkWinner() {
        if(playerPressedFlags[0] == 0 &&
                playerPressedFlags[1] == 0 &&
                prevFlyObj_randToken != currFlyObj_randToken) {

            if(randFly == 1) {
//                roundGoing = false;
                if(UpdateTime < UpdateTime2 && UpdateTime < MY_DELAY) {
                    textView.setText("Player1 is Winner.");
                    score1++;
                    tvScore1.setText(new Integer(score1).toString());
                    playerArea1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    if(UpdateTime2 < MY_DELAY) {
                        score2++;
                        tvScore2.setText(new Integer(score2).toString());
                        playerArea2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else {
                        player2Lives--;
                        textViewPlayer2Lives.setText(new Integer(player2Lives).toString());
                        playerArea2.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                    }
                }
                else if(UpdateTime2 == UpdateTime && UpdateTime < MY_DELAY) {
                    textView.setText("It's a Tie");
                    score1++;
                    tvScore1.setText(new Integer(score1).toString());
                    score2++;
                    tvScore2.setText(new Integer(score2).toString());
                    playerArea1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    playerArea2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                }
                else if(UpdateTime2 < MY_DELAY) {
                    textView.setText("Player2 is Winner");
                    score2++;
                    tvScore2.setText(new Integer(score2).toString());
                    playerArea2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    if(UpdateTime < MY_DELAY) {
                        score1++;
                        tvScore1.setText(new Integer(score1).toString());
                        playerArea1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else {
                        player1Lives--;
                        textViewPlayer1Lives.setText(new Integer(player1Lives).toString());
                        playerArea1.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                    }
                }
                else {
                    player1Lives--;
                    textViewPlayer1Lives.setText(new Integer(player1Lives).toString());
                    player2Lives--;
                    textViewPlayer2Lives.setText(new Integer(player2Lives).toString());
                    playerArea1.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                    playerArea2.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                }
                TotalUpdateTime += UpdateTime;
                TotalUpdateTime2 += UpdateTime2;


                Toast.makeText(getApplicationContext(), "It Could Fly!", Toast.LENGTH_SHORT).show();
            } else {
//                roundGoing = false;
                if(UpdateTime > UpdateTime2 && UpdateTime > MY_DELAY) {
                    textView.setText("Player1 is Winner");
                    score1++;
                    tvScore1.setText(new Integer(score1).toString());
                    playerArea1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    if(UpdateTime2 > MY_DELAY) {
                        score2++;
                        tvScore2.setText(new Integer(score2).toString());
                        playerArea2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else {
                        player2Lives--;
                        textViewPlayer2Lives.setText(new Integer(player2Lives).toString());
                        playerArea2.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                    }
                }
                else if(UpdateTime2 == UpdateTime & UpdateTime2 > MY_DELAY) {
                    textView.setText("It's a Tie");
                    score1++;
                    tvScore1.setText(new Integer(score1).toString());
                    score2++;
                    tvScore2.setText(new Integer(score2).toString());
                    playerArea1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    playerArea2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                }
                else if(UpdateTime2 > MY_DELAY) {
                    textView.setText("Player2 is Winner");
                    score2++;
                    tvScore2.setText(new Integer(score2).toString());
                    playerArea2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    if(UpdateTime > MY_DELAY) {
                        score1++;
                        tvScore1.setText(new Integer(score1).toString());
                        playerArea1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else {
                        player1Lives--;
                        textViewPlayer1Lives.setText(new Integer(player1Lives).toString());
                        playerArea1.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                    }
                }
                else {
                    player1Lives--;
                    textViewPlayer1Lives.setText(new Integer(player1Lives).toString());
                    player2Lives--;
                    textViewPlayer2Lives.setText(new Integer(player2Lives).toString());
                    playerArea1.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                    playerArea2.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                }

                TotalUpdateTime -= UpdateTime;
                TotalUpdateTime2 -= UpdateTime2;


                Toast.makeText(getApplicationContext(), "It Could Not Fly!", Toast.LENGTH_SHORT).show();
            }
            declareWinner();
        }
        if(playerPressedFlags[0] == 1 &&
                playerPressedFlags[1] == 1 &&
                prevFlyObj_randToken != currFlyObj_randToken) {
//            roundGoing = false;
            if(randFly == 1) {
                player1Lives--;
                textViewPlayer1Lives.setText(new Integer(player1Lives).toString());
                player2Lives--;
                textViewPlayer2Lives.setText(new Integer(player2Lives).toString());
                playerArea1.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                playerArea2.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                changeFlyingObject();
                declareWinner();
            } else {
                score1++;
                tvScore1.setText(new Integer(score1).toString());
                score2++;
                tvScore2.setText(new Integer(score2).toString());
                playerArea1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                playerArea2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                changeFlyingObject();
                declareWinner();
            }


            playerArea1.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
            playerArea2.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        }

    }

    public void changeFlyingObject() {
        prevFlyObj_randToken = currFlyObj_randToken;
        if(playerPressedFlags[0] == 1 && playerPressedFlags[1] == 1) {
//            Toast.makeText(getApplicationContext(), "changeFlyingObject", Toast.LENGTH_SHORT).show();
            Random random = new Random();
            int randVal = random.nextInt(5) + 1;
            randFly = random.nextInt(2) + 1;
            if(randFly == 1) {
                switch (randVal) {
                    case 1:
                        textView2.setText(R.string.bird1);
                        break;
                    case 2:
                        textView2.setText(R.string.bird2);
                        break;
                    case 3:
                        textView2.setText(R.string.bird3);
                        break;
                    case 4:
                        textView2.setText(R.string.bird4);
                        break;
                    case 5:
                        textView2.setText(R.string.bird5);
                        break;
                }
            } else {
                switch (randVal) {
                    case 1:
                        textView2.setText(R.string.nonbird1);
                        break;
                    case 2:
                        textView2.setText(R.string.nonbird2);
                        break;
                    case 3:
                        textView2.setText(R.string.nonbird3);
                        break;
                    case 4:
                        textView2.setText(R.string.nonbird4);
                        break;
                    case 5:
                        textView2.setText(R.string.nonbird5);
                        break;
                }
            }

            currFlyObj_randToken = Math.random();
            roundGoing = true;

            MillisecondTime = 0L;
            StartTime = 0L;
            TimeBuff = 0L;
            UpdateTime = 0L;

            MillisecondTime2 = 0L;
            StartTime2 = 0L;
            TimeBuff2 = 0L;
            UpdateTime2 = 0L;

            StartTime = SystemClock.uptimeMillis();
            handler.postDelayed(runnable, 0);
            StartTime2 = SystemClock.uptimeMillis();
            handler2.postDelayed(runnable2, 0);
        }

//        Toast.makeText(getApplicationContext(), "Player1 Touch: " + playerPressedFlags[0] + "\nPlayer2 Touch: " + playerPressedFlags[1], Toast.LENGTH_SHORT).show();
    }

    public void declareWinner() {
        if(gameOver != true) {
            noOfRounds++;
            textViewNoOfRounds.setText(new Integer(noOfRounds).toString());
            if(player1Lives == 0 && player2Lives != 0) {
                gameOver = true;
                Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                i.putExtra("winner", "Player 2 Wins!");
                i.putExtra("reason", "Player 1 is out of Lives");
                startActivity(i);
                finish();
            } else if(player2Lives == 0 && player1Lives != 0) {
                gameOver = true;
                Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                i.putExtra("winner", "Player 1 Wins!");
                i.putExtra("reason", "Player 2 is out of Lives");
                startActivity(i);
                finish();
            } else if(noOfRounds == 10) {
                if(score1 > score2) {
                    gameOver = true;
                    Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                    i.putExtra("winner", "Player 1 Wins!");
                    i.putExtra("reason", "Player 1 had a Higher Score");
                    startActivity(i);
                    finish();
                } else if(score2 > score1) {
                    gameOver = true;
                    Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                    i.putExtra("winner", "Player 2 Wins!");
                    i.putExtra("reason", "Player 2 had a Higher Score");
                    startActivity(i);
                    finish();
                } else {
                    if(TotalUpdateTime < TotalUpdateTime2) {
                        gameOver = true;
                        Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                        i.putExtra("winner", "Player 1 Wins!");
                        i.putExtra("reason", "Player 1 was Faster");
                        startActivity(i);
                        finish();
                    } else if(TotalUpdateTime2 < TotalUpdateTime) {
                        gameOver = true;
                        Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                        i.putExtra("winner", "Player 2 Wins!");
                        i.putExtra("reason", "Player 2 was Faster");
                        startActivity(i);
                        finish();
                    } else {
                        gameOver = true;
                        Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                        i.putExtra("winner", "It's a Tie!!!");
                        i.putExtra("reason", "Both players were good!");
                        startActivity(i);
                        finish();
                    }
                }
            } else if(player1Lives == 0 && player2Lives == 0) {
                if(score1 > score2) {
                    gameOver = true;
                    Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                    i.putExtra("winner", "Player 1 Wins!");
                    i.putExtra("reason", "Player 1 had a Higher Score. Both the players out of lives");
                    startActivity(i);
                    finish();
                } else if(score2 > score1) {
                    gameOver = true;
                    Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                    i.putExtra("winner", "Player 2 Wins!");
                    i.putExtra("reason", "Player 2 had a Higher Score. Both the players out of lives");
                    startActivity(i);
                    finish();
                } else {
                    if(TotalUpdateTime < TotalUpdateTime2) {
                        gameOver = true;
                        Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                        i.putExtra("winner", "Player 1 Wins!");
                        i.putExtra("reason", "Player 1 was Faster. Both the players out of lives");
                        startActivity(i);
                        finish();
                    } else if(TotalUpdateTime2 < TotalUpdateTime) {
                        gameOver = true;
                        Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                        i.putExtra("winner", "Player 2 Wins!");
                        i.putExtra("reason", "Player 2 was Faster. Both the players out of lives");
                        startActivity(i);
                        finish();
                    } else {
                        gameOver = true;
                        Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                        i.putExtra("winner", "It's a Losing Tie!!!");
                        i.putExtra("reason", "Both players Lost!. Both the players out of lives");
                        startActivity(i);
                        finish();
                    }
                }
            }
        }
    }
}
