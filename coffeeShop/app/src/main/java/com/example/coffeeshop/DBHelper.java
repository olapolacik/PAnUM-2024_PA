package com.example.coffeeshop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "drinksnack.db";
    private static final int DATABASE_VERSION = 3;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE products (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, type TEXT, price REAL)");
        db.execSQL("CREATE TABLE locations (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, hours TEXT)");

        // Insert sample data
        db.execSQL("INSERT INTO products (name, type, price) VALUES ('Espresso', 'drink', 10.0)");
        db.execSQL("INSERT INTO products (name, type, price) VALUES ('Latte', 'drink', 12.0)");
        db.execSQL("INSERT INTO products (name, type, price) VALUES ('Cappuccino', 'drink', 11.0)");
        db.execSQL("INSERT INTO products (name, type, price) VALUES ('Sernik', 'snack', 14.0)");
        db.execSQL("INSERT INTO products (name, type, price) VALUES ('Orzeszki', 'snack', 2.5)");
        db.execSQL("INSERT INTO products (name, type, price) VALUES ('Szarlotka', 'snack', 12.5)");
        db.execSQL("INSERT INTO locations (name, address, hours) VALUES ('Lokal 1', 'ul. Kawia 10, Częstochowa ', 'pon-pt: 9:00 - 18:00')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS products");
        db.execSQL("DROP TABLE IF EXISTS locations");
        onCreate(db);
    }
}

