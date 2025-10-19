import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustomerProductDatabase {
    private String filename;
    private ArrayList <CustomerProduct> records = new ArrayList<>();

    public CustomerProductDatabase(String filename) {
        this.filename = filename;
    }
    
//---------------------------------------------------    
    public void readFromFile()
    {
        records.clear();//Clears old records to avoid dup.
        try{
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
        
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                CustomerProduct cp = createRecordFrom(line);
                records.add(cp);//---------------insertRecord(cp)
            }
        
            scanner.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File " + filename + "not found." );
        }
    }
//-------------------------------------------------------     
    public CustomerProduct createRecordFrom(String line)
    {

        String[] parts = line.split(",");
        if(parts.length != 4) {
            System.out.println("Invalid line: " + line);
            return null; //Skip this line
        }

        String customerSSN = parts[0];
        String productID = parts[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");//mm for minutes
        
        LocalDate purchaseDate;
        try {
            purchaseDate = LocalDate.parse(parts[2], formatter);
            } catch (DateTimeParseException e) {
            System.out.println("Invalid date: " + parts[2]);
            return null; //skip this record
        }

        boolean paid = Boolean.parseBoolean(parts[3]);//converts string to boolean

        CustomerProduct cp = new CustomerProduct(customerSSN, productID, purchaseDate);
        cp.setPaid(paid);//constructor don't set paid so we called setter
        System.out.println("Successfully created record from file.");
//        if(cp != null)
//            records.add(cp);
        return cp;
    }
//----------------------------------------------
 
    public ArrayList<CustomerProduct> returnAllRecords()
    {
        return new ArrayList<>(records); //returns a copy so caller can't modify on it directly
    }
//---------------------------------------------------
    
    public boolean contains(String key)
    {
    for (CustomerProduct cp : records) {
        if (cp.getSearchKey().equals(key))
        { 
            System.out.println("'Records' contains Key");
            return true;   
        }
    }
    System.out.println("'Records' doesn't contain Key");
    return false;

    }
//-------------------------------------------------------------    

    public CustomerProduct getRecord(String key)
    {
        System.out.println("Searching for record.");
    for (CustomerProduct cp : records) 
    {
        if (cp.getSearchKey().equals(key)) 
        {  
            System.out.println("Found record.");
            return cp;
        }
    }
    System.out.println("Couldn't find record. ");
    return null;

    }
//-----------------------------------------------------------------
    
    public void insertRecord(CustomerProduct record)
    {
        if(record == null) 
            return;
        if(contains(record.getSearchKey()))
        {
            System.out.println("Found duplicated version.");
            deleteRecord(record.getSearchKey());
        }
        records.add(record);
        System.out.println("Successful Insertion");
    }
//-------------------------------------------------------------------
    
    public void deleteRecord(String key)
    {
        CustomerProduct cp = getRecord(key);
        if(cp != null) 
        {
        records.remove(cp);
        System.out.println("Deletion Successful.");
        }else
            System.out.println("Deletion failed.");
        
    }
//---------------------------------------------------------    
    
    public void saveToFile()
    {
        try(FileWriter writer = new FileWriter(filename)) {
            for (CustomerProduct cp : records) {
                writer.write(cp.lineRepresentation() + "\n");
                }
            System.out.println("File overwritten successfully.");
        } 
        catch(IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

    }
    
    
    public static void main(String[] args)
    {
        
    }
    
}

