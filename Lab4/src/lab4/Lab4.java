/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab4;

/**
 *
 * @author PCV
 */
public class Lab4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Create admin object
        AdminRole admin = new AdminRole();
        
        // Add employees
        admin.addEmployee("E001", "Ali", "ali@email.com", "Cairo", "0101111111");
        admin.addEmployee("E002", "Mona", "mona@email.com", "Alex", "0102222222");
        admin.addEmployee("E003", "Seif", "mona@email.com", "Alex", "0103333333");
        
        // Show all employees
        System.out.println("All Employees:");
        EmployeeUser[] allEmployees = admin.getListOfEmployees();
        for (int i = 0; i < allEmployees.length; i++) {
            System.out.println(allEmployees[i].lineRepresentation());
        }
        
        // Remove one employee
        admin.removeEmployee("E002");
        
        // Show remaining employees
        System.out.println("\nAfter removing E002:");
        allEmployees = admin.getListOfEmployees();
        for (int i = 0; i < allEmployees.length; i++) {
            System.out.println(allEmployees[i].lineRepresentation());
        }
        
        // Save and exit
        admin.logout();
    }
    
}
