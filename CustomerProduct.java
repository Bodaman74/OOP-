import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProduct 
{
    private String customerSSN, productID;
    private LocalDate purchaseDate;
    private boolean paid;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");//for refsctoring
    
    
    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate)
    {
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
    }
    
//-------------------------Setters----------------------------------
    public void setPaid(boolean paid)
    {
       this.paid = paid; 
    }
//--------------------------Getters-------------------------------
    public String getCustomerSSN() {
        return customerSSN;
    }

    public String getProductID() {
        return productID;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public boolean isPaid() {
        return paid;
    }
//--------------------------------------------------------  
    
    public String lineRepresentation() // returns the data of the object comma separated.
    {
        return getCustomerSSN() + "," + getProductID() + "," + purchaseDate.format(formatter) + "," + paid;
    }
    
    public String getSearchKey()
    {
        return customerSSN + "," + productID + "," + purchaseDate.format(formatter);
    }
    
    
}
