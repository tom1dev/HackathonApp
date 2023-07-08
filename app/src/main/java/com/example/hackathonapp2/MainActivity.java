package com.example.hackathonapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_ADD_NOTE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView startButton = findViewById(R.id.StartTrail);
        ImageView stopButton = findViewById(R.id.StopTrail);
        stopButton .setVisibility(View.GONE);
        startButton .setVisibility(View.VISIBLE);

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
                stopButton .setVisibility(View.GONE);
                startButton.setVisibility(View.VISIBLE);
                startActivityForResult(
                        new Intent(getApplicationContext(), StatisiticsActivitity.class),REQUEST_CODE_ADD_NOTE
                );


            }

        });


    }
}