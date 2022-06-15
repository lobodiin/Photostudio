package projectpis.dao.DAOs;


        import projectpis.dao.interfaces.CustomersInterface;
        import projectpis.entities.Customers;

        import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;


public class CustomersDAO implements CustomersInterface {
    private Connection connection;
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_NAME = "name";
    private final static String COLUMN_PHONE = "phone";
    private Statement statement;

    public CustomersDAO(final Connection connection) throws SQLException{
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    public Customers getCustomers(ResultSet resultSet) throws SQLException{
        long id = resultSet.getLong(COLUMN_ID);
        String name = resultSet.getString(COLUMN_NAME);
        String phone = resultSet.getString(COLUMN_PHONE);
        return new Customers(id, name, phone);
    }

    @Override
    public Customers findById(long id) {
        String query = "SELECT * FROM customers WHERE customers.id=" + id;

        Customers customers = new Customers();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            customers = getCustomers(resultSet);

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public Customers findByName(String name) {
        String query = "SELECT * FROM customers WHERE customers.name=" + name;

        Customers customers = new Customers();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            customers = getCustomers(resultSet);

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public List<Customers> findAll() {
        String query = "SELECT * FROM customers";

        List<Customers> customersList = new ArrayList();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Customers customers = getCustomers(resultSet);
                customersList.add(customers);
            }
            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return customersList;
    }

    @Override
    public void create(Customers customers) {
        String query = "INSERT INTO customers (name, phone) VALUES ('";
        query += customers.getName()+"', '"
                +customers.getPhone()+"') " ;

        long id = 0;

        try {
            statement.executeUpdate(query);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteById(long id) {
        String query = "DELETE FROM customers WHERE id="+id;

        try {
            statement.executeUpdate(query);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void print(Customers customers) {
        System.out.println(customers.getId()+" "+customers.getName()+" "+customers.getPhone());

    }

    @Override
    public void printAll(List<Customers> customers) {
        customers.forEach((customer) -> {
            System.out.println(customer.getId()+" "+customer.getName()+" "+customer.getPhone());
        });
    }
}
