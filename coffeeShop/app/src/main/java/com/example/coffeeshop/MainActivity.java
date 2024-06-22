package com.example.coffeeshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button btnDrink;
    private Button btnSnack;
    private Button btnLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDrink = findViewById(R.id.btnDrink);
        btnSnack = findViewById(R.id.btnSnack);
        btnLocation = findViewById(R.id.btnLocation);

        btnDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Drink button clicked");
                openProductActivity("drink");
            }
        });

        btnSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Snack button clicked");
                openProductActivity("snack");
            }
        });

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Location button clicked");
                openLocationActivity();
            }
        });
    }

    private void openProductActivity(String type) {
        Log.d(TAG, "Opening ProductActivity with type: " + type);
        Intent intent = new Intent(MainActivity.this, ProductActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    private void openLocationActivity() {
        Log.d(TAG, "Opening LocationActivity");
        Intent intent = new Intent(MainActivity.this, LocationActivity.class);
        startActivity(intent);
    }
}