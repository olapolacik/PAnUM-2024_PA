package com.example.convertapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText arabicNumberEditText;
    private EditText romanNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        arabicNumberEditText = findViewById(R.id.arabic_number_edittext);
        romanNumberEditText = findViewById(R.id.roman_number_edittext);

        // Ustaw pole arabskie na puste przy uruchomieniu aplikacji
        arabicNumberEditText.setText("");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Metoda obsługująca kliknięcie przycisku przechodzenia do drugiego ekranu
    public void goToRomanActivity(View view) {
        // Tworzymy intencję (intent) do uruchomienia nowej aktywności
        Intent intent = new Intent(this, RomanActivity.class);

        // Uruchamiamy nową aktywność
        startActivity(intent);
    }

    // Obsługa kliknięcia przycisków od 0 do 9 oraz C
    public void onArabicButtonClick(View view) {
        // Pobierz aktualny tekst z pola liczby arabskiej
        String currentNumber = arabicNumberEditText.getText().toString();

        // Sprawdź, który przycisk został kliknięty
        if (view.getId() == R.id.button_clear) {
            // Jeśli pole nie jest puste, skróć bieżący tekst o jeden znak
            if (!currentNumber.isEmpty()) {
                currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
            }
        } else {
            // Pobierz wartość przycisku jako tekst
            String buttonText = ((Button) view).getText().toString();
            currentNumber += buttonText;
        }

        // Zaktualizuj pole z liczbą arabską
        arabicNumberEditText.setText(currentNumber);

        // Wywołaj metodę konwertującą liczbę arabską na rzymską
        updateRomanNumber(currentNumber);
    }

    // Metoda aktualizująca pole z liczbą rzymską
    private void updateRomanNumber(String arabicNumber) {
        // Jeśli pole jest puste, ustaw puste pole z liczbą rzymską
        if (arabicNumber.isEmpty()) {
            romanNumberEditText.setText("");
            return;
        }

        // Tutaj dodaj logikę konwersji liczby arabskiej na rzymską
        // i ustaw ją w polu tekstowym z liczbą rzymską (romanNumberEditText)
        String romanNumber = convertArabicToRoman(Integer.parseInt(arabicNumber));
        romanNumberEditText.setText(romanNumber);
    }

    // Metoda konwertująca liczbę arabską na rzymską
    private String convertArabicToRoman(int number) {
        // Tutaj należy zaimplementować logikę konwersji
        // Skorzystaj z odpowiednich algorytmów lub bibliotek do konwersji
        // i zwróć wynik jako String
        // Poniżej znajduje się przykładowa implementacja dla cyfr od 1 do 10
        if (number < 1 || number > 3999) {
            return "Nieprawidłowa liczba";
        }

        String[] romanDigits = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] thousands = {"", "M", "MM", "MMM"};

        return thousands[number / 1000] +
                hundreds[(number % 1000) / 100] +
                tens[(number % 100) / 10] +
                romanDigits[number % 10];
    }
}
