package com.example.sysdevproject;

public class Customer {

    private int id;
    private int isOVer18;

    public Customer(int id, int isOVer18) {
        this.id = id;
        this.isOVer18 = isOVer18;
    }
    public Customer()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsOVer18() {
        return isOVer18;
    }

    public void setIsOVer18(int isOVer18) {
        this.isOVer18 = isOVer18;
    }
}
