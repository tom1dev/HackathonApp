package com.example.hackathonapp2;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StepsActivity extends AppCompatActivity {

    private int clickCount = 0;
    private int[] imageResources = {R.drawable.map2, R.drawable.map3, R.drawable.stopbutton};
    private int currentImageIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.steps);

        ImageView imageView1 = findViewById(R.id.changeMapImage);
        ImageView imageView2 = findViewById(R.id.changeButtonImage);
        TextView changingText = (TextView)findViewById(R.id.changeText);
        Button btn = (Button)findViewById(R.id.next);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount++;
                currentImageIndex++;

                // Go through the steps for the app
                if (clickCount == 1) {
                    float currentSize = changingText.getTextSize();
                    float scale = getResources().getDisplayMetrics().scaledDensity;
                    float newSize = currentSize / scale - 5;

                    // Set the new image resource
                    imageView1.setImageResource(imageResources[0]);
                    imageView2.setImageResource(imageResources[2]);

                    // Apply the new font size
                    changingText.setTextSize(TypedValue.COMPLEX_UNIT_SP, newSize);
                    changingText.setText("Press the red button to finish your travel, once you’re close to the starting point.");
                } else if (clickCount == 2) {
                    float currentSize = changingText.getTextSize();
                    float scale = getResources().getDisplayMetrics().scaledDensity;
                    float newSize = currentSize / scale + 1;

                    // Set the new image resource
                    imageView1.setImageResource(imageResources[1]);
                    imageView2.setImageDrawable(null);

                    // Apply the new font size
                    changingText.setTextSize(TypedValue.COMPLEX_UNIT_SP, newSize);
                    changingText.setText("Congratulations!\n You have successfully\n claimed your area. ");
                } else if (clickCount == 3) {
                    startActivity(new Intent(StepsActivity.this, MainActivity.class));
                    clickCount = 0; // Reset click count
                }
            }
        });
    }
}
