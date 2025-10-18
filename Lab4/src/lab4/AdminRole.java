/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.util.ArrayList;

/**
 *
 * @author PCV
 */
public class AdminRole {
    private EmployeeUserDatabase database;

    public AdminRole() {
        this.database = new EmployeeUserDatabase("Employees.txt");
    }

    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        EmployeeUser newEmployee = new EmployeeUser(employeeId, name, email, address, phoneNumber);
        database.insertRecord(newEmployee);
        database.saveToFile();
    }

    public EmployeeUser[] getListOfEmployees() {
        ArrayList<EmployeeUser> employeeList = database.returnAllRecords();
        EmployeeUser[] employees = new EmployeeUser[employeeList.size()];
        
        for (int i = 0; i < employeeList.size(); i++) {
            employees[i] = employeeList.get(i);
        }
        
        return employees;
    }

    public void removeEmployee(String key) {
        database.deleteRecord(key);
        database.saveToFile();
    }

    public void logout() {
        database.saveToFile();
    }
}