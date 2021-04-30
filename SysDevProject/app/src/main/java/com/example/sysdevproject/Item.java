package com.example.sysdevproject;

public class Item {

    private static int item_id;
    private static String name;
    private static String description;
    private static Double price;
    private static int is_drink;
    private static int available;

    public Item() {
    }
    public Item(int id, String name, String description, Double price, int is_drink, int available)
    {
        this.item_id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.is_drink = is_drink;
        this.available = available;
    }
    public static int getItem_id() {
        return item_id;
    }

    public static void setItem_id(int item_id) {
        Item.item_id = item_id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Item.name = name;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        Item.description = description;
    }

    public static Double getPrice() {
        return price;
    }

    public static void setPrice(Double price) {
        Item.price = price;
    }

    public static int getIs_drink() {
        return is_drink;
    }

    public static void setIs_drink(int is_drink) {
        Item.is_drink = is_drink;
    }

    public static int getAvailable() {
        return available;
    }

    public static void setAvailable(int available) {
        Item.available = available;
    }
}
