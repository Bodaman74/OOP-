/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author PCV
 */
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class EmployeeUserDatabase {
    private ArrayList<EmployeeUser> records;
    private String filename;

    public EmployeeUserDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<EmployeeUser>();
        readFromFile();
    }

    public void readFromFile() {
        // This method will work if the file exists
        // If file doesn't exist, it will just start with empty records
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            
            while (line != null) {
                EmployeeUser record = createRecordFrom(line);
                if (record != null) {
                    records.add(record);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            // If there's an error, just continue with empty records
        }
    }

    public EmployeeUser createRecordFrom(String line) {
        String[] data = line.split(",");
        if (data.length == 5) {
            return new EmployeeUser(data[0], data[1], data[2], data[3], data[4]);
        }
        return null;
    }

    public ArrayList<EmployeeUser> returnAllRecords() {
        return records;
    }

    public boolean contains(String key) {
        for (int i = 0; i < records.size(); i++) {
            EmployeeUser record = records.get(i);
            if (record.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public EmployeeUser getRecord(String key) {
        for (int i = 0; i < records.size(); i++) {
            EmployeeUser record = records.get(i);
            if (record.getSearchKey().equals(key)) {
                return record;
            }
        }
        return null;
    }

    public void insertRecord(EmployeeUser record) {
        if (!contains(record.getSearchKey())) {
            records.add(record);
        }
    }

    public void deleteRecord(String key) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getSearchKey().equals(key)) {
                records.remove(i);
                break;
            }
        }
    }

    public void saveToFile() {
        // This method will create the file if it doesn't exist
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(filename));
            
            for (int i = 0; i < records.size(); i++) {
                EmployeeUser record = records.get(i);
                writer.println(record.lineRepresentation());
            }
            writer.close();
        } catch (Exception e) {
            // If saving fails, we can't do much about it
        }
    }
}