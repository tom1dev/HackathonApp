package com.example.hackathonapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    //variables for stats
    private ArrayList<String> stats = new ArrayList<String>();
    private String distance ="1M";
    private String area="2M^2";
    private String score="3";

    //variable for googlemap

    private GoogleMap myMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //variables for stop/start/timer
        ImageView startButton = findViewById(R.id.StartTrail);
        ImageView stopButton = findViewById(R.id.StopTrail);
        TextView counter = findViewById(R.id.timer);
        ImageView counterbox = findViewById(R.id.TimerBox);

        //sets initial visibility for objects
        counterbox.setVisibility(View.GONE);
        stopButton .setVisibility(View.GONE);
        startButton .setVisibility(View.VISIBLE);
        counter.setVisibility(View.GONE);

        //initialises google map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




        //start button press
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //changes to correct visablity
                counter.setVisibility(View.VISIBLE);
                counterbox.setVisibility(View.VISIBLE);

                stopButton.setVisibility(View.VISIBLE);
                startButton.setVisibility(View.GONE);

                //count down timer
                new CountDownTimer(3600000, 1000) {


                    public void onTick(long millisUntilFinished) {
                        long seconds = millisUntilFinished / 1000;
                        int minutes = Math.round(seconds/60);
                        seconds = seconds - minutes*60;
                        counter.setText(Integer.toString(minutes)+":"+Long.toString(seconds));
                    }

                    //if timer reaches 0 move to stats page
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

            //moves to stats page
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

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }
}