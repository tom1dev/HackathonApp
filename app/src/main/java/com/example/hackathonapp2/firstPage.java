package com.example.hackathonapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class firstPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //sets the welcome page as the first activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomepage);

        Button acceptButton = findViewById(R.id.acceptButton);
        //if the 'accept button is clicked change the activity to the first tutorial page.
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(firstPage.this, welcomePage.class);
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
