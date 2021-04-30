package com.example.sysdevproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "Restaurant.db";
    private static final String TABLE_ITEMS = "Item";
    private static final String TABLE_CART = "Cart";
    private static final String TABLE_ORDERS = "Orders";
    private static final String TABLE_EMPLOYEE = "Employee";
    private static final String TABLE_CUSTOMER = "Customer";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }


    private static final String CREATE_TABLE_ITEMS =
            "create table Item ( item_id Integer primary key autoincrement, name varchar(64), description text, " +
                    "price double, is_drink Integer, available Integer);";

    private static final String CREATE_TABLE_EMPLOYEE =
            "create table Employee ( employee_id Integer primary key autoincrement, name varchar(16));";

    private static final String CREATE_TABLE_CUSTOMER =
            "create table Customer ( customer_id Integer primary key autoincrement, isOver18 Integer, payment_method varchar(16));";

    private static final String CREATE_TABLE_ORDERS =
            "create table Orders ( order_id Integer primary key autoincrement, customer_id Integer, total_price double, foreign key(customer_id) references Customer(customer_id));";

    private static final String CREATE_TABLE_CART =
            "create table Cart ( order_id Integer, customer_id Integer, item_id Integer, quantity Integer, " +
                    "foreign key(order_id) references Orders(order_id), " +
                    "foreign key(customer_id) references Customer(customer_id), " +
                    "foreign key(item_id) references Item(item_id), " +
                    " PRIMARY KEY (customer_id,item_id));";


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_ITEMS);
        db.execSQL(CREATE_TABLE_EMPLOYEE);
        db.execSQL(CREATE_TABLE_CUSTOMER);
        db.execSQL(CREATE_TABLE_ORDERS);
        db.execSQL(CREATE_TABLE_CART);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }
}
