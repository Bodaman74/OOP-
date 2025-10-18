/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author PCV
 */

public class EmployeeUser {
    private String employeeId;
    private String Name;
    private String Email;
    private String Address;
    private String PhoneNumber;

    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber) {
        this.employeeId = employeeId;
        this.Name = name;
        this.Email = email;
        this.Address = address;
        this.PhoneNumber = phoneNumber;
    }

    public String lineRepresentation() {
        return employeeId + "," + Name + "," + Email + "," + Address + "," + PhoneNumber;
    }

    public String getSearchKey() {
        return employeeId;
    }
}