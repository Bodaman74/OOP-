/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author DELL
 */
public class Product {
    

    private String productID;
    private String productName;
    private String manufacturerName;
    private String supplierName;
    private int quantity;
    private float price;

    public Product(String productID, String productName, String manufacturerName,
                   String supplierName, int quantity, float price) {
        this.productID = productID;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        setQuantity(quantity);
        this.price = price;
    }

    
    public int getQuantity() {
        return quantity;
    }

    
    public void setQuantity(int quantity) {
         if (quantity >= 0) {
        this.quantity = quantity;
    }
         else { System.out.println("The quantity must be a positive number");
         }
    }

    public String lineRepresentation() {
        return productID + "," + productName + "," + manufacturerName + ","
                + supplierName + "," + quantity + "," + price;
    }

    public String getSearchKey() {
        return productID;
    }
    public double getPrice(){
        return price;
    }


}
