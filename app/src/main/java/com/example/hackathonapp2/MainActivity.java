package com.example.hackathonapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    //variables for stats

    private ArrayList<String> stats = new ArrayList<String>();
    private String distance = "1M";
    private String area = "2M^2";
    private String score = "3";

    private DataBase Db;

    //variable for googlemap

    private GoogleMap myMap;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //variables for stop/start/timer
        ImageView startButton = findViewById(R.id.StartTrail);
        ImageView stopButton = findViewById(R.id.StopTrail);
        TextView counter = findViewById(R.id.timer);
        ImageView counterbox = findViewById(R.id.TimerBox);

        Db = new DataBase(MainActivity.this);

        stopButton.setVisibility(View.GONE);
        startButton.setVisibility(View.VISIBLE);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        fusedLocationProviderClient = (FusedLocationProviderClient) LocationServices.getFusedLocationProviderClient(this);

        Dexter.withContext(getApplicationContext()).withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        getCurrentLocation();

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

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

                final int[] pollingcount = {0};
                //count down timer
                new CountDownTimer(3600000, 1000) {


                    public void onTick(long millisUntilFinished) {
                        if(pollingcount[0] == 30){
                            pollingcount[0] = 0;
                            
                        }

                        long seconds = millisUntilFinished / 1000;
                        int minutes = Math.round(seconds / 60);
                        seconds = seconds - minutes * 60;
                        counter.setText(Integer.toString(minutes) + ":" + Long.toString(seconds));
                        pollingcount[0] +=1;
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

                Db.addDATA(0,0,0);

                ArrayList<String> data = Db.getDATA();
                Intent i = new Intent(getApplicationContext(), StatisiticsActivitity.class);

                i.putExtra("key", stats);
                startActivity(i);



            }

        });

    }

    public void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(@NonNull GoogleMap googleMap) {
                        if (location != null) {
                            LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Current Location !");
                            googleMap.addMarker(markerOptions);
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }
}