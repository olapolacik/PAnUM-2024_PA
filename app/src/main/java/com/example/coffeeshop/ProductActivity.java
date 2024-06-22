package com.example.coffeeshop;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProductActivity extends AppCompatActivity {

    private TextView tvProductName;
    private TextView tvProductPrice;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        tvProductName = findViewById(R.id.tvProductName);
        tvProductPrice = findViewById(R.id.tvProductPrice);

        String type = getIntent().getStringExtra("type");

        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();

        if (type != null) {
            loadProduct(type);
        } else {
            Log.e("ProductActivity", "Product type is null");
        }
    }

    private void loadProduct(String type) {
        Cursor cursor = null;
        try {
            cursor = db.query("products", null, "type=?", new String[]{type}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));

                tvProductName.setText(name);
                tvProductPrice.setText(String.format("%.2f PLN", price));
                Log.d(TAG, "Product loaded: " + name + " - " + price);
            } else {
                Log.e(TAG, "No data found for type: " + type);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error loading product data", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
