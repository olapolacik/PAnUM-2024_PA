package com.example.timerapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MinActivity extends AppCompatActivity {
    private TextView timerDisplay;
    private Button startTimerButton;
    private Button resetTimerButton;
    private Button switchBackButton;
    private EditText inputMinutes;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_min);

        timerDisplay = findViewById(R.id.timerDisplay);
        startTimerButton = findViewById(R.id.startTimerButton);
        resetTimerButton = findViewById(R.id.resetTimerButton);
        switchBackButton = findViewById(R.id.switchBackButton);
        inputMinutes = findViewById(R.id.inputMinutes);

        inputMinutes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()) {
                    long inputTime = Long.parseLong(s.toString()) * 60000;
                    setTime(inputTime);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timerRunning) {
                    startTimer();
                } else {
                    stopTimer();
                }
            }
        });

        resetTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        switchBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MinActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setTime(long milliseconds) {
        timeLeftInMillis = milliseconds;
        updateTimer();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                startTimerButton.setText("Start Timer");
            }
        }.start();

        timerRunning = true;
        startTimerButton.setText("Stop Timer");
    }

    private void stopTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        startTimerButton.setText("Start Timer");
    }

    private void resetTimer() {
        if (!inputMinutes.getText().toString().isEmpty()) {
            long inputTime = Long.parseLong(inputMinutes.getText().toString()) * 60000;
            setTime(inputTime);
        } else {
            timeLeftInMillis = 60000; // Default 1 minute
            updateTimer();
        }
    }

    private void updateTimer() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        int milliseconds = (int) (timeLeftInMillis % 1000) / 10;
        String timeLeftFormatted = String.format("%02d:%02d:%02d", minutes, seconds, milliseconds);
        timerDisplay.setText(timeLeftFormatted);
    }
}
