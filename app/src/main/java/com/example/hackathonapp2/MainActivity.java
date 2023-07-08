package com.example.hackathonapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    public static final int REQUEST_CODE_ADD_NOTE = 1;
    private ArrayList<String> stats = new ArrayList<String>();
    private String distance ="1";
    private String area="2";
    private String score="3";

    private GoogleMap myMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView startButton = findViewById(R.id.StartTrail);
        ImageView stopButton = findViewById(R.id.StopTrail);
        stopButton .setVisibility(View.GONE);
        startButton .setVisibility(View.VISIBLE);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //start button press
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                stopButton.setVisibility(View.VISIBLE);
                startButton.setVisibility(View.GONE);
            }

        });
        //stop Button Press
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stats.add(score);
                stats.add(distance);
                stats.add(area);

                stopButton .setVisibility(View.GONE);
                startButton.setVisibility(View.VISIBLE);
                Intent i = new Intent(getApplicationContext(), StatisiticsActivitity.class);

                i.putExtra("key",stats);
                startActivity(i);
                }

        });


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }
}