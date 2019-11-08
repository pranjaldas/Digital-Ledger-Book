package com.android.example.baki_bohi.models;

public class Customer {
    private String name;
    private String address;
    private String phone;
    private String cid;
    private String sid;

    public Customer() {
    }

    public Customer(String name, String address, String phone, String cid, String sid) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.cid = cid;
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", cid='" + cid + '\'' +
                ", sid='" + sid + '\'' +
                '}';
    }
}
