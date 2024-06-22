package com.example.coffeeshop;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
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

        String type = getIntent().getStringExtra("type");

        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();

        if (type != null) {
            loadProducts(type);
        } else {
            Log.e("ProductActivity", "Product type is null");
        }
    }

    private void loadProducts(String type) {
        Cursor cursor = null;
        try {
            cursor = db.query("products", null, "type=?", new String[]{type}, null, null, null);
            while (cursor != null && cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));

                // Tworzymy TextView dla każdego produktu
                TextView productTextView = new TextView(this);
                productTextView.setText(String.format("%s: %.2f PLN", name, price));
                productTextView.setTextSize(18);

                // Dodajemy TextView do LinearLayout w activity_product.xml
                LinearLayout layout = findViewById(R.id.productLayout);
                layout.addView(productTextView);

                Log.d(TAG, "Product loaded: " + name + " - " + price);
            }
            if (cursor == null || cursor.getCount() == 0) {
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