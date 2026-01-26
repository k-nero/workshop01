/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class CartItemDTO {
    private String mobileID;
    private String mobileName;
    private float price;
    private int quantity;

    public CartItemDTO() {
    }

    public CartItemDTO(String mobileID, String mobileName, float price, int quantity) {
        this.mobileID = mobileID;
        this.mobileName = mobileName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getMobileID() {
        return mobileID;
    }

    public void setMobileID(String mobileID) {
        this.mobileID = mobileID;
    }

    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public float getTotal() {
        return price * quantity;
    }
}
