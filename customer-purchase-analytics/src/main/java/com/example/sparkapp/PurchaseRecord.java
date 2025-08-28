package com.example.sparkapp;

public class PurchaseRecord {
    private int customerId;
    private String product;
    private double amount;

    public PurchaseRecord(int customerId, String product, double amount) {
        this.customerId = customerId;
        this.product = product;
        this.amount = amount;
    }

    public int getCustomerId() { return customerId; }
    public String getProduct() { return product; }
    public double getAmount() { return amount; }
}
