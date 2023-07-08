package com.example.hackathonapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


public class StatisiticsActivitity extends AppCompatActivity {
    private String distance;
    private String area;
    private String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("key");
            //The key argument here must match that used in the other activity
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.statpage);
        TextView score = findViewById(R.id.Score);
        TextView area = findViewById(R.id.Area);
        TextView distance = findViewById(R.id.Distance);
    }
}