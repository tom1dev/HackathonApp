package com.example.hackathonapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_ADD_NOTE = 1;
    private ArrayList<String> stats = new ArrayList<String>();
    private String distance ="1M";
    private String area="2M^2";
    private String score="3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView startButton = findViewById(R.id.StartTrail);
        ImageView stopButton = findViewById(R.id.StopTrail);
        TextView counter = findViewById(R.id.timer);
        stopButton.setVisibility(View.GONE);
        startButton.setVisibility(View.VISIBLE);

        //start button press
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                stopButton.setVisibility(View.VISIBLE);
                startButton.setVisibility(View.GONE);
                new CountDownTimer(3600000, 1000) {


                    public void onTick(long millisUntilFinished) {
                        long seconds = millisUntilFinished / 1000;
                        int minutes = Math.round(seconds/60);
                        seconds = seconds - minutes*60;
                        counter.setText(Integer.toString(minutes)+":"+Long.toString(seconds));
                    }

                    public void onFinish() {
                        stats.add(score);
                        stats.add(distance);
                        stats.add(area);

                        stopButton.setVisibility(View.GONE);
                        startButton.setVisibility(View.VISIBLE);
                        Intent i = new Intent(getApplicationContext(), StatisiticsActivitity.class);

                        i.putExtra("key", stats);
                        startActivity(i);

                    }


                }.start();
            }

        });
        //stop Button Press
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stats.add(score);
                stats.add(distance);
                stats.add(area);

                stopButton.setVisibility(View.GONE);
                startButton.setVisibility(View.VISIBLE);
                Intent i = new Intent(getApplicationContext(), StatisiticsActivitity.class);

                i.putExtra("key", stats);
                startActivity(i);
            }

        });

    }
}