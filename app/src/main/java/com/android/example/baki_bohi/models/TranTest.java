package com.android.example.baki_bohi.models;

public class TranTest {
    private String amount;
    private String debit;
    private String credit;
    private String date;
    private String time;
    private String sid;
    private String customer_name;
    private String customer_id;

    //Constructor
    public TranTest() {
    }

    public TranTest(String amount, String debit, String credit, String date, String time, String sid, String customer_name, String customer_id) {
        this.amount = amount;
        this.debit = debit;
        this.credit = credit;
        this.date = date;
        this.time = time;
        this.sid = sid;
        this.customer_name = customer_name;
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
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
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", sid='" + sid + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", customer_id='" + customer_id + '\'' +
                '}';
    }
}
