
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Main {
    public static void main(String[] args) {

        String dateString1 = "12-02-2022";
        String dateString2 = "13-02-2022";
        String dateString3 = "14-02-2022";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse(dateString1, formatter);
        LocalDate date2 = LocalDate.parse(dateString2, formatter);
        LocalDate date3 = LocalDate.parse(dateString3, formatter);
        
        CustomerProductDatabase cpd = new CustomerProductDatabase("CustomersProducts.txt");
        CustomerProduct cp1 = new CustomerProduct("7845345679", "P2568", date1);
        CustomerProduct cp2 = new CustomerProduct("7845345680", "P2569", date2);
        CustomerProduct cp3 = new CustomerProduct("7845345681", "P2570", date3);

        cp1.setPaid(true);
        cp2.setPaid(false);
        cp3.setPaid(true);
        
        cpd.insertRecord(cp1);
        cpd.insertRecord(cp2);
        cpd.insertRecord(cp3);

        cpd.saveToFile();
        
        System.out.println(" CustomerProducts Before Deleteion:");
        for (CustomerProduct cp : cpd.returnAllRecords()) {
            System.out.println(cp.lineRepresentation());
        }
        
        cpd.deleteRecord(cp1.getSearchKey()); 
       
        cpd.saveToFile();
       
        System.out.println("\n CustomerProducts After Deletion:");
        for (CustomerProduct cp : cpd.returnAllRecords()) {
            System.out.println(cp.lineRepresentation());
        }
    }


}
