package com.android.example.baki_bohi.models;

public class Shopkeeper {
    private String name;
    private String phone;
    private String email;
    private String address;
    private String shopname;
    private String uid;

    public Shopkeeper() {

    }

    public Shopkeeper(String name, String phone, String email, String address, String shopname, String uid) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.shopname = shopname;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Shopkeeper{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", shopname='" + shopname + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
