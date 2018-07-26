package org.atctech.lawmanagementsystem;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {


    View rootView;
    ProgressBar progressBar;

    MyTask myTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        progressBar = findViewById(R.id.progressBar);
        rootView = progressBar;


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                myTask = new MyTask();
                myTask.execute();
            }
        }, 3000);
    }

    private class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            if (MainActivity.isNetworkAvailable(SplashActivity.this)) {

                return "1";
            } else {
                return "0";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result.equalsIgnoreCase("0")) {

                progressBar.setVisibility(View.GONE);

                Snackbar snackbar =  Snackbar.make(rootView, "No internet connection", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Retry", new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                progressBar.setVisibility(View.VISIBLE);

                                // Restart MyTask after 3 seconds
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        myTask = new MyTask();
                                        myTask.execute();
                                    }
                                }, 3000);
                            }
                        }).setActionTextColor(Color.RED);
                View sView = snackbar.getView();
                sView.setBackgroundColor(Color.parseColor("#000000"));
                snackbar.show();

            }
            else {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();

            }
        }
    }
}
