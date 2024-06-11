package com.example.timerapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private long startTime = 0L;
    private Handler stopwatchHandler = new Handler();
    private long timeInMilliseconds = 0L;
    private long timeSwapBuff = 0L;
    private long updateTime = 0L;
    private TextView stopwatchDisplay;
    private Button startButton;
    private Button resetButton;
    private Button switchButton;
    private Runnable stopwatchRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stopwatchDisplay = findViewById(R.id.stopwatchDisplay);
        startButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.resetButton);
        switchButton = findViewById(R.id.switchButton);

        stopwatchRunnable = new Runnable() {
            @Override
            public void run() {
                timeInMilliseconds = System.currentTimeMillis() - startTime;
                updateTime = timeSwapBuff + timeInMilliseconds;
                int secs = (int) (updateTime / 1000);
                int mins = secs / 60;
                secs = secs % 60;
                int milliseconds = (int) (updateTime % 1000) / 10;
                stopwatchDisplay.setText(String.format("%02d:%02d:%02d", mins, secs, milliseconds));
                stopwatchHandler.postDelayed(this, 10);
            }
        };

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startButton.getText().equals("Start")) {
                    startTime = System.currentTimeMillis();
                    stopwatchHandler.postDelayed(stopwatchRunnable, 0);
                    startButton.setText("Stop");
                } else {
                    timeSwapBuff += timeInMilliseconds;
                    stopwatchHandler.removeCallbacks(stopwatchRunnable);
                    startButton.setText("Start");
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopwatchHandler.removeCallbacks(stopwatchRunnable);
                stopwatchDisplay.setText("00:00:00");
                timeSwapBuff = 0L;
                startTime = 0L;
                startButton.setText("Start");
            }
        });

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MinActivity.class);
                startActivity(intent);
            }
        });
    }
}
