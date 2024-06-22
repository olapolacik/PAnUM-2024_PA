package com.example.coffeeshop;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LocationActivity extends AppCompatActivity {

    private TextView tvLocationName;
    private TextView tvLocationAddress;
    private TextView tvLocationHours;
    private ImageView imgLocationMap;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        tvLocationName = findViewById(R.id.tvLocationName);
        tvLocationAddress = findViewById(R.id.tvLocationAddress);
        tvLocationHours = findViewById(R.id.tvLocationHours);
        imgLocationMap = findViewById(R.id.imgLocationMap);

        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();

        loadLocation();
    }

    private void loadLocation() {
        Cursor cursor = null;
        try {
            cursor = db.query("locations", null, null, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                String hours = cursor.getString(cursor.getColumnIndexOrThrow("hours"));

                tvLocationName.setText(name);
                tvLocationAddress.setText(address);
                tvLocationHours.setText(hours);
                imgLocationMap.setImageResource(R.drawable.ic_map_placeholder); // Placeholder image for map
            } else {
                Log.e("LocationActivity", "No data found in the locations table.");
            }
        } catch (Exception e) {
            Log.e("LocationActivity", "Error loading location data", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}



