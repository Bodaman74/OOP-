package lab4;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

public class EmployeeRole {
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() {
        this.productsDatabase = new ProductDatabase("Products.txt");
        this.customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
    }

    public void addProduct(String productID, String productName, String manufacturerName, 
                          String supplierName, int quantity, float price) {
        Product newProduct = new Product(productID, productName, manufacturerName, 
                                       supplierName, quantity, price);
        productsDatabase.insertRecord(newProduct);
        productsDatabase.saveToFile();
    }

    public Product[] getListOfProducts() {
        ArrayList<Product> productList = productsDatabase.returnAllRecords();
        Product[] products = new Product[productList.size()];
        for (int i = 0; i < productList.size(); i++) {
            products[i] = productList.get(i);
        }
        return products;
    }

    public CustomerProduct[] getListOfPurchasingOperations() {
        ArrayList<CustomerProduct> operations = customerProductDatabase.returnAllRecords();
        CustomerProduct[] customerProducts = new CustomerProduct[operations.size()];
        for (int i = 0; i < operations.size(); i++) {
            customerProducts[i] = operations.get(i);
        }
        return customerProducts;
    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        Product product = productsDatabase.getRecord(productID);
        if (product == null || product.getQuantity() <= 0) {
            return false;
        }
        
        product.setQuantity(product.getQuantity() - 1);
        CustomerProduct purchase = new CustomerProduct(customerSSN, productID, purchaseDate);
        customerProductDatabase.insertRecord(purchase);
        
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
        
        return true;
    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {
        if (returnDate.isBefore(purchaseDate)) {
            return -1;
        }
        
        Product product = productsDatabase.getRecord(productID);
        if (product == null) {
            return -1;
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String searchKey = customerSSN + "," + productID + "," + purchaseDate.format(formatter);
        
        if (!customerProductDatabase.contains(searchKey)) {
            return -1;
        }
        
        long daysBetween = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        if (daysBetween > 14) {
            return -1;
        }
        
        product.setQuantity(product.getQuantity() + 1);
        customerProductDatabase.deleteRecord(searchKey);
        
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
        
        // Extract price from line representation since it's private
        String productLine = product.lineRepresentation();
        String[] parts = productLine.split(",");
        float price = Float.parseFloat(parts[5]);
        
        return price;
    }

    public boolean applyPayment(String customerSSN, String productID, LocalDate purchaseDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String searchKey = customerSSN + "," + productID + "," + purchaseDate.format(formatter);
        
        CustomerProduct purchase = customerProductDatabase.getRecord(searchKey);
        if (purchase != null && !purchase.isPaid()) {
            purchase.setPaid(true);
            customerProductDatabase.saveToFile();
            return true;
        }
        return false;
    }

    public void logout() {
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }
}