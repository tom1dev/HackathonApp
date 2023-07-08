package com.example.hackathonapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class firstPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button acceptButton = findViewById(R.id.acceptButton);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            //  @Override
            public void onClick(View v) {
                Intent intent = new Intent(firstPage.this, NextActivity.class);
                startActivity(intent);
            }
        });

        Button rejectButton = findViewById(R.id.rejectButton);
        rejectButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
