package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPace;
    private Button buttonCalculate;
    private TextView textViewResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPace = findViewById(R.id.editTextPace);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResults = findViewById(R.id.textViewResults);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResults();
            }
        });
    }

    private void calculateResults() {
        String paceInput = editTextPace.getText().toString();

        if (paceInput.isEmpty()) {
            textViewResults.setText("Proszę podać tempo.");
            return;
        }

        double pace = Double.parseDouble(paceInput);
        double speed = 60 / pace;  // km/h

        int marathonDistance = 42195;  // meters
        int halfMarathonDistance = 21097;  // meters

        String marathonTime = calculateTime(pace, marathonDistance);
        String halfMarathonTime = calculateTime(pace, halfMarathonDistance);

        StringBuilder results = new StringBuilder();
        results.append("Tempo: ").append(String.format("%.2f", speed)).append(" km/h\n");
        results.append("Maraton: ").append(marathonTime).append("\n");
        results.append("Półmaraton: ").append(halfMarathonTime).append("\n");

        textViewResults.setText(results.toString());
    }

    private String calculateTime(double pace, int distance) {
        double totalMinutes = (distance / 1000.0) * pace;
        int hours = (int) totalMinutes / 60;
        int minutes = (int) totalMinutes % 60;
        int seconds = (int) ((totalMinutes - (hours * 60 + minutes)) * 60);

        return String.format("%d godz %d min %d sek", hours, minutes, seconds);
    }
}
