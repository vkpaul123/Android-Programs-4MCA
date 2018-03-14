package com.example.vkpaul123.quotesbro;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    TextView textView;

    Handler handler;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = (ConstraintLayout) findViewById(R.id.contLay);
        textView = (TextView) findViewById(R.id.textView);
        image = (ImageView) findViewById(R.id.imageView2);

        handler = new Handler();
        handler.postDelayed(runnable, 10000);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            changeBros();

            handler.postDelayed(this, 5000);
        }
    };

    public void changeBros() {
        Random random = new Random();
        int imgRand = random.nextInt(5) + 1;
        int quoteRand = random.nextInt(5) + 1;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            switch (imgRand) {
//                case 1:
//                    constraintLayout.setBackground(getResources().getDrawable(R.drawable.imagea));
//                    break;
//                case 2:
//                    constraintLayout.setBackground(getResources().getDrawable(R.drawable.imageb));
//                    break;
//                case 3:
//                    constraintLayout.setBackground(getResources().getDrawable(R.drawable.imagec));
//                    break;
//                case 4:
//                    constraintLayout.setBackground(getResources().getDrawable(R.drawable.imaged));
//                    break;
//                case 5:
//                    constraintLayout.setBackground(getResources().getDrawable(R.drawable.imagee));
//                    break;
//            }

            switch (imgRand) {
                case 1:
                    new DownloadImage().execute(getResources().getString(R.string.url1));
                    break;
                case 2:
                    new DownloadImage().execute(getResources().getString(R.string.url2));
                    break;
                case 3:
                    new DownloadImage().execute(getResources().getString(R.string.url3));
                    break;
                case 4:
                    new DownloadImage().execute(getResources().getString(R.string.url4));
                    break;
                case 5:
                    new DownloadImage().execute(getResources().getString(R.string.url5));
                    break;

            }
        }

        switch (quoteRand) {
            case 1:
                textView.setText(getResources().getString(R.string.quote1));
                break;
            case 2:
                textView.setText(getResources().getString(R.string.quote2));
                break;
            case 3:
                textView.setText(getResources().getString(R.string.quote3));
                break;
            case 4:
                textView.setText(getResources().getString(R.string.quote4));
                break;
            case 5:
                textView.setText(getResources().getString(R.string.quote5));
                break;
        }
    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String imageURL = strings[0];

            Bitmap bitmap = null;

            try {
                InputStream inputStream = new java.net.URL(imageURL).openStream();

                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                image.setImageBitmap(bitmap);
            }
        }
    }
}
