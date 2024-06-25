package com.example.convertapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RomanActivity extends AppCompatActivity {

    private EditText romanNumberEditText;
    private EditText arabicNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roman);

        romanNumberEditText = findViewById(R.id.roman_number_edittext);
        arabicNumberEditText = findViewById(R.id.arabic_number_edittext);

        // Ustaw pole arabskie na puste przy uruchomieniu aktywności
        arabicNumberEditText.setText("");
    }

    // Metoda obsługująca kliknięcia przycisków rzymskich
    public void onRomanButtonClick(View view) {
        // Pobierz aktualny tekst z pola liczby rzymskiej
        String currentRomanNumber = romanNumberEditText.getText().toString();

        // Pobierz wartość przycisku jako tekst
        String buttonText = ((Button) view).getText().toString();

        // Dodaj klikniętą cyfrę rzymską do bieżącej liczby rzymskiej
        currentRomanNumber += buttonText;

        // Zaktualizuj pole z liczbą rzymską
        romanNumberEditText.setText(currentRomanNumber);

        // Wywołaj metodę konwertującą liczbę rzymską na arabską
        updateArabicNumber(currentRomanNumber);
    }

    // Metoda obsługująca kliknięcie przycisku cofania (usuwanie ostatniego znaku)
    public void onBackspaceButtonClick(View view) {
        // Pobierz aktualny tekst z pola liczby rzymskiej
        String currentRomanNumber = romanNumberEditText.getText().toString();

        // Jeśli pole nie jest puste, usuń ostatni znak
        if (!currentRomanNumber.isEmpty()) {
            currentRomanNumber = currentRomanNumber.substring(0, currentRomanNumber.length() - 1);
        }

        // Zaktualizuj pole z liczbą rzymską
        romanNumberEditText.setText(currentRomanNumber);

        // Wywołaj metodę konwertującą liczbę rzymską na arabską
        updateArabicNumber(currentRomanNumber);
    }

    // Metoda aktualizująca pole z liczbą arabską
    private void updateArabicNumber(String romanNumber) {
        // Tutaj dodaj logikę konwersji liczby rzymskiej na arabską
        int arabicNumber = convertRomanToArabic(romanNumber);
        arabicNumberEditText.setText(String.valueOf(arabicNumber));
    }

    // Metoda konwertująca liczbę rzymską na arabską
    private int convertRomanToArabic(String romanNumber) {
        // Mapa wartości dla poszczególnych znaków rzymskich
        // I: 1, V: 5, X: 10, L: 50, C: 100, D: 500, M: 1000
        // Wartości IV, IX, XL, XC, CD, CM to specjalne przypadki
        int result = 0;
        int prevValue = 0;

        for (int i = romanNumber.length() - 1; i >= 0; i--) {
            char romanChar = romanNumber.charAt(i);
            int value = getValue(romanChar);

            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }

            prevValue = value;
        }

        return result;
    }

    // Metoda pomocnicza zwracająca wartość dla danego znaku rzymskiego
    private int getValue(char romanChar) {
        switch (romanChar) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
