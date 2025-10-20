package lab4;

import java.time.LocalDate;

public class Lab4 {
    public static void main(String[] args) {
        System.out.println("=== INVENTORY MANAGEMENT SYSTEM - COMPLETE TEST ===\n");
        
        // Test Part 1: Admin Functions
        testAdminFunctions();
        
        // Test Part 2: Employee Product Management
        testProductManagement();
        
        // Test Part 3: Customer Purchase Operations
        testPurchaseOperations();
        
        // Test Part 4: Returns and Payments
        testReturnsAndPayments();
        
        System.out.println("=== ALL TESTS COMPLETED SUCCESSFULLY ===");
    }
    
    public static void testAdminFunctions() {
        System.out.println("PART 1: ADMIN ROLE TEST");
        System.out.println("========================");
        
        AdminRole admin = new AdminRole();
        
        // Add employees
        System.out.println("Adding employees...");
        admin.addEmployee("E1200", "Ahmed", "ahmed_1999@gmail.com", "Alexandria", "01088877345");
        admin.addEmployee("E1201", "Mohamed", "mohamed@gmail.com", "Cairo", "01012345678");
        admin.addEmployee("E1202", "Fatima", "fatima@email.com", "Giza", "0109998888");
        
        // Display all employees
        System.out.println("\nAll Employees in System:");
        EmployeeUser[] employees = admin.getListOfEmployees();
        for (int i = 0; i < employees.length; i++) {
            System.out.println("  " + employees[i].lineRepresentation());
        }
        
        // Remove an employee
        System.out.println("\nRemoving employee E1201...");
        admin.removeEmployee("E1201");
        
        // Display remaining employees
        System.out.println("Remaining Employees:");
        employees = admin.getListOfEmployees();
        for (int i = 0; i < employees.length; i++) {
            System.out.println("  " + employees[i].lineRepresentation());
        }
        
        admin.logout();
        System.out.println("✓ Admin role test completed\n");
    }
    
    public static void testProductManagement() {
        System.out.println("PART 2: PRODUCT MANAGEMENT TEST");
        System.out.println("===============================");
        
        EmployeeRole employee = new EmployeeRole();
        
        // Add products 
        System.out.println("Adding products...");
        employee.addProduct("P2394", "Laptop", "Apple", "TechSupplier", 10, 1500.0f);
        employee.addProduct("P2568", "Smartphone", "Samsung", "MobileStore", 25, 800.0f);
        employee.addProduct("P3001", "Tablet", "Lenovo", "TechSupplier", 15, 400.0f);
        
        // Display all products
        System.out.println("\nAll Products in Inventory:");
        Product[] products = employee.getListOfProducts();
        for (int i = 0; i < products.length; i++) {
            System.out.println("  " + products[i].lineRepresentation());
        }
        
        employee.logout();
        System.out.println("✓ Product management test completed\n");
    }
    
    public static void testPurchaseOperations() {
        System.out.println("PART 3: PURCHASE OPERATIONS TEST");
        System.out.println("================================");
        
        EmployeeRole employee = new EmployeeRole();
        
        // Test purchasing products 
        System.out.println("Processing customer purchases...");
        
        // Purchase 1: Successful purchase
        boolean purchase1 = employee.purchaseProduct("7845345678", "P2568", LocalDate.of(2025, 10, 17));
        System.out.println("  Purchase 1 (SSN:7845345678, Product:P2568): " + 
                          (purchase1 ? "SUCCESS" : "FAILED - Out of stock"));
        
        // Purchase 2: Another successful purchase
        boolean purchase2 = employee.purchaseProduct("1234567890", "P2394", LocalDate.of(2025, 10, 18));
        System.out.println("  Purchase 2 (SSN:1234567890, Product:P2394): " + 
                          (purchase2 ? "SUCCESS" : "FAILED - Out of stock"));
        
        // Purchase 3: Try to purchase out-of-stock product (after multiple purchases)
        boolean purchase3 = employee.purchaseProduct("7845345678", "P2394", LocalDate.of(2025, 10, 18));
        System.out.println("  Purchase 3 (SSN:7845345678, Product:P2394): " + 
                          (purchase3 ? "SUCCESS" : "FAILED - Out of stock"));
        
        // Display current purchasing operations
        System.out.println("\nCurrent Purchasing Operations:");
        CustomerProduct[] purchases = employee.getListOfPurchasingOperations();
        for (int i = 0; i < purchases.length; i++) {
            System.out.println("  " + purchases[i].lineRepresentation());
        }
        
        // Display updated product quantities
        System.out.println("\nUpdated Product Quantities:");
        Product[] products = employee.getListOfProducts();
        for (int i = 0; i < products.length; i++) {
            System.out.println("  " + products[i].lineRepresentation());
        }
        
        employee.logout();
        System.out.println("✓ Purchase operations test completed\n");
    }
    
    public static void testReturnsAndPayments() {
        System.out.println("PART 4: RETURNS AND PAYMENTS TEST");
        System.out.println("=================================");
        
        EmployeeRole employee = new EmployeeRole();
        
        // Test applying payment 
        System.out.println("Testing payment application...");
        boolean paymentApplied = employee.applyPayment("7845345678", "P2568", LocalDate.of(2025, 10, 17));
        System.out.println("  Payment for SSN:7845345678, Product:P2568: " + 
                          (paymentApplied ? "SUCCESS" : "FAILED - Already paid or not found"));
        
        // Display purchases after payment
        System.out.println("\nPurchases after payment:");
        CustomerProduct[] purchases = employee.getListOfPurchasingOperations();
        for (int i = 0; i < purchases.length; i++) {
            System.out.println("  " + purchases[i].lineRepresentation());
        }
        
        // Test return within 14 days (should succeed)
        System.out.println("\nTesting product return (within 14 days)...");
        double refund1 = employee.returnProduct("1234567890", "P2394", 
                                               LocalDate.of(2025, 10, 18), 
                                               LocalDate.of(2025, 10, 25));
        if (refund1 != -1) {
            System.out.println("  Return SUCCESS - Refund amount: $" + refund1);
        } else {
            System.out.println("  Return FAILED - Outside return period or invalid");
        }
        
        // Test return after 14 days (should fail)
        System.out.println("\nTesting product return (after 14 days - should fail)...");
        double refund2 = employee.returnProduct("7845345678", "P2568", 
                                               LocalDate.of(2025, 10, 17), 
                                               LocalDate.of(2025, 11, 5));
        if (refund2 != -1) {
            System.out.println("  Return SUCCESS - Refund amount: $" + refund2);
        } else {
            System.out.println("  Return FAILED - Outside 14-day return period (as expected)");
        }
        
        // Final state of all data
        System.out.println("\nFINAL SYSTEM STATE:");
        System.out.println("Final Products:");
        Product[] products = employee.getListOfProducts();
        for (int i = 0; i < products.length; i++) {
            System.out.println("  " + products[i].lineRepresentation());
        }
        
        System.out.println("\nFinal Purchases:");
        purchases = employee.getListOfPurchasingOperations();
        for (int i = 0; i < purchases.length; i++) {
            System.out.println("  " + purchases[i].lineRepresentation());
        }
        
        employee.logout();
        System.out.println("✓ Returns and payments test completed\n");
    }
}