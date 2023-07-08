package com.example.hackathonapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class StatisiticsActivitity extends AppCompatActivity {
    private String distance;
    private String area;
    private String score;
    private ArrayList<String> stats = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            stats = extras.getStringArrayList("key");
            //The key argument here must match that used in the other activity
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.statpage);
        TextView score = findViewById(R.id.numScore);
        TextView area = findViewById(R.id.numArea);
        TextView distance = findViewById(R.id.numDistance);
        ImageView NextButton = findViewById(R.id.NextButtonStatPg);

        score.setText(stats.get(0));
        area.setText(stats.get(2));
        distance.setText(stats.get(1));
        NextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }

        });
    }
}