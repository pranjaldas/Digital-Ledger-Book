package com.android.example.baki_bohi.models;

import java.util.Date;

public class Transaction {
    private String customerID;
    private String shopKeeperID;
    private String bilImg;
    private Date date;
    private String time;
    private String amount;
    private String debit;
    private String credit;
    private String note;
    private String balance; //locally calculated attribute at the time of transaction.

    //Constructors
    public Transaction() {
        this.customerID = null;
        this.shopKeeperID = null;
        this.bilImg = null;
        this.date = null;
        this.time = null;
        this.amount = null;
        this.debit = null;
        this.credit = null;
        this.note = null;
        this.balance = null;
    }

    //This Constructor I need for only testing
    public Transaction(Date date, String time, String amount, String debit, String credit, String balance) {
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.debit = debit;
        this.credit = credit;
        this.balance = balance;
    }

    //Actual Parameterized constructor;
    public Transaction(String customerID, String shopKeeperID, String bilImg, Date date, String time, String amount, String debit, String credit, String note, String balance) {
        this.customerID = customerID;
        this.shopKeeperID = shopKeeperID;
        this.bilImg = bilImg;
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.debit = debit;
        this.credit = credit;
        this.note = note;
        this.balance = balance;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getShopKeeperID() {
        return shopKeeperID;
    }

    public void setShopKeeperID(String shopKeeperID) {
        this.shopKeeperID = shopKeeperID;
    }

    public String getBilImg() {
        return bilImg;
    }

    public void setBilImg(String bilImg) {
        this.bilImg = bilImg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "customerID='" + customerID + '\'' +
                ", shopKeeperID='" + shopKeeperID + '\'' +
                ", bilImg='" + bilImg + '\'' +
                ", date=" + date +
                ", time='" + time + '\'' +
                ", amount='" + amount + '\'' +
                ", debit='" + debit + '\'' +
                ", credit='" + credit + '\'' +
                ", note='" + note + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
