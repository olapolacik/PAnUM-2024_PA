package com.example.simplecalculator;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText operand1;
    private EditText operand2;
    private Spinner operator;
    private Button calculateButton;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        operand1 = findViewById(R.id.operand1);
        operand2 = findViewById(R.id.operand2);
        operator = findViewById(R.id.operator);
        calculateButton = findViewById(R.id.calculate_button);
        result = findViewById(R.id.result);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operators, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operator.setAdapter(adapter);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }

    private void calculateResult() {
        String operand1Text = operand1.getText().toString();
        String operand2Text = operand2.getText().toString();
        String operatorText = operator.getSelectedItem().toString();

        if (operand1Text.isEmpty() || operand2Text.isEmpty()) {
            Toast.makeText(this, "Proszę wprowadzić obydwa operandy", Toast.LENGTH_SHORT).show();
            return;
        }

        double num1 = Double.parseDouble(operand1Text);
        double num2 = Double.parseDouble(operand2Text);
        double resultValue = 0;

        switch (operatorText) {
            case "+":
                resultValue = num1 + num2;
                break;
            case "-":
                resultValue = num1 - num2;
                break;
            case "*":
                resultValue = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    resultValue = num1 / num2;
                } else {
                    Toast.makeText(this, "Dzielenie przez zero jest niemożliwe", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
        }

        DecimalFormat df = new DecimalFormat("#.####");
        String formattedResult = df.format(resultValue);

        result.setText("Wynik: " + formattedResult);
    }
}
