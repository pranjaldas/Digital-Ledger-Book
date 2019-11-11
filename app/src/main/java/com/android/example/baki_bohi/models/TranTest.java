package com.android.example.baki_bohi.models;

public class TranTest {
    private String amount;
    private String debit;
    private String credit;

    //Constructor
    public TranTest() {
        this.amount = "";
        this.debit = "";
        this.debit = "";
    }

    public TranTest(String amount, String debit, String credit) {
        this.amount = amount;
        this.debit = debit;
        this.credit = credit;
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

    @Override
    public String toString() {
        return "TranTest{" +
                "amount='" + amount + '\'' +
                ", debit='" + debit + '\'' +
                ", credit='" + credit + '\'' +
                '}';
    }
}
