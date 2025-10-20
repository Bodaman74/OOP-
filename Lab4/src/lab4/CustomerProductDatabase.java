package lab4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProductDatabase extends Database<CustomerProduct> {
    public CustomerProductDatabase(String filename) {
        super(filename);
    }

    @Override
    protected CustomerProduct createRecordFrom(String line) {
        String[] data = line.split(",");
        if (data.length == 4) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate purchaseDate = LocalDate.parse(data[2], formatter);
            
            CustomerProduct record = new CustomerProduct(data[0], data[1], purchaseDate);
            record.setPaid(Boolean.parseBoolean(data[3]));
            
            return record;
        }
        return null;
    }

    @Override
    protected String getSearchKey(CustomerProduct record) {
        return record.getSearchKey();
    }

    @Override
    protected String lineRepresentation(CustomerProduct record) {
        return record.lineRepresentation();
    }
}