package com.android.example.baki_bohi;

public class Customer {
    public String firstName, secondName, phoneNo, eMail, address, shopName, shopType;

    public Customer(){

    }

    public Customer(String firstName, String secondName, String phoneNo, String eMail, String address, String shopName, String shopType) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNo = phoneNo;
        this.eMail = eMail;
        this.address = address;
        this.shopName = shopName;
        this.shopType = shopType;
    }
}
