package com.android.example.baki_bohi.models;

public class TranTest {
    private String amount;
    private String debit;
    private String credit;
    private String date;
    private String time;

    //Constructor
    public TranTest() {
        this.amount = "";
        this.debit = "";
        this.debit = "";
        this.date = "";
        this.time = "";
    }

    public TranTest(String amount, String debit, String credit, String date, String time) {
        this.amount = amount;
        this.debit = debit;
        this.credit = credit;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TranTest{" +
                "amount='" + amount + '\'' +
                ", debit='" + debit + '\'' +
                ", credit='" + credit + '\'' +
                '}';
    }
}
