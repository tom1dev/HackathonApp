package com.example.hackathonapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class StatisiticsActivitity extends AppCompatActivity {
    private int distance;
    private int area;
    private int score;
    private ArrayList<String> stats = new ArrayList<String>();

    private int level;
    private int xp;

    private DataBase Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        //finds if there is a data base if there is import last data
        Db = new DataBase(StatisiticsActivitity.this);
        if(Db.isEmpty()){
            this.level =0;
            this.xp = 0;
        }else{
            ArrayList<String> temp = Db.getDATA();
            xp = Integer.parseInt((String)temp.get(1));
            level = Integer.parseInt((String)temp.get(2));
        }

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            stats = extras.getStringArrayList("key");
            //The key argument here must match that used in the other activity
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.statpage);
        TextView scoreTv = findViewById(R.id.numScore);
        TextView areaTv = findViewById(R.id.numArea);
        TextView distanceTv = findViewById(R.id.numDistance);
        ImageView NextButton = findViewById(R.id.NextButtonStatPg);

        scoreTv.setText(stats.get(0));

        this.score = Integer.parseInt((String)stats.get(0));

        areaTv.setText(stats.get(2));
        this.distance = Integer.parseInt((String)stats.get(1));
        distanceTv.setText(stats.get(1));

        this.area = Integer.parseInt((String)stats.get(2));

        expCount(1000);


        NextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }

        });


    }

    public void expCount(int score){
        final int[] xpgotten = {(int) Math.round(score * 0.015)};
        final int[] xpneeded = {100 + (level - 1) * 10};

        xpneeded[0] += -xp;
        ProgressBar xpPG = findViewById(R.id.progressBar);
        TextView levelTV = findViewById(R.id.lvlTV);
        levelTV.setText("Level: " + Integer.toString(level));
        xpPG.setProgress((int)Math.round(100*((double)xp/xpneeded[0])));

        new CountDownTimer(3600000, 50) {


            public void onTick(long millisUntilFinished) {
                xpgotten[0] +=-1;
                xpneeded[0] +=-1;

                if(xpneeded[0] ==0){

                    level++;
                    levelTV.setText("Level: " + Integer.toString(level));
                    xpneeded[0] = 100 + (level-1)*10;
                    xpPG.setProgress(0);
                }
                if(xpgotten[0] ==0 ){
                    Db.addDATA(xp,level,score);
                    cancel();

                }

                xp = 100 + (level-1)*10 - xpneeded[0];
                xpPG.setProgress((int)Math.round(100*((double)xp/xpneeded[0])));


            }


            //if timer reaches 0 move to stats page
            public void onFinish() {
            }


        }.start();


    }
}