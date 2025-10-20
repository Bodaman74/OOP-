/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab4;

/**
 *
 * @author DELL
 */
public class Lab4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                ProductDatabase db = new ProductDatabase("products.txt");
        
        Product p1 = new Product("P101", "Laptop", "Dell", "TechStore", 5, 15500.75f);
        Product p2 = new Product("P102", "Mouse", "Logitech", "TechStore", 20, 350.0f);
        Product p3 = new Product("P103", "Keyboard", "HP", "TechStore", 15, 700.0f);

        db.insertRecord(p1);
        db.insertRecord(p2);
        db.insertRecord(p3);

        
        db.saveToFile();

        
        System.out.println(" Products Before Delete:");
        for (Product p : db.returnAllRecords()) {
            System.out.println(p.lineRepresentation());
        }

        
        db.deleteRecord("P102"); 
       
        db.saveToFile();

       
        System.out.println("\n Products After Delete:");
        for (Product p : db.returnAllRecords()) {
            System.out.println(p.lineRepresentation());
        }
    }

   
 
    }
    

