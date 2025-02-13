package com.example.ezyeatsapplication;

public class CartItem {
    private String itemName;
    private String itemDescription;
    private String itemPrice;

    // Empty constructor for Firebase
    public CartItem() {
    }

    // Constructor with parameters
    public CartItem(String itemName, String itemDescription, String itemPrice) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }

    // Getters and Setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}
