package lab4;

public class EmployeeUserDatabase extends Database<EmployeeUser> {
    public EmployeeUserDatabase(String filename) {
        super(filename);
    }

    @Override
    protected EmployeeUser createRecordFrom(String line) {
        String[] data = line.split(",");
        if (data.length == 5) {
            return new EmployeeUser(data[0], data[1], data[2], data[3], data[4]);
        }
        return null;
    }

    @Override
    protected String getSearchKey(EmployeeUser record) {
        return record.getSearchKey();
    }

    @Override
    protected String lineRepresentation(EmployeeUser record) {
        return record.lineRepresentation();
    }
}