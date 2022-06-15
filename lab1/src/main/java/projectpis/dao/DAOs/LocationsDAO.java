package projectpis.dao.DAOs;

import projectpis.dao.interfaces.LocationsInterface;
import projectpis.entities.Locations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationsDAO implements LocationsInterface {

    private Connection connection;
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_TITLE = "title";
    private final static String COLUMN_PRICE = "price";
    private Statement statement;

    public LocationsDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    private Locations getLocations(ResultSet resultSet) throws SQLException{
        long id = resultSet.getLong(COLUMN_ID);
        String title = resultSet.getString(COLUMN_TITLE);
        long price = resultSet.getLong(COLUMN_PRICE);

        return new Locations(id, title, price);
    }
    @Override
    public void printLocations(Locations locations){
        System.out.println(locations.getId()+" "+locations.getTitle()+" "+locations.getPrice()+" UAH ");
    }

    @Override
    public Locations findById(long id) {
        String query = "SELECT * FROM locations WHERE locations.id=" + id;

        Locations locations = new Locations();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            locations = getLocations(resultSet);

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return locations;
    }

    @Override
    public List<Locations> findAll() {
        String query = "SELECT * FROM locations";
        List<Locations> locationsList = new ArrayList<Locations>();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Locations locations = getLocations(resultSet);
                locationsList.add(locations);
            }

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return locationsList;
    }

    @Override
    public long createLocations(Locations locations) {
        String query = "INSERT INTO locations (title, price) VALUES ('";
        query += locations.getTitle()+"', '"
                +locations.getPrice()+"') " ;

        long createdId = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                createdId = resultSet.getLong(1);
            }
            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return createdId;
    }

    @Override
    public void updateById(long id, long newPrice) {
        String query = "UPDATE locations SET price = "+newPrice+" WHERE id="+id;

        try {
            Statement statement = connection.createStatement();
            long newId = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            statement.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteById(long id) {
        String query = "DELETE FROM locations WHERE id="+id;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
}
