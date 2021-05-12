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
                    "price double, is_drink Integer, available Integer, category varchar(64));";

    private static final String CREATE_TABLE_EMPLOYEE =
            "create table Employee ( employee_id Integer primary key autoincrement, name varchar(16));";

    private static final String CREATE_TABLE_CUSTOMER =
            "create table Customer ( customer_id Integer primary key autoincrement, isOver18 Integer, paymentMethod text);";

    private static final String CREATE_TABLE_ORDERS =
            "create table Orders ( order_id Integer primary key autoincrement, customer_id Integer,  payment_method varchar(16), total_price double, foreign key(customer_id) references Customer(customer_id));";

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
    public boolean insertItem(String name, String description, Double price, int is_drink, int available, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("price", price);
        contentValues.put("is_drink", is_drink);
        contentValues.put("available", available);
        contentValues.put("category", category);
        long result = db.insert(TABLE_ITEMS, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertCustomer(int isOver18, String payment)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("isOver18", isOver18);
        contentValues.put("paymentMethod", payment);
        long result = db.insert(TABLE_CUSTOMER, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean updateCustomer(String id, String payment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("customer_id", id);
        contentValues.put("paymentMethod", payment);
        db.update(TABLE_CUSTOMER, contentValues, "customer_id = ? ",new String[] {id});
        return true;
    }

    public Cursor findLastCustomer(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result;
        result = db.rawQuery("SELECT * from " + TABLE_CUSTOMER + " ORDER BY customer_id DESC LIMIT 1", null);
        return result;
    }

    public Item getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_ITEMS + " WHERE "
                + "item_id" + " = " + "'" + id + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        Item item = new Item();
        item.setItem_id(cursor.getInt(cursor.getColumnIndex("item_id")));
        item.setName(cursor.getString(cursor.getColumnIndex("name")));
        item.setDescription(cursor.getString(cursor.getColumnIndex("description")));
        item.setPrice(cursor.getDouble(cursor.getColumnIndex("price")));
        item.setIs_drink(cursor.getInt(cursor.getColumnIndex("is_drink")));
        item.setAvailable(cursor.getInt(cursor.getColumnIndex("available")));
        cursor.close();
        db.close();
        return item;
    }

    public Cursor getAllItems() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_ITEMS, null);
        return result;
    }

    public Cursor getComboItems() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * from " + TABLE_ITEMS + " WHERE category ='combo'", null);
        return result;
    }

    public Cursor get2SetItems() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * from " + TABLE_ITEMS + " WHERE category ='2set'", null);
        return result;
    }

    public Cursor getPlateItems() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * from " + TABLE_ITEMS + " WHERE category ='plate'", null);
        return result;
    }

    public Cursor getBibimbapItems() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * from " + TABLE_ITEMS + " WHERE category ='bibimbap'", null);
        return result;
    }

    public Cursor getDrinksItems() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * from " + TABLE_ITEMS + " WHERE category ='drinks'", null);
        return result;
    }

    public Cursor getAllItemsByName(String itemName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_ITEMS + " WHERE "
                + "name" + " = " + "'" + itemName + "'", null);
        return result;
    }

    public void makeItemUnavailable(int id)
    {
        String sql = "UPDATE " + TABLE_ITEMS + " SET available = 1  WHERE " + "item_id" + " = " + "'" + id + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(sql);
        db.close();
    }

    public int getItemName(String itemName) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_ITEMS + " WHERE "
                + "name" + " = " + "'" + itemName + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        int id = cursor.getInt(cursor.getColumnIndex("item_id"));

        cursor.close();
        db.close();
        return id;
    }

    public void deleteItem(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TABLE_ITEMS+ " WHERE " + "item_id" + " = " + "'" + id + "'";
        db.execSQL(sql);
        db.close();
    }





    public boolean addToCart(int orderId, int customerId, int item_id, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("order_id", orderId);
        contentValues.put("customer_id", customerId);
        contentValues.put("item_id", item_id);
        contentValues.put("quantity", quantity);
        long result = db.insert(TABLE_CART, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getItemFromCart(int customerId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_CART + " WHERE customer_id = " + customerId, null);
        return result;
    }

}
