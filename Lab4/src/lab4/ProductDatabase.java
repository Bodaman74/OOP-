package lab4;

public class ProductDatabase extends Database<Product> {
    public ProductDatabase(String filename) {
        super(filename);
    }

    @Override
    protected Product createRecordFrom(String line) {
        String[] data = line.split(",");
        if (data.length == 6) {
            return new Product(
                data[0], data[1], data[2], data[3],
                Integer.parseInt(data[4]),
                Float.parseFloat(data[5])
            );
        }
        return null;
    }

    @Override
    protected String getSearchKey(Product record) {
        return record.getSearchKey();
    }

    @Override
    protected String lineRepresentation(Product record) {
        return record.lineRepresentation();
    }
}