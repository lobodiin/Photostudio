package projectpis.dao;



import projectpis.dao.DAOs.CustomersDAO;
import projectpis.dao.DAOs.LocationsDAO;
import projectpis.dao.DAOs.SessionsDAO;
import projectpis.dao.interfaces.CustomersInterface;
import projectpis.dao.interfaces.LocationsInterface;
import projectpis.dao.interfaces.SessionsInterface;


import java.sql.Connection;
import java.sql.SQLException;

public class DAOFactory {

    public static CustomersInterface createCustomersDao (Connection connection) throws SQLException {
        return new CustomersDAO(connection);
    }
    public static LocationsInterface createLocationsDao (Connection connection) throws SQLException {
        return new LocationsDAO(connection);
    }
    public static SessionsInterface createSessionsDao (Connection connection) throws SQLException {
        return new SessionsDAO(connection) {
        };
    }

}
